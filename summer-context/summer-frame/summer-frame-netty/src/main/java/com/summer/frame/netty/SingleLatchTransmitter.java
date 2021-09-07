package com.summer.frame.netty;


import com.summer.frame.commons.exception.CustomizeException;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Netty 交互
 * 1. 同步
 * 2. 单线程
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-09-07
 */
@Slf4j
public class SingleLatchTransmitter {
    /**
     * 并发线程数
     */
    private final static int COUNT_DOWN_LATCH_SIZE = 1;
    /**
     * 连接超时时长, 5秒
     */
    private final static int CONNECT_TIMEOUT_MILLIS = 5000;
    /**
     * 等待响应时长, 1秒
     */
    private final static int AWAIT_TIME_MILLIS = 1000;
    /**
     * 目标IP
     */
    private final String ip;
    /**
     * 目标端口
     */
    private final int port;
    /**
     * 交互内容
     */
    private final Message[] messages;

    /**
     * 报文交互执行入口
     *
     * @return 执行结果
     * @author Tiny Chiang
     * @since 1.0.0
     */
    public Message[] execute() {
        return Stream.of(this.messages).peek(message -> {
            byte[] content = message.getContent();
            byte[] response = this.communicate(content);
            message.setContent(response);
        }).toArray(Message[]::new);
    }

    /**
     * 执行报文通讯
     *
     * @param content 报文内容
     * @return 响应结果
     * @author Tiny Chiang
     * @since 1.0.0
     */
    private byte[] communicate(byte[] content) {
        // 限定并发为1
        CountDownLatch countDownLatch = new CountDownLatch(COUNT_DOWN_LATCH_SIZE);
        ChannelInboundHandler channelInboundHandler = new ChannelInboundHandler(countDownLatch);
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = this.bootstrapBuilder(eventLoopGroup, channelInboundHandler);
        // 建立通道
        ChannelFuture channelFuture = bootstrap.connect(ip, port);
        try {
            // 异步请求通道写入并刷新报文
            channelFuture.sync().channel().writeAndFlush(content);
            byte[] response = null;
            int waitTimes = 0;
            do {
                if (countDownLatch.await(AWAIT_TIME_MILLIS, TimeUnit.MILLISECONDS)) {
                    response = channelInboundHandler.getResponse();
                }
            } while (response == null && ++waitTimes < 3);
            return response;
        } catch (InterruptedException interruptedException) {
            log.error("Fail to communicate.", interruptedException);
            throw new CustomizeException("Netty communicate wrong.");
        } finally {
            // 关闭连接
            eventLoopGroup.shutdownGracefully();
        }
    }

    /**
     * todo
     *
     * @param eventLoopGroup
     * @param channelInboundHandler
     * @return 构建结果
     * @author Tiny Chiang
     * @since 1.0.0
     */
    private Bootstrap bootstrapBuilder(EventLoopGroup eventLoopGroup, ChannelInboundHandler channelInboundHandler) {
        return new Bootstrap().group(eventLoopGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ByteArrayDecoder());
                        pipeline.addLast(new ByteArrayEncoder());
                        pipeline.addLast(channelInboundHandler);
                    }
                });
    }

    public SingleLatchTransmitter(Builder builder) {
        this.ip = builder.ip;
        this.port = builder.port;
        this.messages = builder.messages;
    }

    @SuppressWarnings("unused")
    public static class Builder {

        private String ip;

        private int port;

        private Message[] messages;

        public Builder ip(String ip) {
            Assert.notNull(ip, "Ip is null");
            this.ip = ip;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder content(Message... messages) {
            Assert.notNull(messages, "Messages is null");
            this.messages = messages;
            return this;
        }

        public SingleLatchTransmitter build() {
            return new SingleLatchTransmitter(this);
        }
    }

    @Data
    public static class Message {
        /**
         * 标记
         */
        private String mark;
        /**
         * 主体
         */
        private byte[] content;
    }
}
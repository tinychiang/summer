package com.summer.frame.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 信道监听
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-09-07
 */
@Slf4j
public class ChannelInboundHandler extends SimpleChannelInboundHandler<byte[]> {

    /**
     * 线程计数器
     */
    private final CountDownLatch countDownLatch;
    /**
     * 返回结果
     */
    @Getter
    private byte[] response;

    /**
     * 读信道
     *
     * @param channelHandlerContext 信道处理器上下文
     * @param bytes                 执行结果
     * @author Tiny Chiang
     * @since 1.0.0
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] bytes) {
        this.response = bytes;
        // 线程计数器-1, 表示线程执行完成, 调用await()主线程被唤醒
        countDownLatch.countDown();
        // 信道关闭
        channelHandlerContext.close();
    }

    /**
     * 数据交互异常处理
     *
     * @param channelHandlerContext 信道处理器上下文
     * @param throwable             异常信息
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) {
        log.error("Socket break.", throwable);
        // 信道关闭
        channelHandlerContext.close();
    }

    public ChannelInboundHandler(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
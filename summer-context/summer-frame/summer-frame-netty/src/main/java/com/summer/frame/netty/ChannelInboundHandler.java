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

    private final CountDownLatch countDownLatch;

    /**
     * 返回结果
     */
    @Getter
    private byte[] response;

    public ChannelInboundHandler(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] bytes) {
        this.response = bytes;
        countDownLatch.countDown();
        channelHandlerContext.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) {
        log.error("Socket break.", throwable);
        channelHandlerContext.close();
    }

}
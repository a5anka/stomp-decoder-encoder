package io.github.asanka.stomp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;

import java.util.logging.Logger;

public class STOMPServerHandler extends SimpleChannelInboundHandler<STOMPFrame> {
    private final static Logger LOGGER = Logger.getLogger(STOMPServerHandler.class.getName());

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, STOMPFrame stompFrame) throws Exception {

    }
}

package io.github.asanka.stomp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;

import java.util.logging.Logger;

public class STOMPServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final static Logger LOGGER = Logger.getLogger(STOMPServerHandler.class.getName());

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        LOGGER.info(textWebSocketFrame.text());
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("CONNECTED\nversion:1.1\nheart-beat:0,0\n\n\0"));
    }
}

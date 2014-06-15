package io.github.asanka.stomp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class STOMPServerHandler extends SimpleChannelInboundHandler<STOMPFrame> {
    private final static Logger LOGGER = Logger.getLogger(STOMPServerHandler.class.getName());

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, STOMPFrame stompFrame) throws Exception {
        STOMPCommandType type = stompFrame.getType();

        switch (type) {
            case CONNECT:
                handleConnect(channelHandlerContext);
                break;
            case SEND:
                handleSend(channelHandlerContext);
                break;
            case SUBSCRIBE:
                handleSubscribe(channelHandlerContext);
                break;
            case UNSUBSCRIBE:
                handleUnsubscribe(channelHandlerContext);
                break;
        }
    }

    private void handleUnsubscribe(ChannelHandlerContext channelHandlerContext) {
        // Add logic
    }

    private void handleSubscribe(ChannelHandlerContext channelHandlerContext) {
        // Add logic
    }

    private void handleSend(ChannelHandlerContext channelHandlerContext) {
        // Add logic
    }

    private void handleConnect(ChannelHandlerContext channelHandlerContext) {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("version","1.1");
        headers.put("heart-beat","0,0");

        STOMPFrame connected = new DefaultSTOMPFrame(STOMPCommandType.CONNECTED,
                headers);
        channelHandlerContext.channel().writeAndFlush(connected);
    }
}

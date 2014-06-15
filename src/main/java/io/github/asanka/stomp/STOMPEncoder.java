package io.github.asanka.stomp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;
import java.util.Map;

public class STOMPEncoder extends MessageToMessageEncoder<STOMPFrame> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, STOMPFrame stompFrame, List<Object> objects) throws Exception {
        StringBuilder builder = new StringBuilder();

        builder.append(getStringFor(stompFrame.getType()));
        builder.append("\n");

        Map<String, String> headers = stompFrame.getHeaders();

        for (Map.Entry<String,String> header: headers.entrySet()) {
            builder.append(header.getKey());
            builder.append(":");
            builder.append(header.getValue());
            builder.append("\n");
        }

        builder.append("\n");

        String stompBody = stompFrame.getContent();

        if (stompBody != null) {
            builder.append(stompBody);
        }

        builder.append("\0");

        String stompFrameString = builder.toString();

        WebSocketFrame result = new TextWebSocketFrame(stompFrameString);

        objects.add(result);
    }

    private String escaapeFrameString(String stompFrameString) {
        return stompFrameString.replace("\n", "\\n")
                .replace(":", "\\c")
                .replace("\\","\\\\");

    }

    private String getStringFor(STOMPCommandType type) {
        switch (type) {
            case MESSAGE:
                return "MESSAGE";
            case SUBSCRIBE:
                return "SUBSCRIBE";
            case ACK:
                return "ACK";
            case CONNECT:
                return "CONNECTED";
            case CONNECTED:
                return "CONNECTED";
            case SEND:
                return "SEND";
            case UNSUBSCRIBE:
                return "UNSUBSCRIBE";
        }

        return "";
    }
}

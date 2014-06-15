package io.github.asanka.stomp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class STOMPDecoder extends MessageToMessageDecoder<TextWebSocketFrame>{

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame frame, List<Object> out) throws Exception {
        String rawContent = frame.text();

        String escapedContent = escapeContent(rawContent);
        String lines[] = escapedContent.split("\\n");

        String commandStr = lines[0];
        STOMPCommandType type = getTypeForString(commandStr);

        int index = 1;
        Map<String, String> headers = new HashMap<String, String>();

        for (; index < lines.length; index++) {
            if (lines[index].isEmpty()) {
                break;
            } else {
                String header[] = lines[index].split(":");
                headers.put(header[0], header[1]);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (; index < lines.length; index++) {
            builder.append(lines[index]);
        }

        String stompBody = builder.toString();

        STOMPFrame result = new DefaultSTOMPFrame(type, headers,stompBody);

        out.add(result);
    }

    private STOMPCommandType getTypeForString(String commandStr) {
        if ("CONNECT".equals(commandStr)) {
            return STOMPCommandType.CONNECT;
        } else if ("CONNECTED".equals(commandStr)) {
            return STOMPCommandType.CONNECTED;
        } else if ("SEND".equals(commandStr)) {
            return STOMPCommandType.SEND;
        } else if ("SUBSCRIBE".equals(commandStr)) {
            return STOMPCommandType.SUBSCRIBE;
        } else if ("ACK".equals(commandStr)) {
            return STOMPCommandType.ACK;
        } else if ("UNSUBSCRIBE".equals(commandStr)) {
            return STOMPCommandType.SUBSCRIBE;
        } else if ("MESSAGE".equals(commandStr)) {
            return STOMPCommandType.MESSAGE;
        } else {
            return null;
        }
    }

    private String escapeContent(String rawContent) {
        String escaped = rawContent.replace("\\n", "\n")
                .replace("\\c", ":")
                .replace("\\\\","\\");

        int frameEnd = escaped.indexOf("\0");

        return escaped.substring(0, frameEnd);
    }
}

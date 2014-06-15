package io.github.asanka.stomp;

import java.util.Map;

/**
 * Created by asanka on 6/14/14.
 */
public class DefaultSTOMPFrame implements STOMPFrame {

    STOMPCommandType type;
    Map<String, String> headers;
    String stomBody;

    public DefaultSTOMPFrame(STOMPCommandType type, Map<String, String> headers, String stompBody) {
        this(type, headers);
        this.stomBody = stompBody;
    }

    public DefaultSTOMPFrame(STOMPCommandType type, Map<String, String> headers) {
        this.type = type;
        this.headers = headers;
    }

    @Override
    public STOMPCommandType getType() {
        return type;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String getContent() {
        return stomBody;
    }
}

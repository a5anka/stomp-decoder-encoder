package io.github.asanka.stomp;

import java.util.HashMap;
import java.util.Map;

public class STOMPConnectFrame implements STOMPFrame{

    String versions;
    String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public STOMPConnectFrame(String versions) {
        this.versions = versions;
        this.host = null;
    }

    @Override
    public STOMPCommandType getType() {
        return STOMPCommandType.CONNECT;
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("accept-version", this.versions);
        if (this.host != null) {
            headers.put("host", this.host);
        }

        return headers;
    }

    @Override
    public String getContent() {
        return null;
    }
}

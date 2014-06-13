package io.github.asanka.stomp;

public class STOMPConnectFrame implements STOMPFrame {

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
    public String toProtocolString() {
        return "CONNECT\n"
                + "version:" + this.versions
                + (this.host == null ? "" : "\nhost:" + this.host)
                + "\n\n\0";
    }
}

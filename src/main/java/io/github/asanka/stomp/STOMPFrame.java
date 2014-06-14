package io.github.asanka.stomp;

import java.util.Map;

public interface STOMPFrame {

    public STOMPCommandType getType();
    public Map<String,String> getHeaders();
    public String getContent();
}

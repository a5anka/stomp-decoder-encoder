package io.github.asanka.stomp;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class STOMPServerInitializer extends ChannelInitializer<SocketChannel> {

    private static final int MAX_HTTP_CONTENT_LENGTH = 65536;

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(MAX_HTTP_CONTENT_LENGTH));
        pipeline.addLast(new WebSocketServerProtocolHandler("/stomp", "v11.stomp, v10.stomp"));
        pipeline.addLast(new STOMPServerHandler());
    }
}
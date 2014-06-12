package io.github.asanka.stomp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class STOMPServer {
    static final int PORT = 5775;

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new STOMPServerInitializer());

        try {
            Channel ch = b.bind(PORT).sync().channel();
            System.out.println("Open your web browser and navigate to " +
                    "http://127.0.0.1:" + PORT + '/');

            ch.closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

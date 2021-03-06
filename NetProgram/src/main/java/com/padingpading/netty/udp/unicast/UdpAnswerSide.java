package com.padingpading.netty.udp.unicast;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程和VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：应答端
 */
public class UdpAnswerSide {

    public static final int ANSWER_PORT = 8080;
    public final static String ANSWER = "古诗来了：";

    public void run(int port) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            /*udp无连接，没有接受连接说法,即使是接受端，也应该用Bootstrap*/
            Bootstrap b = new Bootstrap();
            /*由于我们用的是UDP协议，所以要用NioDatagramChannel来创建*/
            b.group(group)
                .channel(NioDatagramChannel.class)
                .handler(new AnswerHandler());
            //没有接受客户端连接的过程，监听本地端口即可
            ChannelFuture f = b.bind(port).sync();
            System.out.println("应答服务已启动.....");
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    public static void main(String [] args) throws Exception{
        new UdpAnswerSide().run(ANSWER_PORT);
    }
}

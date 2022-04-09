package com.padingpading.netty.udp.broadcast.bcside;

import com.padingpading.netty.udp.broadcast.LogConst;
import com.padingpading.netty.udp.broadcast.LogMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程和VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：日志的广播端
 */
public class LogEventBroadcaster {
    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public LogEventBroadcaster(InetSocketAddress remoteAddress) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        //引导该 NioDatagramChannel（无连接的）
        bootstrap.group(group).channel(NioDatagramChannel.class)
                /*设置通信选型为广播模式*/
             .option(ChannelOption.SO_BROADCAST,true)
             .handler(new LogEventEncoder(remoteAddress));
    }

    public void run() throws Exception {
        //绑定 Channel
        Channel ch = bootstrap.bind(0).sync().channel();
        long count = 0;
        //启动主处理循环，模拟日志发送
        for (;;) {
            ch.writeAndFlush(new LogMsg(null, ++count,
                    LogConst.getLogInfo()));
            try {
                //休眠 2 秒，如果被中断，则退出循环；
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
        }
    }

    public void stop() {
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        //创建并启动一个新的 UdpQuestionSide 的实例
        LogEventBroadcaster broadcaster = new LogEventBroadcaster(
                //广播地址,加端口。
                new InetSocketAddress("255.255.255.255",
                        LogConst.MONITOR_SIDE_PORT)
        );
        try {
            broadcaster.run();
        }
        finally {
            broadcaster.stop();
        }
    }
}
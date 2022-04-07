package com.padingpading.netprograma.net.nio.client;


/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 类说明：nio通信服务端
 */
public class NioServer {
    private static NioServerHandle nioServerHandle;

    public static void main(String[] args){
        nioServerHandle = new NioServerHandle(Const.DEFAULT_PORT);
        new Thread(nioServerHandle,"Server").start();
    }

}

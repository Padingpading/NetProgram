package com.padingpading.netprograma.net.nio.client;


/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 类说明：nio通信服务端
 */
public class NioServerWritable {
    private static NioServerHandleWriteable nioServerHandle;

    public static void start(){

    }
    public static void main(String[] args){
        nioServerHandle = new NioServerHandleWriteable(Const.DEFAULT_PORT);
        new Thread(nioServerHandle,"Server").start();
    }

}

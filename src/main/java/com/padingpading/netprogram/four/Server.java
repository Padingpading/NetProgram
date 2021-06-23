package com.padingpading.netprogram.four;

import com.padingpading.netprogram.three.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author libin
 * @description 实现目标服务端可以同时接受多个客户端的socket通信请求。
 *              服务端每就收一个客户端socket请求对象之后都交给一个独立的线程来处理客户端的数据交互请求
 * @date 2021/6/23
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1、创建socket对象请求服务端的链接
            ServerSocket ss =new ServerSocket(9999);
            //2、定义一个死循环，负责不断的接受客户端的socket链接请求
            HandlerSocketServerPool pool = new HandlerSocketServerPool(3,10);
            while (true){
                Socket socket = ss.accept();
                //3、把socket对象交给一个线程池
                ServerRunnableTarget task = new ServerRunnableTarget(socket);
                pool.submit(task);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.padingpading.netprogram.chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author libin
 * @description BIO模式下的端口转发思想。
 * 1、注册端口
 * 2、接收客户端的socket链接，交给一个独立的线程处理
 * 3、把当前链接的客户端socket存到一个socket 集合中
 * 4、几首客户端的消息，然后推送给当前所有在线的socket接收。
 * @date 2021/6/23
 */
public class Server {

    public  static List<Socket> allSocket = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true){
                Socket socket = serverSocket.accept();
                allSocket.add(socket);
                //为当前登录成功的socket分配一个独立的线程处理
                new  ServerReaderThread(socket).start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

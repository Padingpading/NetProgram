package com.padingpading.netprograma.net.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author libin
 * @description
 * @date 2022-04-06
 */
public class Client {
    public static void main(String[] args) {
        //访问地址
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1",10002);
        Socket socket = new Socket();
        try {
            //绑定地址,简历链接。
            socket.connect(addr);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //发送消息
            oos.writeUTF("james");
            oos.flush();
            //接受服务器返回的消息
            System.out.println(ois.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

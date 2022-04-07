package com.padingpading.netprogram.file;


import java.io.*;
import java.net.Socket;

/**
 * @author libin
 * @description
 * @date 2021/6/23
 */
public class Client {
    public static void main(String[] args)  {
        //1、创建socket对象请求服务端的链接
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",9999);
            //2、把字节输出流包装成一个数据输入流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(".PNG");
            InputStream inputStream = new FileInputStream("I:\\学习\\网络编程\\NetProgram\\src\\main\\resources\\static\\java.png");
            byte[] buffter = new byte[1024];
            int len;
            while ((len=inputStream.read(buffter))>0){
                dos.write(buffter,0,len);
            }
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

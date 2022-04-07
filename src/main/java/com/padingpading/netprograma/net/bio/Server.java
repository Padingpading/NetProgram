package com.padingpading.netprograma.net.bio;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author libin
 * @description
 * @date 2022-04-06
 */
public class Server {
    
    public static void main(String[] args) throws IOException {
        //server服务端。
        ServerSocket  serverSocket = new ServerSocket();
        //绑定id,InetSocketAddress为ip封装对象
        serverSocket.bind(new InetSocketAddress(10002));
        while(true){
            new Thread(new ServerTask(serverSocket.accept())).start();
        }
    }
    
    public static class ServerTask implements Runnable {
        
        private Socket socket = null;
        
        
        public ServerTask(Socket accept) {
            this.socket = accept;
        }
    
        @Override
        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
                //收到消息
                 String s = ois.readUTF();
                System.out.println("accept message:"+s);
                //会写消息
                oos.writeUTF("hello"+s);
                oos.flush();
                socket.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
}

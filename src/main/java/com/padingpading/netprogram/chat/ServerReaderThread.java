package com.padingpading.netprogram.chat;


import java.io.*;
import java.net.Socket;

/**
 * @author libin
 * @description
 * @date 2021/6/23
 */
public class ServerReaderThread extends Thread {

    private Socket socket;

    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            int port = socket.getPort();
            //从socket对象中得到一个字节输入流
            InputStream is = socket.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg=bf.readLine())!=null){
                //2、服务端收到了客户端的消息推送给所有socket
                sendMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Server.allSocket.remove(socket);
        }
    }

    private void sendMessage(String msg) throws  IOException {
        for (Socket s : Server.allSocket) {
            PrintStream printStream = new PrintStream(s.getOutputStream());
            printStream.println(msg);
            printStream.flush();
        }
    }
}

package com.yy.design.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yy
 * @date 2020/7/11 10:22
 */
public class BIOSocketServerThread {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9999));
        while (true) {
            // 等待链接
            System.out.println("服务端等待链接");
            // 建立链接 这里会阻塞
            Socket socket = serverSocket.accept();
            System.out.println("服务端链接成功");
            new Thread(new SocketThread(socket)).start();
        }
    }
    static class SocketThread implements Runnable{
        byte[] bytes = new byte[1024];
        Socket socket;

        public SocketThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                socket.getInputStream().read(bytes);
                String data = new String(bytes);
                System.out.println("数据为:"+data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
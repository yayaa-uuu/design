package com.yy.design.socket.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author yy
 * @date 2020/7/11 10:14
 */
public class BIOSocketClient {
    public static void main(String[] args) throws IOException, IOException {
        Socket socket = new Socket();
        Scanner sc = new Scanner(System.in);
        // 建立连接
        socket.connect(new InetSocketAddress(9999));
        System.out.println("已建立连接，请输入数据");
        while (true){
            String data = sc.next();
            // 写数据
            socket.getOutputStream().write(data.getBytes());
        }
    }
}

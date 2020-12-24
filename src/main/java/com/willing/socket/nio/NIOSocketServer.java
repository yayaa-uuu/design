package com.willing.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yy
 * @date 2020/7/21 17:01
 */
public class NIOSocketServer {
    public static void main(String[] args) throws IOException {
        // 打开服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 检索与此通道关联的服务器套接字
        ServerSocket serverSocket = serverSocketChannel.socket();
        // 进行服务的绑定
        serverSocket.bind(new InetSocketAddress(9999));
        // 通过open()方法找到Selector
        Selector selector = Selector.open();
        // 注册到selector，等待连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("等待客户端连接----8888");
        // 设置缓冲区大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 监听
        while (true) {
            // 选择一组键，并且相应的通道已经打开
            selector.select();
            // 返回此选择器的已选择键集。
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 删除当前事件
                iterator.remove();
                // 处理数据
                ServerSocketChannel server;
                SocketChannel client;
                String text;
                int count = 0;
                // 测试此键的通道是否已准备好接受新的套接字连接。
                if (selectionKey.isAcceptable()) {
                    // 返回为之创建此键的通道。
                    server = (ServerSocketChannel) selectionKey.channel();
                    // 接受到此通道套接字的连接。
                    // 此方法返回的套接字通道（如果有）将处于阻塞模式。
                    client = server.accept();
                    // 配置为非阻塞
                    client.configureBlocking(false);
                    // 注册到selector，等待连接
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端已连接，继续下一个连接");
                } else if (selectionKey.isReadable()) {
                    // 返回为之创建此键的通道。
                    client = (SocketChannel) selectionKey.channel();
                    // 将缓冲区清空以备下次读取
                    buffer.clear();
                    // 读取服务器发送来的数据到缓冲区中
                    count = client.read(buffer);
                    if (count > 0) {
                        text = new String(buffer.array(), 0, count);
                        System.out.println("服务器端接受客户端数据:" + text);
                        client.register(selector, SelectionKey.OP_WRITE);
                    }
                }
            }
        }
    }
}

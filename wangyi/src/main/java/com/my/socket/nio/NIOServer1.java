package com.my.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Author：cc
 * Date：2020-10-15 7:27
 * Description：<描述>
 *
 * @author dell
 */
public class NIOServer1 {

    public static void main(String[] args) throws Exception {
        //创建服务端
        ServerSocketChannel channel = ServerSocketChannel.open();
        //设为非阻塞模式
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功");
        while (true) {
            //获取新的tcp连接通道,一切数据都在这个通道上进行
            SocketChannel socketChannel = channel.accept();
            //在这里进行判断则将非阻塞非阻塞模式浪费
            if (socketChannel != null) {
                System.out.println("LocalAddress:" + socketChannel.getLocalAddress());
                System.out.println("收到新的连接:" + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                //将通道的数据读到缓冲区
                //socketChanner 的read方法:是将请求数据读到缓冲区，返回值为0是:没有请求数据，返回值为-1时：客户端关闭数据完毕，并主动的close Socket
                while (socketChannel.isOpen() && socketChannel.read(byteBuffer) != -1) {
                    //只要有读到数据就认为请求结束了
                    if (byteBuffer.position() > 0) {
                        break;
                    }
                }
                System.out.println(byteBuffer.position());
                //若没有数据,则不继续执行
                if (byteBuffer.position() == 0) {
                    continue;
                }
                //开始读取请求数据
                byteBuffer.flip();
                byte[] request = new byte[byteBuffer.limit()];
                byteBuffer.get(request);
                System.out.println("收到数据来自" + socketChannel.getRemoteAddress());
                System.out.println(new String(request));
                System.out.println("-----------------------------------------------");

                //给与响应
                String response = "HTTP/1.1 200 OK\nContent-Length: 11\n" +
                        "\nHello World";
                ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                while (buffer.hasRemaining()) {
                    //写入通道
                    socketChannel.write(buffer);
                }
            }
        }
    }

}

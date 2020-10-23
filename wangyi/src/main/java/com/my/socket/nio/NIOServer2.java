package com.my.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Author：cc
 *
 * @author cc
 * Date：2020-10-19 12:53
 * Description：<描述>
 */
public class NIOServer2 {

    private static CopyOnWriteArrayList<SocketChannel> socketChannels = new CopyOnWriteArrayList<>();

    /**
     * 一个线程接收多个请求
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress(8080));
            System.out.println("启动成功");
            while (true) {
                SocketChannel socketChannel = channel.accept();
                if (socketChannel != null) {
                    socketChannel.configureBlocking(false);
                    socketChannels.add(socketChannel);
                    System.out.println("接到新的连接:" + socketChannel.getRemoteAddress());
                } else {
                    //没有连接的情况下，去处理连接数据，处理完之后删除该连接
                    Iterator<SocketChannel> iterator = socketChannels.iterator();
                    while (iterator.hasNext()) {
                        try {
                            SocketChannel chan = iterator.next();
                            //读取请求数据并响应
                            ByteBuffer requestByteBuffer = ByteBuffer.allocate(1024);
                            if (chan.read(requestByteBuffer) == 0) {
                                //通道内没有数据
                                continue;
                            }
                            while (chan.isOpen() && chan.read(requestByteBuffer) != -1) {
                                if (requestByteBuffer.position() > 0) {
                                    break;
                                }
                            }
                            if (requestByteBuffer.position() == 0) {
                                continue;
                            }
                            requestByteBuffer.flip();
                            byte[] bytes = new byte[requestByteBuffer.limit()];
                            requestByteBuffer.get(bytes);
                            System.out.println("接受到数据来自:" + chan.getRemoteAddress());
                            System.out.println(new String(bytes));
                            System.out.println("================================");

                            //给予响应
                            String response = "HTTP/1.1 200 OK\nContent-Length: 11\n" +
                                    "\nHello World";
                            ByteBuffer byteBuffer = ByteBuffer.wrap(response.getBytes());
                            while (byteBuffer.hasRemaining()) {
                                chan.write(byteBuffer);
                            }
                            socketChannels.remove(chan);
                        } catch (IOException e) {
                            e.printStackTrace();
                            iterator.remove();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

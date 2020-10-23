package com.my.socket.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Author：cc
 *
 * @author cc
 * Date：2020-10-17 17:24
 * Description：<描述>
 */
public class NIOClient {

    public static void main(String[] args) {
        try {
            SocketChannel channel = SocketChannel.open();
            //设置为非阻塞模式
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress("10.9.9.20", 8080));
            //等待连接
            while (!channel.finishConnect()) {
                Thread.yield();
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入:");
            String s = scanner.nextLine();
            //wrap静态方法：1、创建一块缓冲区;2、mark标记为-1;3、position为0;4、limit设为字节数组的长度;5、容量为字节数组的长度;6、写入数据为该字节数组
            ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes());
            //hasRemaining方法，position < limit 即只要有数据都返回true
            while (byteBuffer.hasRemaining()) {
                channel.write(byteBuffer);
            }
            //读取响应
            System.out.println("收到服务端响应:");
            //申请一块缓冲区,存放响应
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (channel.isOpen() && channel.read(buffer) != -1) {
                //只要有数据返回就认为请求结束
                if (buffer.position() > 0) {
                    break;
                }
            }
            //开始读取数据
            buffer.flip();
            byte[] content = new byte[buffer.limit()];
            buffer.get(content);
            System.out.println(new String(content));
            scanner.close();
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

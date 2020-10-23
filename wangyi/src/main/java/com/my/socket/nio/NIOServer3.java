package com.my.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Author：cc
 *
 * @author cc
 * Date：2020-10-22 20:31
 * Description：<描述>
 */
public class NIOServer3 {


    /**
     * 结合selector实现非阻塞服务端（放弃对channel的轮询，借助消息通知机制）
     * @param args
     */
    public static void main(String[] args) {
        try {
            //1、创建服务端
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            //2、构建一个Select选择器,并且将channel注册上去
            Selector selector = Selector.open();
            //将serverSocketChannel注册到selector
            SelectionKey selectionKey = serverSocketChannel.register(selector, 0, serverSocketChannel);
            //对serverSocketChannel上面的事感兴趣，serverSocketChannel只支持accept操作
            selectionKey.interestOps(SelectionKey.OP_ACCEPT);

            //3、绑定端口
            serverSocketChannel.bind(new InetSocketAddress(8080));
            // serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            System.out.println("启动成功");


            while (true){
                //不在轮询通道，使用selector轮询事件（ 有阻塞效果，直至有事件通知才会返回？？ ）
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

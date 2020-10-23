package com.my.socket.nio;

import java.nio.ByteBuffer;

/**
 * Author：cc
 * <p>
 * Date：2020-10-15 7:29
 * Description：<描述>
 */
public class BufferDemo {

    public static void main(String[] args) {
        //构建一个容量为4的buffer缓冲区
        // ByteBuffer buffer = ByteBuffer.allocate(4);
        //构建一个容量为4的buffer缓冲区，直接操作堆外内存
        ByteBuffer buffer = ByteBuffer.allocateDirect(4);
        //初始化时：capacity为申请的最大容量，position位置为0，limit为申请的最大容量值
        String initialize = String.format("初始化：capacity:%s,position:%s,limit:%s", buffer.capacity(), buffer.position(), buffer.limit());
        System.out.println(initialize);
        //写入数据
        //字符串是拆分成字节数组存入其中，暂时不管
        // System.out.println(Arrays.toString("123".getBytes()));
        // buffer.put("123".getBytes());
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        //写入模式：capacity为申请的最大容量，position为写入字节长度的下一个位置，limit为申请的最大容量值
        String afterWrite = String.format("写入三字节后：capacity:%s,position:%s,limit:%s", buffer.capacity(), buffer.position(), buffer.limit());
        System.out.println(afterWrite);
        //转换为读取模式
        System.out.println("开始读取>>>>>>>>>>>>>>>>>>>>");
        //读操作就是读取重position开始到limit的数据
        //该方法是第一：将limit的位置置为position的位置，将position置为0，若读取不调用此方法，那么position将会从上一次的写入的最后一个开始计数
        buffer.flip();
        // while (buffer.hasRemaining()){
        //     System.out.println(buffer.get());
        // }
        byte a = buffer.get();
        System.out.println(a);
        byte b = buffer.get();
        System.out.println(b);
        String afterReadTwoByte = String.format("读取两字节后：capacity:%s,position:%s,limit:%s", buffer.capacity(), buffer.position(), buffer.limit());
        System.out.println(afterReadTwoByte);

        //此时读模式下继续写入,会从Positin位置就写入，即使已有数据也会覆盖
        // buffer.put((byte) 4);
        // System.out.println(String.format("读模式下继续写入：capacity:%s,position:%s,limit:%s", buffer.capacity(), buffer.position(), buffer.limit()));
        //compact的作用:将已读过的数据“删除”，其实就是将后面的维度数据左移第一位，然后将positon 置为 limit - positon
        buffer.compact();
        System.out.println(String.format("compact压缩数据后:capacity:%s,posotion:%s,limit:%s", buffer.capacity(), buffer.position(), buffer.limit()));
        buffer.put((byte) 4);
        System.out.println(String.format("compact压缩数据后写入一字节:capacity:%s,posotion:%s,limit:%s", buffer.capacity(), buffer.position(), buffer.limit()));
        //读取当前缓冲区的数据
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        // clear()，只是把指针移到位置0，并没有真正清空数据。
        buffer.clear();
        System.out.println(String.format("调用clear后:capacity:%s,posotion:%s,limit:%s", buffer.capacity(), buffer.position(), buffer.limit()));
    }
}

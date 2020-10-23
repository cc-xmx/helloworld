package com.my.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Author：cc
 * Date：2020-09-01 8:01
 * Description：<描述>
 */
public class BIOClientServer {
    public static void main(String[] args) {
        Socket request = null;
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("服務器啟動完成");
            while (!serverSocket.isClosed()){
                //阻塞
                 request = serverSocket.accept();
                System.out.println("收到新的连接："+request.toString());
                InputStream inputStream = request.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String msg;
                while ((msg = reader.readLine()) != null){
                    System.out.println(msg);
                }
                System.out.println("收到数据，来自:" + request.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != request){
                try {
                    request.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

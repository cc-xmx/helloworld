package com.my.socket.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author：cc
 * Date：2020-09-01 7:52
 * Description：<描述>
 */
public class BIOClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8080);
            OutputStream outputStream = socket.getOutputStream();
            Scanner s = new Scanner(System.in);
            System.out.println("请输入");
            String s1 = s.nextLine();
            outputStream.write(s1.getBytes());
            s.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

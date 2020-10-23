package com.my.javabasic.enumtest;

import java.util.Scanner;

/**
 * Author：cc
 * Date：2020-10-16 7:36
 * Description：<描述>
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入");
        String choice = scanner.nextLine();
        // Life life = Life.valueOf(choice);
        // switch (life) {
        //     case A:
        //         System.out.println(Life.A.handler(choice));
        //         break;
        //     case B:
        //         System.out.println(Life.B.handler(choice));
        //         break;
        //     default:
        //         System.out.println(Life.A.handler(choice));
        //         break;
        // }
        switch (Life.matchLifeCode(choice)){
            case A:
                System.out.println(Life.A.handler(choice));
                break;
            case B:
                System.out.println(Life.B.handler(choice));
                break;
            default:
                System.out.println(Life.A.handler(choice));
                break;
        }
    }
}

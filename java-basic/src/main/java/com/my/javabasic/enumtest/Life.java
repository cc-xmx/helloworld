package com.my.javabasic.enumtest;

import org.thymeleaf.util.StringUtils;

/**
 * Author：cc
 * Date：2020-10-15 22:11
 * Description：<描述>
 */
public enum Life {

    A(1) {
        @Override
        public String handler(String a) {
            return a;
        }
    },
    B(2) {
        @Override
        public String handler(String a) {
            return a + "hello";
        }
    };

    private int code;

    Life(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    // A{
    //     @Override
    //     public String handler(String a) {
    //         return a;
    //     }
    // },
    // B{
    //     @Override
    //     public String handler(String a) {
    //         return a + "hello";
    //     }
    // }
    // ;

    public abstract String handler(String a);

    public static Life matchLifeCode(String lifeCode) {
        if (StringUtils.isEmpty(lifeCode)) {
            return null;
        }
        for (Life life : Life.values()) {
            try {
                if (life.getCode() == Integer.parseInt(lifeCode)) {
                    return life;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}

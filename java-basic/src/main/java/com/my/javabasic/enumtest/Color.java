package com.my.javabasic.enumtest;

/**
 * Author：cc
 * Date：2020-10-15 22:27
 * Description：<描述>
 */
public enum Color {
    红色(""),绿色("绿"),蓝色("蓝");

    private String title;

    Color(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Color{" +
                "title='" + title + '\'' +
                '}';
    }
}

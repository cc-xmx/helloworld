package com.my.Iterator.demo;

import java.io.Serializable;

/**
 * Author：cc
 *
 * @author cc
 * Date：2020-12-25 23:10
 * Description：<描述>
 */
public class Book implements Serializable {

    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

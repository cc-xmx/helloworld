package com.my.Iterator.demo;

/**
 * Author：cc
 *
 * @author cc
 * Date：2020-12-25 23:22
 * Description：<描述>
 */
public class test {

    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(5);
        bookShelf.appendBook(new Book("A"));
        bookShelf.appendBook(new Book("B"));
        bookShelf.appendBook(new Book("C"));
        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            System.out.println(((Book) iterator.next()).getName());
        }
    }
}

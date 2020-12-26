package com.my.Iterator.demo;

import java.io.Serializable;

/**
 * Author：cc
 *
 * @author cc
 * Date：2020-12-25 23:10
 * Description：<描述>
 */
public class BookShelf implements Serializable, Aggregate {

    private Book[] books;

    private int last = 0;

    public BookShelf(int maxSize) {
        this.books = new Book[maxSize];
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }

    public int getLength() {
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}

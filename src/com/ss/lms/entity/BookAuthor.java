package com.ss.lms.entity;

import java.util.Objects;

public class BookAuthor {
    private Book book;
    private Author author;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthor that = (BookAuthor) o;
        return book.equals(that.book) && author.equals(that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, author);
    }
}

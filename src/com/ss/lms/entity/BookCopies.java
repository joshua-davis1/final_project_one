package com.ss.lms.entity;

import java.util.Objects;

public class BookCopies {
    private Book book;
    private LibraryBranch libraryBranch;
    private int copies;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LibraryBranch getLibraryBranch() {
        return libraryBranch;
    }

    public void setLibraryBranch(LibraryBranch libraryBranch) {
        this.libraryBranch = libraryBranch;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopies that = (BookCopies) o;
        return book.equals(that.book) && libraryBranch.equals(that.libraryBranch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, libraryBranch);
    }
}

package com.ss.lms.entity;

import java.io.Serializable;
import java.util.Objects;

public class BookCopies implements Serializable {

    private Book book;
    private Branch branch;
    private int noOfCopies;

    public BookCopies(Book book, Branch branch, int noOfCopies) {
        this.book = book;
        this.branch = branch;
        this.noOfCopies = noOfCopies;
    }

    public BookCopies() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopies that = (BookCopies) o;
        return book.equals(that.book) && branch.equals(that.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, branch);
    }
}

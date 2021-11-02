package com.ss.lms.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public class BookLoans {

    private Book book;
    private LibraryBranch libraryBranch;
    private Borrower borrower;
    private Date dateOut;
    private Date dateIn;
    private Date dueDate;

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

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoans bookLoans = (BookLoans) o;
        return book.equals(bookLoans.book) && libraryBranch.equals(bookLoans.libraryBranch) && borrower.equals(bookLoans.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, libraryBranch, borrower);
    }
}

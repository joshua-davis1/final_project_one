package com.ss.lms.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BookLoans implements Serializable {

    @Serial
    private static final long serialVersionUID = -7862020504591759523L;
    private Book book;
    private Branch branch;
    private Borrower borrower;
    private LocalDate dateOut;
    private LocalDate dateIn = null;
    private LocalDate dueDate;

    public BookLoans(Book book, Branch branch, Borrower borrower, LocalDate dateOut, LocalDate dateIn, LocalDate dueDate) {
        this.book = book;
        this.branch = branch;
        this.borrower = borrower;
        this.dateOut = dateOut;
        this.dateIn = dateIn;
        this.dueDate = dueDate;
    }

    public BookLoans(Book book, Branch branch, Borrower borrower, LocalDate dateOut, LocalDate dueDate) {
        this.book = book;
        this.branch = branch;
        this.borrower = borrower;
        this.dateOut = dateOut;
        this.dueDate = dueDate;
    }

    public BookLoans() {
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

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoans bookLoans = (BookLoans) o;
        return book.equals(bookLoans.book) && branch.equals(bookLoans.branch) && borrower.equals(bookLoans.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, branch, borrower);
    }

    @Override
    public String toString() {
        return "BookLoans{" +
                "book=" + book +
                ", branch=" + branch +
                ", borrower=" + borrower +
                ", dateOut=" + dateOut +
                ", dateIn=" + dateIn +
                ", dueDate=" + dueDate +
                '}';
    }
}

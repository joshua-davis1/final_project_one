package com.ss.lms.backend.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Loan implements Serializable {

    @Serial
    private static final long serialVersionUID = 7861560901234156740L;
    private int bookId;
    private int branchId;
    private Borrower borrower;
    private LocalDate dateOut;
    private LocalDate dateIn = null;
    private LocalDate dueDate;

    public Loan(int bookId, int branchId, Borrower borrower, LocalDate dateOut, LocalDate dateIn, LocalDate dueDate) {
        this.bookId = bookId;
        this.branchId = branchId;
        this.borrower = borrower;
        this.dateOut = dateOut;
        this.dateIn = dateIn;
        this.dueDate = dueDate;
    }

    public Loan(int bookId, int branchId, Borrower borrower, LocalDate dateOut, LocalDate dueDate) {
        this.bookId = bookId;
        this.branchId = branchId;
        this.borrower = borrower;
        this.dateOut = dateOut;
        this.dueDate = dueDate;
    }

    public Loan(int bookId, int branchId, Borrower borrower) {
        this.bookId = bookId;
        this.branchId = branchId;
        this.borrower = borrower;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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
        Loan loan = (Loan) o;
        return bookId == loan.bookId && branchId == loan.branchId && borrower.equals(loan.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, branchId, borrower);
    }

    @Override
    public String toString() {
        return "Loans{" +
                "bookId=" + bookId +
                ", branchId=" + branchId +
                ", borrower=" + borrower +
                ", dateOut=" + dateOut +
                ", dateIn=" + dateIn +
                ", dueDate=" + dueDate +
                '}';
    }
}

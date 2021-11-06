package com.ss.lms.View;

import com.ss.lms.entity.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowerView extends View{


    private Integer cardNo = null;

    void displayOne() {
        System.out.println("Enter your card number:");
    }

    void displayTwo() {
        System.out.println("1)\tCheck out a book");
        System.out.println("2)\tReturn a book");
        System.out.println("3)\tQuit to previous");
    }

    Boolean cardIsValid(int cardNo)  {
        List<Borrower> borrowerList = null;
        try {
            borrowerList = adminService.getBorrowerService().getAllBorrowers();
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
        if (borrowerList == null) return Boolean.FALSE;
        if(borrowerList.stream().anyMatch((client) -> client.getCardNo() == cardNo)) {
            this.cardNo = cardNo;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void startViewTwo() {
        while(true) {
            displayTwo();
            int input = getInput(3);
            if (input == 3) break;
            switch (input) {
                case 1:
                    displayOptionOne();
                    break;
                case 2:
                    displayOptionThree();
                    break;
            }
        }
    }

    public void printBooks(List<Book> books){
        int i = 1;
        for (Book book: books) {
            System.out.println(i+")\t"+book.getTitle());
            i++;
        }
        System.out.println((books.size()+1)+")\tQuit to previous");
    }

    public void displayOptionThree() {
        int input = 0;
        System.out.println("Pick the Branch you want to return a book too: ");
        setBranches(null);
        getAllBranches();
        if(branches == null) return;
        printAllBranches();
        input = getInput(branches.size()+1);
        if(input == branches.size()+1) return;
        displayOptionFour(branches.get(input - 1));
    }

    public void displayOptionFour(Branch branch) {
        System.out.println("Pick the Book you want to return.");
        List<Book> booksInBranch = branch.getInventory().stream().map((stock) -> stock.getBook()).collect(Collectors.toList());
        printBooks(booksInBranch);
        int input = getInput(booksInBranch.size()+1);
        if(input == booksInBranch.size()+1) return;
        // check if user has the book
        try {
            System.out.println(adminService.getBookLoansService().getAllBookLoans());
            if(!adminService.getBookLoansService().getAllBookLoans().stream().anyMatch((bookLoans -> bookLoans.getBorrower().getCardNo() == cardNo))) {
                System.out.println("You dont have this book checked out!");
                return;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        // check book in
    }

    public void displayOptionTwo(Branch branch) {
        System.out.println("Pick the Book you want to check out.");
        List<Book> booksInBranch = branch.getInventory().stream().filter((stock) -> stock.getNoOfCopies() > 0).map((stock) -> stock.getBook()).collect(Collectors.toList());
        printBooks(booksInBranch);
        int input = getInput(booksInBranch.size()+1);
        if(input == booksInBranch.size()+1) return;
        try {
            System.out.println(adminService.getBookLoansService().getAllBookLoans());
            if(adminService.getBookLoansService().getAllBookLoans().stream().anyMatch((bookLoans -> bookLoans.getBorrower().getCardNo() == cardNo))) {
                System.out.println("You have already checked this book out.");
                return;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        LocalDate dateOut = LocalDate.now();
        LocalDate dueDate = dateOut.plusDays(7);
        // create bookloan
        BookLoans bookLoans = new BookLoans(booksInBranch.get(input-1), branch, new Borrower(cardNo), dateOut, dueDate);
        try {
            adminService.getBookLoansService().addBookLoan(bookLoans);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void displayOptionOne() {
        // display here
        System.out.println("Pick the Branch you want to check out from: ");
        int input = 0;
        getAllBranches();
        if (branches == null) return;
        printAllBranches();
        input = getInput(branches.size()+1);
        if (input == branches.size()+1) return;
        displayOptionTwo(branches.get(input - 1));
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            displayOne();
            int cardNo = getInputAnyInt();
            if (!cardIsValid(cardNo)) System.out.println("Invalid card number. Please try again.");
            else {
                startViewTwo();
                flag = false;
            }
        }
    }
}

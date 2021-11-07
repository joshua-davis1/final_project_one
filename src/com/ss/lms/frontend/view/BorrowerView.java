package com.ss.lms.frontend.view;

import com.ss.lms.backend.entity.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowerView extends View {

    private List<Borrower> borrowerList = null;
    private Borrower borrower;
    private List<Branch> branchList = null;
    private List<String> branchNames = null;
    private Branch branch;
    private List<Book> booksInBranch = null;
    private Book book = null;
    private List<Loan> loanList = null;
    private Loan loan = null;

    private final List<String> mainMenu = new ArrayList<>() {{add("Enter your card number:");}};
    private final List<String> menuOne = new ArrayList<>() {{add("Check out a book");add("Return a book");}};
    private final List<String> menuTwo = new ArrayList<>() {{add("Select branch location: ");}};
    private final List<String> checkOutMenu = new ArrayList<>() {{add("Select the Book you want to check out.");}};
    private final List<String> checkOutMenutwo = new ArrayList<>() {{add("Select the Book you want to return.");}};
    private final List<String> invalidCard = new ArrayList<>() {{add("Invalid card number. Please try again.");}};
    private final List<String> checkOutErr = new ArrayList<>() {{add("You have already checked this book out.");}};
    private final List<String> checkOutMenuOne = new ArrayList<>() {{add("Select branch location: ");}};
    private final String bookCheckOut = "Book successfully checked out.";



    @Override
    void run() {
        borrowerList = adminController.getBorrowers();
        boolean flag = true;
        while (flag) {
            printMessages(mainMenu, false, false);
            intInput = valIntInput();
            if (!cardNoIsValid(intInput)) printMessages(invalidCard,false,false);
            else {
                borrower = borrowerList.stream().filter(borrower1 -> borrower1.getCardNo() == intInput).findFirst().get();
                startMenuOne();
                flag = false;
            }
        }
    }

    public void startMenuOne() {
        while(true) {
            printMessages(menuOne, true, true);
            intInput = valIntInputLimit(3);
            if(intInput == 3) break;
            switch (intInput) {
                case 1 -> checkBookOutView();
                case 2 -> returnBookView();
            }
        }
    }

    public void returnBookView() {
        printAllBranches();
        if (intInput == branchNames.size()+1) return;
        branch = branchList.get(intInput - 1);
        printMessages(checkOutMenu,false,false);

        System.out.println("Pick the Book you want to return.");
        booksInBranch = branch.getInventory().stream().map(Stock::getBook).collect(Collectors.toList());
        List<String> bookTitles = booksInBranch.stream().map(book1 -> book1.getTitle()).collect(Collectors.toList());
        printMessages(bookTitles,true,true);
        intInput = valIntInputLimit(booksInBranch.size() + 1);
        book = booksInBranch.get(intInput-1);
        if(!bookIsTaken()) {
            System.out.println("You do not have this book checked out!");
            return;
        }
        loan.setDateIn(LocalDate.now());
        adminController.checkInBook(loan);
        System.out.println("Book successfully checked in");
    }

    public void checkBookOutView() {
        printAllBranches();
        if (intInput == branchNames.size()+1) return;
        branch = branchList.get(intInput - 1);
        printMessages(checkOutMenutwo,false,false);
        booksInBranch = branch.getInventory().stream().filter((stock) -> stock.getQuantity() > 0).map(Stock::getBook).collect(Collectors.toList());
        List<String> bookTitles = booksInBranch.stream().map(Book::getTitle).collect(Collectors.toList());
        printMessages(bookTitles,true,true);
        intInput = valIntInputLimit(booksInBranch.size()+1);
        if(intInput == booksInBranch.size()+1) return;
        book = booksInBranch.get(intInput-1);
        if (bookIsTaken()) {
            System.out.println("You have already have a loan of this book.");
            return;
        };
        loan = new Loan(book.getId(), branch.getId(), borrower, LocalDate.now(), LocalDate.now().plusDays(7));
        adminController.addLoan(loan);
        System.out.println(bookCheckOut);
    }

    void printAllBranches() {
        // prinst branches
        printMessages(menuTwo,false,false);
        branchList = adminController.getBranches();
        if (branchList == null) return;
        branchNames = branchList.stream().map(Branch::getName).collect(Collectors.toList());
        printMessages(branchNames,true,true);
        intInput = valIntInputLimit(branchNames.size()+1);
    }

    Boolean bookIsTaken() {
        loanList = adminController.getLoans();
        loan = new Loan(book.getId(),branch.getId(),borrower);
        if(adminController.loanExist(loan)) {
            loan = adminController.findLoan(loan.getBookId(), loan.getBranchId(), borrower);
        }
        return adminController.loanExist(loan);
    }

    Boolean cardNoIsValid(int cardNo)  {
        if(adminController.getAllCardNo().stream().anyMatch(num -> num == cardNo)) return Boolean.TRUE;
        return Boolean.FALSE;
    }
}

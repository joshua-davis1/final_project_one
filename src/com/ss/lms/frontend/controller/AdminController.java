package com.ss.lms.frontend.controller;

import com.ss.lms.backend.entity.*;
import com.ss.lms.backend.service.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminController {

    private final ConnectionUtil connUtil = new ConnectionUtil();

    private final AuthorService authorService = new AuthorService(connUtil);
    private final BookAuthorService bookAuthorService = new BookAuthorService(connUtil);
    private final BookService bookService = new BookService(connUtil);
    private final BorrowerService borrowerService = new BorrowerService(connUtil);
    private final BranchService branchService = new BranchService(connUtil);
    private final LoanService loanService = new LoanService(connUtil);
    private final StockService stockService = new StockService(connUtil);

    final String dbErr = "Error retrieving data.";

    public Loan findLoan(int bookId, int branchId, Borrower borrower) {
        Loan loan = new Loan(bookId,branchId, borrower);
        try {
            return loanService.findLoan(loan);
        } catch (SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
        return loan;
    }

    public void updateBranch(Branch branch) {
        try {
            branchService.updateBranch(branch);
        } catch(SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
    }

    public void updateStockQty(Stock stock) {
        try {
            stockService.updateStockQty(stock);
        } catch (SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
    }

    public Borrower getBorrowerByCardNo(int cardNo){
        return borrowerService.getBorrowerByCardNo(cardNo);
    }

    public void addLoan(Loan loan) {
        try {
            loanService.addLoan(loan);
        } catch(SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
    }

    public List<Integer> getAllCardNo() {
        try {
            return borrowerService.getBorrowers().stream().map(borrower -> borrower.getCardNo()).collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Boolean loanExist(Loan loan) {
        try {
            return loanService.loanExist(loan);
        } catch (SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    public List<Loan> getLoans() {
        List<Loan> loanList = new ArrayList<>();
        try {
            return loanService.getLoans();
        } catch (SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
        return loanList;
    }

    public List<Borrower> getBorrowers() {
        List<Borrower> borrowerList = new ArrayList<>();
        try {
            return borrowerService.getBorrowers();
        } catch (SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
        return borrowerList;
    }

    public List<Branch> getBranches() {
        return loadBranches();
    }

    public List<Branch> getBranchesLazy() {
        List<Branch> branchList = new ArrayList<>();
        try {
            return branchService.getBranches();
        } catch(SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
        return branchList;
    }

    public void checkInBook(Loan loan) {
        try {
            loanService.updateLoan(loan);
        } catch (SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
    }

    public List<Book> getBooksLazy() {
        List<Book> bookList = new ArrayList<>();
        try {
            return bookService.getBooks();
        } catch(SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
        return bookList;
    }

    public List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();
        try {
            List<Author> authorList = authorService.getAuthors();
            List<BookAuthor> bookAuthorList = bookAuthorService.getBookAuthors();
            bookList = bookService.getBooks();
            joinAuthorToBook(bookList, authorList, bookAuthorList);
        } catch(SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
        return bookList;
    }

    private List<Branch> loadBranches() {
        List<Branch> branches = new ArrayList<>();
        try {
            List<Author> authorList = authorService.getAuthors();
            List<Book> bookList = bookService.getBooks();
            List<BookAuthor> bookAuthorList = bookAuthorService.getBookAuthors();
            List<Loan> loanList = loanService.getLoans();
            List<Stock> stockList = stockService.getStock();
            List<Branch> branchList = branchService.getBranches();

            // join author and book
            joinAuthorToBook(bookList, authorList, bookAuthorList);
            joinBookToStock(bookList, stockList);
            joinLoanToBranch(loanList, branchList);
            joinStockToBranch(stockList, branchList);
            branches = branchList;
        } catch(SQLException e) {
            System.out.println(dbErr);
            e.printStackTrace();
        }
        return branches;
    }

    private void joinLoanToBranch(List<Loan> loans, List<Branch> branches) {
        loans.forEach(loan -> {
            branches.stream().filter(branch -> branch.getId() == loan.getBranchId()).findFirst().get().addLoan(loan);
        });
    }

    private void joinStockToBranch(List<Stock> inventory, List<Branch> branches){
        inventory.forEach(stock -> {
            branches.stream().filter(branch -> branch.getId() == stock.getBranchId()).findFirst().get().addStock(stock);
        });
    };

    private void joinBookToStock(List<Book> books, List<Stock> inventory) {
        inventory.forEach(stock -> {
            stock.setBook(books.stream().filter(book -> book.getId() == stock.getBook().getId()).findFirst().get());
        });
    }

    private void joinAuthorToBook(List<Book> books, List<Author> authors, List<BookAuthor> bookAuthorList) {
        bookAuthorList.forEach(table -> {
            Author fAuthor = authors.stream().filter(author -> author.getId() == table.getAuthorId()).findFirst().get();
            Book fBook = books.stream().filter(book -> book.getId() == table.getBookId()).findFirst().get();
            fBook.addAuthor(fAuthor);
        });
    }
}

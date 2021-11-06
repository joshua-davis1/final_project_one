package com.ss.lms.View;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.Branch;
import com.ss.lms.service.AdminService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class View {

    protected AdminService adminService = new AdminService();
    protected List<Branch> branches = null;
    abstract void run();

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public void getAllBranches(){
        List<Branch> branches = null;
        try {
            branches = adminService.getBranchService().getAllBranches();
            List<BookCopies> inventory = adminService.getBookCopiesService().getAllBookCopies();
            List<Book> books = adminService.getBookService().getAllBooks();

            for (BookCopies stock: inventory) {
                stock.setBook(books.stream().filter((book) -> book.getBookId() == stock.getBook().getBookId()).collect(Collectors.toList()).get(0));
                branches.stream().filter((branch) -> branch.getBranchId() == stock.getBranch().getBranchId()).collect(Collectors.toList()).get(0).addInventory(stock);
            }
            setBranches(branches);

        } catch (SQLException e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }

    public void printAllBranches() {
        int i = 1;
        for(Branch branch: branches) {
            System.out.println(i+")\t"+branch.getBranchName());
            i++;
        }
        System.out.println((branches.size()+1)+")\tQuit to previous");
    }

    public Boolean stringIsQuit(String input) {
        if (input.toUpperCase().contentEquals("QUIT")) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public Boolean stringIsNA(String input) {
        if (input.toUpperCase().contentEquals("N/A")) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public int getInputAnyInt() {
        final String invalid = "Invalid input. Try again.";
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) return scanner.nextInt();
        else {
            System.out.println(invalid);
            return getInputAnyInt();
        }
    }

    public int getInput(int count){
        final String invalid = "Invalid input. Try again.";
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= count) return choice;
            else {
                System.out.println(invalid);
                return getInput(count);
            }
        }
        else {
            System.out.println(invalid);
            return getInput(count);
        }
    }
}

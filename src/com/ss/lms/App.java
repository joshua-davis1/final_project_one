package com.ss.lms;

import com.ss.lms.entity.*;
import com.ss.lms.service.AdminService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    private AdminService adminService = new AdminService();

    public static void main(String[] args) throws SQLException {
        App app = new App();
        // populated list
        List<Author> authors = app.adminService.getAuthorService().getAllAuthors();
        List<Book> books = app.adminService.getBookService().getAllBooks();
        List<Branch> branches = app.adminService.getBranchService().getAllBranches();
        List<Publisher> publishers = app.adminService.getPublisherService().getAllPublishers();
        List<BookCopies> inventory = app.adminService.getBookCopiesService().getAllBookCopies();

        // add book data into inventory
        // add inventory into branch
//        for (BookCopies stock: inventory) {
//            stock.setBook(books.stream().filter((book) -> book.getBookId() == stock.getBook().getBookId()).collect(Collectors.toList()).get(0));
//            branches.stream().filter((branch) -> branch.getBranchId() == stock.getBranch().getBranchId()).collect(Collectors.toList()).get(0).addInventory(stock);
//        }
//
//        for(Branch branch: branches) {
//            for (BookCopies stock: branch.getInventory()) {
//                System.out.println(branch.getBranchName()+"\ttitle: "+stock.getBook().getTitle()+"\t"+stock.getNoOfCopies());
//            }
//        }


        app.startMainView();







        //System.out.println("Authors");
        //for(Author author: authors) System.out.println("id:\t"+author.getAuthorId()+"\tfullName: "+author.getAuthorName());
        //System.out.println("Books");
        // add publisher to books
        //for(Book book: books) book.setPublisher(publishers.stream().filter((pub) -> pub.getPublisherId() == book.getPublisher().getPublisherId()).collect(Collectors.toList()).get(0));
        // add authors to books

    }

    List<Branch> getPopulatedBranches() throws SQLException {
        List<Book> books = adminService.getBookService().getAllBooks();
        List<Branch> branches = adminService.getBranchService().getAllBranches();
        List<BookCopies> inventory = adminService.getBookCopiesService().getAllBookCopies();
        // add book data into inventory
        // add inventory into branch
        for (BookCopies stock: inventory) {
            stock.setBook(books.stream().filter((book) -> book.getBookId() == stock.getBook().getBookId()).collect(Collectors.toList()).get(0));
            branches.stream().filter((branch) -> branch.getBranchId() == stock.getBranch().getBranchId()).collect(Collectors.toList()).get(0).addInventory(stock);
        }
        return branches;
    }

    Boolean stringIsQuit(String input) {
        if (input.toUpperCase().contentEquals("QUIT")) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    Boolean stringIsNA(String input) {
        if (input.toUpperCase().contentEquals("N/A")) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void startMainView() throws SQLException {
        printMainMessage();
        while(true) {
            int input = 0;
            printMainMenu();
            // validate and save user response
            input = getInput(3);
            switch (input) {
                case 1:
                    startLibViewOne();
                    break;
                case 2:
                    // startAdminView();
                    break;
                case 3:
                    // startBorrowerView():
                    break;
            }
        }
    }

    public void startLibViewTwo() throws SQLException {
        Branch branch;
        while(true) {
            int input = 0;
            // load in branches
            List<Branch> branches = getPopulatedBranches();
            printAllBranch(branches);
            input = getInput(branches.size()+1);
            if (input == branches.size()+1) break;
            branch = branches.get(input -1);
            startLibViewThree(branch);
        }
    }

    public void printLibOptOneViewOne(int branchId, String branchName){
        System.out.println("You have chosen to update the Branch with Branch Id: "+branchId+" and Branch Name: "+branchName+". ");
        System.out.println("Enter ‘quit’ at any prompt to cancel operation.\n");
    }

    public void printUpdateBranchName() {
        System.out.println("Please enter new branch name or enter N/A for no change: ");
    }

    public void printUpdateBranchAddress() {
        System.out.println("Please enter new branch address or enter N/A for no change: ");
    }

    public void startLibOptTwo(Branch branch) throws SQLException {
        System.out.println("Pick the Book you want to add copies of, to your branch:");
        int i = 1;
        for(BookCopies stock: branch.getInventory()) {
            System.out.println(i+")\t"+stock.getBook().getTitle());
            i++;
        }
        System.out.println(branch.getInventory().size()+1+")\tQuit to previous");
        final int bookIdx = getInput(branch.getInventory().size()+1);
        if( bookIdx == branch.getInventory().size()+1) return;
        // get inventory count
        BookCopies stock = branch.getInventory().get(bookIdx-1);
        System.out.println("Existing number of copies: "+stock.getNoOfCopies()+"\n");
        System.out.println("Enter new number of copies: ");
        i = getInputAnyInt();
        stock.setNoOfCopies(i);
        adminService.getBookCopiesService().updateInventory(stock);
    }

    public void startLibOptOne(Branch branch) throws SQLException {
        while(true) {
            StringBuilder input = new StringBuilder();
            Scanner scanner = new Scanner(System.in);
            printLibOptOneViewOne(branch.getBranchId(), branch.getBranchName());
            printUpdateBranchName();
            if(scanner.hasNextLine()) input.append(scanner.nextLine());
            if (stringIsQuit(input.toString())) break;
            if (!stringIsNA(input.toString())) branch.setBranchName(input.toString());
            input.delete(0,input.length());
            printUpdateBranchAddress();
            if(scanner.hasNextLine()) input.append(scanner.nextLine());
            if (stringIsQuit(input.toString())) break;
            if (!stringIsNA(input.toString())) branch.setBranchAddress(input.toString());
            String rs = adminService.getBranchService().updateBranch(branch);
            System.out.println(rs+"\n");
            break;
        }


    }

    public void startLibViewThree(Branch branch) throws SQLException {
        while (true) {
            int input = 0;
            printLibDisplayThree();
            input = getInput(3);
            if(input == 3) break;
            switch (input) {
                case 1:
                    startLibOptOne(branch);
                    break;
                case 2:
                    startLibOptTwo(branch);
                    break;
            }
        }
    }

    public void startLibViewOne() throws SQLException {
        while(true) {
            int input = 0;
            printLibDisplayOne();
            input = getInput(2);
            if (input == 2) break;
            startLibViewTwo();
        }

    }

    public void printLibDisplayOne() {
        System.out.println("1)\tEnter branch you manage");
        System.out.println("2)\tQuit to previous");
    }

    public void printLibDisplayThree() {
        System.out.println("1)\tUpdate the details of the library");
        System.out.println("2)\tAdd copies of Book to the Branch");
        System.out.println("3)\tQuit to previous");
    }

    public void printMainMessage() {
        System.out.println("Welcome to the SS Library Management System. Which category of a user are you\n");
    }

    public void printMainMenu() {
        System.out.println("1)\tLibrarian");
        System.out.println("2)\tAdministrator");
        System.out.println("3)\tBorrower");
    }

    public void printAllBranch(List<Branch> branches) {
        int i = 1;
        for(Branch branch: branches) {
            System.out.println(i+")\t"+branch.getBranchName());
            i++;
        }
        System.out.println((branches.size()+1)+")\tQuit to previous");
    }

    int getInputAnyInt() {
        final String invalid = "Invalid input. Try again.";
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) return scanner.nextInt();
        else {
            System.out.println(invalid);
            return getInputAnyInt();
        }
    }

    int getInput(int count){
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

package com.ss.lms.View;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.Branch;
import com.ss.lms.service.AdminService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibrarianView extends View {

    AdminService adminService = new AdminService();

    public void printLibDisplayOne() {
        System.out.println("1)\tEnter branch you manage");
        System.out.println("2)\tQuit to previous");
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

    public void startLibViewTwo() throws SQLException {
        Branch branch;
        while(true) {
            int input = 0;
            // load in branches
            List<Branch> branches = getPopulatedBranches();
            getAllBranches();
            printAllBranches();
            input = getInput(branches.size()+1);
            if (input == branches.size()+1) break;
            branch = branches.get(input -1);
            startLibViewThree(branch);
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

    public void printUpdateBranchAddress() {
        System.out.println("Please enter new branch address or enter N/A for no change: ");
    }


    public void printLibOptOneViewOne(int branchId, String branchName){
        System.out.println("You have chosen to update the Branch with Branch Id: "+branchId+" and Branch Name: "+branchName+". ");
        System.out.println("Enter ‘quit’ at any prompt to cancel operation.\n");
    }

    public void printUpdateBranchName() {
        System.out.println("Please enter new branch name or enter N/A for no change: ");
    }

    public void printLibDisplayThree() {
        System.out.println("1)\tUpdate the details of the library");
        System.out.println("2)\tAdd copies of Book to the Branch");
        System.out.println("3)\tQuit to previous");
    }


    @Override
    public void run() {
        while(true) {
            int input = 0;
            printLibDisplayOne();
            input = getInput(2);
            if (input == 2) break;
            try {
                startLibViewTwo();
            } catch (SQLException e) {
                System.out.println("Something went wrong.");
                break;
            }

        }
    }
}

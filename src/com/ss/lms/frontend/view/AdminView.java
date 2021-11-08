package com.ss.lms.frontend.view;

import com.ss.lms.backend.entity.Author;
import com.ss.lms.backend.entity.Book;
import com.ss.lms.backend.entity.Branch;
import com.ss.lms.backend.entity.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminView extends View {

    private final List<String> mainMenu = new ArrayList<>(){{
        add("Book and Author");
        add("Genres");
        add("Publishers");
        add("Library Branches");
        add("Borrowers");
        add("Over-ride Due Date for a Book Loan");}};
    private final List<String> crudOptions = new ArrayList<>(){{
        add("add new record");
        add("update a record");
        add("delete a record");
        add("read a record");}};
    private final String extendDueDateOption = "Enter new date";

    public List<String> getBranchNames(List<Branch> branches) {
        return branches.stream().map(Branch::getName).collect(Collectors.toList());
    }

    public List<Book> getBooksInBranch(Branch branch) {
        return branch.getInventory().stream().map(Stock::getBook).collect(Collectors.toList());
    }

    public List<String> getBookTitles(List<Book> bookList) {
        return bookList.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    public List<Author> getAuthorsInBranch(Branch branch) {
        List<Author> authorList = new ArrayList<>();
        branch.getInventory().forEach(stock -> authorList.addAll(stock.getBook().getAuthors()));
        return authorList;
    }

    @Override
    void run() {
        while (true){
            List<Branch> branchList = adminController.getBranches();
            List<String> branchNameList = getBranchNames(branchList);
            System.out.println("Select branch you administer.");
            printMessages(branchNameList, true, true);
            intInput = valIntInputLimit(branchNameList.size()+1);
            if(intInput == branchNameList.size()+1) break;
            Branch thisBranch = branchList.get(intInput - 1);
            System.out.println("Select table to perform one of the following CRUD operations.");
            printMessages(mainMenu,true,true);
            intInput = valIntInputLimit(mainMenu.size()+1);
            if (intInput == mainMenu.size()+1) break;
            switch (intInput) {
                case 1: // Book and Author
                    while (true){
                        List<Book> booksList = getBooksInBranch(thisBranch);
                        List<String> bookTitles = getBookTitles(booksList);
                        Scanner scanner = new Scanner(System.in);
                        clearStrBuff();
                        printMessages(crudOptions, true, true);
                        intInput = valIntInputLimit(crudOptions.size()+1);
                        if (intInput == crudOptions.size()+1) break;
                        switch (intInput) {
                            case 1: // add
                                Book newBook = new Book();
                                System.out.println("Enter title: ");
                                newBook.setTitle(scanner.nextLine());
                                while(true) {
                                    selectAuthorsMenu(thisBranch);
                                    break;
                                }

                                //newBook.setAuthors();
                                break;
                            case 2: // update
                                break;
                            case 3: // delete
                                break;
                            case 4: // read
                                printMessages(bookTitles, true,true);
                                intInput = valIntInputLimit(bookTitles.size()+1);
                                if (intInput == bookTitles.size()+1) break;
                                System.out.println(intInput);
                                System.out.println("title: "+bookTitles.get(intInput-1));
                                System.out.println("author: "+booksList.get(intInput-1).getAuthors().stream().map(Author::getName).collect(Collectors.toList()));


                                break;
                        }
                    }

                    //printAllBooks(booksList);
                    break;
                case 2: // Add/Update/Delete/Read Genres
                    break;
                case 3: // Add/Update/Delete/Read Publishers

                    break;
                case 4: // Add/Update/Delete/Read Library Branches
                    break;
                case 5: // Add/Update/Delete/Read Borrowers
                    break;
                case 6: // Over-ride Due Date for a Book Loan
                    break;
            }
        }
    }

    public void selectAuthorsMenu(Branch branch){
        List<Author> authorsInBranch = getAuthorsInBranch(branch);
        List<String> authorNames = authorsInBranch.stream().map(Author::getName).collect(Collectors.toList());
        List<Author> selectedAuthors = new ArrayList<>();
        while(true) {
            System.out.println("Selected authors: "+selectedAuthors.stream().map(Author::getName));
            System.out.println("Select author to add.");
            printMessages(authorNames,true,true);
            intInput = valIntInputLimit(authorNames.size()+1);

        }
    };
}


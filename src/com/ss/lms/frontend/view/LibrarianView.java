package com.ss.lms.frontend.view;

import com.ss.lms.backend.entity.Book;
import com.ss.lms.backend.entity.Branch;
import com.ss.lms.backend.entity.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibrarianView extends View {

    // state
    private List<Branch> branches = null;
    private Branch branch = null;
    private List<Book> branchBookList = null;
    private Book book = null;
    private Stock stock = null;

    // display
    private final List<String> menuOne = new ArrayList<>(){{add("Enter branch you manage");}};
    private final List<String> menuTwo = new ArrayList<>() {{add("Update the details of the library");add("Add copies of Book to the Branch");}};
    private final List<String> updateMessageOne = new ArrayList<>(){{add("Please enter new branch name or enter N/A for no change: ");}};
    private final List<String> updateMessageTwo = new ArrayList<>(){{add("Please enter new branch address or enter N/A for no change: ");}};
    private final List<String> updateMessageThree = new ArrayList<>(){{add("Branch details successfully updated.");}};
    private final List<String> addBookMessageOne = new ArrayList<>(){{add("Pick the Book you want to add copies of, to your branch:");}};
    private final List<String> addBookMessageTwo = new ArrayList<>(){{add("Book quantity successfully updated.");}};

    public void printOptionOne() {
        printMessages(
                new ArrayList<>(){{
                    add("You have chosen to update the Branch with Branch Id: "+branch.getId()+" and Branch Name: "+branch.getName()+".");
                    add("Enter ‘quit’ at any prompt to cancel operation.\n");}},
                false,
                false);
    }

    private void printAddCopiesMenu() {
        int qty = branch.getInventory().stream().filter(stock -> stock.getBook().getId() == book.getId()).findFirst().get().getQuantity();
        printMessages(new ArrayList<>(){{add("Existing number of copies: "+qty);add("Enter new number of copies: ");}}, false ,false);
    }

    private void runViewOne() {
        while(true) {
            int input = 0;
            branches = (adminController.getBranches());
            printMessages(branches.stream().map(Branch::getName).collect(Collectors.toList()), true, true); // print all branches
            input = valIntInputLimit(branches.size()+1);
            if (input == branches.size()+1) break;
            branch = branches.get(input -1); // set selected branch
            runViewTwo();
        }
    }

    private void runViewTwo() {
        while (true) {
            int input = 0;
            printMessages(menuTwo, true, true);
            input = valIntInputLimit(3);
            if(input == 3) break;
            switch (input) {
                case 1:
                    updateDetailsView();
                    break;
                case 2:
                    addCopiesToBookView();
                    break;
            }
        }
    }

    private void addCopiesToBookView() {
        branchBookList = branch.getInventory().stream().map(stock -> stock.getBook()).collect(Collectors.toList());
        printMessages(addBookMessageOne, false,false);
        printMessages(branchBookList.stream().map(book1 ->  book1.getTitle()).collect(Collectors.toList()), true, true);
        intInput = valIntInputLimit(branch.getInventory().size()+1);
        book = branchBookList.get(intInput - 1);
        stock = branch.getInventory().stream().filter(stock1 -> stock1.getBook().getId() == book.getId()).findFirst().get();
        printAddCopiesMenu();
        intInput = valIntInput();
        stock.setQuantity(intInput);
        adminController.updateStockQty(stock);
        printMessages(addBookMessageTwo, false, false);
    }

    private void updateDetailsView() {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            printOptionOne();
            printMessages(updateMessageOne, false,false);
            if(scanner.hasNextLine()) strInput.append(scanner.nextLine());
            if (stringIsQuit(strInput.toString())) break;
            if (!stringIsNA(strInput.toString())) branch.setName(strInput.toString());
            clearStrBuff();
            printMessages(updateMessageTwo, false, false);
            if(scanner.hasNextLine()) strInput.append(scanner.nextLine());
            if (stringIsQuit(strInput.toString())) break;
            if (!stringIsNA(strInput.toString())) branch.setAddress(strInput.toString());
            clearStrBuff();
            adminController.updateBranch(branch);
            printMessages(updateMessageThree, false, false);
            break;
        }
    }

    @Override
    void run() {
        while(true) {
            int input = 0;
            printMessages(menuOne, true, true);
            input = valIntInputLimit(2);
            if (input == 2) break;
            runViewOne();
        }
    }
}

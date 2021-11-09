package com.ss.lms.frontend.view;

import java.util.ArrayList;
import java.util.List;

public class MainView extends View {

    // Views
    private final AdminView adminView = new AdminView();
    private final BorrowerView borrowerView = new BorrowerView();
    private final LibrarianView librarianView = new LibrarianView();


    // Constants
    private final String messageOne = "Welcome to the SS Library Management System. Which category of a user are you\n";
    private final List<String> menuOne = new ArrayList(){{add("Librarian");add("Administrator");add("Borrower");}};

    @Override
    public void run() {
        printMessages(new ArrayList<>(){{add(messageOne);}}, false, false);
        while(true) {
            int input = 0;
            printMessages(menuOne,true, false);
            // validate and save user response
            input = valIntInputLimit(3);
            switch (input) {
                case 1 -> librarianView.run();
                case 2 -> adminView.run();
                case 3 -> borrowerView.run();
            }
        }
    }
}

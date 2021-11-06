package com.ss.lms.View;

public class MainView extends View {

    private LibrarianView libView = new LibrarianView();
    private BorrowerView BorrowerView = new BorrowerView();

    public void printMainMessage() {
        System.out.println("Welcome to the SS Library Management System. Which category of a user are you\n");
    }

    public void printMainMenu() {
        System.out.println("1)\tLibrarian");
        System.out.println("2)\tAdministrator");
        System.out.println("3)\tBorrower");
    }

    @Override
    public void run() {
        printMainMessage();
        while(true) {
            int input = 0;
            printMainMenu();
            // validate and save user response
            input = getInput(3);
            switch (input) {
                case 1:
                    libView.run();
                    break;
                case 2:
                    // startAdminView();
                    break;
                case 3:
                    BorrowerView.run();
                    break;
            }
        }
    }
}

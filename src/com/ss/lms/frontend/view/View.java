package com.ss.lms.frontend.view;

import com.ss.lms.frontend.controller.AdminController;

import java.util.List;
import java.util.Scanner;

public abstract class View {

    protected AdminController adminController = new AdminController();

    // state
    protected Integer intInput = null;
    protected StringBuilder strInput = new StringBuilder();

    abstract void run();

    protected  void clearStrBuff() {
        strInput.delete(0,strInput.length());
    }

    protected void printMessages(List<String> messages, Boolean oFlag, Boolean qFlag) {
        // oFlag = ordered list. true -> ol   false -> ul
        // qFlag = add "Quit to previous" to end of list
        int i = 1;
        if (oFlag) for (String message: messages) {
            System.out.println(i+")\t"+message);
            i++;
        }
        else for (String message: messages) {
            System.out.println(message);
            i++;
        }
        if(qFlag) System.out.println(i+")\tQuit to previous");
    }


    protected int valIntInputLimit(int count){
        // Input Validator
        // int input where count equals max range
        final String invalid = "Invalid input. Try again.";
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= count) return choice;
            else {
                System.out.println(invalid);
                return valIntInputLimit(count);
            }
        }
        else {
            System.out.println(invalid);
            return valIntInputLimit(count);
        }
    }

    protected int valIntInput() {
        // Input Validator
        // any int is valid
        final String invalid = "Invalid input. Try again.";
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) return scanner.nextInt();
        else {
            System.out.println(invalid);
            return valIntInput();
        }
    }

    protected Boolean stringIsQuit(String input) {
        // String conditional
        if (input.toUpperCase().contentEquals("QUIT")) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    protected Boolean stringIsNA(String input) {
        // String conditional
        if (input.toUpperCase().contentEquals("N/A")) return Boolean.TRUE;
        return Boolean.FALSE;
    }
}

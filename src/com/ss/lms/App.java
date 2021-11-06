package com.ss.lms;

import com.ss.lms.View.MainView;
import com.ss.lms.entity.*;
import com.ss.lms.service.AdminService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    private AdminService adminService = new AdminService();

    /*
    * TODO: - build borrowers view
    * */

    public static void main(String[] args) {
        MainView main = new MainView();
        main.run();
    }
}

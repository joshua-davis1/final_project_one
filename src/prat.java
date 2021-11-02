import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.AdminService;

import java.sql.*;
import java.util.Scanner;

public class prat {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 1. register driver
        // 2. create connection
        // 3. statement object
        // 4. exec
        AdminService admin = new AdminService();
        Book book = new Book();
        Publisher pub = new Publisher();
        pub.setPublisherId(1);
        book.setTitle("To Kill A Mockingbird");
        book.setPublisher(pub);
        admin.addBook(book);
    }
}

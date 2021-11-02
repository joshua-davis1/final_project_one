import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.AdminService;

import java.sql.*;
import java.util.Scanner;

public class prat {


    public static void main(String[] args) throws SQLException {
        // 1. register driver
        // 2. create connection
        // 3. statement object
        // 4. exec
        System.out.println("Add a new book.");
        AdminService admin = new AdminService();
          for (int i=0; i < 10; i++) {
              Scanner scanner = new Scanner(System.in);
              System.out.println("Enter book title: ");
              Book book = new Book();
              Publisher pub = new Publisher();
              pub.setPublisherId(i);
              book.setTitle(scanner.nextLine());
              book.setPublisher(pub);
              admin.addBook(book);


          }
    }
}
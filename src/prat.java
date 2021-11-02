import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookAuthor;
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
        AdminService admin = new AdminService();

        // add authors
//        Author author1 = new Author();
//        author1.setAuthorName("Stephen King");
//        admin.addAuthor(author1);
//
//        Author author2 = new Author();
//        author2.setAuthorName("Giovanna Bellingham");
//        admin.addAuthor(author2);
//
//        Author author3 = new Author();
//        author3.setAuthorName("Giustina Minter");
//        admin.addAuthor(author3);
//
//        Author author4 = new Author();
//        author4.setAuthorName("Shaylah Cussen");
//        admin.addAuthor(author4);
//
//        Author author5 = new Author();
//        author5.setAuthorName("Patrica Bynert");
//        admin.addAuthor(author5);
//
//        Author author6 = new Author();
//        author6.setAuthorName("Wiley Szymaniak");
//        admin.addAuthor(author6);
//
//        Author author7 = new Author();
//        author7.setAuthorName("Benedikta Gethin");
//        admin.addAuthor(author7);
//
//        Author author8 = new Author();
//        author8.setAuthorName("Nevsa Annandale");
//        admin.addAuthor(author8);
//
//        Author author9 = new Author();
//        author9.setAuthorName("Justis Schuricht");
//        admin.addAuthor(author9);
//
//        Author author10 = new Author();
//        author10.setAuthorName("Seymour Kubec");
//        admin.addAuthor(author10);


        // add book author
//        for (int i=2; i < 10; i++) {
//            BookAuthor bkAuth = new BookAuthor();
//            Book book = new Book();
//            Author author = new Author();
//            book.setBookId(i);
//            author.setAuthorId(i - 1);
//            bkAuth.setAuthor(author);
//            bkAuth.setBook(book);
//            System.out.println(admin.addBookAuthor(bkAuth));
//        }

//          for (int i=0; i < 10; i++) {
//              Scanner scanner = new Scanner(System.in);
//              System.out.println("Enter book title: ");
//              Book book = new Book();
//              Publisher pub = new Publisher();
//              pub.setPublisherId(i);
//              book.setTitle(scanner.nextLine());
//              book.setPublisher(pub);
//              admin.addBook(book);
//          }
    }
}
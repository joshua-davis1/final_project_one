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
//        Book book = new Book();
//        Publisher pub = new Publisher();
//        pub.setPublisherId(1);
//        book.setTitle("To Kill A Mockingbird");
//        book.setPublisher(pub);
//        admin.addBook(book);

        Publisher pub1 = new Publisher();
        pub1.setPublisherName("Rippin Inc");
        pub1.setPublisherAddress("752 Graedel Circle");
        pub1.setPublisherPhone("756-206-0758");
        System.out.println(admin.addPublisher(pub1));

        Publisher pub2 = new Publisher();
        pub2.setPublisherName("Graham LLC");
        pub2.setPublisherAddress("92 Melvin Place");
        pub2.setPublisherPhone("190-261-0953");
        System.out.println(admin.addPublisher(pub2));

        Publisher pub3 = new Publisher();
        pub3.setPublisherName("McGlynn, Welch and Dooley");
        pub3.setPublisherAddress("9 Mariners Cove Street");
        pub3.setPublisherPhone("938-258-2542");
        System.out.println(admin.addPublisher(pub3));

        Publisher pub4 = new Publisher();
        pub4.setPublisherName("Stiedemann, Zieme and Funk");
        pub4.setPublisherAddress("6 Arkansas Terrace");
        pub4.setPublisherPhone("575-304-7375");
        System.out.println(admin.addPublisher(pub4));

        Publisher pub5 = new Publisher();
        pub5.setPublisherName("Donnelly, Feest and McGlynn");
        pub5.setPublisherAddress("630 Loeprich Court");
        pub5.setPublisherPhone("450-168-3524");
        System.out.println(admin.addPublisher(pub5));

        Publisher pub6 = new Publisher();
        pub6.setPublisherName("Feeney-Bartoletti");
        pub6.setPublisherAddress("38523 Steensland Court");
        pub6.setPublisherPhone("834-822-0932");
        System.out.println(admin.addPublisher(pub6));

        Publisher pub7 = new Publisher();
        pub7.setPublisherName("Rippin IncStreich, Johnston and Carroll");
        pub7.setPublisherAddress("8 Ronald Regan Center");
        pub7.setPublisherPhone("449-938-9789");
        System.out.println(admin.addPublisher(pub7));

        Publisher pub8 = new Publisher();
        pub8.setPublisherName("Homenick, Abshire and Jacobson");
        pub8.setPublisherAddress("310 Aberg Pass");
        pub8.setPublisherPhone("461-513-2625");
        System.out.println(admin.addPublisher(pub8));

        Publisher pub9 = new Publisher();
        pub9.setPublisherName("Bins, Brown and Rogahn");
        pub9.setPublisherAddress("917 Burning Wood Place");
        pub9.setPublisherPhone("849-745-2771");
        System.out.println(admin.addPublisher(pub9));

        Publisher pub10 = new Publisher();
        pub10.setPublisherName("Paucek-Veum");
        pub10.setPublisherAddress("4 Melody Hill");
        pub10.setPublisherPhone("923-273-6046");
        System.out.println(admin.addPublisher(pub10));


    }
}
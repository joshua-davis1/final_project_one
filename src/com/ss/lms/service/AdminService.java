package com.ss.lms.service;

import com.ss.lms.dao.*;
import com.ss.lms.entity.*;

import java.sql.Connection;
import java.sql.SQLException;


public class AdminService {

    ConnectionUtil connUtil = new ConnectionUtil();

    public String addBook(Book book) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookDAO bdao = new BookDAO(conn);
            bdao.addBook(book);
            conn.commit();
            return "Book added successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Book failed to be added";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String addPublisher(Publisher pub) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            PublisherDAO pdao = new PublisherDAO(conn);
            pdao.addPublisher(pub);
            conn.commit();
            return "Publisher added successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Publisher failed to be added";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String addAuthor(Author author) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);
            adao.addAuthor(author);
            conn.commit();
            return "Author created successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Author failed to be created";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String addBookAuthor(BookAuthor bookAuthor) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookAuthorDAO bdao = new BookAuthorDAO(conn);
            bdao.addBookAuthor(bookAuthor);
            conn.commit();
            return "Author added successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Author failed to be added";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String addLibraryBranch(LibraryBranch libraryBranch) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
            ldao.addLibraryBranch(libraryBranch);
            conn.commit();
            return "library branch added successfully";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "library branch failed to be added";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String setCopiesToBranch(BookCopies BookCopies) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookCopiesDAO bdao = new BookCopiesDAO(conn);
            bdao.addBookCopies(BookCopies);
            conn.commit();
            return "Number of copies successfully updated";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Failed to set number of copies";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String addBorrower(Borrower borrower) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BorrowerDOA bdao = new BorrowerDOA(conn);
            bdao.addBorrower(borrower);
            conn.commit();
            return "Borrower successfully created";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Failed to create borrower";
        } finally {
            if (conn != null) conn.close();
        }
    }

    public String addBookLoan(BookLoans bookLoan) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookLoanDOA bdao = new BookLoanDOA(conn);
            bdao.addBookLoans(bookLoan);
            conn.commit();
            return "Book loan successfully added";
        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) conn.rollback();
            return "Failed to add book loan";
        } finally {
            if (conn != null) conn.close();
        }
    }
}

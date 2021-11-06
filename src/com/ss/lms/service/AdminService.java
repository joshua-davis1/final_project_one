package com.ss.lms.service;

public class AdminService {

    ConnectionUtil connUtil = new ConnectionUtil();

    private final AuthorService authorService = new AuthorService(connUtil);
    private final BookCopiesService bookCopiesService = new BookCopiesService(connUtil);
    private final BookService bookService = new BookService(connUtil);
    private final BranchService branchService = new BranchService(connUtil);
    private final PublisherService publisherService = new PublisherService(connUtil);
    private final BorrowerService borrowerService = new BorrowerService(connUtil);
    private final BookLoansService bookLoansService = new BookLoansService(connUtil);

    public BookLoansService getBookLoansService() {
        return bookLoansService;
    }

    public BorrowerService getBorrowerService() {
        return borrowerService;
    }

    public BookCopiesService getBookCopiesService() {
        return bookCopiesService;
    }

    public PublisherService getPublisherService() {
        return publisherService;
    }

    public BranchService getBranchService() {
        return branchService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }


}

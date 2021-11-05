package com.ss.lms.service;

public class AdminService {
    ConnectionUtil connUtil = new ConnectionUtil();

    private final AuthorService authorService = new AuthorService(connUtil);
    private final BookCopiesService bookCopiesService = new BookCopiesService(connUtil);
    private final BookService bookService = new BookService(connUtil);
    private final BranchService branchService = new BranchService(connUtil);
    private final PublisherService publisherService = new PublisherService(connUtil);

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

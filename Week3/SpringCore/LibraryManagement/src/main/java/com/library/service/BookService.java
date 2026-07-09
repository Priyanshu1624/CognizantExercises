package com.library.service;

import com.library.repository.BookRepository;

import java.util.List;


public class BookService {

    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void listBooks() {
        List<String> books = bookRepository.findAllBooks();
        System.out.println("Books currently in the library:");
        for (String title : books) {
            System.out.println(" - " + title);
        }
    }
}

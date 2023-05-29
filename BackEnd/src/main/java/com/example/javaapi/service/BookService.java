package com.example.javaapi.service;

import com.example.javaapi.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Page<Book> getBookAproval(Optional<String> searchBy, Optional<Integer> page, Optional<String> sortBy,
                              Optional<String> order);
    void deleteBook(long bookId);
    void editBook(Book book);
    void addBook(String imageName, Book information);
    Book getBookbyId(Long bookId);
    Integer getBooksCount();
    List<Book> getAll();

}

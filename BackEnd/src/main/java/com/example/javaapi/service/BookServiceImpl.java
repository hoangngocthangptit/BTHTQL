package com.example.javaapi.service;

import com.example.javaapi.entity.Book;
import com.example.javaapi.repository.BookRepository;
import com.example.javaapi.until.JwtGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository repository;
    @Autowired
    private JwtGenerator generate;
    private Book bookinformation = new Book();
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<Book> getBookAproval(Optional<String> searchBy, Optional<Integer> page, Optional<String> sortBy, Optional<String> order) {
        return null;
    }

    @Override
    public void deleteBook(long bookId) {
        repository.deleteById(bookId);
    }

    @Override
    public void editBook(Book book) {
        Book info = repository.findByBookId(book.getBookId());
        repository.save(info);
    }

    @Override
    public void addBook(String imageName, Book information) {
        information.setImage(imageName);
        information.setImageFile(null);
        repository.save(information);
    }

    @Override
    public Book getBookbyId(Long bookId) {
        Book info = (Book) repository.findByBookId(bookId);
        if (info != null) {
            return info;
        }
        return null;
    }

    @Override
    public Integer getBooksCount() {
        List<Book> books= (List<Book>) repository.findAll();
        return books.size();
    }

    @Override
    public List<Book> getAll() {
        return (List<Book>) repository.findAll();
    }
}

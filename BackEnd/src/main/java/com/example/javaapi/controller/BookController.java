package com.example.javaapi.controller;

import com.example.javaapi.entity.Book;
import com.example.javaapi.response.Response;
import com.example.javaapi.service.BookService;
import com.example.javaapi.until.CopyFile;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
public class BookController {
    @Autowired
    private ServletContext servletContext;


    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private BookService bookservice;
    @PostMapping("/book")
    public ResponseEntity<Response> addBook(@ModelAttribute Book book) throws IOException {
        String imageName = book.getImageFile().getOriginalFilename();
        book.setImage(imageName);
        CopyFile.saveFiles(book.getBookName(), book.getImageFile());
        bookservice.addBook(imageName,book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Book Added Successfully..",200,book));

    }



    @GetMapping("/book")
    public ResponseEntity<Response> getBooks() {
        List<Book> books = bookservice.getAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("The Book details are",200, books));
    }


    @GetMapping(value = "/book/getId/{bookId}")
    public ResponseEntity<Response> getBookbyId(@PathVariable("bookId") Long bookId) {
        Book info = bookservice.getBookbyId(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("The Book details are",200, info));
    }
//
//
    @PutMapping("/book/{bookId}")
    public ResponseEntity<Response> editBook(@PathVariable("bookId") long bookId,@ModelAttribute Book book,@RequestHeader("token") String token) throws IOException {
        Book bookInfo=bookservice.getBookbyId(bookId);
        bookInfo.setNoOfBooks(book.getNoOfBooks());
        bookInfo.setBookDetails(book.getBookDetails());
        bookInfo.setBookName(book.getBookName());
        bookInfo.setCategory(book.getCategory());
        bookInfo.setAuthorName(book.getAuthorName());
        if(book.getCreatedDate() != null){
            bookInfo.setCreatedDate(book.getCreatedDate());
        }
        bookInfo.setTitle(book.getTitle());
        if(book.getImageFile() != null) {
            bookInfo.setImage(book.getImageFile().getOriginalFilename());
        }
        bookservice.addBook(bookInfo.getImage(),bookInfo);
        if(bookInfo!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("The Book details are",200,bookInfo));
        return null;
    }
//
    @DeleteMapping("book/{bookId}")
    public ResponseEntity<Response> deleteBook(@PathVariable long bookId, @RequestHeader("token") String token) {
        Book book=bookservice.getBookbyId(bookId);
        bookservice.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("delete book",200,book));

    }
    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        String uploadDir="D:\\cham_cong\\BTL_LTRWEB\\BackEnd\\src\\main\\java\\img";
        Path imagePath = Paths.get(uploadDir, filename);
        byte[] imageBytes = Files.readAllBytes(imagePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // change this to the appropriate content type
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }


}

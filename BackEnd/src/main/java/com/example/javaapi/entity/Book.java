package com.example.javaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private Long noOfBooks;
    private String authorName;
    private String bookDetails;
    private String title;
    private Date createdDate;
    private String image;
    private String category;
    @JsonIgnore
    @Transient
    private MultipartFile imageFile;
}

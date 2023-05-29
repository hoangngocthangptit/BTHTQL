package com.example.javaapi.controller;

import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.NhanVien;
import com.example.javaapi.entity.PhieuLuong;
import com.example.javaapi.repository.NhanVienRepo;
import com.example.javaapi.repository.PhieuLuongRepo;
import com.example.javaapi.response.Response;
import com.example.javaapi.service.BookService;
import com.example.javaapi.until.CopyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RequestMapping("phieu-luong")
@RestController
@CrossOrigin("*")
public class PhieuLuongController {
    @Autowired
    private PhieuLuongRepo nhanVienRepo;

    @GetMapping("")
    public ResponseEntity<Response> getBooks() {
        List<PhieuLuong> books = (List<PhieuLuong>) nhanVienRepo.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Nhan vien",200, books));
    }
}

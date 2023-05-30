package com.example.javaapi.controller;

import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.ChamCong;
import com.example.javaapi.entity.PhieuLuong;
import com.example.javaapi.repository.ChamCongRepo;
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
@RequestMapping("cham-cong")
@RestController
@CrossOrigin("*")
public class ChamCongController {
    @Autowired private ChamCongRepo nhanVienRepo;

    @GetMapping("")
    public ResponseEntity<Response> get() {
        List<ChamCong> books = (List<ChamCong>) nhanVienRepo.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Nhan vien",200, books));
    }

}

package com.example.javaapi.controller;

import com.example.javaapi.entity.ChamCong;
import com.example.javaapi.entity.ChucVu;
import com.example.javaapi.repository.ChucVuRepo;
import com.example.javaapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("chuc-vu")
@RestController
@CrossOrigin("*")
public class ChucVuController {
    @Autowired
    private ChucVuRepo repo;
    @GetMapping("")
    public ResponseEntity<Response> get() {
        List<ChucVu> books = (List<ChucVu>) repo.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Cham cong",200, books));
    }
}

package com.example.javaapi.controller;

import com.example.javaapi.entity.ChucVu;
import com.example.javaapi.entity.KhenThuong;
import com.example.javaapi.entity.KyLuat;
import com.example.javaapi.repository.ChucVuRepo;
import com.example.javaapi.repository.KhenThuongRepo;
import com.example.javaapi.repository.KyLuatRepo;
import com.example.javaapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("khen-phat")
@RestController
@CrossOrigin("*")
public class KhenKiController {
    @Autowired
    private KhenThuongRepo repo;
    @Autowired
    private KyLuatRepo kyLuatRepo;
    @GetMapping("khen-thuong")
    public ResponseEntity<Response> getKhen() {
        List<KhenThuong> kt = (List<KhenThuong>) repo.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Cham cong",200, kt));
    }
    @GetMapping("ky-luat")
    public ResponseEntity<Response> get() {
        List<KyLuat> kl = (List<KyLuat>) kyLuatRepo.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Cham cong",200, kl));
    }
}

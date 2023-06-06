package com.example.javaapi.controller;

import com.example.javaapi.entity.BaoHiem;
import com.example.javaapi.entity.KhenThuong;
import com.example.javaapi.entity.KyLuat;
import com.example.javaapi.entity.PhongBan;
import com.example.javaapi.repository.BaoHiemRepo;
import com.example.javaapi.repository.KhenThuongRepo;
import com.example.javaapi.repository.KyLuatRepo;
import com.example.javaapi.repository.PhongBanRepo;
import com.example.javaapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("phong-bao")
@RestController
@CrossOrigin("*")
public class PhongBaoController {
    @Autowired
    private PhongBanRepo repo;
    @Autowired
    private BaoHiemRepo kyLuatRepo;
    @GetMapping("phong-ban")
    public ResponseEntity<Response> getKhen() {
        List<PhongBan> kt = (List<PhongBan>) repo.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Cham cong",200, kt));
    }
    @GetMapping("bao-hiem")
    public ResponseEntity<Response> get() {
        List<BaoHiem> kl = (List<BaoHiem>) kyLuatRepo.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Cham cong",200, kl));
    }
}

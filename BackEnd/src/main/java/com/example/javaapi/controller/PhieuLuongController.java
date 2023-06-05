package com.example.javaapi.controller;

import com.example.javaapi.dto.DoanhThuDTO;
import com.example.javaapi.dto.PhieuLuongDTO;
import com.example.javaapi.entity.ChamCong;
import com.example.javaapi.entity.NhanVien;
import com.example.javaapi.entity.PhieuLuong;
import com.example.javaapi.repository.KhenThuongRepo;
import com.example.javaapi.repository.KyLuatRepo;
import com.example.javaapi.repository.PhieuLuongRepo;
import com.example.javaapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
@RequestMapping("phieu-luong")
@RestController
@CrossOrigin("*")
public class PhieuLuongController {
    @Autowired
    private PhieuLuongRepo repo;
    @Autowired
    private KhenThuongRepo khenThuongRepo;
    @Autowired
    private KyLuatRepo kyLuatRepo;

    @GetMapping("")
    public ResponseEntity<Response> get() {
        List<PhieuLuong> books = (List<PhieuLuong>) repo.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Nhan vien", 200, books));
    }

    @PostMapping("")
    public ResponseEntity<Response> addPerson(@RequestBody List<NhanVien> nhanViens, @RequestBody ChamCong chamCong) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("phieu luong Added Successfully..", 200, nhanViens));
    }
    @GetMapping("/findTime/{time}")
    public ResponseEntity<Response> getMonth(@PathVariable Integer time) {
        if (time==0){
            List<PhieuLuong> phieuLuong= repo.findPhieuLuongByMonth(null);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Phieu Luong", 200, phieuLuong));
        }
       else{ List<PhieuLuong> phieuLuong= repo.findPhieuLuongByMonth(time);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Phieu Luong", 200, phieuLuong));}

        }


    @GetMapping("/doanhThuTheoNam")
    public ResponseEntity<Response> doanhThuName() {
        List<DoanhThuDTO> doanhThuDTOS=repo.tongDoanhThuTheoNam();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Phieu Luong", 200, doanhThuDTOS));
    }
    @GetMapping("/doanhThuThang")
    public ResponseEntity<Response> doanhThuThang() {
        List<DoanhThuDTO> doanhThuDTOS=repo.tongDoanhThuTheoThang();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Phieu Luong", 200, doanhThuDTOS));
    }
}

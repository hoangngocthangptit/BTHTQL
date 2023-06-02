package com.example.javaapi.controller;

import com.example.javaapi.dto.PhieuLuongDTO;
import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.ChamCong;
import com.example.javaapi.entity.NhanVien;
import com.example.javaapi.entity.PhieuLuong;
import com.example.javaapi.repository.ChamCongRepo;
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
import java.util.Date;
import java.util.List;
@RequestMapping("cham-cong")
@RestController
@CrossOrigin("*")
public class ChamCongController {
    @Autowired private ChamCongRepo repo;
    @Autowired private PhieuLuongRepo phieuLuongRepo;
    @Autowired private NhanVienRepo nhanVienRepo;

    @GetMapping("")
    public ResponseEntity<Response> get() {
        List<ChamCong> books = (List<ChamCong>) repo.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Cham cong",200, books));
    }
    @GetMapping("/result")
    public ResponseEntity<Response> getLuong(@RequestParam Integer mounth) {
        List<PhieuLuongDTO> chamCongs =  repo.ChamCong(mounth);

        for (PhieuLuongDTO chamCong:chamCongs
             ) {
            PhieuLuong phieuLuong=new PhieuLuong();
            NhanVien nv=nhanVienRepo.findById(chamCong.getIdNhanVien()).get();
            phieuLuong.setIdNhanVien(nv);
            Date currentTime = new Date();
            phieuLuong.setThoiGian(currentTime);
            if(nv.getIdChucVu().getTenChucvu()=="Quản lý cơ sở")
            {
                if (chamCong.getTongSoGioLam()>160)
                    phieuLuong.setTienLuong(5000000.0);
                else
                    phieuLuong.setTienLuong(5000000.0*0.8);
            }
            if(nv.getIdChucVu().getTenChucvu()=="Marketing")
            {
                if (chamCong.getTongSoGioLam()>160)
                    phieuLuong.setTienLuong(5000000.0);
                else
                    phieuLuong.setTienLuong(5000000.0*0.8);

            }
            if(nv.getIdChucVu().getTenChucvu()=="Quản lý nhân sự")
            {
                if (chamCong.getTongSoGioLam()>160)
                    phieuLuong.setTienLuong(10000000.0);
                else
                    phieuLuong.setTienLuong(10000000.0*0.8);

            }
            if(nv.getIdChucVu().getTenChucvu()=="Lễ tân")
            {
                phieuLuong.setTienLuong(5000000.0);

            }
            if(nv.getIdChucVu().getTenChucvu()=="Giáo viên")
            {
                phieuLuong.setTienLuong(chamCong.getTongSoGioLam()*500000.0);

            }
            if(nv.getIdChucVu().getTenChucvu()=="Trợ giảng")
            {
                phieuLuong.setTienLuong(chamCong.getTongSoGioLam()*40000.0);

            }
            phieuLuongRepo.save(phieuLuong);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Cham cong",200, chamCongs));
    }

}

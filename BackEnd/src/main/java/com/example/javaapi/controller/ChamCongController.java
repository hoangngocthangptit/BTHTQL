package com.example.javaapi.controller;

import com.example.javaapi.dto.PhieuLuongDTO;
import com.example.javaapi.entity.*;
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
    @GetMapping("/result/{month}")
    public ResponseEntity<Response> getLuong(@PathVariable Integer month) {
        if (month==0){
            month=1;
        }
        List<PhieuLuongDTO> chamCongs =  repo.ChamCong(month);

        for (PhieuLuongDTO chamCong:chamCongs
             ) {
            PhieuLuong phieuLuong=new PhieuLuong();
            NhanVien nv=nhanVienRepo.findById(chamCong.getIdNhanVien()).get();
            phieuLuong.setIdNhanVien(nv);
            Date currentTime = new Date();
            phieuLuong.setThoiGian(currentTime);
            int max1=nv.getListKhenThuong().size();
             int max2=nv.getListKyLuat().size();
             KhenThuong kt=new KhenThuong();
             KyLuat kl=new KyLuat();
             if(max1>0&&max2>0) {
                 kt = nv.getListKhenThuong().get(max1 - 1);
                 kl = nv.getListKyLuat().get(max2 - 1);
             }

            Double Cong=0.0;
            Integer bhxh=0;
            if(nv.getListBaoHiem()!=null){bhxh=nv.getListBaoHiem().get(0).getMucDong();}
             if(max1>0&&max2>0) {Cong=kt.getMucThuong()- kl.getMucPhat();}
            if(nv.getIdChucVu().getTenChucvu()=="Quản lý cơ sở")
            {
                if (chamCong.getTongSoGioLam()>160)
                    phieuLuong.setTienLuong(5000000.0*nv.getIdChucVu().getHeSoLuong()+Cong-bhxh);
                else
                    phieuLuong.setTienLuong(5000000.0*0.8-bhxh);
            }
            if(nv.getIdChucVu().getTenChucvu()=="Marketing")
            {
                if (chamCong.getTongSoGioLam()>160)
                    phieuLuong.setTienLuong(5000000.0*nv.getIdChucVu().getHeSoLuong()+Cong-bhxh);
                else
                    phieuLuong.setTienLuong(5000000.0*0.8+Cong-bhxh);

            }
            if(nv.getIdChucVu().getTenChucvu()=="Quản lý nhân sự")
            {
                if (chamCong.getTongSoGioLam()>160)
                    phieuLuong.setTienLuong(5000000*nv.getIdChucVu().getHeSoLuong()+Cong-bhxh);
                else
                    phieuLuong.setTienLuong(10000000.0*0.8+Cong-bhxh);

            }
            if(nv.getIdChucVu().getTenChucvu()=="Lễ tân")
            {
                phieuLuong.setTienLuong(5000000.0*nv.getIdChucVu().getHeSoLuong()+Cong-bhxh);

            }
            if(nv.getIdChucVu().getTenChucvu()=="Giáo viên")
            {
                phieuLuong.setTienLuong(5000000.0*nv.getIdChucVu().getHeSoLuong()+Cong-bhxh);

            }
            if(nv.getIdChucVu().getTenChucvu()=="Trợ giảng")
            {
                phieuLuong.setTienLuong(chamCong.getTongSoGioLam()*40000.0+Cong-bhxh);

            }
            phieuLuongRepo.save(phieuLuong);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Cham cong",200, chamCongs));
    }
    @GetMapping("/findTime/{time}")
    public ResponseEntity<Response> getMonth(@PathVariable Integer time) {
        if (time==0){
            List<ChamCong> phieuLuong= repo.findCongByMonth(null);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Phieu Luong", 200, phieuLuong));
        }
        else{ List<ChamCong> phieuLuong= repo.findCongByMonth(time);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Phieu Luong", 200, phieuLuong));}

    }

}

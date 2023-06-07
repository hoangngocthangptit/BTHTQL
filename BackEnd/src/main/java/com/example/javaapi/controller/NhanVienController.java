package com.example.javaapi.controller;

import com.example.javaapi.dto.NhanVienDTO;
import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.ChucVu;
import com.example.javaapi.entity.NhanVien;
import com.example.javaapi.entity.PhongBan;
import com.example.javaapi.repository.ChucVuRepo;
import com.example.javaapi.repository.NhanVienRepo;
import com.example.javaapi.repository.PhongBanRepo;
import com.example.javaapi.response.Response;
import com.example.javaapi.service.BookService;
import com.example.javaapi.until.CopyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RequestMapping("/nhan-vien")
@RestController
@CrossOrigin("*")
public class NhanVienController {
    @Autowired
    private NhanVienRepo nhanVienRepo;
    @Autowired
    private PhongBanRepo phongBanRepo;
    @Autowired
    private ChucVuRepo chucVuRepo;

    @GetMapping("")
    public ResponseEntity<Response> getPerson() {
        List<NhanVien> books = nhanVienRepo.findAllA();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Nhan vien",200, books));
    }

    //
//
    @PutMapping("/{id}")
    public ResponseEntity<Response> edit(@PathVariable("id") long id,@ModelAttribute NhanVien nv) throws IOException {
        NhanVien information=nhanVienRepo.findById(id).get();

        if(nv.getNgayBatDau() != null){
            information.setNgayBatDau(nv.getNgayBatDau());
        }
        if(nv.getImageFile() != null) {
            information.setImage(nv.getImageFile().getOriginalFilename());
        }
        information.setDiaChi(nv.getDiaChi());
        information.setEmail(nv.getEmail());
        information.setGioiTinh(nv.getGioiTinh());
        information.setTen(nv.getTen());
        information.setHo(nv.getHo());
        information.setQuocTich(nv.getQuocTich());

        nv.setImageFile(null);
        information.setImageFile(null);
        nhanVienRepo.save(information);
        if(information!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Nhan vien details are",200,nv));
        return null;
    }
    //
    @PostMapping("")
    public ResponseEntity<Response> addPerson(@ModelAttribute NhanVienDTO nv) throws IOException {
        String imageName = nv.getImageFile().getOriginalFilename();
        NhanVien nhanVien=new NhanVien();
        nhanVien.setImage(imageName);
        nhanVien.setEmail(nv.getEmail());
        nhanVien.setDiaChi(nv.getDiaChi());
        nhanVien.setEmail(nv.getEmail());
        nhanVien.setGioiTinh(nv.getGioiTinh());
        nhanVien.setTen(nv.getTen());
        nhanVien.setHo(nv.getHo());
        nhanVien.setQuocTich(nv.getQuocTich());
        ChucVu chucVu=chucVuRepo.findById(nv.getIdChucVu()).get();
        PhongBan phongBan=phongBanRepo.findById(nv.getIdPhongBan()).get();
        nhanVien.setListBaoHiem(nv.getBaoHiem());
        nhanVien.setIdPhongBan(phongBan);
        nhanVien.setIdChucVu(chucVu);
        nhanVien.setNgayBatDau(nv.getNgayBatDau());
        CopyFile.saveFiles(nhanVien.getImage(), nv.getImageFile());
        nv.setImageFile(null);
       nhanVienRepo.save(nhanVien);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("person Added Successfully..",200,nv));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteBook(@PathVariable long id) {
        NhanVien book=nhanVienRepo.findById(id).get();
        book.setXoa(1);
        nhanVienRepo.save(book);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("delete person",200,book));

    }
}

package com.example.javaapi.repository;

import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.NhanVien;
import com.example.javaapi.entity.PhieuLuong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NhanVienRepo extends CrudRepository<NhanVien, Long> {
    @Query("FROM NhanVien u WHERE u.xoa is null")
    List<NhanVien> findAllA();

}

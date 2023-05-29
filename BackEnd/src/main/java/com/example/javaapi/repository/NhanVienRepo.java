package com.example.javaapi.repository;

import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.NhanVien;
import org.springframework.data.repository.CrudRepository;

public interface NhanVienRepo extends CrudRepository<NhanVien, Long> {
}

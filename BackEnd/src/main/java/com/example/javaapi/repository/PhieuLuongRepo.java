package com.example.javaapi.repository;

import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.PhieuLuong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface PhieuLuongRepo extends CrudRepository<PhieuLuong, Long> {
    @Query("SELECT u FROM PhieuLuong u WHERE month(u.thoiGian) = ?1")
    PhieuLuong findPhieuLuongByMonth(Date thoiGian);
}

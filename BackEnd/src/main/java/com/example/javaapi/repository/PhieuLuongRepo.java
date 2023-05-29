package com.example.javaapi.repository;

import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.PhieuLuong;
import org.springframework.data.repository.CrudRepository;

public interface PhieuLuongRepo extends CrudRepository<PhieuLuong, Long> {
}

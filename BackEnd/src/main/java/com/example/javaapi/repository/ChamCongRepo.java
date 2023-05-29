package com.example.javaapi.repository;

import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.ChamCong;
import org.springframework.data.repository.CrudRepository;

public interface ChamCongRepo extends CrudRepository<ChamCong, Long> {
}

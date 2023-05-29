package com.example.javaapi.repository;

import com.example.javaapi.entity.KhenThuong;
import com.example.javaapi.entity.KyLuat;
import org.springframework.data.repository.CrudRepository;

public interface KhenThuongRepo extends CrudRepository<KhenThuong, Long> {
}

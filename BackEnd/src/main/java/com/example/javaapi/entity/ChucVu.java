package com.example.javaapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chuc_vu")
@Data
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String moTa;
    private String tenChucvu;
    private Double heSoLuong;
}

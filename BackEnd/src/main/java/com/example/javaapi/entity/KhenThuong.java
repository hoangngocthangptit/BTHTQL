package com.example.javaapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "khen_thuong")
@Data
public class KhenThuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    private Double mucThuong;
    private String mieuTa;
}

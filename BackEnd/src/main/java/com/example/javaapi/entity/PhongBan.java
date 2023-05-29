package com.example.javaapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "phong_ban")
@Data
public class PhongBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    private String thongTin;
    private String mieuTa;
}

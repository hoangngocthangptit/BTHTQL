package com.example.javaapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ky_luat")
@Data
public class KyLuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tenLoi;
    private Double mucPhat;
    private String mieuTa;
}

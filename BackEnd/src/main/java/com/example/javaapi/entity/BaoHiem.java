package com.example.javaapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bao_hiem")
@Data
public class BaoHiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maSo;
    private String ten;
    private Integer mucDong;
    private String thongTin;
}

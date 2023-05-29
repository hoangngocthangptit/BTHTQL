package com.example.javaapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "phieu_luong")
@Data
public class PhieuLuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double tongSoGioLam;
    private Double tienLuong;
    private Double khenThuong;
    private Double kyLuat;
    private Double BHXH;
    private Date thoiGian;
    private String phieuLuongCol;
    @ManyToOne
    @JoinColumn(name = "id_nhan_vien") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private NhanVien idNhanVien;
}

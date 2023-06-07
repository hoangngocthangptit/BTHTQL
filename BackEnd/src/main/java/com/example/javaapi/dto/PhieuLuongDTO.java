package com.example.javaapi.dto;

import lombok.Data;

@Data
public class PhieuLuongDTO {
    private Long idNhanVien;
    private Double tongSoGioLam;

    public PhieuLuongDTO(Long idNhanVien, Double tongSoGioLam) {
        this.idNhanVien = idNhanVien;
        this.tongSoGioLam = tongSoGioLam;
    }
}

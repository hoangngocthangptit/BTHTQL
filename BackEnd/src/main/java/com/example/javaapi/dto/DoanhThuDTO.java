package com.example.javaapi.dto;

import lombok.Data;

@Data
public class DoanhThuDTO {
    private Integer thang;
    private Integer nam;
    private Double tongDoanhThu;
    private String PhongBan;

    public DoanhThuDTO(Integer thang, Double tongDoanhThu, String idPhongBan) {
        this.thang = thang;
        this.tongDoanhThu = tongDoanhThu;
        this.PhongBan =idPhongBan;
    }

    public DoanhThuDTO(Integer nam, Double tongDoanhThu) {
        this.nam = nam;
        this.tongDoanhThu = tongDoanhThu;
    }
}

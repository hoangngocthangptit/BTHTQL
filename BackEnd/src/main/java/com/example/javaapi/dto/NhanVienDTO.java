package com.example.javaapi.dto;

import com.example.javaapi.entity.BaoHiem;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class NhanVienDTO {
    private String ho;
    private String ten;
    private String email;
    private String gioiTinh;
    private String diaChi;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String quocTich;
    private Long idPhongBan;
    private Long idChucVu;
    private MultipartFile imageFile;
    private List<BaoHiem>baoHiem;
}

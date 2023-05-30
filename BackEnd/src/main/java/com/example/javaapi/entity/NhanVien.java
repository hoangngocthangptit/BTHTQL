package com.example.javaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nhan_vien")
@Data
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ho;
    private String ten;
    private String email;
    private String gioiTinh;
    private String diaChi;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String quocTich;
    @ManyToOne
    @JoinColumn(name = "id_chuc_vu") // thông qua khóa ngoại
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ChucVu idChucVu;

    @ManyToOne
    @JoinColumn(name = "id_phong_ban") // thông qua khóa ngoại
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PhongBan idPhongBan;

    @ManyToMany
    @JoinTable(
            name = "nhan_vien_bao_hiem",
            joinColumns = @JoinColumn(name = "id_nhan_vien"),
            inverseJoinColumns = @JoinColumn(name = "id_bao_hiem"))
    List<BaoHiem> listBaoHiem;
    @ManyToMany
    @JoinTable(
            name = "nhan_vien_khen_thuong",
            joinColumns = @JoinColumn(name = "id_nhan_vien"),
            inverseJoinColumns = @JoinColumn(name = "id_khen_thuong"))
    List<KhenThuong> listKhenThuong;
    @ManyToMany
    @JoinTable(
            name = "nhan_vien_ky_luat",
            joinColumns = @JoinColumn(name = "id_nhan_vien"),
            inverseJoinColumns = @JoinColumn(name = "id_ky_luat"))
    List<KyLuat> listKyLuat;
    private String image;
    @JsonIgnore
    @Transient
    private MultipartFile imageFile;



}

package com.example.javaapi.entity;

import jakarta.persistence.*;
import jnr.ffi.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "cham_cong")
@Data
public class ChamCong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double soGioLam;
    private Date ngayLam;
    private String moTa;
    @ManyToOne
    @JoinColumn(name = "id_nhan_vien") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private NhanVien idNhanVien ;
    @ManyToOne
    @JoinColumn(name = "id_phong_ban") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PhongBan idPhongBan;

}

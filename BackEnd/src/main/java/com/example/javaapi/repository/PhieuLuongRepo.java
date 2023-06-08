package com.example.javaapi.repository;

import com.example.javaapi.dto.DoanhThuDTO;
import com.example.javaapi.dto.PhieuLuongDTO;
import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.PhieuLuong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PhieuLuongRepo extends CrudRepository<PhieuLuong, Long> {
    @Query("FROM PhieuLuong u WHERE ?1 is null or month(u.thoiGian) = ?1")
    List<PhieuLuong> findPhieuLuongByMonth(Integer time);
    @Query("SELECT new com.example.javaapi.dto.DoanhThuDTO(month(p.thoiGian),SUM(p.tienLuong),nv.idPhongBan.ten) " +
            "FROM PhieuLuong p " +
            "INNER JOIN NhanVien nv ON nv.id = p.idNhanVien.id " +
            "WHERE YEAR(p.thoiGian) = 2023" +
            "GROUP BY MONTH(p.thoiGian),nv.idPhongBan.id")
    List<DoanhThuDTO> tongDoanhThuTheoThang();
    @Query("SELECT new com.example.javaapi.dto.DoanhThuDTO(year (p.thoiGian),SUM(p.tienLuong))" +
            "from PhieuLuong p group by year (p.thoiGian)")
    List<DoanhThuDTO> tongDoanhThuTheoNam();

}

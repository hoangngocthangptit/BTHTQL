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
    @Query("SELECT u FROM PhieuLuong u WHERE month(u.thoiGian) = ?1")
    PhieuLuong findPhieuLuongByMonth(Date thoiGian);
    @Query("SELECT new com.example.javaapi.dto.DoanhThuDTO(month (p.thoiGian),SUM(p.tienLuong),nv.idPhongBan.ten)" +
            "from PhieuLuong p inner join NhanVien nv " +
            "on nv.id=p.idNhanVien.id" +
            "  where year (p.thoiGian)=2023 group by nv.idPhongBan")
    List<DoanhThuDTO> tongDoanhThuTheoThang();
    @Query("SELECT new com.example.javaapi.dto.DoanhThuDTO(year (p.thoiGian),SUM(p.tienLuong))" +
            "from PhieuLuong p group by year (p.thoiGian)")
    List<DoanhThuDTO> tongDoanhThuTheoNam();

}

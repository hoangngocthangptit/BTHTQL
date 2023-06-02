package com.example.javaapi.repository;

import com.example.javaapi.dto.PhieuLuongDTO;
import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.ChamCong;
import com.example.javaapi.entity.PhieuLuong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ChamCongRepo extends CrudRepository<ChamCong, Long> {
    @Query("SELECT new com.example.javaapi.dto.PhieuLuongDTO(u.idNhanVien.id,SUM (u.soGioLam))" +
            "from ChamCong u " +
            "where month(u.ngayLam)=?1 " +
            "group by u.idNhanVien.id")
    List<PhieuLuongDTO> ChamCong(Integer thoiGian);
}

package com.example.javaapi.repository;

import com.example.javaapi.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
  Users findByEmail(String email);
  @Query(value = "select * from users u where ?1 is null or name like %?1%", nativeQuery = true)
  Page<Users> GetAll(String name, Pageable pageable);
  List<Users> findByName(String name);

}

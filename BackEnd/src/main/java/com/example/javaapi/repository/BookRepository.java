package com.example.javaapi.repository;

import com.example.javaapi.entity.Book;
import com.example.javaapi.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query( value = "select * from bookinfo where book_name like %?1%", nativeQuery = true)
    Page<Book> findByBookName(String name, Pageable pageable);
    Book findByBookName(String name);
    Book findByBookId(Long id);



}

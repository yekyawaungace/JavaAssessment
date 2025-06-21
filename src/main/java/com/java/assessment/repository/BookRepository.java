package com.java.assessment.repository;


import com.java.assessment.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByno(String bookno);
}

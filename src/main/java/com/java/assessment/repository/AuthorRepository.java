package com.java.assessment.repository;


import com.java.assessment.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByid(Long Id);
}

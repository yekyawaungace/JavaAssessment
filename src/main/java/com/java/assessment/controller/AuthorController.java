package com.java.assessment.controller;

import com.java.assessment.entity.Author;
import com.java.assessment.service.imp.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private  AuthorService authorService;
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping
    public ResponseEntity<Author> getAuthorById(@RequestParam Long Id) {
        logger.info("Fetching all author by id :  {} ", Id);
        Author author = authorService.getAuthorByID(Id);
        logger.info("Author fetched: {}", author);
        return ResponseEntity.ok(author);
    }


}

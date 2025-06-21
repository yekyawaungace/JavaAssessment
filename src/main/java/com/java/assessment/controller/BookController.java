package com.java.assessment.controller;


import com.java.assessment.entity.Author;
import com.java.assessment.entity.Book;
import com.java.assessment.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {


    @Autowired
    private IBookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Book _book){

        if (bookService.save(_book) == true)
        {
            logger.info("Book have been created Successfully : {}" , _book.getNo());
            return new ResponseEntity<String>("Save Successful", HttpStatus.CREATED);
        }else{
            logger.info("Book have been created Fail : {}" , _book.getNo());
            return new ResponseEntity<String>("Save Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Book _book){

        if (bookService.update(_book) == true)
        {
            logger.info("Book have been Updated Successfully : {}" , _book.getNo());
            return new ResponseEntity<String>("Update Successful", HttpStatus.OK);
        }else{
            logger.error("Book have been Updated Successfully : {}" , _book.getNo());
            return new ResponseEntity<String>("Update Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long  Id){

        if (bookService.delete(Id) == true)
        {
            logger.info("Book have been Delete Successfully : {}" , Id);
            return new ResponseEntity<String>("Delete Successful", HttpStatus.OK);
        }else{
            logger.error("Book Not Found : {}" , Id);
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/selectedbyid")
    public ResponseEntity<Book> selectbyId(@RequestParam Long  Id){
        Book book = bookService.selectBookbyid(Id);
        logger.info("Book selectbyId  : {}" , Id);
        if (book != null){
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        }else{
            return new ResponseEntity<Book>(book, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        logger.info("Fetching all books");
        List<Book> books = bookService.getAllBooks();
        logger.info("Book fetched: {}", books.size());
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Book>> getPaginated(@RequestParam(defaultValue = "0") int page) {
        logger.info("Fetching page {}", page);
        Page<Book> books = bookService.getPaginatedBooks(page);
        logger.info("Page size: {}", books.getContent().size());
        return ResponseEntity.ok(books);
    }

    @GetMapping("/nestedcallinganotherapifrom3rdparty")
    public ResponseEntity<Author> nestedcallinganotherapifrom3rdparty(@RequestParam long Id) {
        logger.info("Calling nested calling another api from 3rd party ...");
        Author author = bookService.callauthorbyId(Id);
        logger.info("Received from nested calling another api from 3rd party: {}", author);
        //return ResponseEntity.ok(result);

        if (author != null){
            return new ResponseEntity<Author>(author, HttpStatus.OK);
        }else{
            return new ResponseEntity<Author>(author, HttpStatus.NOT_FOUND);
        }

    }
}

package com.java.assessment.service;



import com.java.assessment.entity.Author;
import com.java.assessment.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

public interface IBookService {

    boolean save(Book _book);
    List<Book> getAllBooks();
    Book selectBookbyid(Long id);
    boolean delete(Long id);
    boolean update(Book _book);
    Page<Book> getPaginatedBooks(int page);
    //callnestedcallinganotherapifrom3rdparty
    public Author callauthorbyId(Long Id);
}

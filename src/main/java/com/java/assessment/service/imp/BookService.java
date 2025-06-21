package com.java.assessment.service.imp;


import com.java.assessment.component.Bookcomponent;
import com.java.assessment.entity.Author;
import com.java.assessment.entity.Book;
import com.java.assessment.repository.BookRepository;
import com.java.assessment.service.IBookService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    protected Bookcomponent bookComponent;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Override
    public boolean save(Book _book) {
        try {
            bookRepository.save(_book);
            return true;
        }catch (Exception e){
            logger.error("Booking saving: " + e.toString());
            return false;
        }

    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book selectBookbyid(Long id) {
        Book _book = bookRepository.findById(id).orElse(null);
        return _book;
    }

    @Override
    public boolean delete(Long id) {
        if (bookComponent.checkexisting(id) == true)
        {
            try {
                bookRepository.deleteById(id);
            }catch (Exception e){
                logger.error("Booking deleting : {}" + e.toString());
                return false;
            }
        }
        else{
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Book _book) {
        if (bookComponent.checkexisting(_book.getId()) == true)
        {
            try {
                bookRepository.save(_book);
            }catch (Exception e){
                return false;
            }
        }
        else{
            return false;
        }

        return true;
    }

    @Transactional
    public Page<Book> getPaginatedBooks(int page) {
        return bookRepository.findAll(PageRequest.of(page, 10));
    }

    @Override
    public Author callauthorbyId(Long Id) {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject("http://localhost:8080/api/author?Id=1", Author.class);

        RestTemplate restTemplate = new RestTemplate();

        // URL with query parameter
        String url = "http://localhost:8080/api/author?Id=1";

        // Call GET and map response to Author
        ResponseEntity<Author> response = restTemplate.getForEntity(url, Author.class);

        Author author = response.getBody();

        return author;

    }


}

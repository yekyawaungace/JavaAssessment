package com.java.assessment;


import com.java.assessment.component.Bookcomponent;
import com.java.assessment.entity.Author;
import com.java.assessment.repository.BookRepository;
import com.java.assessment.service.imp.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class nestedcallinganotherapifrom3rdpartyTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private Bookcomponent bookComponent;

    @Test
    void testCallAuthorById_success() {
        // Arrange
        int authorId = 1;
        Author expectedAuthor = new Author(authorId, "brown");
        String url = "http://localhost:8080/api/author?Id=" + authorId;

        when(restTemplate.getForEntity(url, Author.class))
                .thenReturn(new ResponseEntity<>(expectedAuthor, HttpStatus.OK));

        // Act
        Author actualAuthor = bookService.callauthorbyId(authorId);

        // Assert
        assertNotNull(actualAuthor);
        assertEquals(expectedAuthor.getId(), actualAuthor.getId());
        assertEquals(expectedAuthor.getName(), actualAuthor.getName());
    }
}
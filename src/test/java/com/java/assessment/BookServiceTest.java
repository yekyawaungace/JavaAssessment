package com.java.assessment;

import com.java.assessment.entity.Book;
import com.java.assessment.repository.BookRepository;
import com.java.assessment.component.Bookcomponent;
import com.java.assessment.service.imp.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks

    private BookService bookService;

    @Mock

    private BookRepository bookRepository;

    @Mock

    private Bookcomponent bookComponent;

    // ---------- Test save(Book) ----------
    @Test
    public void testSave_success() {
        Book book = new Book();
        book.setId(2L);
        book.setNo("B005");
        book.setName("Java Programming");
        book.setAuthorId(3L);
        book.setType("Education");

        when(bookRepository.save(book)).thenReturn(book);

        boolean result = bookService.save(book);

        assertTrue(result);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testSave_exceptionThrown() {

        Book book = new Book();
        book.setNo("B005");
        book.setName("Java Programming");
        book.setAuthorId(3L);
        book.setType("Education");

        when(bookRepository.save(book)).thenThrow(new RuntimeException("DB Error"));

        boolean result = bookService.save(book);

        assertFalse(result);
        verify(bookRepository, times(1)).save(book);
    }

    // ---------- Test getAllBooks() ----------
    @Test
    public void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(),
                new Book()
        );

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
    }

    // ---------- Test selectBookbyid(Long) ----------
    @Test
    public void testSelectBookById_found() {
        Book book = new Book();

        book.setId(2L);
        book.setNo("B005");
        book.setName("Java Programming");
        book.setAuthorId(3L);
        book.setType("Education");

        when(bookRepository.findById(2L)).thenReturn(Optional.of(book));

        Book result = bookService.selectBookbyid(2L);

        assertNotNull(result);
        assertEquals("B005", result.getNo());
    }

    @Test
    public void testSelectBookById_notFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        Book result = bookService.selectBookbyid(99L);

        assertNull(result);
    }

    // ---------- Test delete(Long) ----------
    @Test
    public void testDelete_success() {
        when(bookComponent.checkexisting(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        boolean result = bookService.delete(1L);

        assertTrue(result);
    }

    @Test
    public void testDelete_notExists() {
        when(bookComponent.checkexisting(99L)).thenReturn(false);

        boolean result = bookService.delete(99L);

        assertFalse(result);
        verify(bookRepository, never()).deleteById(any());
    }

    @Test
    public void testDelete_exception() {
        when(bookComponent.checkexisting(1L)).thenReturn(true);
        doThrow(new RuntimeException("DB Error")).when(bookRepository).deleteById(1L);

        boolean result = bookService.delete(1L);

        assertFalse(result);
    }

    // ---------- Test update(Book) ----------
    @Test
    public void testUpdate_success() {
        Book book = new Book();
        book.setId(2L);
        book.setNo("B005");
        book.setName("Java Programming");
        book.setAuthorId(3L);
        book.setType("Education");

        when(bookComponent.checkexisting(2L)).thenReturn(true);
        when(bookRepository.save(book)).thenReturn(book);

        boolean result = bookService.update(book);

        assertTrue(result);
    }

    @Test
    public void testUpdate_notExists() {
        Book book = new Book();

        book.setId(2L);
        book.setNo("B005");
        book.setName("Java Programming");
        book.setAuthorId(3L);
        book.setType("Education");

        when(bookComponent.checkexisting(2L)).thenReturn(false);

        boolean result = bookService.update(book);

        assertFalse(result);
        verify(bookRepository, never()).save(any());
    }

    @Test
    public void testUpdate_exception() {
        Book book = new Book();

        book.setId(3L);
        book.setNo("B005");
        book.setName("Java Programming");
        book.setAuthorId(3L);
        book.setType("Education");

        when(bookComponent.checkexisting(3L)).thenReturn(true);
        when(bookRepository.save(book)).thenThrow(new RuntimeException());

        boolean result = bookService.update(book);

        assertFalse(result);
    }

    // ---------- Test getPaginatedBooks(int page) ----------
    @Test
    public void testGetPaginatedBooks() {
        int page = 0;
        List<Book> bookList = Arrays.asList(new Book(), new Book());
        Page<Book> bookPage = new PageImpl<>(bookList);

        when(bookRepository.findAll(PageRequest.of(page, 10))).thenReturn(bookPage);

        Page<Book> result = bookService.getPaginatedBooks(page);

        assertEquals(2, result.getContent().size());
    }
}

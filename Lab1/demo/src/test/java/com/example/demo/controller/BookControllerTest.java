package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    private BookController bookController;

    @BeforeEach
    public void setUp() {
        bookController = new BookController(bookService);
    }

    @Test
    void TestGetBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "The Book1", "Fiction", 9, "A great book"));
        books.add(new Book(2L, "The Book2", "Fiction", 9, "A great book"));
        when(bookService.getBooksFromRepo()).thenReturn(books);

        ResponseEntity<List<Book>> response = bookController.getBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(bookService, times(1)).getBooksFromRepo();
    }

    @Test
    void getBook() {
        long bookId = 1L;
        when(bookService.getBookFromRepo(bookId)).thenReturn(new Book(1L, "The Book1", "Fiction", 9, "A great book"));

        ResponseEntity<Book> response = bookController.getBook(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("The Book1", response.getBody().getName());
        verify(bookService, times(1)).getBookFromRepo(bookId);
    }

    @Test
    void createBook() {
        Book book = new Book(1L, "The Book1", "Fiction", 9, "A great book");
        when(bookService.insertBookIntoRepo(any(Book.class))).thenReturn(1L);

        ResponseEntity<Long> response = bookController.createBook(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody());
        verify(bookService, times(1)).insertBookIntoRepo(any(Book.class));
    }

    @Test
    void TestDeleteBook() {
        long bookId = 1L;
        when(bookService.removeBookFromRepo(bookId)).thenReturn(bookId);

        ResponseEntity<Long> response = bookController.deleteBook(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookId, response.getBody());
        verify(bookService, times(1)).removeBookFromRepo(bookId);
    }

    @Test
    void updateBook() {
        Book book = new Book(1L, "The Book1", "Fiction", 9, "A great book");
        when(bookService.updateBookInRepo(any(Book.class))).thenReturn(1L);

        ResponseEntity<Long> response = bookController.updateBook(book);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody());
        verify(bookService, times(1)).updateBookInRepo(any(Book.class));
    }
}
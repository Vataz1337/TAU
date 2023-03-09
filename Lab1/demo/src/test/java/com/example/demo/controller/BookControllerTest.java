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
    }

    @Test
    void createBook() {
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
    }
}
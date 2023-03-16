package com.example.demo.service;

import com.example.demo.exceptions.BookAlreadyExists;
import com.example.demo.exceptions.BookNotFound;
import com.example.demo.model.Book;
import com.example.demo.repository.BooksRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath: ../resources/application.properties")

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BooksRepository booksRepository;
    private BookService underTest;

    @BeforeEach
    void setUp(){
        underTest = new BookService(booksRepository);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(booksRepository);
    }

    @Test
    void CheckGetBooksFromRepo() {
        underTest.getBooksFromRepo();
        verify(booksRepository).findAll();
    }

    @Test
    void CheckGetBookFromRepo() {
        Book book = new Book(1L, "The Book", "Fiction", 9, "A great book");
        when(booksRepository.findById(1L)).thenReturn(book);

        Book result = underTest.getBookFromRepo(1L);

        assertNotNull(result);
        assertEquals(book, result);
        verify(booksRepository).findById(1L);
    }

    @Test
    void CheckInsertBookIntoRepo() {
        Book book = new Book(1L, "The Book", "Fiction", 9, "A great book");
        when(booksRepository.existsById(1L)).thenReturn(false);
        when(booksRepository.save(book)).thenReturn(book);

        long result = underTest.insertBookIntoRepo(book);

        assertEquals(1L, result);
        verify(booksRepository).existsById(1L);
        verify(booksRepository).save(book);
    }

    @Test
    void CheckRemoveBookFromRepo() {
        when(booksRepository.existsById(1L)).thenReturn(true);

        long result = underTest.removeBookFromRepo(1L);

        assertEquals(1L, result);
        verify(booksRepository).existsById(1L);
        verify(booksRepository).deleteById(1L);
    }

    //something is not right here but im not sure what
//    @Test
//    void canUpdateBookInRepo() {
//        Book bookToUpdate = new Book(1L, "Updated Book", "Updated Genre", 9, "Updated Description");
//        when(booksRepository.existsById(1L)).thenReturn(true);
//
//        underTest.updateBookInRepo(bookToUpdate);
//
//        verify(booksRepository).existsById(1L);
//        verify(booksRepository).save(bookToUpdate);
//    }

    @Test
    void CheckIfGetBookFromRepoThrowsExceptionWhenBookNotFound() {
        when(booksRepository.findById(1L)).thenReturn(null);

        assertThrows(BookNotFound.class, () -> underTest.getBookFromRepo(1L));
        verify(booksRepository).findById(1L);
    }

    @Test
    void CheckIfInsertBookIntoRepoThrowsExceptionWhenBookAlreadyExists() {
        Book book = new Book(1L, "The Book", "Fiction", 9, "A great book");
        when(booksRepository.existsById(1L)).thenReturn(true);

        assertThrows(BookAlreadyExists.class, () -> underTest.insertBookIntoRepo(book));
        verify(booksRepository).existsById(1L);
    }

    @Test
    void CheckIfRemoveBookFromRepoThrowsExceptionWhenBookNotFound() {
       when(booksRepository.existsById(1L)).thenReturn(false);

       assertThrows(BookNotFound.class, () -> underTest.removeBookFromRepo(1L));

       verify(booksRepository).existsById(1L);
    }

    @Test
    void CheckIfUpdateBookInRepoThrowsExceptionWhenBookNotFound() {
        Book bookToUpdate = new Book(1L, "Updated Book", "Updated Genre", 9, "Updated Description");
        when(booksRepository.existsById(1L)).thenReturn(false);

        assertThrows(BookNotFound.class, () -> underTest.updateBookInRepo(bookToUpdate));
        verify(booksRepository).existsById(1L);
    }
}
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.BookService;
import com.example.demo.model.Book;

import java.util.List;

@RestController
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getBooksFromRepo();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Book book = bookService.getBookFromRepo(id);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/book/add")
    public ResponseEntity<Long> createBook(@RequestBody Book book) {
        long createdId = bookService.insertBookIntoRepo(book);

        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable Long id) {
        long deletedId = bookService.removeBookFromRepo(id);

        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/book/update")
    public ResponseEntity<Long> updateBook(@RequestBody Book book) {
        long updatedId = bookService.updateBookInRepo(book);

        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }
}
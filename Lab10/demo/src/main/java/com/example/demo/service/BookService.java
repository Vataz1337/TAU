package com.example.demo.service;

import com.example.demo.exceptions.BookAlreadyExists;
import com.example.demo.exceptions.BookNotFound;
import com.example.demo.model.Book;
import com.example.demo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository){
        this.booksRepository = booksRepository;
    }

    public List<Book> getBooksFromRepo(){
        return booksRepository.findAll();
    }


    public Book getBookFromRepo(long id) {
        Book book = booksRepository.findById(id);

        if (book == null) {
            throw new BookNotFound();
        }
        return book;
    }

    public long insertBookIntoRepo(Book book) {
        if (booksRepository.existsById(book.getId())){
            throw new BookAlreadyExists();
        }
        return booksRepository.save(book).getId();
    }

    public long removeBookFromRepo(long id) {
        if (booksRepository.existsById(id)) {
            booksRepository.deleteById(id);
            return id;
        } else {
            throw new BookNotFound();
        }
    }

    public long updateBookInRepo(Book book) {
        if (booksRepository.existsById(book.getId())) {
            Book bookToUpdate = new Book();
            bookToUpdate.setId(book.getId());
            bookToUpdate.setName(book.getName());
            bookToUpdate.setGenre(book.getGenre());
            bookToUpdate.setPrice(book.getPrice());
            bookToUpdate.setDescription(book.getDescription());
            return booksRepository.save(bookToUpdate).getId();
        } else {
            throw new BookNotFound();
        }
    }
}
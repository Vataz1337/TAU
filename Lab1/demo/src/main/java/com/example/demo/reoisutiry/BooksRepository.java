package com.example.demo.reoisutiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Book;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {
    Book findById(long id);
    List<Book> findAll();
}

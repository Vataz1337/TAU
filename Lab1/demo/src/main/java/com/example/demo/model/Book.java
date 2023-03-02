package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="books", schema = "bartosh_crud")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "genre")
    private String genre;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book books = (Book) o;
        return Objects.equals(id, books.id) && price == books.price && Objects.equals(name, books.name) && Objects.equals(genre, books.genre) && Objects.equals(description, books.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, price, description);
    }
}
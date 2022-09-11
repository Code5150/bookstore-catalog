package com.code5150.bookstorecatalog.dto;

import com.code5150.bookstorecatalog.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookDTO {
    private UUID id;
    private String name;
    private String description;
    private String author;
    private String genre;
    private LocalDate editionYear;
    private Double price;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.editionYear = book.getEditionYear();
        this.price = book.getPrice();
        this.author = book.getAuthor();
        this.genre = book.getGenre();
    }
}

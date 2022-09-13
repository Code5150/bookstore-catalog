package com.code5150.bookstorecatalog.entity;

import com.code5150.bookstorecatalog.dto.BookDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "book")
@ToString
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    @Getter
    protected UUID id;

    @Column(name = "name", length = 512)
    @Getter
    @Setter
    private String name;

    @Column(name = "description")
    @Getter
    @Setter
    @Lob
    private String description;

    @Column(name = "author", length = 512)
    @Getter
    @Setter
    private String author;

    @Column(name = "genre", length = 512)
    @Getter
    @Setter
    private String genre;

    @Column(name = "edition_year")
    @Getter
    @Setter
    private LocalDate editionYear;

    @Column(name = "price")
    @Getter
    @Setter
    private Double price;

    @Version
    @Getter
    @Setter
    private Integer version;

    public Book(BookDTO bookDTO) {
        this.id = bookDTO.getId();
        this.name = bookDTO.getName();
        this.description = bookDTO.getDescription();
        this.editionYear = bookDTO.getEditionYear();
        this.price = bookDTO.getPrice();
        this.author = bookDTO.getAuthor();
        this.genre = bookDTO.getGenre();
        this.version = bookDTO.getVersion();
    }
}

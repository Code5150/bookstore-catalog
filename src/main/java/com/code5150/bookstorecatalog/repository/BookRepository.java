package com.code5150.bookstorecatalog.repository;

import com.code5150.bookstorecatalog.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}

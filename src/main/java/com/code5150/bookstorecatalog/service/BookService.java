package com.code5150.bookstorecatalog.service;

import com.code5150.bookstorecatalog.dto.BookDTO;
import com.code5150.bookstorecatalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    protected BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(BookDTO::new).collect(Collectors.toList());
    }
}

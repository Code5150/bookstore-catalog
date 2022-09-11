package com.code5150.bookstorecatalog.controller;

import com.code5150.bookstorecatalog.dto.BookDTO;
import com.code5150.bookstorecatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/")
public class BookController {
    protected BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }

    @GetMapping({"/newBook", "/editBook", "/addBook"})
    public String redirectFromForms() {
        return "redirect:/";
    }

    @PostMapping("/newBook")
    public String newBook(Model model) {
        model.addAttribute("book", new BookDTO());
        return "editor";
    }

    @PostMapping("/editBook")
    public String editBook(@RequestParam("id") String id, Model model) {
        model.addAttribute("book", bookService.findBook(UUID.fromString(id)));
        return "editor";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("book") BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
        return "redirect:/";
    }
}

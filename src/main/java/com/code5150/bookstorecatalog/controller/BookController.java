package com.code5150.bookstorecatalog.controller;

import com.code5150.bookstorecatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        return "index";
    }

    @GetMapping("/newBook")
    public String newBook(Model model) {
        return "editor";
    }

    @PostMapping("/addBook")
    public ModelAndView addBook(Model model) {
        return new ModelAndView("redirect:/", model.asMap());
    }
}

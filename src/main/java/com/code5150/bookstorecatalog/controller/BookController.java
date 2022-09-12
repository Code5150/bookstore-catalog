package com.code5150.bookstorecatalog.controller;

import com.code5150.bookstorecatalog.dto.BookDTO;
import com.code5150.bookstorecatalog.service.BookService;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    /*@GetMapping({"/newBook", "/editBook", "/addBook"})
    public String redirectFromForms() {
        return "redirect:/";
    }*/

    @GetMapping("/newBook")
    public String newBook(Model model) {
        model.addAttribute("book", new BookDTO());
        return "editor";
    }

    @GetMapping("/editBook")
    public String editBook(@RequestParam("id") String id, Model model) {
        model.addAttribute("book", bookService.findBook(UUID.fromString(id)));
        return "editor";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("book") BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
        return "redirect:/";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") String id) {
        bookService.deleteBook(UUID.fromString(id));
        return "redirect:/";
    }

    @GetMapping("/uploadCsv")
    public String uploadCsvPage(Model model) {
        if (!model.containsAttribute("loadFailed")) {
            model.addAttribute("loadFailed", false);
        }
        return "upload";
    }

    @PostMapping("/uploadCsvFile")
    public String uploadCsv(@RequestParam("file") MultipartFile file, RedirectAttributes attr) {
        if (file.isEmpty()) {
            attr.addFlashAttribute("loadFailed", true);
            attr.addFlashAttribute("errorCause", "Файл не содержит строк");
        } else {
            try(var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                var csvToDto = new CsvToBeanBuilder<BookDTO>(reader)
                        .withType(BookDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                var csvResults = csvToDto.parse();
                bookService.saveAll(csvResults);

            } catch (IOException e) {
                attr.addFlashAttribute("loadFailed", true);
                attr.addFlashAttribute("errorCause", "Не удалось загрузить файл");
            }
        }
        return "redirect:/uploadCsv";
    }
}

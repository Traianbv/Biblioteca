package com.library.controller;

import com.library.service.BookService;
import com.library.service.ExportService;
import com.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ExportService exportService;

    @GetMapping("/")
    public String home(Model model, 
                      @RequestParam(required = false) String searchTerm,
                      @RequestParam(required = false) String category) {
        
        if (searchTerm != null && !searchTerm.isEmpty()) {
            model.addAttribute("books", bookService.searchBooks(searchTerm));
        } else if (category != null && !category.isEmpty()) {
            model.addAttribute("books", bookService.getBooksByCategory(category));
        } else {
            model.addAttribute("books", bookService.getAllBooks());
        }
        
        model.addAttribute("categories", bookService.getAllCategories());
        return "home";
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportBooks(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String category) throws IOException {
        
        List<Book> books;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            books = bookService.searchBooks(searchTerm);
        } else if (category != null && !category.isEmpty()) {
            books = bookService.getBooksByCategory(category);
        } else {
            books = bookService.getAllBooks();
        }

        ByteArrayInputStream in = exportService.exportBooksToExcel(books);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=carti.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }
} 
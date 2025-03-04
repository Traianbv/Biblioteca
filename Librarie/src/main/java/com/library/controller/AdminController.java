package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "admin/panel";
    }

    @GetMapping("/book/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", bookService.getAllCategories());
        return "admin/book-form";
    }

    @PostMapping("/book/save")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", bookService.getAllCategories());
            return "admin/book-form";
        }

        try {
            bookService.saveBook(book);
            return "redirect:/admin";
        } catch (RuntimeException e) {
            model.addAttribute("categories", bookService.getAllCategories());
            model.addAttribute("isbnError", e.getMessage());
            return "admin/book-form";
        }
    }

    @GetMapping("/book/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("categories", bookService.getAllCategories());
        return "admin/book-form";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/admin";
    }
} 
package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    public List<Book> searchBooks(String searchTerm) {
        return bookRepository.search(searchTerm);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carte negăsită"));
    }

    public void saveBook(Book book) {
        // Verifică dacă este o carte nouă sau o editare
        if (book.getId() == null) {
            // Carte nouă - verifică dacă ISBN-ul există deja
            if (bookRepository.existsByIsbn(book.getIsbn())) {
                throw new RuntimeException("Există deja o carte cu acest ISBN!");
            }
        } else {
            // Editare carte - verifică dacă ISBN-ul există la altă carte
            if (bookRepository.existsByIsbnAndIdNot(book.getIsbn(), book.getId())) {
                throw new RuntimeException("Există deja o carte cu acest ISBN!");
            }
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<String> getAllCategories() {
        return Arrays.asList(
            "Literatură", 
            "Științe", 
            "Ficțiune", 
            "Istorie", 
            "Artă",
            "Geografie"
        );
    }
} 
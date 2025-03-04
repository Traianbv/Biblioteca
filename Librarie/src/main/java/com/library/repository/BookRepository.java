package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(String category);
    
    boolean existsByIsbn(String isbn);
    
    @Query("SELECT COUNT(b) > 0 FROM Book b WHERE b.isbn = :isbn AND b.id != :id")
    boolean existsByIsbnAndIdNot(@Param("isbn") String isbn, @Param("id") Long id);
    
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:searchTerm% " +
           "OR b.author LIKE %:searchTerm% " +
           "OR b.isbn LIKE %:searchTerm%")
    List<Book> search(@Param("searchTerm") String searchTerm);
} 
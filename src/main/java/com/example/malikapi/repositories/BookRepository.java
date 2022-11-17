package com.example.malikapi.repositories;

import com.example.malikapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book>findByTitleContainingOrDescriptionContaining(String text, String textAgain);
}

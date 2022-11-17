package com.example.malikapi.controllers;

import com.example.malikapi.entities.Book;
import com.example.malikapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;

    @GetMapping("/book")
    public List<Book> index(){
        return repository.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable Long bookId) {
        Book book = null;
        Optional<Book> optionalBook = repository.findById(bookId);
        if(optionalBook.isPresent()){
            book = optionalBook.get();
        }

        return book;
    }

    @PostMapping("/book/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return repository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/book")
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

    @PutMapping("/book/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        // getting blog
        Book bookToUpdate = repository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    @DeleteMapping("book/{id}")
    public boolean delete(@PathVariable Long id){
        repository.deleteById(id);
        return true;
    }
}

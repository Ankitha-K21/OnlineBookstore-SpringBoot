package com.springboot.bookstore.service;

import com.springboot.bookstore.entity.Book;
import com.springboot.bookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public List<Book> getAllBooks(){
        return new ArrayList<>(bookRepo.findAll());
    }

    //Managing books
    public String addBook(Book book){
        bookRepo.save(book);
        return "Book Added";
    }

    public String deleteBook(Long id){
        bookRepo.deleteById(id);
        return "Book Deleted";
    }

    public List<Book> searchBooks(String title, String author, Integer minPrice, Integer maxPrice) {
        return bookRepo.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCaseAndPriceBetween(
                title,author,minPrice,maxPrice
        );
    }
}

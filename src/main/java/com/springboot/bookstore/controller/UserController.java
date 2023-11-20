package com.springboot.bookstore.controller;

import com.springboot.bookstore.entity.Book;
import com.springboot.bookstore.entity.OrderStatus;
import com.springboot.bookstore.entity.Orders;
import com.springboot.bookstore.service.BookService;
import com.springboot.bookstore.service.JwtService;
import com.springboot.bookstore.service.OrderService;
import com.springboot.bookstore.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/bookstore/user")
public class UserController {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/userProfile")
    public String userProfile(){
        return "User Profile";
    }

    //View Books
    @GetMapping("/buyBooks")
    public List<Book> viewBooks(){

        return bookService.getAllBooks();
    }

    //Place order
    @PostMapping("/order/{bookId}")
    public void createOrder(@PathVariable Long bookId, HttpServletRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1].trim();
        String username = jwtService.extractUsername(token);
//        System.out.println(username);

        Orders order = new Orders();
        order.setBookId(bookId);
        order.setUserId(usersService.getUserIdByUsername(username));
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.ORDERED);

        orderService.saveOrder(order);
    }

    //Search books
    @PostMapping("/searchBooks")
    public List<Book> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice
            ){
        return bookService.searchBooks(title,author,minPrice,maxPrice);

    }
}

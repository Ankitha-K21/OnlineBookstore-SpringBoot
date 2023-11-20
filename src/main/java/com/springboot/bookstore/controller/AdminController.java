package com.springboot.bookstore.controller;

import com.springboot.bookstore.entity.Book;
import com.springboot.bookstore.entity.Orders;
import com.springboot.bookstore.entity.Users;
import com.springboot.bookstore.service.BookService;
import com.springboot.bookstore.service.OrderService;
import com.springboot.bookstore.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/bookstore/admin")
public class AdminController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @GetMapping("/adminProfile")
    public String adminProfile(){
        return "Admin Profile";
    }

    //Managing users
    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Users userInfo) {
        return usersService.addUser(userInfo);
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody Users userInfo) {
        return usersService.deleteUser(userInfo.getId());
    }

    //Managing books
    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestBody Book book) {

        return bookService.deleteBook(book.getId());
    }

    //Managing orders
    @PostMapping("/processOrder/{orderId}")
    public String processOrder(@PathVariable("orderId") Long orderId){
        Orders order = orderService.getOrderById(orderId);
        return orderService.processOrder(order);
    }

    @PostMapping("/deliveredOrder/{orderId}")
    public String deliveredOrder(@PathVariable("orderId") Long orderId){
        Orders order = orderService.getOrderById(orderId);
        return orderService.deliveredOrder(order);
    }
}

package com.springboot.bookstore.service;

import com.springboot.bookstore.entity.OrderStatus;
import com.springboot.bookstore.entity.Orders;
import com.springboot.bookstore.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public Orders getOrderById(Long id){
        return orderRepo.findById(id).get();
    }

    public String saveOrder(Orders order) {
        orderRepo.save(order);
        return "Order placed";
    }

    public String processOrder(Orders order) {
            order.setOrderStatus(OrderStatus.PROCESSING);
            orderRepo.save(order);
            return "Order is being processed";
    }

    public String deliveredOrder(Orders order) {
        order.setOrderStatus(OrderStatus.DELIVERED);
        orderRepo.save(order);
        return "Order is out for delivery";
    }
}

package com.zero.restaurant.Service;

import com.zero.restaurant.Models.Order;
import com.zero.restaurant.Request.OrderRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface OrderService {

    Order addNewOrders(@RequestBody final OrderRequest orderRequest);
    List<Order> checkForOrders();
    List<Order> findOrderById( @PathVariable final String restaurantId);
    List<Order> findOrderByIds( @PathVariable final String userId);
}

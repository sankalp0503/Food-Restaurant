package com.zero.restaurant.Controller;



import com.zero.restaurant.Models.Order;
import com.zero.restaurant.Request.OrderRequest;
import com.zero.restaurant.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;


    @PostMapping
    public ResponseEntity<Order> addOrders(@Valid @RequestBody final OrderRequest orderRequest) {
       Order orderSaved= orderService.addNewOrders(orderRequest);
        return ResponseEntity.ok().body(orderSaved);
    }

    @GetMapping
    public List<Order> checkOrders()
    {
        return  orderService.checkForOrders();
    }

    @GetMapping("/{restaurantId}")
    public List<Order> findOrderAsRestaurant(@PathVariable final String restaurantId){
        return orderService.findOrderById(restaurantId);
    }

    @GetMapping("/order/{userId}")
    public List<Order> findOrderAsUser(@PathVariable final String userId){
        return orderService.findOrderByIds(userId);
    }


}

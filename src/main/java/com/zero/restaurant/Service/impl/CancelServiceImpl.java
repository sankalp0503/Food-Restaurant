package com.zero.restaurant.Service.impl;


import com.zero.restaurant.CustomExceptions.RequestException;
import com.zero.restaurant.Models.Menu;
import com.zero.restaurant.Models.Order;
import com.zero.restaurant.Repository.MenuRepository;
import com.zero.restaurant.Repository.OrderRepository;
import com.zero.restaurant.Service.CancelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CancelServiceImpl implements CancelService {


    private OrderRepository orderRepository;

    private MenuRepository menuRepository;

    private final String cancel="CANCELLED";


    public void cancelNewOrders(@PathVariable final String Id) {
        Optional<Order> orderCancel = orderRepository.findById(Id);
        if (orderCancel.isPresent()) {

            Order order = orderCancel.get();

            Optional<Menu> foodOptional = menuRepository
                    .findById(order.getFoodId());
            if (foodOptional.isPresent()) {

                Menu food = foodOptional.get();
                food.setQuantity(food.getQuantity().add(order.getQuantity()));


                if(order.getStatus().equals(cancel)){
                    throw new RuntimeException("order is already cancelled");
                }

                menuRepository.save(food);


                order.setQuantity(null);
                order.setStatus("CANCELLED");

                orderRepository.save(order);

            }
            else{
                throw new RequestException("The requested food order is not present in followed orders");
            }
        }
        else{
            throw new RequestException("No such order is done");
        }
    }
}

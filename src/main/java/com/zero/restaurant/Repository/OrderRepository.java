package com.zero.restaurant.Repository;

import com.zero.restaurant.Models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {

    List<Order> findOrdersByRestaurantId(String restaurantId);

    List<Order> findOrdersByUserId(String userId);
}

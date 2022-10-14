package com.zero.restaurant.Repository;

import com.zero.restaurant.Models.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuRepository extends MongoRepository<Menu,String> {

    List<Menu> findMenuByRestaurantId(String restaurantId);
}

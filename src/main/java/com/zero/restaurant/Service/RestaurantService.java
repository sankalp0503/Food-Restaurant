package com.zero.restaurant.Service;

import com.zero.restaurant.Models.Restaurant;
import com.zero.restaurant.Request.RestaurantRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface RestaurantService {

    Restaurant addRestaurant(@RequestBody RestaurantRequest rest);
    List<Restaurant> getAllRestaurants();
    Restaurant findRestaurants(@PathVariable final String restaurantId);
    ResponseEntity delete(@PathVariable final String restaurantId);
     void update(@PathVariable final String restaurantId, @RequestBody RestaurantRequest restaurant);
}

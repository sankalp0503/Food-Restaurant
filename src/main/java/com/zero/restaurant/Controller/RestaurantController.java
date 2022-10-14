package com.zero.restaurant.Controller;


import com.zero.restaurant.Models.Restaurant;
import com.zero.restaurant.Request.RestaurantRequest;
import com.zero.restaurant.Service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody RestaurantRequest rest){
        Restaurant restSaved = restaurantService.addRestaurant(rest);
        return ResponseEntity.ok().body(restSaved);
    }



    @GetMapping
    public List<Restaurant> getAllRestaurant(){
        return restaurantService.getAllRestaurants();
    }



    @GetMapping("/{restaurantId}")
    public Restaurant findRestaurantsById(@PathVariable final String restaurantId){
        return restaurantService.findRestaurants(restaurantId);
    }


    @DeleteMapping("/{restaurantId}")
    public void deleteRestaurantsById(@PathVariable final String restaurantId) {
        restaurantService.delete(restaurantId);

    }


    @PutMapping("/{restaurantId}")
    public void updateUserById(@PathVariable final String restaurantId, @RequestBody RestaurantRequest restaurant){
        restaurantService.update(restaurantId,restaurant) ;

    }
}

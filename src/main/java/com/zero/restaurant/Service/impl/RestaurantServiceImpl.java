package com.zero.restaurant.Service.impl;


import com.zero.restaurant.Models.Restaurant;
import com.zero.restaurant.Repository.RestaurantRepository;
import com.zero.restaurant.Request.RestaurantRequest;
import com.zero.restaurant.Service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Service
public class RestaurantServiceImpl implements RestaurantService {


    private RestaurantRepository restaurantRepository;


    //POST
    public Restaurant addRestaurant(@RequestBody RestaurantRequest rest){
        Optional<Restaurant>restauOptional=restaurantRepository
                .findRestaurantsByAddress((rest.getAddress()));
        if(restauOptional.isPresent()){
            throw new RuntimeException("Restaurant with this address is  already present");
        }
             Optional<Restaurant> restOptional=restaurantRepository
                .findRestaurantByPhoneNo(rest.getPhoneNo()); // taking anothr field as unique key so that if same with another goes it doe not add as new data
               if(restOptional.isPresent()){
                 throw new RuntimeException("Restaurant with this phone no is  already present");
                }

        Restaurant restaurant=new Restaurant();
        restaurant.setPhoneNo(rest.getPhoneNo());
        restaurant.setRestaurantName(rest.getRestaurantName());
        restaurant.setAddress(rest.getAddress());
        return restaurantRepository.save(restaurant);
    }




    //GET
    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }



    //GETbyID
    public Restaurant findRestaurants(@PathVariable final String restaurantId){
        return restaurantRepository.findById(restaurantId).orElseGet(Restaurant::new);
    }


    //DELETE
    public ResponseEntity delete(@PathVariable final String restaurantId) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            restaurantRepository.deleteById(restaurantId);
            return ResponseEntity.ok("Success");
        } else {
            return  ResponseEntity.badRequest().body("the restaurant with id:" + restaurantId + "was not found");
        }
    }


    //PUT
    public void update(@PathVariable final String restaurantId, @RequestBody RestaurantRequest restaurant){
        Restaurant savedRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find userby id %s ",restaurantId)));

        savedRestaurant.setPhoneNo(restaurant.getPhoneNo());
        savedRestaurant.setAddress(restaurant.getAddress());



        restaurantRepository.save(savedRestaurant);

    }
}

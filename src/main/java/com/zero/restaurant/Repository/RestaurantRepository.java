package com.zero.restaurant.Repository;

import com.zero.restaurant.Models.Restaurant;
import com.zero.restaurant.Models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant,String> {

    Optional<Restaurant> findRestaurantByPhoneNo(String phoneNo);
    Optional<Restaurant> findRestaurantsByAddress(String address);
}

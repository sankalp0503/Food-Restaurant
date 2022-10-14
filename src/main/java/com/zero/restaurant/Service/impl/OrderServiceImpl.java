package com.zero.restaurant.Service.impl;



import com.zero.restaurant.CustomExceptions.RequestException;
import com.zero.restaurant.Models.Menu;
import com.zero.restaurant.Models.Order;
import com.zero.restaurant.Models.Restaurant;
import com.zero.restaurant.Models.Users;
import com.zero.restaurant.Repository.MenuRepository;
import com.zero.restaurant.Repository.OrderRepository;
import com.zero.restaurant.Repository.RestaurantRepository;
import com.zero.restaurant.Repository.UserRepository;
import com.zero.restaurant.Request.OrderRequest;
import com.zero.restaurant.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {


    private OrderRepository orderRepository;

    private RestaurantRepository restaurantRepository;

    private MenuRepository menuRepository;

    private UserRepository userRepository;


    public Order addNewOrders(@RequestBody final OrderRequest orderRequest){
        Optional<Restaurant> restaurantOptional = restaurantRepository
                .findById(orderRequest.getRestaurantId());
        if (restaurantOptional.isPresent()) {

            Optional<Menu> foodOptional = menuRepository
                    .findById(orderRequest.getFoodId());
            if (foodOptional.isPresent()) {

                Optional<Users> userOptional = userRepository
                        .findById(orderRequest.getUserId());
                if (userOptional.isPresent()) {

                    Users user = userOptional.get();
                    Menu food = foodOptional.get();
                    Restaurant rest = restaurantOptional.get();


                    if (food.getQuantity().intValue() > orderRequest.getQuantity().intValue()) {
                        food.setQuantity(food.getQuantity().subtract(orderRequest.getQuantity()));

                        menuRepository.save(food);

                        Order order = new Order();
                        order.setRestaurantId(orderRequest.getRestaurantId());
                        order.setFoodId(orderRequest.getFoodId());
                        order.setUserId(orderRequest.getUserId());
                        order.setQuantity(orderRequest.getQuantity());
                        order.setRestaurantName(rest.getRestaurantName());
                        order.setRestaurantPhoneNo(rest.getPhoneNo());
                        order.setFoodName(food.getName());
                        order.setPrices(food.getPrice());
                        order.setUserName(user.getUserName());
                        order.setUserPhoneNo(user.getUserPhoneNo());
                        order.setUserAddress(user.getUserAddress());
                        order.setStatus("Order is placed");
                        return orderRepository.save(order);
                    }
                    else{
                        throw new RequestException("Required quantity is not available");
                    }

                }
                else{
                    throw new RequestException("User information is necessary");
                }
            }
            else{
                throw new RequestException("Requested Food is not available");
            }
        }
        else {
            throw new RequestException("Requested Restaurant is not present");
        }

    }



    public List<Order> checkForOrders() {
        return orderRepository.findAll();
    }


    //GetByRestaurantId
    public List<Order> findOrderById( @PathVariable final String restaurantId) {
        return orderRepository.findOrdersByRestaurantId(restaurantId);
    }

    //GetByUserId
    public List<Order> findOrderByIds( @PathVariable final String userId) {
        return orderRepository.findOrdersByUserId(userId);}



}

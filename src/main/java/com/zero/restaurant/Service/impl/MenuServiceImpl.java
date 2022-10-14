package com.zero.restaurant.Service.impl;

import com.zero.restaurant.Models.Menu;
import com.zero.restaurant.Repository.MenuRepository;
import com.zero.restaurant.Repository.RestaurantRepository;
import com.zero.restaurant.Request.MenuRequest;
import com.zero.restaurant.Service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {


    private MenuRepository menuRepository;

    private RestaurantRepository restaurantRepository;

    //POST
    public Menu addFood(@RequestBody MenuRequest menu){

        Menu food= new Menu();

        food.setName(menu.getName());
        food.setDescription(menu.getDescription());
        food.setQuantity(menu.getQuantity());
        food.setRestaurantId(menu.getRestaurantId());
        food.setPrice(menu.getPrice());

        return menuRepository.save(food);
    }



    //GET
    public List<Menu> getAllFood(){
        return menuRepository.findAll();
    }



    //GETbyID
    public  List<Menu>findMenu(@PathVariable final String restaurantId) {

            return  menuRepository.findMenuByRestaurantId(restaurantId);


    }

    //GETbyID
    public  Menu findFood(@PathVariable final String foodId){
        return menuRepository.findById(foodId).orElseGet(Menu::new);
    }


    //DELETE
    public ResponseEntity delete(@PathVariable final String foodId) {

        Optional<Menu> food = menuRepository.findById(foodId);
        if (food.isPresent()) {
            restaurantRepository.deleteById(foodId);
            return ResponseEntity.ok("Success");
        } else {
            return  ResponseEntity.badRequest().body("the restaurant with id:" + foodId + "was not found");
        }
    }


    //PUT
    public void update(@PathVariable final String foodId, @RequestBody MenuRequest menu){
        Menu savedFood = menuRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find foodby id %s ",foodId)));

        savedFood.setDescription(menu.getDescription());
        savedFood.setQuantity(menu.getQuantity());
        savedFood.setRestaurantId(menu.getRestaurantId());



        menuRepository.save(savedFood);

    }
}

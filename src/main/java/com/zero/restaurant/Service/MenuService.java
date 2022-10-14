package com.zero.restaurant.Service;

import com.zero.restaurant.Models.Menu;
import com.zero.restaurant.Request.MenuRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface MenuService {


    Menu addFood(@RequestBody MenuRequest menu);
    List<Menu> getAllFood();
    List<Menu>findMenu(@PathVariable final String restaurantId);
    Menu findFood(@PathVariable final String foodId);
    ResponseEntity delete(@PathVariable final String foodId);
    void update(@PathVariable final String foodId, @RequestBody MenuRequest menu);
}

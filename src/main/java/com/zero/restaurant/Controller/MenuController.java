package com.zero.restaurant.Controller;


import com.zero.restaurant.Models.Menu;
import com.zero.restaurant.Request.MenuRequest;
import com.zero.restaurant.Service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/menu")
@AllArgsConstructor
public class MenuController {

    private MenuService menuService;


    @PostMapping
    public ResponseEntity<Menu> addMenu(@Valid @RequestBody MenuRequest menu){
        Menu menuSaved = menuService.addFood(menu);
        return ResponseEntity.ok().body(menuSaved);
    }



    @GetMapping
    public List<Menu> findMenu(){
        return menuService.getAllFood();
    }



    @GetMapping("/menu/{restaurantId}")
    public List<Menu> findMenuById(@PathVariable final String restaurantId){
        return menuService.findMenu(restaurantId);
    }

    @GetMapping("/{foodId}")
    public Menu findFoodById(@PathVariable final String foodId){
        return menuService.findFood(foodId);
    }


    @DeleteMapping("/{foodId}")
    public void deleteFoodById(@PathVariable final String foodId) {
        menuService.delete(foodId);

    }


    @PutMapping("/{foodId}")
    public void updateUserById(@PathVariable final String foodId, @RequestBody MenuRequest menu){
        menuService.update(foodId,menu) ;

    }

}

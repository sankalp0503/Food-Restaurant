package com.zero.restaurant.Controller;


import com.zero.restaurant.CustomExceptions.RequestException;
import com.zero.restaurant.Models.Users;
import com.zero.restaurant.Request.UserRequest;
import com.zero.restaurant.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor

public class UserController {


    private UserService userService;


    @PostMapping
    public ResponseEntity<Users> addUser(@Valid @RequestBody UserRequest user){

               log.info("users are being added");
               Users useSaved = userService.addUsers(user);
               return ResponseEntity.ok().body(useSaved);

    }



    @GetMapping
    public List<Users> findAllUsers(){

        try {
            log.info("Listing Users");
            List<Users> use = userService.getAllUsers();

            log.info("UserController findAllRestaurants() response{}", use);
            return use;
        }
        catch(RequestException e){
            throw new RequestException(e.getMessage());

        }
        catch(Exception e){
            throw new RequestException("something went wrong in controller:");
        }
    }



    @GetMapping("/{userId}")
    public Users findRestaurantsById(@PathVariable final String userId){
        return userService.findUsers(userId);
    }


    @DeleteMapping("/{userId}")
    public void deleteRestaurantsById(@PathVariable final String userId) {
        userService.delete(userId);

    }


    @PutMapping("/{userId}")
    public void updateUserById(@PathVariable final String userId, @RequestBody UserRequest user){
        userService.update(userId,user) ;

    }
}

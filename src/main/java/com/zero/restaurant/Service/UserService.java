package com.zero.restaurant.Service;

import com.zero.restaurant.Models.Users;
import com.zero.restaurant.Request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    Users addUsers(@RequestBody UserRequest user);
    List<Users> getAllUsers();
    Users findUsers(@PathVariable final String userId);
    ResponseEntity delete(@PathVariable final String userId);
    void update(@PathVariable final String userId, @RequestBody UserRequest user);

}

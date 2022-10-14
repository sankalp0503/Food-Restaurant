package com.zero.restaurant.Service.impl;


import com.zero.restaurant.CustomExceptions.RequestException;
import com.zero.restaurant.Models.Users;
import com.zero.restaurant.Repository.UserRepository;
import com.zero.restaurant.Request.UserRequest;
import com.zero.restaurant.Service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    public Users addUsers(@RequestBody  UserRequest user){

        if (user.getUserName().isEmpty() || user.getUserName().length() == 0) {
            throw new RequestException("Please type a valid user name");
        }
        if(user.getUserPhoneNo().length() != 10 || !NumberUtils.isCreatable(user.getUserPhoneNo())){
            throw new RequestException("Please enter a valid phone no xx");
        }
        if(user.getUserAddress().isEmpty() || user.getUserAddress().length()==0){
            throw new RequestException("Please type an address");
        }

        Optional<Users> userOptional=userRepository
                .findUsersByUserEmail(user.getUserEmail()); // taking anothr field as unique key so that if same with another goes it doe not add as new data
        if(userOptional.isPresent()){
            throw new RequestException("E-mail Id is taken");
        }

             try {
                 Users use=new Users();
                 use.setUserPhoneNo(user.getUserPhoneNo());
                 use.setUserName(user.getUserName());
                 use.setUserAddress(user.getUserAddress());
                 use.setUserEmail(user.getUserEmail());
                 return userRepository.save(use);

             }
            catch(IllegalArgumentException e){
                 throw new RequestException("Given employee is null");
             }
             catch(Exception e){
                 throw new RequestException("Something went wrong in service layer");
             }
    }




    //GET
    public List<Users> getAllUsers(){
        List<Users> emp=null;
        try {
            emp=userRepository.findAll();
        }
        catch(Exception e){
            throw new RequestException("Something went wrong while fetching the list"+e.getMessage());

        }
        if(emp.isEmpty()){
            throw new RequestException("List is empty we have nothing ");
        }

        return emp;
    }



    //GETbyID
    public Users findUsers(@PathVariable final String userId){
        return userRepository.findById(userId).orElseGet(Users::new);
    }


    //DELETE
    public ResponseEntity delete(@PathVariable final String userId) {

        Optional<Users> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok("Success");
        } else {
            return  ResponseEntity.badRequest().body("the restaurant with id:" + userId + "was not found");
        }
    }


    //PUT
    public void update(@PathVariable final String userId, @RequestBody UserRequest user){
        Users savedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find userby id %s ",userId)));

        savedUser.setUserName(user.getUserName());
        savedUser.setUserPhoneNo(user.getUserPhoneNo());
        savedUser.setUserAddress(user.getUserAddress());


        userRepository.save(savedUser);

    }

}

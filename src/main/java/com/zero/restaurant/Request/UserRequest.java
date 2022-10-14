package com.zero.restaurant.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;


@Data
@AllArgsConstructor
@NoArgsConstructor


public class UserRequest {

    private String userName;

    private  String  userPhoneNo;

    private String userAddress;


    @Email(message = "Email address is not valid !!")
    private String userEmail;

}

package com.zero.restaurant.Models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Users {


    @Id
    private String id;


    private String userName;


    private String userPhoneNo;


    private String userAddress;

   @Indexed(unique=true)
    private String userEmail;

}

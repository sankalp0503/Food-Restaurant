package com.zero.restaurant.Models;



import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Document
public class Restaurant{

   @Id
   private String id;


    private String restaurantName;

    @Indexed(unique=true)
    private String  phoneNo;

    @Indexed(unique=true)
    private String address;



}

package com.zero.restaurant.Request;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;


@Data
public class MenuRequest {


    @NotEmpty
    @Size(min=2,max=10,message = " Enter food name minimum of 4 characters")
    private  String name;

    @NotEmpty
    @Size(min=0,max=100)
    private String description;


    private BigInteger quantity;

    @NotEmpty
    private String restaurantId;


    private BigDecimal price;

}

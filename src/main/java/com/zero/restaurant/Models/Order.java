package com.zero.restaurant.Models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Document
public class Order{

    @Id
    private String id;

    @Indexed()
    private String restaurantId;


    private String restaurantName;


    private String restaurantPhoneNo;

    @Indexed()
    private String foodId;


    private String foodName;

    @Indexed()
    private BigInteger quantity;


    private BigDecimal prices;

    @Indexed()
    private String userId;


    private String userName;


    private String userPhoneNo;


    private String userAddress;


    private String status;

}

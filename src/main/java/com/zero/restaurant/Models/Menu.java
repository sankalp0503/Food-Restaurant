package com.zero.restaurant.Models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Document
public class Menu {

    @Id
    private String id;


    private  String name;


    private String description;


    private BigInteger quantity;

    @Indexed()
    private String restaurantId;


    private BigDecimal price;


}

package com.zero.restaurant.Request;


import lombok.Data;


import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Data
public class OrderRequest {



    @NotEmpty
    private String restaurantId;


    @NotEmpty
    private String foodId;


    private BigInteger quantity;



    @NotEmpty
    private String userId;



}

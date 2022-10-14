package com.zero.restaurant.Request;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data

public class RestaurantRequest {

    @NotEmpty
    @Size(min=3,message = " User name minimum of 4 characters")
    private String restaurantName;

    @NotEmpty
    @Size(min=10,max=10)
    @Pattern(regexp="(^$|[0-9]{10})",message = "Enter a valid phone number")
    private String  phoneNo;

    @NotEmpty

    private String address;
}

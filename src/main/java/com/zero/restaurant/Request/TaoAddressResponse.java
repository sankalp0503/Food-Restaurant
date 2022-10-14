package com.zero.restaurant.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaoAddressResponse {

  @JsonProperty("completeAddress")
    private String completeAddress;

   @JsonProperty("country")
    private String country;

  @JsonProperty("district")
    private  String district;

  @JsonProperty("house")
    private String house;

  @JsonProperty("id")
    private String id;

  @JsonProperty("phone")
    private String  phone;

  @JsonProperty("pin")
    private Integer pin;

  @JsonProperty("state")
    private String state;

  @JsonProperty("street")
    private String street;

  @JsonProperty("userId")
    private String userId;



}

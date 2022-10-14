package com.zero.restaurant.Service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zero.restaurant.Models.TaoAddress;
import com.zero.restaurant.Models.TaoUpdateAddress;
import com.zero.restaurant.Request.TaoAddressRequest;
import com.zero.restaurant.Request.TaoAddressResponse;
import com.zero.restaurant.Service.TaoAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class TaoAddressServiceImpl implements TaoAddressService {

    private static final String GET_ADDRESS_BY_USER_ID="https://iostream04.com/tao/api/v1/address/user/";

    @Autowired
    private  RestTemplate restTemplate;


    @Autowired
    private  ObjectMapper mapper;


    //GET BY ID
    @Retryable(value=Exception.class,maxAttempts = 3,backoff = @Backoff(value=2000))
    public List<TaoAddressResponse> getAddressById(@PathVariable final String taoStudentId) throws IOException {

//        System.out.println("Check retry method started");
//          int i =0;
//          int n=5/i;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("userid","60fdc71951ee9f349b822a72");
        headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5OTE5NTQ2OTc2IiwiZXhwIjoxNjYzMzA3NDY1LCJpYXQiOjE2NjEyMzM4NjV9._PgUU1ufzyBrWlTXil38aWtfDspx-xH2VqzsjGwmZ8horc5HX5yJmd_2aMLT7nPscHej7w9_kbWBuNViSBK3aA");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity responseEntity  =    restTemplate.exchange(GET_ADDRESS_BY_USER_ID + taoStudentId, HttpMethod.GET,requestEntity,String.class);

        HttpStatus statusCode=responseEntity.getStatusCode();
        System.out.println("status code-"+statusCode);

        if(!statusCode.is2xxSuccessful()){
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        } else {


            List<TaoAddressResponse> taoAddressResponsesList = mapper.readValue(responseEntity.getBody().toString(), new TypeReference<List<TaoAddressResponse>>() {});

            return  taoAddressResponsesList;

        }
    }


        //Post
    @Retryable(value=Exception.class,maxAttempts = 5,backoff = @Backoff(value=5000))
    public TaoAddress createNewAddress(@RequestBody TaoAddressRequest taoRequest){

        String url = "https://iostream04.com/tao/api/v1/address/create/";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        TaoAddress tao = new TaoAddress();


        tao.setCountry(taoRequest.getCountry());
        tao.setDistrict(taoRequest.getDistrict());
        tao.setHouse(taoRequest.getHouse());
        tao.setPhone(taoRequest.getPhone());
        tao.setStreet(taoRequest.getStreet());
        tao.setUserId(taoRequest.getUserId());
        tao.setState(taoRequest.getState());
        tao.setPin(taoRequest.getPin());

        HttpEntity<TaoAddress> request =
                new HttpEntity<>(tao, headers);

        ResponseEntity<TaoAddress> response = this.restTemplate.postForEntity(url, request, TaoAddress.class);

        HttpStatus statusCode=response.getStatusCode();
        System.out.println(statusCode);
        if (statusCode.is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("New address is not created");
        }

    }


    //Delete
    @Retryable(value=Exception.class,maxAttempts = 5,backoff = @Backoff(value=5000))
    public ResponseEntity deleteAddress(@PathVariable final String taoId) {


        String url = "https://iostream04.com/tao/api/v1/address/delete/user/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<TaoAddress> responseEntity  =    restTemplate.exchange(url+ taoId, HttpMethod.DELETE,requestEntity,TaoAddress.class);

        HttpStatus statusCode=responseEntity.getStatusCode();
        System.out.println(statusCode);
        if (statusCode== HttpStatus.NO_CONTENT) {
            return ResponseEntity.ok("Success and no content to send back");
        } else {
            return  ResponseEntity.badRequest().body("the address with id:" + taoId + "was not found");

        }
    }


    //Put
    @Retryable(value=Exception.class,maxAttempts = 5,backoff = @Backoff(value=5000))
    public TaoUpdateAddress update(@PathVariable final String taoId,@RequestBody TaoAddressRequest taoUpdate){

        String url = "https://iostream04.com/tao/api/v1/address/update/";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        TaoUpdateAddress tao = new TaoUpdateAddress();

        tao.setCountry(taoUpdate.getCountry());
        tao.setDistrict(taoUpdate.getDistrict());
        tao.setHouse(taoUpdate.getHouse());
        tao.setPhone(taoUpdate.getPhone());
        tao.setStreet(taoUpdate.getStreet());
        tao.setUserId(taoUpdate.getUserId());
        tao.setState(taoUpdate.getState());
        tao.setPin(taoUpdate.getPin());

        HttpEntity<TaoUpdateAddress> requestEntity = new HttpEntity<>(tao,headers);

        ResponseEntity<TaoUpdateAddress> responseEntity  = restTemplate.exchange(url+ taoId, HttpMethod.POST,requestEntity,TaoUpdateAddress.class);

        HttpStatus statusCode=responseEntity.getStatusCode();
        System.out.println(statusCode);

        if (statusCode.is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("address is not updated");
        }
    }
}

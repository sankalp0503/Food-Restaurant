package com.zero.restaurant.Service;

import com.zero.restaurant.Models.TaoAddress;
import com.zero.restaurant.Models.TaoUpdateAddress;
import com.zero.restaurant.Request.TaoAddressRequest;
import com.zero.restaurant.Request.TaoAddressResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;


public interface TaoAddressService {

    List<TaoAddressResponse> getAddressById(@PathVariable final String taoStudentId) throws IOException;

    TaoAddress createNewAddress(@RequestBody TaoAddressRequest taoRequest);

    ResponseEntity deleteAddress(@PathVariable final String id);

    TaoUpdateAddress update(@PathVariable final String taoId,@RequestBody TaoAddressRequest taoUpdate);
}


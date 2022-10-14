package com.zero.restaurant.Controller;

import com.zero.restaurant.Models.TaoAddress;
import com.zero.restaurant.Models.TaoUpdateAddress;
import com.zero.restaurant.Request.TaoAddressRequest;
import com.zero.restaurant.Request.TaoAddressResponse;
import com.zero.restaurant.Service.TaoAddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class TaoAddressController {

    private TaoAddressService taoAddressService;

    @GetMapping("/{taoStudentId}")
    public ResponseEntity<List<TaoAddressResponse>> findAddressById(@PathVariable final String taoStudentId) throws IOException {
        return ResponseEntity.ok().body(taoAddressService.getAddressById(taoStudentId));
    }

    @PostMapping
    public ResponseEntity<TaoAddress> addAddress(@RequestBody TaoAddressRequest taoRequest) {
        TaoAddress addressSaved = taoAddressService.createNewAddress(taoRequest);
        return ResponseEntity.ok().body(addressSaved);
    }


    @DeleteMapping("/{taoId}")
        public ResponseEntity<Void> deleteAddressById(@PathVariable final String taoId) {
            taoAddressService.deleteAddress(taoId);
            return ResponseEntity.noContent().build();

    }

    @PutMapping("/{taoId}")
    public ResponseEntity<TaoUpdateAddress> updateAddress(@PathVariable final String taoId,@RequestBody TaoAddressRequest taoUpdate){
          TaoUpdateAddress addressUpdate = taoAddressService.update(taoId,taoUpdate) ;
        return  ResponseEntity.ok().body(addressUpdate);

    }
}

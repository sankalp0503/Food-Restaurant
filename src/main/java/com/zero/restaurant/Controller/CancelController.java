package com.zero.restaurant.Controller;



import com.zero.restaurant.Service.CancelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cancels")
@AllArgsConstructor
public class CancelController {

    private CancelService cancelService;

    @PostMapping("/{Id}")
    public void cancelOrders(@PathVariable final String Id) {
        cancelService.cancelNewOrders(Id);
    }
}




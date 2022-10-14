package com.zero.restaurant.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


public interface CancelService {

    void cancelNewOrders(@PathVariable final String Id);
}

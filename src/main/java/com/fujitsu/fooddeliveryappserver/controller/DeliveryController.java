package com.fujitsu.fooddeliveryappserver.controller;

import com.fujitsu.fooddeliveryappserver.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController (DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    /**
     * takes parameters from HTTP request, delegates the actual calculation
     * to the 'DeliveryService' class, and returns the result as an HTTP response.
     * */
    @GetMapping("/fee")
    public ResponseEntity<Response> calculateDeliveryFee(
            @RequestParam String city,
            @RequestParam String vehicleType,
            @RequestParam(required = false) String weatherCondition
    ) {
        Response deliveryFee = deliveryService.calculateDeliveryFee(city, vehicleType, weatherCondition);
        return ResponseEntity.ok(deliveryFee);
    }
}

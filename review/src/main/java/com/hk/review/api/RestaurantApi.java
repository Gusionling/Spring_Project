package com.hk.review.api;


import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantApi {

    @GetMapping("/restaurants")
    public String getRestaurants() {
        return "This is getRestaurants";
    }

    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurant(
            @PathVariable Long restaurantId
    ) {
        return "This is getRestaurant " + restaurantId;
    }

    @PostMapping("/restaurant")
    public String createRestaurant() {
        return "this is createRestaurant";
    }

    @PutMapping("/restaurant/{restaurantId}")
    public String editRestaurant(
            @PathVariable Long restaurantId
    ){
        return "this is editRestaurant " + restaurantId;
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public String deleteRestaurant(
            @PathVariable Long restaurantId
    ){
        return "this is deleteRestaurant " + restaurantId;
    }
}

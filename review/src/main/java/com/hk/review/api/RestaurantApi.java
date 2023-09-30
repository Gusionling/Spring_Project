package com.hk.review.api;


import com.hk.review.api.request.CreateAndEditRestaurantRequest;
import com.hk.review.model.RestaurantEntity;
import com.hk.review.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {

    private final RestaurantService restaurantService;

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
    public RestaurantEntity createRestaurant(
            @RequestBody CreateAndEditRestaurantRequest request
            ) {
        return restaurantService.createRestaurant(request);
    }

    @PutMapping("/restaurant/{restaurantId}")
    public String editRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody CreateAndEditRestaurantRequest request
    ){
        return "this is editRestaurant " + restaurantId + "address = " + request.getAddress();
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public String deleteRestaurant(
            @PathVariable Long restaurantId
    ){
        return "this is deleteRestaurant " + restaurantId;
    }
}

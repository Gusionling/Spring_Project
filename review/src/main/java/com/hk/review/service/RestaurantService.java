package com.hk.review.service;


import com.hk.review.api.request.CreateAndEditRestaurantRequest;
import com.hk.review.model.MenuEntity;
import com.hk.review.model.RestaurantEntity;
import com.hk.review.repository.MenuRepository;
import com.hk.review.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public RestaurantEntity createRestaurant(CreateAndEditRestaurantRequest request) {
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        restaurantRepository.save(restaurant);

        request.getMenus().forEach((menu)->{
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createdAt(ZonedDateTime.now())
                    .updatedAt(ZonedDateTime.now())
                    .build();
            menuRepository.save(menuEntity);
        });

        return restaurant;
    }

    public void editRestaurant(){

    }

    public void deleteRestaurant(){

    }
}

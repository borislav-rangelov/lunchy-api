package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.RestaurantLocation;

import java.util.Optional;

public interface RestaurantLocationService extends BaseService<RestaurantLocation> {
    Optional<RestaurantLocation> remove(long restaurantId, long id);
}

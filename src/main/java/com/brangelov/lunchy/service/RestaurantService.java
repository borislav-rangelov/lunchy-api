package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.Restaurant;
import com.brangelov.lunchy.entity.RestaurantWithMenus;

import java.util.Optional;

public interface RestaurantService extends BaseService<Restaurant> {
    Optional<RestaurantWithMenus> getByIdWithMenus(long id);
}

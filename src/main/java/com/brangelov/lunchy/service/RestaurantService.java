package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RestaurantService {
    Page<Restaurant> get(Pageable pageable);
    Optional<Restaurant> get(long id);
    Restaurant save(Restaurant restaurant);
    Optional<Restaurant> remove(long id);
}

package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MenuService {
    Page<Menu> getByRestaurantId(long restaurantId, Pageable pageable);
    Optional<Menu> getByRestaurantIdAndId(long restaurantId, long id);

    Menu save(Menu menu);

    Optional<Menu> removeByRestaurantIdAndId(long restaurantId, long id);
}

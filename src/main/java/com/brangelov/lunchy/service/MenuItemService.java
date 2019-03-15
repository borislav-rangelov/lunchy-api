package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MenuItemService {

    Page<MenuItem> getByRestaurantId(long restaurantId, Pageable pageable);

    Optional<MenuItem> getByRestaurantIdAndId(long restaurantId, long id);

    MenuItem save(MenuItem entity);

    Optional<MenuItem> removeByRestaurantIdAndId(long restaurantId, long id);
}

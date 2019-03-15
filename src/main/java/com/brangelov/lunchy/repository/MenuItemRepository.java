package com.brangelov.lunchy.repository;

import com.brangelov.lunchy.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Page<MenuItem> findByRestaurantId(long restaurantId, Pageable pageable);
    MenuItem findByRestaurantIdAndId(long restaurantId, long id);
}

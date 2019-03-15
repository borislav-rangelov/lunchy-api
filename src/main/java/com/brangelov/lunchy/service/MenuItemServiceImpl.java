package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.MenuItem;
import com.brangelov.lunchy.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private MenuItemRepository repository;

    @Override
    public Page<MenuItem> getByRestaurantId(long restaurantId, Pageable pageable) {
        return repository.findByRestaurantId(restaurantId, pageable);
    }

    @Override
    public Optional<MenuItem> getByRestaurantIdAndId(long restaurantId, long id) {
        return Optional.ofNullable(repository.findByRestaurantIdAndId(restaurantId, id));
    }

    @Override
    public MenuItem save(MenuItem entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<MenuItem> removeByRestaurantIdAndId(long restaurantId, long id) {
        Optional<MenuItem> item = getByRestaurantIdAndId(restaurantId, id);
        item.ifPresent(i -> repository.delete(i));
        return item;
    }

    @Autowired
    public void setRepository(MenuItemRepository repository) {
        this.repository = repository;
    }
}

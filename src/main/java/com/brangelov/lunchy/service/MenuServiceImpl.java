package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.Menu;
import com.brangelov.lunchy.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository repository;

    @Override
    public Page<Menu> getByRestaurantId(long restaurantId, Pageable pageable) {
        return repository.findByRestaurantId(restaurantId, pageable);
    }

    @Override
    public Optional<Menu> getByRestaurantIdAndId(long restaurantId, long id) {
        return Optional.ofNullable(repository.findByRestaurantIdAndId(restaurantId, id));
    }

    @Override
    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    @Override
    public Optional<Menu> removeByRestaurantIdAndId(long restaurantId, long id) {
        Optional<Menu> menu = getByRestaurantIdAndId(restaurantId, id);
        menu.ifPresent(m -> repository.delete(m));
        return menu;
    }

    @Autowired
    public void setRepository(MenuRepository repository) {
        this.repository = repository;
    }
}

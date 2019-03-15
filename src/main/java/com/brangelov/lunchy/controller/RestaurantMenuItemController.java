package com.brangelov.lunchy.controller;

import com.brangelov.lunchy.entity.MenuItem;
import com.brangelov.lunchy.entity.Restaurant;
import com.brangelov.lunchy.model.MenuItemEditModel;
import com.brangelov.lunchy.model.MenuItemGetModel;
import com.brangelov.lunchy.model.PageModel;
import com.brangelov.lunchy.service.MenuItemService;
import com.brangelov.lunchy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/menu-items")
public class RestaurantMenuItemController extends BaseController {

    private RestaurantService restaurantService;
    private MenuItemService service;

    @GetMapping
    public HttpEntity getPage(@PathVariable long restaurantId, Pageable pageable) {
        Page<MenuItem> items = service.getByRestaurantId(restaurantId, pageable);
        return ResponseEntity.ok(new PageModel<>(items.map(i -> map(i, MenuItemGetModel.class))));
    }

    @GetMapping("/{id}")
    public HttpEntity getById(@PathVariable long restaurantId, @PathVariable long id) {
        Optional<MenuItem> item = service.getByRestaurantIdAndId(restaurantId, id);

        if (!item.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(map(item.get(), MenuItemGetModel.class));
    }

    @PostMapping
    public HttpEntity create(@PathVariable long restaurantId, @Valid @RequestBody MenuItemEditModel model) {

        Optional<Restaurant> restaurant = restaurantService.get(restaurantId);

        if (!restaurant.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        MenuItem item = map(model, MenuItem.class);
        item.setRestaurant(restaurant.get());

        item = service.save(item);

        return ResponseEntity.created(
                linkTo(methodOn(RestaurantMenuItemController.class).getById(restaurantId, item.getId())).toUri()
        ).body(map(item, MenuItemGetModel.class));
    }

    @PutMapping("/{id}")
    public HttpEntity update(@PathVariable long restaurantId, @PathVariable long id,
                             @RequestBody MenuItemEditModel model) {

        Optional<MenuItem> item = service.getByRestaurantIdAndId(restaurantId, id);

        if (!item.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        MenuItem menuItem = item.get();
        map(model, menuItem);

        menuItem = service.save(menuItem);

        return ResponseEntity.ok(map(menuItem, MenuItemGetModel.class));
    }

    @DeleteMapping("/{id}")
    public HttpEntity delete(@PathVariable long restaurantId, @PathVariable long id) {
        Optional<MenuItem> item = service.removeByRestaurantIdAndId(restaurantId, id);

        if (!item.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setService(MenuItemService service) {
        this.service = service;
    }
}

package com.brangelov.lunchy.controller;

import com.brangelov.lunchy.entity.Restaurant;
import com.brangelov.lunchy.model.PageModel;
import com.brangelov.lunchy.model.RestaurantEditModel;
import com.brangelov.lunchy.model.RestaurantGetModel;
import com.brangelov.lunchy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController extends BaseController {

    private RestaurantService service;

    @GetMapping
    public HttpEntity getPage(Pageable pageable) {
        return ResponseEntity.ok(new PageModel<>(service.get(pageable).map(r -> map(r, RestaurantGetModel.class))));
    }

    @GetMapping("/{id}")
    public HttpEntity getById(@PathVariable long id) {
        Optional<Restaurant> restaurant = service.get(id);

        if (!restaurant.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(map(restaurant.get(), RestaurantGetModel.class));
    }

    @PostMapping
    public HttpEntity create(@Valid @RequestBody RestaurantEditModel model) {
        Restaurant restaurant = map(model, Restaurant.class);

        restaurant = service.save(restaurant);

        return ResponseEntity.created(linkTo(methodOn(RestaurantController.class).getById(restaurant.getId())).toUri())
                .body(map(restaurant, RestaurantGetModel.class));
    }

    @PostMapping
    public HttpEntity update(@Valid @RequestBody RestaurantEditModel model) {
        Restaurant restaurant = map(model, Restaurant.class);

        restaurant = service.save(restaurant);

        return ResponseEntity.created(linkTo(methodOn(RestaurantController.class).getById(restaurant.getId())).toUri())
                .body(map(restaurant, RestaurantGetModel.class));
    }

    @Autowired
    public void setService(RestaurantService service) {
        this.service = service;
    }
}

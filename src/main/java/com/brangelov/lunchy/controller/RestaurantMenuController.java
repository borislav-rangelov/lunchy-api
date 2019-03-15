package com.brangelov.lunchy.controller;

import com.brangelov.lunchy.entity.Menu;
import com.brangelov.lunchy.model.MenuGetModel;
import com.brangelov.lunchy.model.PageModel;
import com.brangelov.lunchy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/menus")
public class RestaurantMenuController extends BaseController {

    private MenuService menuService;

    @GetMapping
    public HttpEntity getPage(@PathVariable long restaurantId, Pageable pageable) {
        Page<Menu> menus = menuService.getByRestaurantId(restaurantId, pageable);
        return ResponseEntity.ok(new PageModel<>(menus.map(m -> map(m, MenuGetModel.class))));
    }

    @GetMapping("/{id}")
    public HttpEntity getById(@PathVariable long restaurantId, long id) {
        Optional<Menu> menu = menuService.getByRestaurantIdAndId(restaurantId, id);

        if (!menu.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(map(menu.get(), MenuGetModel.class));
    }

//    @PostMapping
//    public HttpEntity create(@PathVariable long restaurantId, @Valid @RequestBody MenuEditModel model) {
//
//    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}

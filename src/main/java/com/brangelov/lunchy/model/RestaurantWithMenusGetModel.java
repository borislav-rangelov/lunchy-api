package com.brangelov.lunchy.model;

import java.util.List;

public class RestaurantWithMenusGetModel {
    private long id;
    private String name;
    private List<MenuGetModel> menus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuGetModel> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuGetModel> menus) {
        this.menus = menus;
    }
}

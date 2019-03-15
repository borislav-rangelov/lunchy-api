package com.brangelov.lunchy.model;

import java.time.LocalDateTime;
import java.util.List;

public class MenuGetModel {

    private long id;

    private String name;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;

    private List<MenuItemGetModel> menuItems;

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

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    public List<MenuItemGetModel> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemGetModel> menuItems) {
        this.menuItems = menuItems;
    }
}

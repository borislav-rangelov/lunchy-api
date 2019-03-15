package com.brangelov.lunchy.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "restaurants",
        indexes = @Index(name = "restaurant_name", columnList = "name")
)
public class Restaurant implements Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<RestaurantLocation> locations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(@NotEmpty String name) {
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestaurantLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<RestaurantLocation> locations) {
        this.locations = locations;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}

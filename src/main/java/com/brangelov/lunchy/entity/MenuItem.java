package com.brangelov.lunchy.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_items")
public class MenuItem implements Identity {

    public MenuItem() {
    }

    public MenuItem(String name, String description, int price, int grams) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.grams = grams;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(length = 6000)
    private String description;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int grams;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId", nullable = false)
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "menuItems", fetch = FetchType.LAZY)
    private List<Menu> menus = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}

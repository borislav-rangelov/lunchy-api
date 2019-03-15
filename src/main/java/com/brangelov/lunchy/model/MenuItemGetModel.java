package com.brangelov.lunchy.model;

public class MenuItemGetModel {
    private long id;

    private String name;

    private String description;

    private int price;

    private int grams;

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
}

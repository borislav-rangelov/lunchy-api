package com.brangelov.lunchy.entity;

import java.util.List;

public interface RestaurantWithMenus {
    long getId();
    String getName();
    List<Menu> getMenus();
}

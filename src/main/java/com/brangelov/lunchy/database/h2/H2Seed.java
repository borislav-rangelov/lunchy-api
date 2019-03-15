package com.brangelov.lunchy.database.h2;

import com.brangelov.lunchy.entity.*;
import com.brangelov.lunchy.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@Profile("h2")
public class H2Seed implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantLocationRepository locationRepository;
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;
    private final PasswordEncoder encoder;

    public H2Seed(UserRepository userRepository, AuthorityRepository authorityRepository,
                  RestaurantRepository restaurantRepository, RestaurantLocationRepository locationRepository,
                  MenuRepository menuRepository, MenuItemRepository menuItemRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.restaurantRepository = restaurantRepository;
        this.locationRepository = locationRepository;
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {

        Authority role_user = authorityRepository.save(new Authority("ROLE_USER"));
        Authority role_admin = authorityRepository.save(new Authority("ROLE_ADMIN"));

        userRepository.saveAll(Arrays.asList(
                new User("user@test.com", encoder.encode("Testpass1"),
                        Stream.of(role_user).collect(Collectors.toList()), true),
                new User("admin@test.com", encoder.encode("Testpass1"),
                        Stream.of(role_admin).collect(Collectors.toList()), true)
        ));

        Restaurant restaurant = restaurantRepository.save(new Restaurant("Example Restaurant"));

        RestaurantLocation location = new RestaurantLocation("Location 1", null);
        location.setRestaurant(restaurant);

        locationRepository.save(location);

        MenuItem menuItem1 = new MenuItem("Menu item 1", "Description 1", 300, 250);
        MenuItem menuItem2 = new MenuItem("Menu item 2", "Description 1", 300, 250);

        menuItem1.setRestaurant(restaurant);
        menuItem2.setRestaurant(restaurant);

        menuItem1 = menuItemRepository.save(menuItem1);
        menuItem2 = menuItemRepository.save(menuItem2);

        Menu menu = new Menu("Menu name");
        menu.setRestaurant(restaurant);
        menu.getMenuItems().add(menuItem1);
        menu.getMenuItems().add(menuItem2);

        menuRepository.save(menu);
    }
}

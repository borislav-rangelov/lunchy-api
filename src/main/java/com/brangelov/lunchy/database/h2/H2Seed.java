package com.brangelov.lunchy.database.h2;

import com.brangelov.lunchy.entity.Authority;
import com.brangelov.lunchy.entity.Restaurant;
import com.brangelov.lunchy.entity.RestaurantLocation;
import com.brangelov.lunchy.entity.User;
import com.brangelov.lunchy.repository.AuthorityRepository;
import com.brangelov.lunchy.repository.RestaurantLocationRepository;
import com.brangelov.lunchy.repository.RestaurantRepository;
import com.brangelov.lunchy.repository.UserRepository;
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
    private final PasswordEncoder encoder;

    public H2Seed(UserRepository userRepository, AuthorityRepository authorityRepository,
                  RestaurantRepository restaurantRepository, RestaurantLocationRepository locationRepository,
                  PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.restaurantRepository = restaurantRepository;
        this.locationRepository = locationRepository;
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
        restaurant.setLocations(Arrays.asList(location));
        locationRepository.save(location);
        restaurantRepository.save(restaurant);
    }
}

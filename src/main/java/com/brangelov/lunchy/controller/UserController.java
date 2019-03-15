package com.brangelov.lunchy.controller;

import com.brangelov.lunchy.entity.User;
import com.brangelov.lunchy.model.PageModel;
import com.brangelov.lunchy.model.UserGetModel;
import com.brangelov.lunchy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController extends BaseController {

    private UserRepository userRepository;

    @GetMapping
    public HttpEntity getUserPage(Pageable pageable) {

        Page<User> users = userRepository.findAll(pageable);

        return ResponseEntity.ok(new PageModel<>(users.map(u -> map(u, UserGetModel.class))));
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

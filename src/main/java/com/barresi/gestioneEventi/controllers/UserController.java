package com.barresi.gestioneEventi.controllers;


import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.services.EventService;
import com.barresi.gestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User user) {
        return user;
    }

}

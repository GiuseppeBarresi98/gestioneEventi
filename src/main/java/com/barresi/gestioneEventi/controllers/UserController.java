package com.barresi.gestioneEventi.controllers;


import com.barresi.gestioneEventi.entities.Event;
import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.payloads.EventDTO;
import com.barresi.gestioneEventi.services.EventService;
import com.barresi.gestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create_event")
    public Event createEvent(@AuthenticationPrincipal User user, @RequestBody EventDTO eventDTO){
        return eventService.createEvent(eventDTO,user);
    }


}

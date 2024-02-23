package com.barresi.gestioneEventi.controllers;


import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.exceptions.BadRequestException;
import com.barresi.gestioneEventi.payloads.LoginResponseDTO;
import com.barresi.gestioneEventi.payloads.UserDTO;
import com.barresi.gestioneEventi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody UserDTO payload) {
        return new LoginResponseDTO(authService.authentication(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated UserDTO userDTO, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return authService.saveUser(userDTO);
    }
}

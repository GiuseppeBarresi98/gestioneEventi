package com.barresi.gestioneEventi.services;


import com.barresi.gestioneEventi.Enum.Role;
import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.payloads.UserDTO;
import com.barresi.gestioneEventi.repository.UserDAO;
import com.barresi.gestioneEventi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTools jwtTools;
    public User saveUser(UserDTO userDTO){
        User user = new User();
            user.setName(userDTO.getName());
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setRuolo(Role.NORMALE);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setRuolo(Role.NORMALE);
            return userDAO.save(user);
    }

    public String authentication(UserDTO payload){
        User user = userService.findByemail(payload.getEmail());
        return jwtTools.createToken(user);
    }
    }


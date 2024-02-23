package com.barresi.gestioneEventi.services;


import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.exceptions.NotFoundException;
import com.barresi.gestioneEventi.repository.EventDAO;
import com.barresi.gestioneEventi.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private EventDAO eventDAO;

    public User findUserById(UUID id){
        LOGGER.info(id);
        return userDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public User findByemail(String email){
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

}

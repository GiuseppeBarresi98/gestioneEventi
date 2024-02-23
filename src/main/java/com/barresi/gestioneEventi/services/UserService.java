package com.barresi.gestioneEventi.services;


import com.barresi.gestioneEventi.entities.Event;
import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.exceptions.NotFoundException;
import com.barresi.gestioneEventi.payloads.EventDTO;
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

    public Event createEvent(EventDTO eventDTO){
        Event evento = new Event();
        evento.setTitolo(eventDTO.getTitolo());
        evento.setDescrizione(eventDTO.getDescrizione());
        evento.setLuogo(eventDTO.getLuogo());
        evento.setData(eventDTO.getData());
        evento.setNumero_posti_disponibili(eventDTO.getNumero_posti_disponibili());
        User organizzatore = userDAO.findById(eventDTO.getOrganizzatore_id()).orElseThrow(() -> new NotFoundException("Non trovato"));
        evento.setOrganizzatore(organizzatore);
        return eventDAO.save(evento);
    }

    public User findUserById(UUID id){
        LOGGER.info(id);
        return userDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public User findByemail(String email){
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

}

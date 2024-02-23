package com.barresi.gestioneEventi.services;


import com.barresi.gestioneEventi.entities.Event;
import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.payloads.EventDTO;
import com.barresi.gestioneEventi.repository.EventDAO;
import com.barresi.gestioneEventi.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User organizzatore = userDAO.findById(eventDTO.getOrganizzatore_id()).orElseThrow(() -> new RuntimeException());
        evento.setOrganizzatore(organizzatore);
        return eventDAO.save(evento);
    }
}

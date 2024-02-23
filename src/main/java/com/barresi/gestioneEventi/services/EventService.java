package com.barresi.gestioneEventi.services;


import com.barresi.gestioneEventi.entities.Event;
import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.exceptions.BadRequestException;
import com.barresi.gestioneEventi.exceptions.NotFoundException;
import com.barresi.gestioneEventi.payloads.EventDTO;
import com.barresi.gestioneEventi.repository.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventDAO eventDAO;

    public Page<Event> getListaEventi(int pageNumber, int size, String orderBy) {
        if(size < 100 ) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return eventDAO.findAll(pageable);
    }

    public Event createEvent(EventDTO eventDTO, User user){
        Event evento = new Event();
        evento.setTitolo(eventDTO.getTitolo());
        evento.setDescrizione(eventDTO.getDescrizione());
        evento.setLuogo(eventDTO.getLuogo());
        evento.setData(eventDTO.getData());
        evento.setNumero_posti_disponibili(eventDTO.getNumero_posti_disponibili());
        evento.setOrganizzatore(user);
        return eventDAO.save(evento);
    }


    public void deleteEvent(UUID uuid){
        Event evento = eventDAO.findById(uuid).orElseThrow(()-> new NotFoundException(uuid));
        eventDAO.delete(evento);
    }

    public Event findEventById(UUID id){
        return eventDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Event updateEvent(UUID id,EventDTO eventDTO){
        Event found = this.findEventById(id);
        found.setTitolo(eventDTO.getTitolo());
        found.setLuogo(eventDTO.getLuogo());
        found.setDescrizione(eventDTO.getDescrizione());
        found.setData(eventDTO.getData());
        return eventDAO.save(found);
    }


    public Event partecipaEvento(UUID idEvento, User user) {
        Event evento = this.findEventById(idEvento);
        if (evento.getUserList().size() < evento.getNumero_posti_disponibili()) {
            evento.getUserList().add(user);
            return eventDAO.save(evento);
        } else {
            throw new BadRequestException("Evento pieno");
        }
    }



}

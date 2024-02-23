package com.barresi.gestioneEventi.services;


import com.barresi.gestioneEventi.entities.Event;
import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.payloads.EventDTO;
import com.barresi.gestioneEventi.repository.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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


    public void deleteEvent

}

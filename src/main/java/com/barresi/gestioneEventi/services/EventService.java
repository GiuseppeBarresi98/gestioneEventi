package com.barresi.gestioneEventi.services;


import com.barresi.gestioneEventi.entities.Event;
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

}

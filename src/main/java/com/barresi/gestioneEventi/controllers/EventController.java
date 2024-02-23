package com.barresi.gestioneEventi.controllers;


import com.barresi.gestioneEventi.entities.Event;
import com.barresi.gestioneEventi.entities.User;
import com.barresi.gestioneEventi.payloads.EventDTO;
import com.barresi.gestioneEventi.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    public EventService eventService;


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public void deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Event updateEvent(@PathVariable UUID id, @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }

    @PostMapping("/create_event")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Event createEvent(@AuthenticationPrincipal User user, @RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO, user);
    }

    @GetMapping
    public Page<Event> getListaEventi(@RequestParam(defaultValue = "0") int pagine, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "titolo") String orderBy) {
        return eventService.getListaEventi(pagine, size, orderBy);
    }

    @PostMapping("/{eventId}/partecipa")
    public Event partecipaEvento(@PathVariable UUID eventId, @AuthenticationPrincipal User authenticatedUser) {
        Event eventoPartecipato = eventService.partecipaEvento(eventId, authenticatedUser);
        return eventoPartecipato;
    }
}

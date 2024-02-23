package com.barresi.gestioneEventi.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String name;
    private String email;
    @ManyToMany
    @JoinTable(
            name = "user-event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> eventiList;
}

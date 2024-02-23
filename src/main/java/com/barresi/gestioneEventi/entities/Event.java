package com.barresi.gestioneEventi.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numero_posti_disponibili;
    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private User organizzatore;
    @ManyToMany
    @JoinTable(
            name = "user-event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList;


    public Event(String titolo, String descrizione, LocalDate data, String luogo, int numero_posti_disponibili,User organizzatore) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.numero_posti_disponibili = numero_posti_disponibili;
        this.organizzatore = organizzatore;
    }
}

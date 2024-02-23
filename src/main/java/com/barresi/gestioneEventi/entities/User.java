package com.barresi.gestioneEventi.entities;


import com.barresi.gestioneEventi.Enum.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@NoArgsConstructor
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
    private String password;
    @Enumerated(EnumType.STRING)
    private Role ruolo;
    @OneToMany(mappedBy = "organizzatore")
    private List<Event> eventList;
    @ManyToMany
    @JoinTable(
            name = "user-event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> eventiList;


    public User(String username,String name,String email,String password){
        this.username = username;
        this.name = name ;
        this.email = email;
        this.password = password;
    }
}

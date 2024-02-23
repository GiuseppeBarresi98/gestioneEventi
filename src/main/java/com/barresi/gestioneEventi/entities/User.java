package com.barresi.gestioneEventi.entities;


import com.barresi.gestioneEventi.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@Entity
@Getter
@Setter
@JsonIgnoreProperties({
        "password", "credentialsNonExpired", "accountNonExpired", "authorities",
        "username", "accountNonLocked", "enabled", "ruolo", "eventList"
})
public class User implements UserDetails {
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
    @ManyToMany(fetch = FetchType.EAGER)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.barresi.gestioneEventi.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String name;
    private String email;
    private String password;
}

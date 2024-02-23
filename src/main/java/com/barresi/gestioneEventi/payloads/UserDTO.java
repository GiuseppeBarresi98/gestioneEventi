package com.barresi.gestioneEventi.payloads;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {
    @NotEmpty(message = "inserire username")
    private String username;
    @NotEmpty(message = "Nome )
    private String name;
    @NotEmpty(message = "Email obbligatoria")
    private String email;
    @NotEmpty(message = "Password obbligatoria")
    private String password;
}

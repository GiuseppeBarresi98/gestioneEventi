package com.barresi.gestioneEventi.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EventDTO {

    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numero_posti_disponibili;

}

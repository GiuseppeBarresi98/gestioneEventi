package com.barresi.gestioneEventi.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorsPayload {
    private String messaggio;
    private LocalDateTime time;
}

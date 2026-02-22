package com.example.registropersona_back.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {

    private Long documento;
    private String nombre;
    private String apellido;
    private String email;

}

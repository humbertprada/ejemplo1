package com.example.registropersona_back.controller;

import com.example.registropersona_back.dto.PersonaDto;
import com.example.registropersona_back.dto.RespuestaDto;
import com.example.registropersona_back.persistence.model.Persona;
import com.example.registropersona_back.persistence.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/persona")
public class RegistroController {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/ver")
    public String ver(){

        return "Hola Mundo";
    }

    @PostMapping("/guardar")
    public RespuestaDto guardar(@RequestBody PersonaDto personaDto){
        RespuestaDto respuesta = new RespuestaDto();
try {
    Persona persona = new Persona();
    persona.setDocumento(personaDto.getDocumento());
    persona.setNombre(personaDto.getNombre());
    persona.setApellido(personaDto.getApellido());
    persona.setEmail(personaDto.getEmail());
    personaRepository.save(persona);
    respuesta.setEstado("OK");
    respuesta.setMensaje("Persona guardada correctamente");

}catch (Exception e){
    respuesta.setEstado("ERROR");
    System.out.println(e.getMessage());
    if(e.getMessage().toLowerCase().contains("documento") && e.getMessage().toLowerCase().contains("no nulo"))
        respuesta.setMensaje("El documento es obligatorio");
    else respuesta.setMensaje("Otro error");
}
        return respuesta;
    }


    @GetMapping("/lista")
    public List<Persona> listPersona(){
        return personaRepository.findAll();
    }


}

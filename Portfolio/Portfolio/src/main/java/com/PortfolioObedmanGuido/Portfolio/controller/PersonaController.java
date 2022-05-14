package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.entity.Persona;
import com.PortfolioObedmanGuido.Portfolio.service.PersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins="http://localhost:4200/")
public class PersonaController {
    @Autowired
    PersonaService personaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> getLista(){
        List<Persona> lista = personaService.obtenerTodas();
        return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/individuo/{id}")
    public ResponseEntity<Persona> getOne(@PathVariable Long id){
        if(!personaService.existeId(id))
            return new ResponseEntity(new Mensaje("La persona no existe (id)."), HttpStatus.NOT_FOUND);
        Persona persona = personaService.buscarId(id).get();
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody Persona persona){
        if(StringUtils.isBlank(persona.getEmail()))
            return new ResponseEntity(new Mensaje("Tiene que poner el email."), HttpStatus.BAD_REQUEST);
        if(personaService.existeEmail(persona.getEmail()))
            return new ResponseEntity(new Mensaje("Esa persona (email) ya existe."), HttpStatus.BAD_REQUEST);
        personaService.crearPersona(persona);
        return new ResponseEntity(new Mensaje("Persona creada."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@RequestBody Persona persona, @PathVariable("id") Long id){
        if(!personaService.existeId(id))
            return new ResponseEntity(new Mensaje("La persona no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(persona.getEmail()))
            return new ResponseEntity(new Mensaje("Tiene que poner el email."), HttpStatus.BAD_REQUEST);
        Persona personaUpdate = personaService.buscarId(id).get();
        personaUpdate.setName(persona.getName());
        personaUpdate.setSurname(persona.getSurname());
        personaUpdate.setProfilepicture(persona.getProfilepicture());
        personaUpdate.setTitle(persona.getTitle());
        personaUpdate.setPosition(persona.getPosition());
        personaUpdate.setBannerpicture(persona.getBannerpicture());
        personaUpdate.setAboutpersona(persona.getAboutpersona());
        personaUpdate.setDateofbirth(persona.getDateofbirth());
        personaUpdate.setTelephone(persona.getTelephone());
        personaUpdate.setEmail(persona.getEmail());
        personaUpdate.setProvincia(persona.getProvincia());
        personaService.crearPersona(personaUpdate);
        return new ResponseEntity(new Mensaje("Persona actualizada."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!personaService.existeId(id))
            return new ResponseEntity(new Mensaje("La persona no existe (id)."), HttpStatus.NOT_FOUND);
        personaService.borrarPersona(id);
        return new ResponseEntity(new Mensaje("Persona borrada."), HttpStatus.OK);
    }
}
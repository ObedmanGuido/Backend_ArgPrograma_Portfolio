package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.model.Proyecto;
import com.PortfolioObedmanGuido.Portfolio.exception.ResourceNotFoundException;
import com.PortfolioObedmanGuido.Portfolio.repository.ProyectoRepository;
import com.PortfolioObedmanGuido.Portfolio.repository.PersonaRepository;
import com.PortfolioObedmanGuido.Portfolio.service.ProyectoService;
import com.PortfolioObedmanGuido.Portfolio.service.PersonaService;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/personas")
@CrossOrigin(origins="*")
public class ProyectoController {
    
    @Autowired
    PersonaService personaService;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Autowired
    ProyectoRepository proyectoRepository;
    
    @Autowired
    ProyectoService proyectoService;
    
    @GetMapping("/{personaId}/proyecto/lista")
    public ResponseEntity<List<Proyecto>> getAllProyectoByPersonaId(@PathVariable(value = "personaId") Long personaId) {
        List<Proyecto> lista = proyectoRepository.findByPersonaId(personaId);
        return new ResponseEntity<List<Proyecto>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/proyecto/lista")
    public ResponseEntity<List<Proyecto>> getLista(){
        List<Proyecto> lista = proyectoService.obtenerTodos();
        return new ResponseEntity<List<Proyecto>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/proyecto/{id}")
    public ResponseEntity<Proyecto> getOne(@PathVariable Long id){
        if(!proyectoService.existeId(id))
            return new ResponseEntity(new Mensaje("El proyecto no existe (id)."), HttpStatus.NOT_FOUND);
        Proyecto proyecto = proyectoService.buscarId(id).get();
        return new ResponseEntity<Proyecto>(proyecto, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{personaId}/proyecto/nuevo")
    public ResponseEntity<Proyecto> createProyecto(@PathVariable(value = "personaId") Long personaId,
            @Valid @RequestBody Proyecto proyectoRequest, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Proyecto proyecto = personaRepository.findById(personaId).map(persona -> {
            proyectoRequest.setPersona(persona);
            return proyectoRepository.save(proyectoRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No encontrada persona con id " + personaId));
        return new ResponseEntity<>(proyecto, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/proyecto/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Proyecto proyecto, @PathVariable("id") Long id, Errors errors){
        if(!proyectoService.existeId(id))
            return new ResponseEntity(new Mensaje("El proyecto no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(proyecto.getProjectName()))
            return new ResponseEntity(new Mensaje("Tiene que poner el nombre (projectName)."), HttpStatus.BAD_REQUEST);
        if(errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Proyecto proyectoUpdate = proyectoService.buscarId(id).get();
        proyectoUpdate.setProjectName(proyecto.getProjectName());
        proyectoUpdate.setCreationDate(proyecto.getCreationDate());
        proyectoUpdate.setProjectDescription(proyecto.getProjectDescription());
        proyectoUpdate.setProjectLink(proyecto.getProjectLink());
        proyectoUpdate.setImage1(proyecto.getImage1());
        proyectoUpdate.setImage2(proyecto.getImage2());
        proyectoUpdate.setImage3(proyecto.getImage3());
        proyectoUpdate.setVideo(proyecto.getVideo());
        proyectoService.crearProyecto(proyectoUpdate);
        return new ResponseEntity(new Mensaje("Proyecto actualizado."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/proyecto/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!proyectoService.existeId(id))
            return new ResponseEntity(new Mensaje("El proyecto no existe (id)."), HttpStatus.NOT_FOUND);
        proyectoService.borrarProyecto(id);
        return new ResponseEntity(new Mensaje("Proyecto borrado."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{personaId}/proyecto/borrar")
    public ResponseEntity<List<Proyecto>> deleteAllProyectoOfPersona(@PathVariable(value = "personaId") Long personaId) {
        if (!personaRepository.existsById(personaId)){
            throw new ResourceNotFoundException("No encontrada persona con id " + personaId);
        }
    proyectoRepository.deleteByPersonaId(personaId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
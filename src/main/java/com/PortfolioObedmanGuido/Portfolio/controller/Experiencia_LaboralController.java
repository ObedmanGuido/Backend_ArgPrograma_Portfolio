package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.model.Experiencia_Laboral;
import com.PortfolioObedmanGuido.Portfolio.exception.ResourceNotFoundException;
import com.PortfolioObedmanGuido.Portfolio.repository.Experiencia_LaboralRepository;
import com.PortfolioObedmanGuido.Portfolio.repository.PersonaRepository;
import com.PortfolioObedmanGuido.Portfolio.service.Experiencia_LaboralService;
import com.PortfolioObedmanGuido.Portfolio.service.PersonaService;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins="http://localhost:4200")
public class Experiencia_LaboralController {
    @Autowired
    PersonaService personaService;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Autowired
    Experiencia_LaboralRepository experiencia_laboralRepository;

    @Autowired
    Experiencia_LaboralService experiencia_laboralService;
    
    @GetMapping("/{personaId}/experiencia-laboral/lista")
    public ResponseEntity<List<Experiencia_Laboral>> getAllExperiencia_LaboralByPersonaId(@PathVariable(value = "personaId") Long personaId) {
        List<Experiencia_Laboral> lista = experiencia_laboralRepository.findByPersonaId(personaId);
        return new ResponseEntity<List<Experiencia_Laboral>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/experiencia-laboral/lista")
    public ResponseEntity<List<Experiencia_Laboral>> getLista(){
        List<Experiencia_Laboral> lista = experiencia_laboralService.obtenerTodas();
        return new ResponseEntity<List<Experiencia_Laboral>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/experiencia-laboral/{id}")
    public ResponseEntity<Experiencia_Laboral> getOne(@PathVariable Long id){
        if(!experiencia_laboralService.existeId(id))
            return new ResponseEntity(new Mensaje("La experiencia laboral no existe (id)."), HttpStatus.NOT_FOUND);
        Experiencia_Laboral experiencia_laboral = experiencia_laboralService.buscarId(id).get();
        return new ResponseEntity<Experiencia_Laboral>(experiencia_laboral, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{personaId}/experiencia-laboral/nuevo")
    public ResponseEntity<Experiencia_Laboral> createExperiencia_Laboral(@PathVariable(value = "personaId") Long personaId,
            @Valid @RequestBody Experiencia_Laboral experiencia_laboralRequest, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Experiencia_Laboral experiencia_laboral = personaRepository.findById(personaId).map(persona -> {
            experiencia_laboralRequest.setPersona(persona);
            return experiencia_laboralRepository.save(experiencia_laboralRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No encontrada persona con id " + personaId));
        return new ResponseEntity<>(experiencia_laboral, HttpStatus.CREATED); 
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experiencia-laboral/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Experiencia_Laboral experiencia_laboral, @PathVariable("id") Long id, Errors errors){
        if(!experiencia_laboralService.existeId(id))
            return new ResponseEntity(new Mensaje("La experiencia laboral no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(experiencia_laboral.getCompany()))
            return new ResponseEntity(new Mensaje("Tiene que poner la compan??a (company)."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experiencia_laboral.getPosition()))
            return new ResponseEntity(new Mensaje("Tiene que poner la posici??n (position)."), HttpStatus.BAD_REQUEST);
        if (errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Experiencia_Laboral experiencia_laboralUpdate = experiencia_laboralService.buscarId(id).get();
        experiencia_laboralUpdate.setCompany(experiencia_laboral.getCompany());
        experiencia_laboralUpdate.setPosition(experiencia_laboral.getPosition());
        experiencia_laboralUpdate.setLogo(experiencia_laboral.getLogo());
        experiencia_laboralUpdate.setStartDate(experiencia_laboral.getStartDate());
        experiencia_laboralUpdate.setEndDate(experiencia_laboral.getEndDate());
        experiencia_laboralUpdate.setWorkDescription(experiencia_laboral.getWorkDescription());
        experiencia_laboralUpdate.setCurrentJob(experiencia_laboral.getCurrentJob());
        experiencia_laboralService.crearExperiencia_Laboral(experiencia_laboralUpdate);
        return new ResponseEntity(new Mensaje("Experiencia laboral actualizada."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/experiencia-laboral/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!experiencia_laboralService.existeId(id))
            return new ResponseEntity(new Mensaje("La experiencia laboral no existe (id)."), HttpStatus.NOT_FOUND);
        experiencia_laboralService.borrarExperiencia_Laboral(id);
        return new ResponseEntity(new Mensaje("Experiencia laboral borrada."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{personaId}/experiencia-laboral/borrar")
    public ResponseEntity<List<Experiencia_Laboral>> deleteAllExperiencia_LaboralOfPersona(@PathVariable(value = "personaId") Long personaId) {
        if (!personaRepository.existsById(personaId)){
            throw new ResourceNotFoundException("No encontrada persona con id " + personaId);
        }
    experiencia_laboralRepository.deleteByPersonaId(personaId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
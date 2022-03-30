package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.entity.Educacion;
import com.PortfolioObedmanGuido.Portfolio.exception.ResourceNotFoundException;
import com.PortfolioObedmanGuido.Portfolio.repository.EducacionRepository;
import com.PortfolioObedmanGuido.Portfolio.repository.PersonaRepository;
import com.PortfolioObedmanGuido.Portfolio.service.EducacionService;
import com.PortfolioObedmanGuido.Portfolio.service.PersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins="*")
public class EducacionController {
    @Autowired
    PersonaService personaService;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Autowired
    EducacionRepository educacionRepository;
    
    @Autowired
    EducacionService educacionService;
    
    @GetMapping("/{personaId}/educacion/lista")
    public ResponseEntity<List<Educacion>> getAllEducacionByPersonaId(@PathVariable(value = "personaId") Long personaId) {
        List<Educacion> lista = educacionRepository.findByPersonaId(personaId);
        return new ResponseEntity<List<Educacion>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/educacion/lista")
    public ResponseEntity<List<Educacion>> getLista(){
        List<Educacion> lista = educacionService.obtenerTodas();
        return new ResponseEntity<List<Educacion>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/educacion/{id}")
    public ResponseEntity<Educacion> getOne(@PathVariable Long id){
        if(!educacionService.existeId(id))
            return new ResponseEntity(new Mensaje("La educación no existe (id)."), HttpStatus.NOT_FOUND);
        Educacion educacion = educacionService.buscarId(id).get();
        return new ResponseEntity<Educacion>(educacion, HttpStatus.OK);
    }

    @PostMapping("/{personaId}/educacion/nuevo")
    public ResponseEntity<Educacion> createEducacion(@PathVariable(value = "personaId") Long personaId,
            @RequestBody Educacion educacionRequest){
        Educacion educacion = personaRepository.findById(personaId).map(persona -> {
            educacionRequest.setPersona(persona);
            return educacionRepository.save(educacionRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No encontrada persona con id " + personaId));
        return new ResponseEntity<>(educacion, HttpStatus.CREATED);
    }

    @PutMapping("/educacion/actualizar/{id}")
    public ResponseEntity<?> update(@RequestBody Educacion educacion, @PathVariable("id") Long id){
        if(!educacionService.existeId(id))
            return new ResponseEntity(new Mensaje("La educación no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(educacion.getTitle()))
            return new ResponseEntity(new Mensaje("Tiene que poner el título (title)."), HttpStatus.BAD_REQUEST);
        Educacion educacionUpdate = educacionService.buscarId(id).get();
        educacionUpdate.setSchoolname(educacion.getSchoolname());
        educacionUpdate.setTitle(educacion.getTitle());
        educacionUpdate.setLogo(educacion.getLogo());
        educacionUpdate.setStartyear(educacion.getStartyear());
        educacionUpdate.setEndyear(educacion.getEndyear());
        educacionUpdate.setTypeofschool(educacion.getTypeofschool());
        educacionUpdate.setStudiesstatus(educacion.getStudiesstatus());
        educacionService.crearEducacion(educacionUpdate);
        return new ResponseEntity(new Mensaje("Educación actualizada."), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/educacion/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!educacionService.existeId(id))
            return new ResponseEntity(new Mensaje("La educación no existe (id)."), HttpStatus.NOT_FOUND);
        educacionService.borrarEducacion(id);
        return new ResponseEntity(new Mensaje("Educación borrada."), HttpStatus.OK);
    }
    
    @DeleteMapping("/{personaId}/educacion/borrar")
    public ResponseEntity<List<Educacion>> deleteAllEducacionOfPersona(@PathVariable(value = "personaId") Long personaId) {
        if (!personaRepository.existsById(personaId)){
            throw new ResourceNotFoundException("No encontrada persona con id " + personaId);
        }
    educacionRepository.deleteByPersonaId(personaId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
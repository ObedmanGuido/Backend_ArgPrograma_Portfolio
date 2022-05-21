package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.entity.Skill;
import com.PortfolioObedmanGuido.Portfolio.exception.ResourceNotFoundException;
import com.PortfolioObedmanGuido.Portfolio.repository.PersonaRepository;
import com.PortfolioObedmanGuido.Portfolio.repository.SkillRepository;
import com.PortfolioObedmanGuido.Portfolio.service.PersonaService;
import com.PortfolioObedmanGuido.Portfolio.service.SkillService;
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
@CrossOrigin(origins="http://localhost:4200")
public class SkillController {
    @Autowired
    PersonaService personaService;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillService skillService;
    
    @GetMapping("/{personaId}/skill/lista")
    public ResponseEntity<List<Skill>> getAllSkillByPersonaId(@PathVariable(value = "personaId") Long personaId) {
        List<Skill> lista = skillRepository.findByPersonaId(personaId);
        return new ResponseEntity<List<Skill>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/skill/lista")
    public ResponseEntity<List<Skill>> getLista(){
        List<Skill> lista = skillService.obtenerTodas();
        return new ResponseEntity<List<Skill>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/skill/{id}")
    public ResponseEntity<Skill> getOne(@PathVariable Long id){
        if(!skillService.existeId(id))
            return new ResponseEntity(new Mensaje("El skill no existe (id)."), HttpStatus.NOT_FOUND);
        Skill skill = skillService.buscarId(id).get();
        return new ResponseEntity<Skill>(skill, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{personaId}/skill/nuevo")
    public ResponseEntity<Skill> createSkill(@PathVariable(value = "personaId") Long personaId,
            @Valid @RequestBody Skill skillRequest, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Skill skill = personaRepository.findById(personaId).map(persona -> {
            skillRequest.setPersona(persona);
            return skillRepository.save(skillRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No encontrada persona con id " + personaId));
        return new ResponseEntity<>(skill, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/skill/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Skill skill, @PathVariable("id") Long id, Errors errors){
        if(!skillService.existeId(id))
            return new ResponseEntity(new Mensaje("El skill no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(skill.getSkillname()))
            return new ResponseEntity(new Mensaje("Tiene que poner el nombre del skill (skillname)."), HttpStatus.BAD_REQUEST);
        if (errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Skill skillUpdate = skillService.buscarId(id).get();
        skillUpdate.setSkillname(skill.getSkillname());
        skillUpdate.setLevelnumber(skill.getLevelnumber());
        skillUpdate.setSkilldescription(skill.getSkilldescription());
        skillUpdate.setSkill_tipo(skill.getSkill_tipo());
        skillService.crearSkill(skillUpdate);
        return new ResponseEntity(new Mensaje("Skill actualizado."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/skill/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!skillService.existeId(id))
            return new ResponseEntity(new Mensaje("El skill no existe (id)."), HttpStatus.NOT_FOUND);
        skillService.borrarSkill(id);
        return new ResponseEntity(new Mensaje("Skill borrado."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{personaId}/skill/borrar")
    public ResponseEntity<List<Skill>> deleteAllSkillOfPersona(@PathVariable(value = "personaId") Long personaId) {
        if (!personaRepository.existsById(personaId)){
            throw new ResourceNotFoundException("No encontrada persona con id " + personaId);
        }
    skillRepository.deleteByPersonaId(personaId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
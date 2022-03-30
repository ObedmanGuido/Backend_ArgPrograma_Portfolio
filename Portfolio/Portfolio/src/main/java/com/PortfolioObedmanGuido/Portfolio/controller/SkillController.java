package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.entity.Skill;
import com.PortfolioObedmanGuido.Portfolio.exception.ResourceNotFoundException;
import com.PortfolioObedmanGuido.Portfolio.repository.PersonaRepository;
import com.PortfolioObedmanGuido.Portfolio.repository.SkillRepository;
import com.PortfolioObedmanGuido.Portfolio.service.PersonaService;
import com.PortfolioObedmanGuido.Portfolio.service.SkillService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @PostMapping("/{personaId}/skill/nuevo")
    public ResponseEntity<Skill> createSkill(@PathVariable(value = "personaId") Long personaId,
            @RequestBody Skill skillRequest){
        Skill skill = personaRepository.findById(personaId).map(persona -> {
            skillRequest.setPersona(persona);
            return skillRepository.save(skillRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No encontrada persona con id " + personaId));
        return new ResponseEntity<>(skill, HttpStatus.CREATED);
    }

    @PutMapping("/skill/actualizar/{id}")
    public ResponseEntity<?> update(@RequestBody Skill skill, @PathVariable("id") Long id){
        if(!skillService.existeId(id))
            return new ResponseEntity(new Mensaje("El skill no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(skill.getSkillname()))
            return new ResponseEntity(new Mensaje("Tiene que poner el nombre del skill (skillname)."), HttpStatus.BAD_REQUEST);
        Skill skillUpdate = skillService.buscarId(id).get();
        skillUpdate.setSkillname(skill.getSkillname());
        skillUpdate.setLevelname(skill.getLevelname());
        skillUpdate.setLevelnumber(skill.getLevelnumber());
        skillService.crearSkill(skillUpdate);
        return new ResponseEntity(new Mensaje("Skill actualizado."), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/skill/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!skillService.existeId(id))
            return new ResponseEntity(new Mensaje("El skill no existe (id)."), HttpStatus.NOT_FOUND);
        skillService.borrarSkill(id);
        return new ResponseEntity(new Mensaje("Skill borrado."), HttpStatus.OK);
    }
    
    @DeleteMapping("/{personaId}/skill/borrar")
    public ResponseEntity<List<Skill>> deleteAllSkillOfPersona(@PathVariable(value = "personaId") Long personaId) {
        if (!personaRepository.existsById(personaId)){
            throw new ResourceNotFoundException("No encontrada persona con id " + personaId);
        }
    skillRepository.deleteByPersonaId(personaId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.entity.Skill;
import com.PortfolioObedmanGuido.Portfolio.service.SkillService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skill")
@CrossOrigin(origins="http://localhost:4200")
public class SkillController {
    @Autowired
    SkillService skillService;
    
    @GetMapping("/lista")
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

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody Skill skill){
        if(StringUtils.isBlank(skill.getSkillname()))
            return new ResponseEntity(new Mensaje("Tiene que poner el nombre del skill (skillname)."), HttpStatus.BAD_REQUEST);
        if(skillService.existeSkill(skill.getSkillname()))
            return new ResponseEntity(new Mensaje("Ese skill (skillname) ya existe."), HttpStatus.BAD_REQUEST);
        skillService.crearSkill(skill);
        return new ResponseEntity(new Mensaje("Skill creado."), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
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
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!skillService.existeId(id))
            return new ResponseEntity(new Mensaje("El skill no existe (id)."), HttpStatus.NOT_FOUND);
        skillService.borrarSkill(id);
        return new ResponseEntity(new Mensaje("Skill borrado."), HttpStatus.OK);
    }
}

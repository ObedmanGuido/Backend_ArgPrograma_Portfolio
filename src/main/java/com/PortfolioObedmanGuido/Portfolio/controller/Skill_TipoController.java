package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.model.Skill_Tipo;
import com.PortfolioObedmanGuido.Portfolio.repository.SkillRepository;
import com.PortfolioObedmanGuido.Portfolio.repository.Skill_TipoRepository;
import com.PortfolioObedmanGuido.Portfolio.service.SkillService;
import com.PortfolioObedmanGuido.Portfolio.service.Skill_TipoService;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/personas")
@CrossOrigin(origins="*")
public class Skill_TipoController {
    @Autowired
    SkillService skillService;
    
    @Autowired
    SkillRepository skillRepository;
    
    @Autowired
    Skill_TipoRepository skill_TipoRepository;
    
    @Autowired
    Skill_TipoService skill_TipoService;
    
    @GetMapping("/skill/tipo/lista")
    public ResponseEntity<List<Skill_Tipo>> getLista(){
        List<Skill_Tipo> lista = skill_TipoService.obtenerTodos();
        return new ResponseEntity<List<Skill_Tipo>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/skill/tipo/{id}")
    public ResponseEntity<Skill_Tipo> getOne(@PathVariable Long id){
        if(!skill_TipoService.existeId(id))
            return new ResponseEntity(new Mensaje("El tipo de skill no existe (id)."), HttpStatus.NOT_FOUND);
        Skill_Tipo skill_Tipo = skill_TipoService.buscarId(id).get();
        return new ResponseEntity<Skill_Tipo>(skill_Tipo, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/skill/tipo/nuevo")
    public ResponseEntity<?> create(@RequestBody Skill_Tipo skill_Tipo){
        if(StringUtils.isBlank(skill_Tipo.getTypeName()))
            return new ResponseEntity(new Mensaje("Tiene que poner el nombre."), HttpStatus.BAD_REQUEST);
        if(skill_TipoService.existeTipoNombre(skill_Tipo.getTypeName()))
            return new ResponseEntity(new Mensaje("Ese tipo de skill ya existe."), HttpStatus.BAD_REQUEST);
        skill_TipoService.crearSkill_Tipo(skill_Tipo);
        return new ResponseEntity(new Mensaje("Tipo de skill creado."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/skill/tipo/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Skill_Tipo skill_Tipo, @PathVariable("id") Long id, Errors errors){
        if(!skill_TipoService.existeId(id))
            return new ResponseEntity(new Mensaje("El tipo de skill no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(skill_Tipo.getTypeName()))
            return new ResponseEntity(new Mensaje("Tiene que poner el nombre (typeName)."), HttpStatus.BAD_REQUEST);
        if(errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Skill_Tipo skill_TipoUpdate = skill_TipoService.buscarId(id).get();
        skill_TipoUpdate.setTypeName(skill_Tipo.getTypeName());
        skill_TipoService.crearSkill_Tipo(skill_TipoUpdate);
        return new ResponseEntity(new Mensaje("Tipo de skill actualizado."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/skill/tipo/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!skill_TipoService.existeId(id))
            return new ResponseEntity(new Mensaje("El tipo de skill no existe (id)."), HttpStatus.NOT_FOUND);
        skill_TipoService.borrarSkill_Tipo(id);
        return new ResponseEntity(new Mensaje("Tipo de skill borrado."), HttpStatus.OK);
    }
}
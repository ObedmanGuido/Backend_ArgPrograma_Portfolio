package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.model.Educacion_Tipo;
import com.PortfolioObedmanGuido.Portfolio.repository.EducacionRepository;
import com.PortfolioObedmanGuido.Portfolio.repository.Educacion_TipoRepository;
import com.PortfolioObedmanGuido.Portfolio.service.EducacionService;
import com.PortfolioObedmanGuido.Portfolio.service.Educacion_TipoService;
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
public class Educacion_TipoController {
    @Autowired
    EducacionService educacionService;
    
    @Autowired
    EducacionRepository educacionRepository;
    
    @Autowired
    Educacion_TipoRepository educacion_TipoRepository;
    
    @Autowired
    Educacion_TipoService educacion_TipoService;
    
    @GetMapping("/educacion/tipo/lista")
    public ResponseEntity<List<Educacion_Tipo>> getLista(){
        List<Educacion_Tipo> lista = educacion_TipoService.obtenerTodos();
        return new ResponseEntity<List<Educacion_Tipo>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/educacion/tipo/{id}")
    public ResponseEntity<Educacion_Tipo> getOne(@PathVariable Long id){
        if(!educacion_TipoService.existeId(id))
            return new ResponseEntity(new Mensaje("El tipo de educación no existe (id)."), HttpStatus.NOT_FOUND);
        Educacion_Tipo educacion_Tipo = educacion_TipoService.buscarId(id).get();
        return new ResponseEntity<Educacion_Tipo>(educacion_Tipo, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/educacion/tipo/nuevo")
    public ResponseEntity<?> create(@RequestBody Educacion_Tipo educacion_Tipo){
        if(StringUtils.isBlank(educacion_Tipo.getEducationType()))
            return new ResponseEntity(new Mensaje("Tiene que poner el tipo."), HttpStatus.BAD_REQUEST);
        if(educacion_TipoService.existeEducacionTipo(educacion_Tipo.getEducationType()))
            return new ResponseEntity(new Mensaje("Ese tipo de educación ya existe."), HttpStatus.BAD_REQUEST);
        educacion_TipoService.crearEducacion_Tipo(educacion_Tipo);
        return new ResponseEntity(new Mensaje("Tipo de educación creado."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/educacion/tipo/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Educacion_Tipo educacion_Tipo, @PathVariable("id") Long id, Errors errors){
        if(!educacion_TipoService.existeId(id))
            return new ResponseEntity(new Mensaje("El tipo de educación no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(educacion_Tipo.getEducationType()))
            return new ResponseEntity(new Mensaje("Tiene que poner el tipo (educationType)."), HttpStatus.BAD_REQUEST);
        if(errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Educacion_Tipo educacion_TipoUpdate = educacion_TipoService.buscarId(id).get();
        educacion_TipoUpdate.setEducationType(educacion_Tipo.getEducationType());
        educacion_TipoService.crearEducacion_Tipo(educacion_TipoUpdate);
        return new ResponseEntity(new Mensaje("Tipo de educación actualizado."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/educacion/tipo/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!educacion_TipoService.existeId(id))
            return new ResponseEntity(new Mensaje("El tipo de educación no existe (id)."), HttpStatus.NOT_FOUND);
        educacion_TipoService.borrarEducacion_Tipo(id);
        return new ResponseEntity(new Mensaje("Tipo de educación borrado."), HttpStatus.OK);
    }
}
package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.model.Educacion_Estado;
import com.PortfolioObedmanGuido.Portfolio.repository.EducacionRepository;
import com.PortfolioObedmanGuido.Portfolio.repository.Educacion_EstadoRepository;
import com.PortfolioObedmanGuido.Portfolio.service.EducacionService;
import com.PortfolioObedmanGuido.Portfolio.service.Educacion_EstadoService;
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
public class Educacion_EstadoController {
    @Autowired
    EducacionService educacionService;
    
    @Autowired
    EducacionRepository educacionRepository;
    
    @Autowired
    Educacion_EstadoRepository educacion_EstadoRepository;
    
    @Autowired
    Educacion_EstadoService educacion_EstadoService;
    
    @GetMapping("/educacion/estado/lista")
    public ResponseEntity<List<Educacion_Estado>> getLista(){
        List<Educacion_Estado> lista = educacion_EstadoService.obtenerTodos();
        return new ResponseEntity<List<Educacion_Estado>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/educacion/estado/{id}")
    public ResponseEntity<Educacion_Estado> getOne(@PathVariable Long id){
        if(!educacion_EstadoService.existeId(id))
            return new ResponseEntity(new Mensaje("El estado de educación no existe (id)."), HttpStatus.NOT_FOUND);
        Educacion_Estado educacion_Estado = educacion_EstadoService.buscarId(id).get();
        return new ResponseEntity<Educacion_Estado>(educacion_Estado, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/educacion/estado/nuevo")
    public ResponseEntity<?> create(@RequestBody Educacion_Estado educacion_Estado){
        if(StringUtils.isBlank(educacion_Estado.getEducationStatus()))
            return new ResponseEntity(new Mensaje("Tiene que poner el estado."), HttpStatus.BAD_REQUEST);
        if(educacion_EstadoService.existeEducacionEstado(educacion_Estado.getEducationStatus()))
            return new ResponseEntity(new Mensaje("Ese estado de educación ya existe."), HttpStatus.BAD_REQUEST);
        educacion_EstadoService.crearEducacion_Estado(educacion_Estado);
        return new ResponseEntity(new Mensaje("Estado de educación creado."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/educacion/estado/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Educacion_Estado educacion_Estado, @PathVariable("id") Long id, Errors errors){
        if(!educacion_EstadoService.existeId(id))
            return new ResponseEntity(new Mensaje("El estado de educación no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(educacion_Estado.getEducationStatus()))
            return new ResponseEntity(new Mensaje("Tiene que poner el estado (educationStatus)."), HttpStatus.BAD_REQUEST);
        if(errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Educacion_Estado educacion_EstadoUpdate = educacion_EstadoService.buscarId(id).get();
        educacion_EstadoUpdate.setEducationStatus(educacion_Estado.getEducationStatus());
        educacion_EstadoService.crearEducacion_Estado(educacion_EstadoUpdate);
        return new ResponseEntity(new Mensaje("Estado de educación actualizado."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/educacion/estado/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!educacion_EstadoService.existeId(id))
            return new ResponseEntity(new Mensaje("El estado de educación no existe (id)."), HttpStatus.NOT_FOUND);
        educacion_EstadoService.borrarEducacion_Estado(id);
        return new ResponseEntity(new Mensaje("Estado de educación borrado."), HttpStatus.OK);
    }
}
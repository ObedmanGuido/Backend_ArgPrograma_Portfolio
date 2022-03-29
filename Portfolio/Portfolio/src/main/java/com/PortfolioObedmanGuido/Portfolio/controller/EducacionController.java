package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.entity.Educacion;
import com.PortfolioObedmanGuido.Portfolio.service.EducacionService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/educacion")
@CrossOrigin(origins="*")
public class EducacionController {
    @Autowired
    EducacionService educacionService;
    
    @GetMapping("/lista")
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

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody Educacion educacion){
        if(StringUtils.isBlank(educacion.getTitle()))
            return new ResponseEntity(new Mensaje("Tiene que poner el título (title)."), HttpStatus.BAD_REQUEST);
        if(educacionService.existeTitulo(educacion.getTitle()))
            return new ResponseEntity(new Mensaje("Esa educación/título (title) ya existe."), HttpStatus.BAD_REQUEST);
        educacionService.crearEducacion(educacion);
        return new ResponseEntity(new Mensaje("Educación creada."), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
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
        educacionUpdate.setStatus(educacion.getStatus());
        educacionService.crearEducacion(educacionUpdate);
        return new ResponseEntity(new Mensaje("Educación actualizada."), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!educacionService.existeId(id))
            return new ResponseEntity(new Mensaje("La educación no existe (id)."), HttpStatus.NOT_FOUND);
        educacionService.borrarEducacion(id);
        return new ResponseEntity(new Mensaje("Educación borrada."), HttpStatus.OK);
    }
}
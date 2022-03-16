package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.entity.Experiencia_Laboral;
import com.PortfolioObedmanGuido.Portfolio.service.Experiencia_LaboralService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/experiencia-laboral")
@CrossOrigin(origins="http://localhost:4200")
public class Experiencia_LaboralController {
    @Autowired
    Experiencia_LaboralService experiencia_laboralService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia_Laboral>> getLista(){
        List<Experiencia_Laboral> lista = experiencia_laboralService.obtenerTodas();
        return new ResponseEntity<List<Experiencia_Laboral>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/trabajo/{id}")
    public ResponseEntity<Experiencia_Laboral> getOne(@PathVariable Long id){
        if(!experiencia_laboralService.existeId(id))
            return new ResponseEntity(new Mensaje("La experiencia laboral no existe (id)."), HttpStatus.NOT_FOUND);
        Experiencia_Laboral experiencia_laboral = experiencia_laboralService.buscarId(id).get();
        return new ResponseEntity<Experiencia_Laboral>(experiencia_laboral, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody Experiencia_Laboral experiencia_laboral){
        if(StringUtils.isBlank(experiencia_laboral.getCompany()))
            return new ResponseEntity(new Mensaje("Tiene que poner la companía (company)."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experiencia_laboral.getPosition()))
            return new ResponseEntity(new Mensaje("Tiene que poner la posición (position)."), HttpStatus.BAD_REQUEST);
        if(experiencia_laboralService.existePosicion(experiencia_laboral.getPosition()))
            return new ResponseEntity(new Mensaje("Esa posición (position) existe."), HttpStatus.BAD_REQUEST);
        experiencia_laboralService.crearExperiencia_Laboral(experiencia_laboral);
        return new ResponseEntity(new Mensaje("Experiencia laboral creada."), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@RequestBody Experiencia_Laboral experiencia_laboral, @PathVariable("id") Long id){
        if(!experiencia_laboralService.existeId(id))
            return new ResponseEntity(new Mensaje("La experiencia laboral no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(experiencia_laboral.getCompany()))
            return new ResponseEntity(new Mensaje("Tiene que poner la companía (company)."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experiencia_laboral.getPosition()))
            return new ResponseEntity(new Mensaje("Tiene que poner la posición (position)."), HttpStatus.BAD_REQUEST);
        Experiencia_Laboral experiencia_laboralUpdate = experiencia_laboralService.buscarId(id).get();
        experiencia_laboralUpdate.setCompany(experiencia_laboral.getCompany());
        experiencia_laboralUpdate.setPosition(experiencia_laboral.getPosition());
        experiencia_laboralUpdate.setLogo(experiencia_laboral.getLogo());
        experiencia_laboralUpdate.setStartmonth(experiencia_laboral.getStartmonth());
        experiencia_laboralUpdate.setStartyear(experiencia_laboral.getStartyear());
        experiencia_laboralUpdate.setEndmonth(experiencia_laboral.getEndmonth());
        experiencia_laboralUpdate.setEndyear(experiencia_laboral.getEndyear());
        experiencia_laboralService.crearExperiencia_Laboral(experiencia_laboralUpdate);
        return new ResponseEntity(new Mensaje("Experiencia laboral actualizada."), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!experiencia_laboralService.existeId(id))
            return new ResponseEntity(new Mensaje("La experiencia laboral no existe (id)."), HttpStatus.NOT_FOUND);
        experiencia_laboralService.borrarExperiencia_Laboral(id);
        return new ResponseEntity(new Mensaje("Experiencia laboral borrada."), HttpStatus.OK);
    }
}
package com.PortfolioObedmanGuido.Portfolio.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.entity.Persona;
import com.PortfolioObedmanGuido.Portfolio.entity.Provincia;
import com.PortfolioObedmanGuido.Portfolio.exception.ResourceNotFoundException;
import com.PortfolioObedmanGuido.Portfolio.repository.PersonaRepository;
import com.PortfolioObedmanGuido.Portfolio.repository.ProvinciaRepository;
import com.PortfolioObedmanGuido.Portfolio.service.PersonaService;
import com.PortfolioObedmanGuido.Portfolio.service.ProvinciaService;
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
public class ProvinciaController {
    @Autowired
    PersonaService personaService;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Autowired
    ProvinciaRepository provinciaRepository;
    
    @Autowired
    ProvinciaService provinciaService;
    
    @GetMapping("/provincia/lista")
    public ResponseEntity<List<Provincia>> getLista(){
        List<Provincia> lista = provinciaService.obtenerTodas();
        return new ResponseEntity<List<Provincia>>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/provincia/{id}")
    public ResponseEntity<Provincia> getOne(@PathVariable Long id){
        if(!provinciaService.existeId(id))
            return new ResponseEntity(new Mensaje("La provincia no existe (id)."), HttpStatus.NOT_FOUND);
        Provincia provincia = provinciaService.buscarId(id).get();
        return new ResponseEntity<Provincia>(provincia, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/provincia/nuevo")
    public ResponseEntity<?> create(@RequestBody Provincia provincia){
        if(StringUtils.isBlank(provincia.getProvincename()))
            return new ResponseEntity(new Mensaje("Tiene que poner el nombre."), HttpStatus.BAD_REQUEST);
        if(provinciaService.existeProvinciaNombre(provincia.getProvincename()))
            return new ResponseEntity(new Mensaje("Esa provincia (nombre) ya existe."), HttpStatus.BAD_REQUEST);
        provinciaService.crearProvincia(provincia);
        return new ResponseEntity(new Mensaje("Provincia creada."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/provincia/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Provincia provincia, @PathVariable("id") Long id, Errors errors){
        if(!provinciaService.existeId(id))
            return new ResponseEntity(new Mensaje("La provincia no existe (id)."), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(provincia.getProvincename()))
            return new ResponseEntity(new Mensaje("Tiene que poner el nombre (provincename)."), HttpStatus.BAD_REQUEST);
        if(errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Provincia provinciaUpdate = provinciaService.buscarId(id).get();
        provinciaUpdate.setProvincename(provincia.getProvincename());
        provinciaService.crearProvincia(provinciaUpdate);
        return new ResponseEntity(new Mensaje("Provincia actualizada."), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/provincia/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!provinciaService.existeId(id))
            return new ResponseEntity(new Mensaje("La provincia no existe (id)."), HttpStatus.NOT_FOUND);
        provinciaService.borrarProvincia(id);
        return new ResponseEntity(new Mensaje("Provincia borrada."), HttpStatus.OK);
    }
}

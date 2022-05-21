package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.PortfolioObedmanGuido.Portfolio.repository.PersonaRepository;

@Service
@Transactional
public class PersonaService implements IPersonaService {
    
    @Autowired
    public PersonaRepository personaRepository;

    @Override
    public List<Persona> obtenerTodas() {
        List<Persona> lista = personaRepository.findAll();
        return lista;
    }

    @Override
    public Optional<Persona> buscarId(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public Optional<Persona> buscarEmail(String em) {
        return personaRepository.findByEmail(em);
    }

    @Override
    public void crearPersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public void borrarPersona(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public boolean existeEmail(String em) {
        return personaRepository.existsByEmail(em);
    }

    @Override
    public boolean existeId(Long id) {
        return personaRepository.existsById(id);
    }
}
package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Persona;
import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    public List<Persona> obtenerTodas();
    public Optional<Persona> buscarId(Long id);
    public Optional<Persona> buscarEmail(String em);
    public void crearPersona(Persona persona);
    public void borrarPersona(Long id);
    public boolean existeEmail(String em);
    public boolean existeId(Long id);
}

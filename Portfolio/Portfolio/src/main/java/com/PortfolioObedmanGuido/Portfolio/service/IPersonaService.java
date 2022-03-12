package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Persona;
import java.util.List;
import java.util.Optional;


public interface IPersonaService {
    public List<Persona> obtenerTodas();
    public Optional<Persona> buscarId(Long id);
    public Optional<Persona> buscarNombreCompleto(String fn);
    public void crearPersona(Persona acercaDe);
    public void borrarPersona(Long id);
    public boolean existeNombreCompleto(String fn);
    public boolean existeId(Long id);
}

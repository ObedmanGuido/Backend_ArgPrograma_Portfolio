package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Educacion;
import java.util.List;
import java.util.Optional;

public interface IEducacionService {
    public List<Educacion> obtenerTodas();
    public Optional<Educacion> buscarId(Long id);
    public Optional<Educacion> buscarTitulo(String ti);
    public void crearEducacion(Educacion educacion);
    public void borrarEducacion(Long id);
    public boolean existeTitulo(String ti);
    public boolean existeId(Long id);
}

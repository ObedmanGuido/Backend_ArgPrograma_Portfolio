package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.model.Educacion_Estado;
import java.util.List;
import java.util.Optional;

public interface IEducacion_EstadoService {
    public List<Educacion_Estado> obtenerTodos();
    public Optional<Educacion_Estado> buscarId(Long id);
    public Optional<Educacion_Estado> buscarEducacionEstado(String es);
    public void crearEducacion_Estado(Educacion_Estado educacion_Estado);
    public void borrarEducacion_Estado(Long id);
    public boolean existeEducacionEstado(String es);
    public boolean existeId(Long id);
}
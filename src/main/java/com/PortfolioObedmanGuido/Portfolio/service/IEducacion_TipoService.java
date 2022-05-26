package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.model.Educacion_Tipo;
import java.util.List;
import java.util.Optional;

public interface IEducacion_TipoService {
    public List<Educacion_Tipo> obtenerTodos();
    public Optional<Educacion_Tipo> buscarId(Long id);
    public Optional<Educacion_Tipo> buscarEducacionTipo(String et);
    public void crearEducacion_Tipo(Educacion_Tipo educacion_Tipo);
    public void borrarEducacion_Tipo(Long id);
    public boolean existeEducacionTipo(String et);
    public boolean existeId(Long id);
}
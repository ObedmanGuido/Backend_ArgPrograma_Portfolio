package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.model.Proyecto;
import java.util.List;
import java.util.Optional;

public interface IProyectoService {
    public List<Proyecto> obtenerTodos();
    public Optional<Proyecto> buscarId(Long id);
    public Optional<Proyecto> buscarProyectoNombre(String pn);
    public void crearProyecto(Proyecto proyecto);
    public void borrarProyecto(Long id);
    public boolean existeProyectoNombre(String pn);
    public boolean existeId(Long id);
}
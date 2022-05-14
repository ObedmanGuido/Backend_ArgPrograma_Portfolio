package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Provincia;
import java.util.List;
import java.util.Optional;

public interface IProvinciaService {
    public List<Provincia> obtenerTodas();
    public Optional<Provincia> buscarId(Long id);
    public Optional<Provincia> buscarProvinciaNombre(String pn);
    public void crearProvincia(Provincia provincia);
    public void borrarProvincia(Long id);
    public boolean existeProvinciaNombre(String pn);
    public boolean existeId(Long id);
}
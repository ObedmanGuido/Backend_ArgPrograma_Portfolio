package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.model.Experiencia_Laboral;
import java.util.List;
import java.util.Optional;

public interface IExperiencia_LaboralService {
    public List<Experiencia_Laboral> obtenerTodas();
    public Optional<Experiencia_Laboral> buscarId(Long id);
    public Optional<Experiencia_Laboral> buscarPosicion(String po);
    public void crearExperiencia_Laboral(Experiencia_Laboral experiencia_laboral);
    public void borrarExperiencia_Laboral(Long id);
    public boolean existePosicion(String po);
    public boolean existeId(Long id);
}
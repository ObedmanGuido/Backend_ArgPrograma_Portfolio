package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Skill_Tipo;
import java.util.List;
import java.util.Optional;

public interface ISkill_TipoService {
    public List<Skill_Tipo> obtenerTodos();
    public Optional<Skill_Tipo> buscarId(Long id);
    public Optional<Skill_Tipo> buscarTipoNombre(String tn);
    public void crearSkill_Tipo(Skill_Tipo skill_Tipo);
    public void borrarSkill_Tipo(Long id);
    public boolean existeTipoNombre(String tn);
    public boolean existeId(Long id);
}
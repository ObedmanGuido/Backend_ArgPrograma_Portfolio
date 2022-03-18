package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Skill;
import java.util.List;
import java.util.Optional;

public interface ISkillService {
    public List<Skill> obtenerTodas();
    public Optional<Skill> buscarId(Long id);
    public Optional<Skill> buscarSkill(String sk);
    public void crearSkill(Skill skill);
    public void borrarSkill(Long id);
    public boolean existeSkill(String sk);
    public boolean existeId(Long id);
}

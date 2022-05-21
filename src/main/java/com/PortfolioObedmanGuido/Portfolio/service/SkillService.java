package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.model.Skill;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.PortfolioObedmanGuido.Portfolio.repository.SkillRepository;

@Service
@Transactional
public class SkillService implements ISkillService{
    @Autowired
    public SkillRepository skillRepository;

    @Override
    public List<Skill> obtenerTodas() {
        List<Skill> lista = skillRepository.findAll();
        return lista;
    }

    @Override
    public Optional<Skill> buscarId(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public Optional<Skill> buscarSkill(String sk) {
        return skillRepository.findBySkillName(sk);
    }

    @Override
    public void crearSkill(Skill skill) {
        skillRepository.save(skill);
    }

    @Override
    public void borrarSkill(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public boolean existeSkill(String sk) {
        return skillRepository.existsBySkillName(sk);
    }

    @Override
    public boolean existeId(Long id) {
        return skillRepository.existsById(id);
    }
}
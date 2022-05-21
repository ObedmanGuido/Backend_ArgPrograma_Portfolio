package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Skill_Tipo;
import com.PortfolioObedmanGuido.Portfolio.repository.Skill_TipoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Skill_TipoService implements ISkill_TipoService{
    @Autowired
    public Skill_TipoRepository skill_TipoRepository;
            
    @Override
    public List<Skill_Tipo> obtenerTodos() {
        List<Skill_Tipo> lista = skill_TipoRepository.findAll();
        return lista;
    }

    @Override
    public Optional<Skill_Tipo> buscarId(Long id) {
        return skill_TipoRepository.findById(id);
    }

    @Override
    public Optional<Skill_Tipo> buscarTipoNombre(String tn) {
        return skill_TipoRepository.findByTypename(tn);
    }

    @Override
    public void crearSkill_Tipo(Skill_Tipo skill_Tipo) {
        skill_TipoRepository.save(skill_Tipo);
    }

    @Override
    public void borrarSkill_Tipo(Long id) {
        skill_TipoRepository.deleteById(id);
    }

    @Override
    public boolean existeTipoNombre(String tn) {
        return skill_TipoRepository.existsByTypename(tn);
    }

    @Override
    public boolean existeId(Long id) {
        return skill_TipoRepository.existsById(id);
    }
}
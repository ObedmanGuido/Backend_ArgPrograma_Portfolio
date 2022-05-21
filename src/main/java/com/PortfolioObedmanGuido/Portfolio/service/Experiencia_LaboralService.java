package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.model.Experiencia_Laboral;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.PortfolioObedmanGuido.Portfolio.repository.Experiencia_LaboralRepository;

@Service
@Transactional
public class Experiencia_LaboralService implements IExperiencia_LaboralService{
    @Autowired
    public Experiencia_LaboralRepository experiencia_laboralRepository;

    @Override
    public List<Experiencia_Laboral> obtenerTodas() {
        List<Experiencia_Laboral> lista = experiencia_laboralRepository.findAll();
        return lista;
    }

    @Override
    public Optional<Experiencia_Laboral> buscarId(Long id) {
        return experiencia_laboralRepository.findById(id);
    }

    @Override
    public Optional<Experiencia_Laboral> buscarPosicion(String po) {
        return experiencia_laboralRepository.findByPosition(po);
    }

    @Override
    public void crearExperiencia_Laboral(Experiencia_Laboral experiencia_laboral) {
        experiencia_laboralRepository.save(experiencia_laboral);
    }

    @Override
    public void borrarExperiencia_Laboral(Long id) {
        experiencia_laboralRepository.deleteById(id);
    }

    @Override
    public boolean existePosicion(String po) {
        return experiencia_laboralRepository.existsByPosition(po);
    }

    @Override
    public boolean existeId(Long id) {
        return experiencia_laboralRepository.existsById(id);
    }
}

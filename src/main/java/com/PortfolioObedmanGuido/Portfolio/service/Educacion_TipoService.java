package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.model.Educacion_Tipo;
import com.PortfolioObedmanGuido.Portfolio.repository.Educacion_TipoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Educacion_TipoService implements IEducacion_TipoService{
    @Autowired
    public Educacion_TipoRepository educacion_TipoRepository;
    
    @Override
    public List<Educacion_Tipo> obtenerTodos() {
        List<Educacion_Tipo> lista = educacion_TipoRepository.findAll();
        return lista;
    }

    @Override
    public Optional<Educacion_Tipo> buscarId(Long id) {
        return educacion_TipoRepository.findById(id);
    }

    @Override
    public Optional<Educacion_Tipo> buscarEducacionTipo(String et) {
        return educacion_TipoRepository.findByEducationType(et);
    }

    @Override
    public void crearEducacion_Tipo(Educacion_Tipo educacion_Tipo) {
        educacion_TipoRepository.save(educacion_Tipo);
    }

    @Override
    public void borrarEducacion_Tipo(Long id) {
        educacion_TipoRepository.deleteById(id);
    }

    @Override
    public boolean existeEducacionTipo(String et) {
        return educacion_TipoRepository.existsByEducationType(et);
    }

    @Override
    public boolean existeId(Long id) {
        return educacion_TipoRepository.existsById(id);
    }
}
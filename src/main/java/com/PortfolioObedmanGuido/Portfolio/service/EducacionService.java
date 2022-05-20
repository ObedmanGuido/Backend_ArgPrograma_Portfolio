package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Educacion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.PortfolioObedmanGuido.Portfolio.repository.EducacionRepository;

@Service
@Transactional
public class EducacionService implements IEducacionService{
    
    @Autowired
    public EducacionRepository educacionRepository;

    @Override
    public List<Educacion> obtenerTodas() {
        List<Educacion> lista = educacionRepository.findAll();
        return lista;
    }

    @Override
    public Optional<Educacion> buscarId(Long id) {
        return educacionRepository.findById(id);
    }

    @Override
    public Optional<Educacion> buscarTitulo(String ti) {
        return educacionRepository.findByTitle(ti);
    }

    @Override
    public void crearEducacion(Educacion educacion) {
        educacionRepository.save(educacion);
    }

    @Override
    public void borrarEducacion(Long id) {
        educacionRepository.deleteById(id);
    }

    @Override
    public boolean existeTitulo(String ti) {
        return educacionRepository.existsByTitle(ti);
    }

    @Override
    public boolean existeId(Long id) {
        return educacionRepository.existsById(id);
    }
    
}
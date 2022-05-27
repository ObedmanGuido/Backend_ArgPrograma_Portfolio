package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.model.Educacion_Estado;
import com.PortfolioObedmanGuido.Portfolio.repository.Educacion_EstadoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Educacion_EstadoService implements IEducacion_EstadoService{
    @Autowired
    public Educacion_EstadoRepository educacion_EstadoRepository;
    
    @Override
    public List<Educacion_Estado> obtenerTodos() {
        List<Educacion_Estado> lista = educacion_EstadoRepository.findAll();
        return lista;
    }

    @Override
    public Optional<Educacion_Estado> buscarId(Long id) {
        return educacion_EstadoRepository.findById(id);
    }

    @Override
    public Optional<Educacion_Estado> buscarEducacionEstado(String es) {
        return educacion_EstadoRepository.findByEducationStatus(es);
    }

    @Override
    public void crearEducacion_Estado(Educacion_Estado educacion_Estado) {
        educacion_EstadoRepository.save(educacion_Estado);
    }

    @Override
    public void borrarEducacion_Estado(Long id) {
        educacion_EstadoRepository.deleteById(id);
    }

    @Override
    public boolean existeEducacionEstado(String es) {
        return educacion_EstadoRepository.existsByEducationStatus(es);
    }

    @Override
    public boolean existeId(Long id) {
        return educacion_EstadoRepository.existsById(id);
    }
}
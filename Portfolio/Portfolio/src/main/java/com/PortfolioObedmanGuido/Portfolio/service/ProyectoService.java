package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Proyecto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.PortfolioObedmanGuido.Portfolio.repository.ProyectoRepository;

@Service
@Transactional
public class ProyectoService implements IProyectoService{
    @Autowired
    public ProyectoRepository proyectoRepository;
    
    @Override
    public List<Proyecto> obtenerTodos() {
        List<Proyecto> lista = proyectoRepository.findAll();
        return lista;
    }

    @Override
    public Optional<Proyecto> buscarId(Long id) {
        return proyectoRepository.findById(id);
    }

    @Override
    public Optional<Proyecto> buscarProyectoNombre(String pn) {
        return proyectoRepository.findByProjectname(pn);
    }

    @Override
    public void crearProyecto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    @Override
    public void borrarProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public boolean existeProyectoNombre(String pn) {
        return proyectoRepository.existsByProjectname(pn);
    }

    @Override
    public boolean existeId(Long id) {
        return proyectoRepository.existsById(id);
    }
}
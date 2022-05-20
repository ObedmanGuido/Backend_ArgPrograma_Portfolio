package com.PortfolioObedmanGuido.Portfolio.service;

import com.PortfolioObedmanGuido.Portfolio.entity.Provincia;
import com.PortfolioObedmanGuido.Portfolio.repository.ProvinciaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProvinciaService implements IProvinciaService{
    @Autowired
    public ProvinciaRepository provinciaRepository;
    
    @Override
    public List<Provincia> obtenerTodas() {
        List<Provincia> lista = provinciaRepository.findAll();
        return lista;
    }

    @Override
    public Optional<Provincia> buscarId(Long id) {
        return provinciaRepository.findById(id);
    }

    @Override
    public Optional<Provincia> buscarProvinciaNombre(String pn) {
        return provinciaRepository.findByProvincename(pn);
    }

    @Override
    public void crearProvincia(Provincia provincia) {
        provinciaRepository.save(provincia);
    }

    @Override
    public void borrarProvincia(Long id) {
        provinciaRepository.deleteById(id);
    }

    @Override
    public boolean existeProvinciaNombre(String pn) {
        return provinciaRepository.existsByProvincename(pn);
    }

    @Override
    public boolean existeId(Long id) {
        return provinciaRepository.existsById(id);
    }
}
package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.model.Educacion_Estado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Educacion_EstadoRepository extends JpaRepository<Educacion_Estado, Long>{
    Optional<Educacion_Estado> findByEducationStatus(String es);
    boolean existsByEducationStatus(String es);
}
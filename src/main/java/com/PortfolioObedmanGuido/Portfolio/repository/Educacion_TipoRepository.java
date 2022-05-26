package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.model.Educacion_Tipo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Educacion_TipoRepository extends JpaRepository<Educacion_Tipo, Long>{
    Optional<Educacion_Tipo> findByEducationType(String et);
    boolean existsByEducationType(String et);
}
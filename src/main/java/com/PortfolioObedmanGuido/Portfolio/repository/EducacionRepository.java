package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.entity.Educacion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Long>{
    Optional<Educacion> findByTitle(String ti);
    boolean existsByTitle(String ti);
    List<Educacion> findByPersonaId(Long eduId);
    
    @Transactional
    void deleteByPersonaId(long personaId);
}
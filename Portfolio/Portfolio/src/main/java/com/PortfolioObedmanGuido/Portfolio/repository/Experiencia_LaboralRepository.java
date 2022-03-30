package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.entity.Experiencia_Laboral;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Experiencia_LaboralRepository extends JpaRepository<Experiencia_Laboral, Long>{
    Optional<Experiencia_Laboral> findByPosition(String po);
    boolean existsByPosition(String po);
    List<Experiencia_Laboral> findByPersonaId(Long expId);
    
    @Transactional
    void deleteByPersonaId(long personaId);
}
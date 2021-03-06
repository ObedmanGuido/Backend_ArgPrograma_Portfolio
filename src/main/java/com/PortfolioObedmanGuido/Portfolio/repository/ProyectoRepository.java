package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.model.Proyecto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long>{
    Optional<Proyecto> findByProjectName(String pn);
    boolean existsByProjectName(String pn);
    List<Proyecto> findByPersonaId(Long pnId);
    
    @Transactional
    void deleteByPersonaId(long personaId);
}
package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.entity.Skill_Tipo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Skill_TipoRepository extends JpaRepository<Skill_Tipo, Long>{
    Optional<Skill_Tipo> findByTypename(String tn);
    boolean existsByTypename(String tn);
}
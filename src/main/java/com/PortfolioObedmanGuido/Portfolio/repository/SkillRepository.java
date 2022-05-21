package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.model.Skill;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{
    Optional<Skill> findBySkillName(String sk);
    boolean existsBySkillName(String sk);
    List<Skill> findByPersonaId(Long skiId);
    
    @Transactional
    void deleteByPersonaId(long personaId);
}
package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.entity.Skill;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{
    Optional<Skill> findBySkillname(String sk);
    boolean existsBySkillname(String sk);
    List<Skill> findByPersonaId(Long skiId);
    
    @Transactional
    void deleteByPersonaId(long personaId);
}
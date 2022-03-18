package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.entity.Skill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SkillRepository extends JpaRepository<Skill, Long>{
    Optional<Skill> findBySkillname(String sk);
    boolean existsBySkillname(String sk);
}
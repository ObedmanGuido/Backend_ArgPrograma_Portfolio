package com.PortfolioObedmanGuido.Portfolio.repository;

import com.PortfolioObedmanGuido.Portfolio.model.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    Optional<Persona> findByEmail(String em);
    boolean existsByEmail(String em);
}
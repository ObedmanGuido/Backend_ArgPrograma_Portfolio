package com.PortfolioObedmanGuido.Portfolio.security.repository;

import com.PortfolioObedmanGuido.Portfolio.security.entity.Rol;
import com.PortfolioObedmanGuido.Portfolio.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
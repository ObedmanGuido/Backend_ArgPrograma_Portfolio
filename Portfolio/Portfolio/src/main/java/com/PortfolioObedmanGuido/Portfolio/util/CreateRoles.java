/*package com.PortfolioObedmanGuido.Portfolio.util;

import com.PortfolioObedmanGuido.Portfolio.security.entity.Rol;
import com.PortfolioObedmanGuido.Portfolio.security.enums.RolNombre;
import com.PortfolioObedmanGuido.Portfolio.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {
    
    @Autowired
    RolService rolService;
    
    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
    }
}
Crear roles solo se usaría para crear los roles cuando se hace la BBDD.*/
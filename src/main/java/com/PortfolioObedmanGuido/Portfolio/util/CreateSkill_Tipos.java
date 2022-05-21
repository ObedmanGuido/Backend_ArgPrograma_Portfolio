/*package com.PortfolioObedmanGuido.Portfolio.util;

import com.PortfolioObedmanGuido.Portfolio.model.Skill_Tipo;
import com.PortfolioObedmanGuido.Portfolio.service.Skill_TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateSkill_Tipos implements CommandLineRunner{
    @Autowired
    Skill_TipoService skill_TipoService;
    
    @Override
    public void run(String... args) throws Exception {
        Skill_Tipo Idioma = new Skill_Tipo(null, "Idioma");
        skill_TipoService.crearSkill_Tipo(Idioma);
        Skill_Tipo Programacion = new Skill_Tipo(null, "Programación y afines");
        skill_TipoService.crearSkill_Tipo(Programacion);
        Skill_Tipo Diseno = new Skill_Tipo(null, "Diseño");
        skill_TipoService.crearSkill_Tipo(Diseno);
        Skill_Tipo Sociales = new Skill_Tipo(null, "Habilidades sociales");
        skill_TipoService.crearSkill_Tipo(Sociales);
        Skill_Tipo Otros = new Skill_Tipo(null, "Otros");
        skill_TipoService.crearSkill_Tipo(Otros);
    }
} Crear tipos de skills solo se usaría al crear BBDD.*/
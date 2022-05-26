/*package com.PortfolioObedmanGuido.Portfolio.util;

import com.PortfolioObedmanGuido.Portfolio.model.Educacion_Tipo;
import com.PortfolioObedmanGuido.Portfolio.service.Educacion_TipoService;
import com.PortfolioObedmanGuido.Portfolio.model.Skill_Tipo;
import com.PortfolioObedmanGuido.Portfolio.service.Skill_TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateTipos_Skill_Educacion implements CommandLineRunner{
    @Autowired
    Skill_TipoService skill_TipoService;
    
    @Autowired
    Educacion_TipoService educacion_TipoService;
    
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
        Educacion_Tipo Primaria = new Educacion_Tipo(null, "Escuela Primaria");
        educacion_TipoService.crearEducacion_Tipo(Primaria);
        Educacion_Tipo Secundaria = new Educacion_Tipo(null, "Escuela Secundaria");
        educacion_TipoService.crearEducacion_Tipo(Secundaria);
        Educacion_Tipo Terciaria = new Educacion_Tipo(null, "Escuela Terciaria");
        educacion_TipoService.crearEducacion_Tipo(Terciaria);
        Educacion_Tipo Universidad = new Educacion_Tipo(null, "Universidad");
        educacion_TipoService.crearEducacion_Tipo(Universidad);
        Educacion_Tipo Posgrado = new Educacion_Tipo(null, "Posgrado");
        educacion_TipoService.crearEducacion_Tipo(Posgrado);
        Educacion_Tipo Master = new Educacion_Tipo(null, "Master");
        educacion_TipoService.crearEducacion_Tipo(Master);
        Educacion_Tipo Doctorado = new Educacion_Tipo(null, "Doctorado");
        educacion_TipoService.crearEducacion_Tipo(Doctorado);
        Educacion_Tipo Otro = new Educacion_Tipo(null, "Otro");
        educacion_TipoService.crearEducacion_Tipo(Otro);
    }
} Solo se usa la primera vez que se enciende el backend*/
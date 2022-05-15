/*package com.PortfolioObedmanGuido.Portfolio.util;*/

/*import com.PortfolioObedmanGuido.Portfolio.entity.Provincia;
import com.PortfolioObedmanGuido.Portfolio.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateProvincias implements CommandLineRunner{
    @Autowired
    ProvinciaService provinciaService;
    
    @Override
    public void run(String... args) throws Exception {
        Provincia BA = new Provincia(null, "Buenos Aires");
        provinciaService.crearProvincia(BA);
        Provincia CABA = new Provincia(null, "Ciudad Autónoma de Buenos Aires");
        provinciaService.crearProvincia(CABA);
        Provincia Catamarca = new Provincia(null, "Catamarca");
        provinciaService.crearProvincia(Catamarca);
        Provincia Chaco = new Provincia(null, "Chaco");
        provinciaService.crearProvincia(Chaco);
        Provincia Chubut = new Provincia(null, "Chubut");
        provinciaService.crearProvincia(Chubut);
        Provincia Cordoba = new Provincia(null, "Córdoba");
        provinciaService.crearProvincia(Cordoba);
        Provincia Corrientes = new Provincia(null, "Corrientes");
        provinciaService.crearProvincia(Corrientes);
        Provincia ER = new Provincia(null, "Entre Ríos");
        provinciaService.crearProvincia(ER);
        Provincia Formosa = new Provincia(null, "Formosa");
        provinciaService.crearProvincia(Formosa);
        Provincia Jujuy = new Provincia(null, "Jujuy");
        provinciaService.crearProvincia(Jujuy);
        Provincia LP = new Provincia(null, "La Pampa");
        provinciaService.crearProvincia(LP);
        Provincia LR = new Provincia(null, "La Rioja");
        provinciaService.crearProvincia(LR);
        Provincia Mendoza = new Provincia(null, "Mendoza");
        provinciaService.crearProvincia(Mendoza);
        Provincia Misiones = new Provincia(null, "Misiones");
        provinciaService.crearProvincia(Misiones);
        Provincia Neuquen = new Provincia(null, "Neuquén");
        provinciaService.crearProvincia(Neuquen);
        Provincia RN = new Provincia(null, "Río Negro");
        provinciaService.crearProvincia(RN);
        Provincia Salta = new Provincia(null, "Salta");
        provinciaService.crearProvincia(Salta);
        Provincia SJ = new Provincia(null, "San Juan");
        provinciaService.crearProvincia(SJ);
        Provincia SL = new Provincia(null, "San Luis");
        provinciaService.crearProvincia(SL);
        Provincia SC = new Provincia(null, "Santa Cruz");
        provinciaService.crearProvincia(SC);
        Provincia SF = new Provincia(null, "Santa Fe");
        provinciaService.crearProvincia(SF);
        Provincia SDE = new Provincia(null, "Santiago del Estero");
        provinciaService.crearProvincia(SDE);
        Provincia TDF = new Provincia(null, "Tierra del Fuego, Antártida e Islas del Atlántico Sur");
        provinciaService.crearProvincia(TDF);
        Provincia Tucuman = new Provincia(null, "Tucumán");
        provinciaService.crearProvincia(Tucuman);
        Provincia NEELA = new Provincia(null, "No está en la Argentina");
        provinciaService.crearProvincia(NEELA);
    }
}
Crear provincias se usa cuando se crea la BBDD para crear provincias y después quedaría comentada para que no se creen cada vez que se inicia backend.*/
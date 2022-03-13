package com.PortfolioObedmanGuido.Portfolio.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "educacion")
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String schoolname;
    
    private String title;
    
    private String logo;
    
    private String startyear;
    
    private String endyear;
    
    private String typeofschool;
    
    private String status;

    public Educacion() {
    }

    public Educacion(Long id, String schoolname, String title, String logo, String startyear, String endyear, String typeofschool, String status) {
        this.id = id;
        this.schoolname = schoolname;
        this.title = title;
        this.logo = logo;
        this.startyear = startyear;
        this.endyear = endyear;
        this.typeofschool = typeofschool;
        this.status = status;
    }
    
}

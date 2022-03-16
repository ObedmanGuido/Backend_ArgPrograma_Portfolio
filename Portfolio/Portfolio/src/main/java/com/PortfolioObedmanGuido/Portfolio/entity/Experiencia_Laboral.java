package com.PortfolioObedmanGuido.Portfolio.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "experiencia_laboral")
public class Experiencia_Laboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String company;
    
    private String position;
    
    private String logo;
    
    private String startmonth;
    
    private String startyear;
    
    private String endmonth;
    
    private String endyear;

    public Experiencia_Laboral() {
    }

    public Experiencia_Laboral(Long id, String company, String position, String logo, String startmonth, String startyear, String endmonth, String endyear) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.logo = logo;
        this.startmonth = startmonth;
        this.startyear = startyear;
        this.endmonth = endmonth;
        this.endyear = endyear;
    }
 
}

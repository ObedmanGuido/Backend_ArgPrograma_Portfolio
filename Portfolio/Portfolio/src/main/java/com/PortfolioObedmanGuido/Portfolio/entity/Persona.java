package com.PortfolioObedmanGuido.Portfolio.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullname;
    
    private String name;
    
    private String surname;
    
    private String profilepicture;
    
    private String title;
    
    private String position;

    public Persona() {
    }

    public Persona(String fullname, String name, String surname, String profilepicture, String title, String position) {
        this.fullname = fullname;
        this.name = name;
        this.surname = surname;
        this.profilepicture = profilepicture;
        this.title = title;
        this.position = position;
    }
    
}

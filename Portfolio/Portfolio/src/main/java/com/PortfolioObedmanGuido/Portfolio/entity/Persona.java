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
    @Column(name="fullname")
    private String fullname;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="profilepicture")
    private String profilepicture;
    @Column(name="title")
    private String title;
    @Column(name="position")
    private String position;
    @Column(name="bannerpicture")
    private String bannerpicture;
    @Column(name="aboutpersona")
    private String aboutpersona;

    public Persona() {
    }

    public Persona(String fullname, String name, String surname, String profilepicture, String title, String position, String bannerpicture, String aboutpersona) {
        this.fullname = fullname;
        this.name = name;
        this.surname = surname;
        this.profilepicture = profilepicture;
        this.title = title;
        this.position = position;
        this.bannerpicture = bannerpicture;
        this.aboutpersona = aboutpersona;
    }
    
}

package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="fullname")
    private String fullname;
    @NotEmpty
    @Column(name="name")
    private String name;
    @NotEmpty
    @Column(name="surname")
    private String surname;
    @NotEmpty
    @Column(name="profilepicture")
    private String profilepicture;
    @NotEmpty
    @Column(name="title")
    private String title;
    @NotEmpty
    @Column(name="position")
    private String position;
    @NotEmpty
    @Column(name="bannerpicture")
    private String bannerpicture;
    @NotEmpty
    @Column(name="aboutpersona")
    private String aboutpersona;
    @NotEmpty
    @Column(name="username", unique=true)
    private String username;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Educacion> educacion;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Experiencia_Laboral> experiencia_laboral;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Skill> skill;
    
    public Persona() {
    }

    public Persona(String fullname, String name, String surname, String profilepicture, String title, String position, String bannerpicture, String aboutpersona, String username) {
        this.fullname = fullname;
        this.name = name;
        this.surname = surname;
        this.profilepicture = profilepicture;
        this.title = title;
        this.position = position;
        this.bannerpicture = bannerpicture;
        this.aboutpersona = aboutpersona;
        this.username = username;
    }
    
}

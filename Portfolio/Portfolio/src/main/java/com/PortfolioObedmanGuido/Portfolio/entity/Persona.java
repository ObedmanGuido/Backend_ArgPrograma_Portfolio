package com.PortfolioObedmanGuido.Portfolio.entity;

import com.PortfolioObedmanGuido.Portfolio.security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter @Setter
@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="name", nullable = false)
    private String name;
    @NotEmpty
    @Column(name="surname", nullable = false)
    private String surname;
    @Column(name="profilepicture")
    private String profilepicture;
    @NotEmpty
    @Column(name="title", nullable = false)
    private String title;
    @NotEmpty
    @Column(name="position", nullable = false)
    private String position;
    @Column(name="bannerpicture")
    private String bannerpicture;
    @NotEmpty
    @Column(name="aboutpersona", nullable = false, length = 1000)
    private String aboutpersona;
    @NotEmpty
    @Column(name="address", nullable = false)
    private String address;
    @NotNull
    @Column(name="dateofbirth", nullable = false)
    private LocalDate dateofbirth;
    @NotNull
    @Column(name="telephone", nullable = false)
    private String telephone;
    @NotNull
    @Column(name="email", nullable = false)
    private String email;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Educacion> educacion;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Experiencia_Laboral> experiencia_laboral;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Skill> skill;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Proyecto> proyecto;
    @OneToOne
    @JoinTable(name = "persona_usuario", joinColumns = { @JoinColumn(name = "persona_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "usuario_id", referencedColumnName = "id") })
    private Usuario usuario;
    
    public Persona() {
    }

    public Persona(String name, String surname, String profilepicture, String title, String position,
            String bannerpicture, String aboutpersona, String address, LocalDate dateofbirth, String telephone, String email) {
        this.name = name;
        this.surname = surname;
        this.profilepicture = profilepicture;
        this.title = title;
        this.position = position;
        this.bannerpicture = bannerpicture;
        this.aboutpersona = aboutpersona;
        this.address = address;
        this.dateofbirth = dateofbirth;
        this.telephone = telephone;
        this.email = email;
    }
}
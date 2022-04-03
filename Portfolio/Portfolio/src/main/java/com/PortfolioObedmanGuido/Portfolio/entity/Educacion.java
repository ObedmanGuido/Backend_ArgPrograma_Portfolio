package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@Entity
@Table(name = "educacion")
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="schoolname")
    private String schoolname;
    @NotEmpty
    @Column(name="title")
    private String title;
    @Column(name="logo")
    private String logo;
    @NotEmpty
    @Length(min=4, max = 4)
    @Column(name="startyear")
    private int startyear;
    @NotEmpty
    @Length(min=4, max = 4)
    @Column(name="endyear")
    private int endyear;
    @NotEmpty
    @Column(name="typeofschool")
    private String typeofschool;
    @NotEmpty
    @Column(name="studiesstatus")
    private String studiesstatus;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;

    public Educacion() {
    }

    public Educacion(Long id, String schoolname, String title, String logo, int startyear, int endyear, String typeofschool, String studiesstatus) {
        this.id = id;
        this.schoolname = schoolname;
        this.title = title;
        this.logo = logo;
        this.startyear = startyear;
        this.endyear = endyear;
        this.typeofschool = typeofschool;
        this.studiesstatus = studiesstatus;
    }
    
}

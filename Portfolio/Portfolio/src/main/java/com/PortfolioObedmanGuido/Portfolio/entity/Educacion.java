package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Getter @Setter
@Entity
@Table(name = "educacion")
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="schoolname", nullable = false)
    private String schoolname;
    @NotEmpty
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="logo")
    private String logo;
    @NotNull
    @Range(min = 1900, max = 2050, message = "Año de inicio debe estar entre 1900 y 2050")
    @Column(name="startyear", nullable = false)
    private int startyear;
    @NotNull
    @Range(min = 1900, max = 2050, message = "Año de fin debe estar entre 1900 y 2050")
    @Column(name="endyear", nullable = false)
    private int endyear;
    @NotEmpty
    @Column(name="typeofschool", nullable = false)
    private String typeofschool;
    @NotEmpty
    @Column(name="studiesstatus", nullable = false)
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

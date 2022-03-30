package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter @Setter
@Entity
@Table(name = "educacion")
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="schoolname")
    private String schoolname;
    @Column(name="title")
    private String title;
    @Column(name="logo")
    private String logo;
    @Column(name="startyear")
    private String startyear;
    @Column(name="endyear")
    private String endyear;
    @Column(name="typeofschool")
    private String typeofschool;
    @Column(name="studiesstatus")
    private String studiesstatus;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;

    public Educacion() {
    }

    public Educacion(Long id, String schoolname, String title, String logo, String startyear, String endyear, String typeofschool, String studiesstatus) {
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

package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter @Setter
@Entity
@Table(name = "experiencia_laboral")
public class Experiencia_Laboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="company")
    private String company;
    @Column(name="position")
    private String position;
    @Column(name="logo")
    private String logo;
    @Column(name="startmonth")
    private String startmonth;
    @Column(name="startyear")
    private String startyear;
    @Column(name="endmonth")
    private String endmonth;
    @Column(name="endyear")
    private String endyear;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;
    

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

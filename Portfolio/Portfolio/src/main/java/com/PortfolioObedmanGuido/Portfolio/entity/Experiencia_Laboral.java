package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

@Getter @Setter
@Entity
@Table(name = "experiencia_laboral")
public class Experiencia_Laboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="company", nullable = false)
    private String company;
    @NotEmpty
    @Column(name="position", nullable = false)
    private String position;
    @Column(name="logo")
    private String logo;
    @NotEmpty
    @Column(name="startmonth", nullable = false)
    private String startmonth;
    @NotNull
    @Range(min = 1900, max = 2050, message = "Año de inicio debe estar entre 1900 y 2050")
    @Column(name="startyear", nullable = false)
    private int startyear;
    @NotEmpty
    @Column(name="endmonth", nullable = false)
    private String endmonth;
    @NotNull
    @Range(min = 1900, max = 2050, message = "Año de fin debe estar entre 1900 y 2050")
    @Column(name="endyear", nullable = false)
    private int endyear;
    @NotEmpty
    @Column(name="workdescription", nullable = false, length = 1000)
    private String workdescription;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;
    

    public Experiencia_Laboral() {
    }

    public Experiencia_Laboral(Long id, String company, String position, String logo, String startmonth, int startyear, String endmonth, int endyear, String workdescription) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.logo = logo;
        this.startmonth = startmonth;
        this.startyear = startyear;
        this.endmonth = endmonth;
        this.endyear = endyear;
        this.workdescription = workdescription;
    }
}
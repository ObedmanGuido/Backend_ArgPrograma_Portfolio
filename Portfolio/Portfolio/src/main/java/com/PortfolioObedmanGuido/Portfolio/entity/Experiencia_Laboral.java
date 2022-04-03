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
@Table(name = "experiencia_laboral")
public class Experiencia_Laboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="company")
    private String company;
    @NotEmpty
    @Column(name="position")
    private String position;
    @Column(name="logo")
    private String logo;
    @NotEmpty
    @Column(name="startmonth")
    private String startmonth;
    @NotEmpty
    @Length(min=4, max = 4)
    @Column(name="startyear")
    private int startyear;
    @NotEmpty
    @Column(name="endmonth")
    private String endmonth;
    @NotEmpty
    @Length(min=4, max = 4)
    @Column(name="endyear")
    private int endyear;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;
    

    public Experiencia_Laboral() {
    }

    public Experiencia_Laboral(Long id, String company, String position, String logo, String startmonth, int startyear, String endmonth, int endyear) {
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

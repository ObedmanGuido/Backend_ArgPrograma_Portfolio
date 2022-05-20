package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
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
    @NotNull
    @Column(name="startdate", nullable = false)
    private LocalDate startdate;
    @NotNull
    @Column(name="enddate", nullable = false)
    private LocalDate enddate;
    @NotEmpty
    @Column(name="workdescription", nullable = false, length = 1000)
    private String workdescription;
    @Column(name="currentjob")
    private Boolean currentjob;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;
    

    public Experiencia_Laboral() {
    }

    public Experiencia_Laboral(Long id, String company, String position, String logo, LocalDate startdate, LocalDate enddate, String workdescription, Boolean currentjob) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.logo = logo;
        this.startdate = startdate;
        this.enddate = enddate;
        this.workdescription = workdescription;
        this.currentjob = currentjob;
    }
}
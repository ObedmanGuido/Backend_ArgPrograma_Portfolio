package com.PortfolioObedmanGuido.Portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty
    @Column(name="schoolname", nullable = false)
    private String schoolName;
    @NotEmpty
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="logo")
    private String logo;
    @NotNull
    @Column(name="startdate", nullable = false)
    private LocalDate startDate;
    @NotNull
    @Column(name="enddate", nullable = false)
    private LocalDate endDate;
    @NotEmpty
    @Column(name="typeofschool", nullable = false)
    private String typeOfSchool;
    @NotEmpty
    @Column(name="studiesstatus", nullable = false)
    private String studiesStatus;
    @NotEmpty
    @Column(name="educationdescription", nullable = false, length = 1000)
    private String educationDescription;
    @Column(name="currenteducation")
    private Boolean currentEducation;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;

    public Educacion() {
    }

    public Educacion(Long id, String schoolName, String title, String logo, LocalDate startDate, LocalDate endDate, String typeOfSchool,
            String studiesStatus, String educationDescription, Boolean currentEducation) {
        this.id = id;
        this.schoolName = schoolName;
        this.title = title;
        this.logo = logo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeOfSchool = typeOfSchool;
        this.studiesStatus = studiesStatus;
        this.educationDescription = educationDescription;
        this.currentEducation = currentEducation;
    }
    
}
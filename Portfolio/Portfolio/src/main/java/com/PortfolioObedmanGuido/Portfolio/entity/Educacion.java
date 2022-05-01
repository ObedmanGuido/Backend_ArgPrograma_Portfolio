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
    @Column(name="startdate", nullable = false)
    private LocalDate startdate;
    @NotNull
    @Column(name="enddate", nullable = false)
    private LocalDate enddate;
    @NotEmpty
    @Column(name="typeofschool", nullable = false)
    private String typeofschool;
    @NotEmpty
    @Column(name="studiesstatus", nullable = false)
    private String studiesstatus;
    @NotEmpty
    @Column(name="educationdescription", nullable = false, length = 1000)
    private String educationdescription;
    @Column(name="currenteducation")
    private Boolean currenteducation;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;

    public Educacion() {
    }

    public Educacion(Long id, String schoolname, String title, String logo, LocalDate startdate, LocalDate enddate, String typeofschool,
            String studiesstatus, String educationdescription, Boolean currenteducation) {
        this.id = id;
        this.schoolname = schoolname;
        this.title = title;
        this.logo = logo;
        this.startdate = startdate;
        this.enddate = enddate;
        this.typeofschool = typeofschool;
        this.studiesstatus = studiesstatus;
        this.educationdescription = educationdescription;
        this.currenteducation = currenteducation;
    }
    
}
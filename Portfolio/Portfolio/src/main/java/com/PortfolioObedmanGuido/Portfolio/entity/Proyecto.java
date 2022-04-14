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
@Table(name = "educacion")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="projectname", nullable = false)
    private String projectname;
    @NotNull
    @Range(min = 0, max = 31, message = "Día de realización debe estar entre 0 y 31")
    @Column(name="realizationday", nullable = false)
    private int realizationday;
    @NotEmpty
    @Column(name="realizationmonth", nullable = false)
    private String realizationmonth;
    @NotNull
    @Range(min = 1900, max = 2050, message = "Año de realización debe estar entre 1900 y 2050")
    @Column(name="realizationyear", nullable = false)
    private int realizationyear;
    @NotEmpty
    @Column(name="projectdescription", nullable = false, length = 1000)
    private String projectdescription;
    @NotEmpty
    @Column(name="projectlink", nullable = false)
    private String projectlink;
    @Column(name="media1")
    private String media1;
    @Column(name="media2")
    private String media2;
    @Column(name="media3")
    private String media3;
    @Column(name="media4")
    private String media4;
    @Column(name="media5")
    private String media5;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;

    public Proyecto() {
    }

    public Proyecto(Long id, String projectname, int realizationday, String realizationmonth, int realizationyear, String projectdescription, String projectlink, String media1, String media2, String media3, String media4, String media5) {
        this.id = id;
        this.projectname = projectname;
        this.realizationday = realizationday;
        this.realizationmonth = realizationmonth;
        this.realizationyear = realizationyear;
        this.projectdescription = projectdescription;
        this.projectlink = projectlink;
        this.media1 = media1;
        this.media2 = media2;
        this.media3 = media3;
        this.media4 = media4;
        this.media5 = media5;
    }
}
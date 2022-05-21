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
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="projectname", nullable = false)
    private String projectName;
    @NotNull
    @Column(name="creationdate", nullable = false)
    private LocalDate creationDate;
    @NotEmpty
    @Column(name="projectdescription", nullable = false, length = 1000)
    private String projectDescription;
    @NotEmpty
    @Column(name="projectlink", nullable = false)
    private String projectLink;
    @Column(name="image1")
    private String image1;
    @Column(name="image2")
    private String image2;
    @Column(name="image3")
    private String image3;
    @Column(name="video")
    private String video;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;

    public Proyecto() {
    }

    public Proyecto(Long id, String projectName, LocalDate creationDate, String projectDescription, String projectLink, String image1, String image2, String image3, String video) {
        this.id = id;
        this.projectName = projectName;
        this.creationDate = creationDate;
        this.projectDescription = projectDescription;
        this.projectLink = projectLink;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.video = video;
    }
}
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
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="projectname", nullable = false)
    private String projectname;
    @NotNull
    @Column(name="creationdate", nullable = false)
    private LocalDate creationdate;
    @NotEmpty
    @Column(name="projectdescription", nullable = false, length = 1000)
    private String projectdescription;
    @NotEmpty
    @Column(name="projectlink", nullable = false)
    private String projectlink;
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

    public Proyecto(Long id, String projectname, LocalDate creationdate, String projectdescription, String projectlink, String image1, String image2, String image3, String video) {
        this.id = id;
        this.projectname = projectname;
        this.creationdate = creationdate;
        this.projectdescription = projectdescription;
        this.projectlink = projectlink;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.video = video;
    }
}
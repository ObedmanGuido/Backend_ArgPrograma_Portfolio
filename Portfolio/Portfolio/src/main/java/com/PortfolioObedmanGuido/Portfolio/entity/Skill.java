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
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="skillname", nullable = false)
    private String skillname;
    @NotEmpty
    @Column(name="levelname", nullable = false)
    private String levelname;
    @NotNull
    @Range(min = 0, max = 100, message = "Nivel debe estar entre 0 y 100")
    @Column(name="levelnumber", nullable = false)
    private int levelnumber;
    @NotEmpty
    @Column(name="skilltype", nullable = false)
    private String skilltype;
    @Column(name="skilldescription", length = 1000)
    private String skilldescription;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;

    public Skill() {
    }

    public Skill(Long id, String skillname, String levelname, int levelnumber, String skilltype, String skilldescription) {
        this.id = id;
        this.skillname = skillname;
        this.levelname = levelname;
        this.levelnumber = levelnumber;
        this.skilltype = skilltype;
        this.skilldescription = skilldescription;
    }
}
package com.PortfolioObedmanGuido.Portfolio.model;

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
    private String skillName;
    @NotNull
    @Range(min = 0, max = 100, message = "Nivel debe estar entre 0 y 100")
    @Column(name="levelnumber", nullable = false)
    private int levelNumber;
    @Column(name="skilldescription", length = 1000)
    private String skillDescription;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "skill_tipo_id", nullable = false)
    private Skill_Tipo skill_tipo;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;

    public Skill() {
    }

    public Skill(Long id, String skillName, int levelNumber, String skillDescription, Skill_Tipo skill_tipo) {
        this.id = id;
        this.skillName = skillName;
        this.levelNumber = levelNumber;
        this.skillDescription = skillDescription;
        this.skill_tipo = skill_tipo;
    }
}
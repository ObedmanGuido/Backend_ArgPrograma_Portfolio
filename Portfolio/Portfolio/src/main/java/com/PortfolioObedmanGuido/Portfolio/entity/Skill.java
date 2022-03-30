package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter @Setter
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="skillname")
    private String skillname;
    @Column(name="levelname")
    private String levelname;
    @Column(name="levelnumber")
    private int levelnumber;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Persona persona;

    public Skill() {
    }

    public Skill(Long id, String skillname, String levelname, int levelnumber) {
        this.id = id;
        this.skillname = skillname;
        this.levelname = levelname;
        this.levelnumber = levelnumber;
    }
    
}
package com.PortfolioObedmanGuido.Portfolio.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique=true)
    private String skillname;
    
    private String levelname;
    
    private int levelnumber;

    public Skill() {
    }

    public Skill(Long id, String skillname, String levelname, int levelnumber) {
        this.id = id;
        this.skillname = skillname;
        this.levelname = levelname;
        this.levelnumber = levelnumber;
    }
    
}
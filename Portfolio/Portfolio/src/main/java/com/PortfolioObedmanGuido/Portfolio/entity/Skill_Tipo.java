package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "skill_tipo")
public class Skill_Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="typename", nullable = false)
    private String typename;
    @OneToMany(mappedBy = "skill_tipo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference //No mostrar las personas en provincia.
    private List<Skill> skill;

    public Skill_Tipo() {
    }

    public Skill_Tipo(Long id, String typename) {
        this.id = id;
        this.typename = typename;
    }
}

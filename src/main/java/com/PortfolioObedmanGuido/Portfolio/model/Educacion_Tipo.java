package com.PortfolioObedmanGuido.Portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "educacion_tipo")
public class Educacion_Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="educationType", nullable = false)
    private String educationType;
    @OneToMany(mappedBy = "educacion_tipo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference //No mostrar las educaciones en educacion_tipo.
    private List<Educacion> educacion;

    public Educacion_Tipo() {
    }

    public Educacion_Tipo(Long id, String educationType) {
        this.id = id;
        this.educationType = educationType;
    }
}

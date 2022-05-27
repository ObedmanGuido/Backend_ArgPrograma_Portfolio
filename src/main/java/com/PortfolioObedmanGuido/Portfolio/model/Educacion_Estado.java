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
@Table(name = "educacion_estado")
public class Educacion_Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="educationstatus", nullable = false)
    private String educationStatus;
    @OneToMany(mappedBy = "educacion_estado", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference //No mostrar las educaciones en educacion_tipo.
    private List<Educacion> educacion;

    public Educacion_Estado() {
    }

    public Educacion_Estado(Long id, String educationStatus) {
        this.id = id;
        this.educationStatus = educationStatus;
    }
}
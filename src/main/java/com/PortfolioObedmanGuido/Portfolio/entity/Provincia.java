package com.PortfolioObedmanGuido.Portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
@Table(name = "provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name="provincename", nullable = false)
    private String provincename;
    //@OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonBackReference //No mostrar las personas en provincia.
    //private List<Persona> persona;

    public Provincia() {
    }
    
    public Provincia(Long id, String provincename) {
        this.id = id;
        this.provincename = provincename;
    }
}
package com.PortfolioObedmanGuido.Portfolio.security.entity;

import com.PortfolioObedmanGuido.Portfolio.model.Persona;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String nombre;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min=8, max=20)
    private String password;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
    @OneToOne(mappedBy = "usuario")
    @JsonBackReference
    private Persona persona;

    public Usuario() {
    }

    public Usuario(@NotBlank String nombre, @NotBlank String username, @NotBlank String email, @NotBlank String password) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

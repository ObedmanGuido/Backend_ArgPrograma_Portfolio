package com.PortfolioObedmanGuido.Portfolio.security.DTO;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NuevoUsuario {
    @NotBlank
    private String nombre;
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min=8, max=20)
    private String password;
    private Set<String> roles = new HashSet<>();
}
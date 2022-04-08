package com.PortfolioObedmanGuido.Portfolio.security.DTO;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginUsuario {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

package com.PortfolioObedmanGuido.Portfolio.security.controller;

import com.PortfolioObedmanGuido.Portfolio.DTO.Mensaje;
import com.PortfolioObedmanGuido.Portfolio.security.DTO.JwtDto;
import com.PortfolioObedmanGuido.Portfolio.security.DTO.LoginUsuario;
import com.PortfolioObedmanGuido.Portfolio.security.DTO.NuevoUsuario;
import com.PortfolioObedmanGuido.Portfolio.security.entity.Rol;
import com.PortfolioObedmanGuido.Portfolio.security.entity.Usuario;
import com.PortfolioObedmanGuido.Portfolio.security.enums.RolNombre;
import com.PortfolioObedmanGuido.Portfolio.security.jwt.JwtProvider;
import com.PortfolioObedmanGuido.Portfolio.security.service.RolService;
import com.PortfolioObedmanGuido.Portfolio.security.service.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/register")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos, incorrecto email o contraseña con menos de 8 caracteres o más de 20."), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByUsername(nuevoUsuario.getUsername()))
            return new ResponseEntity(new Mensaje("El username ya existe."), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("El email ya existe."), HttpStatus.BAD_REQUEST);
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getUsername(),nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get()); /*Los nuevos usuarios son USER.*/
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario creado."), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        try {if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Contraseña en blanco."), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity(new Mensaje("Usuario o contraseña incorrecto/a."), HttpStatus.BAD_REQUEST);
        }
    }
}
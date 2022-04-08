package com.PortfolioObedmanGuido.Portfolio.security.service;

import com.PortfolioObedmanGuido.Portfolio.security.entity.Usuario;
import com.PortfolioObedmanGuido.Portfolio.security.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Optional<Usuario> getByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }
    
    public boolean existsByUsername(String username){
        return usuarioRepository.existsByUsername(username);
    }
    
    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}

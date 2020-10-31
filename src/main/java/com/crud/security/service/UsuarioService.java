package com.crud.security.service;

import com.crud.security.entity.Usuario;
import com.crud.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usurioRepo;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usurioRepo.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
       return usurioRepo.existsByNombreUsuario( nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usurioRepo.existsByEmail( email);
    }

    public void save(Usuario usuario){
        usurioRepo.save(usuario);
    }
}

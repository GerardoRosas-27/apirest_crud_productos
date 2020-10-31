package com.crud.security.service;

import com.crud.security.entity.Rol;
import com.crud.security.enums.RolNombre;
import com.crud.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepo;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepo.findByRolNombre(rolNombre);
    }
    public void  save(Rol rol){
        rolRepo.save(rol);
    }
}

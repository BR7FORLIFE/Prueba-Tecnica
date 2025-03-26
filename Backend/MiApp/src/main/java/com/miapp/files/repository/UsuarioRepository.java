package com.miapp.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miapp.files.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long>{
    
}

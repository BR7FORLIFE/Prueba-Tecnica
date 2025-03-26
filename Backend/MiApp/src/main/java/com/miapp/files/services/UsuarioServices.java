package com.miapp.files.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miapp.files.models.UsuarioModel;
import com.miapp.files.repository.UsuarioRepository;

@Service
public class UsuarioServices {
    private UsuarioRepository usuarioRepository;

    @Autowired //inyecccion de dependencias
    public UsuarioServices(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    //obtener todos los usuarios
    public List<UsuarioModel> getUser(){
        return usuarioRepository.findAll();
    }

    //filtrar los usuarios por id 
    public Optional<UsuarioModel> getUserById(Long id){
        return usuarioRepository.findById(id);
    }

    //guardar un usuario (crear o actualizar)
    public UsuarioModel saveUser(UsuarioModel usuarioModel){
        return usuarioRepository.save(usuarioModel);
    }

    //eliminar un usuario por su id
    public void deleteUser(Long id){
        usuarioRepository.deleteById(id);
    }
}

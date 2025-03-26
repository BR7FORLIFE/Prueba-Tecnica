package com.miapp.files.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miapp.files.models.UsuarioModel;
import com.miapp.files.services.UsuarioServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioServices usuarioService;

    @Autowired 
    public UsuarioController(UsuarioServices usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioModel> getUsuarios() {
        return usuarioService.getUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<UsuarioModel> usuario = usuarioService.getUserById(id);
        return usuario.map(ResponseEntity::ok) // 📌 Si existe, devuelve 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // 📌 Si no, devuelve 404
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> crearUsuario(@RequestBody UsuarioModel usuario) {
        System.out.println("Recibiendo usuario: " + usuario); // Log del usuario recibido
        UsuarioModel nuevoUsuario = usuarioService.saveUser(usuario);
        System.out.println("Usuario guardado: " + nuevoUsuario); // Log después de guardar
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        if (!usuarioService.getUserById(id).isPresent()) {
            return ResponseEntity.notFound().build(); // 📌 Si no existe, devuelve 404
        }
        usuario.setId(id);
        UsuarioModel usuarioActualizado = usuarioService.saveUser(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioService.getUserById(id).isPresent()) {
            return ResponseEntity.notFound().build(); // 📌 Si no existe, devuelve 404
        }
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 📌 Devuelve 204 No Content
    }
}

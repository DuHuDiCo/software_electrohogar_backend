package com.software_electrohogar_backend.software_electrohogar_backend.services;

import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario) throws Exception;

    public Boolean existByUsername(String username);

    public Boolean existByEmail(String email);
    
    public List<Usuario> obtenerUsuarios();
    
    public Optional<Usuario> obtenerUsuarioById(String id);
}

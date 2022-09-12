package com.software_electrohogar_backend.software_electrohogar_backend.services;

import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario) throws Exception;

    public Boolean existByUsername(String username);

    public Boolean existByEmail(String email);
}

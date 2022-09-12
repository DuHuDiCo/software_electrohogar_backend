

package com.software_electrohogar_backend.software_electrohogar_backend.services;

import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;
import java.util.Optional;


public interface UsuarioService{
    
    public Usuario guardarUsuario(Usuario usuario) throws Exception;
    
    public Usuario obtenerUsuario(String username);
    
    public Usuario editarUsuario(Usuario usuario);
    
    public void eliminarUsuario(String usuario);
    
    public Optional<Usuario> obtenerUsuarioById(String id);

}

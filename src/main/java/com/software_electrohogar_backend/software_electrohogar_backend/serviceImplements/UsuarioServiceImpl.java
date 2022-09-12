
package com.software_electrohogar_backend.software_electrohogar_backend.serviceImplements;

import com.software_electrohogar_backend.software_electrohogar_backend.exceptions.UsuarioFoundException;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;
import com.software_electrohogar_backend.software_electrohogar_backend.repository.UsuarioRepository;
import com.software_electrohogar_backend.software_electrohogar_backend.services.UsuarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            throw new UsuarioFoundException("El Usuario ya existe");
        }else{
            usuarioLocal = usuarioRepository.save(usuario);
           
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    //pendiente por terminar
    @Override
    public Usuario editarUsuario(Usuario usuario) {
        Usuario user = usuarioRepository.findByUsername(usuario.getUsername());
        return user;
    }

    @Override
    public void eliminarUsuario(String usuario) {
        usuarioRepository.deleteById(usuario);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioById(String id) {
        return usuarioRepository.findById(id);
    }

}

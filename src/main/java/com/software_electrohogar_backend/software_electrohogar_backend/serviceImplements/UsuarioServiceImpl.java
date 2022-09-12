
package com.software_electrohogar_backend.software_electrohogar_backend.serviceImplements;

import com.software_electrohogar_backend.software_electrohogar_backend.exceptions.UsuarioFoundException;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;
import com.software_electrohogar_backend.software_electrohogar_backend.repository.UsuarioRepository;
import com.software_electrohogar_backend.software_electrohogar_backend.services.UsuarioService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) throws Exception {
        boolean usuarioLocal = usuarioRepository.existsByUsername(usuario.getUsername());
        Usuario user = null;
        if(usuarioLocal == true){
            throw new UsuarioFoundException("El Usuario ya existe");
        }else{
            user = usuarioRepository.save(usuario);
           
        }
        return user;
    }

    @Override
    public Boolean existByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
        
    }

    @Override
    public Boolean existByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioById(String id) {
        return usuarioRepository.findById(id);
    }

    
   
}

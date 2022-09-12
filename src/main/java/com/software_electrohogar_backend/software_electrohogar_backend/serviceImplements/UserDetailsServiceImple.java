
package com.software_electrohogar_backend.software_electrohogar_backend.serviceImplements;

import com.software_electrohogar_backend.software_electrohogar_backend.exceptions.UsuarioNotFoundException;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;
import com.software_electrohogar_backend.software_electrohogar_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImple implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException("usuario no encontrado");
        }
        return (UserDetails) usuario;
    }

}

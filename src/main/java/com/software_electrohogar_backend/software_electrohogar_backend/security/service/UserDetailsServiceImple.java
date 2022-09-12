
package com.software_electrohogar_backend.software_electrohogar_backend.security.service;

import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;
import com.software_electrohogar_backend.software_electrohogar_backend.repository.UsuarioRepository;
import com.software_electrohogar_backend.software_electrohogar_backend.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImple implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado : "+ username));
        
        
        return UserDetailsImpl.build(usuario);
    }

}

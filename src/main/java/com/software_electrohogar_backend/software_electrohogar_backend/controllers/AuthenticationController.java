
package com.software_electrohogar_backend.software_electrohogar_backend.controllers;

import com.software_electrohogar_backend.software_electrohogar_backend.conigurations.JwtUtils;
import com.software_electrohogar_backend.software_electrohogar_backend.exceptions.UsuarioNotFoundException;
import com.software_electrohogar_backend.software_electrohogar_backend.models.JwtRequest;
import com.software_electrohogar_backend.software_electrohogar_backend.models.JwtResponse;
import com.software_electrohogar_backend.software_electrohogar_backend.serviceImplements.UserDetailsServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServiceImple userDetailsServiceImple;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    
    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsuarioNotFoundException ex) {
            ex.printStackTrace();
            throw new Exception("Usuario no Encontrado");
        }
        
        UserDetails userDetails = userDetailsServiceImple.loadUserByUsername(jwtRequest.getUsername());
        
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    public void autenticar(String username, String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException disabledException) {
            throw new Exception("USUARIO DESHABILITADO "+ disabledException.getMessage());
        } catch (BadCredentialsException badCredentialsException){
            throw new Exception("Credenciales Invalidas " + badCredentialsException.getMessage());
        }
        
    }

}

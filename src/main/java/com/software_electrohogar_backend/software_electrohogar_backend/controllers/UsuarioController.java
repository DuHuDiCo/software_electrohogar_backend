
package com.software_electrohogar_backend.software_electrohogar_backend.controllers;

import com.software_electrohogar_backend.software_electrohogar_backend.DTOS.UsuarioDto;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Rol;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;
import com.software_electrohogar_backend.software_electrohogar_backend.serviceImplements.UsuarioServiceImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users/")
public class UsuarioController {
    
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    
    
    @GetMapping("/all")
    public List<UsuarioDto> obtenerUsuarios(){
        
        List<Usuario> users = usuarioServiceImpl.obtenerUsuarios();
        List<UsuarioDto> usersDto = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        UsuarioDto user = null;
        
        for(Usuario u : users){
            user = mapper.map(u, UsuarioDto.class);
            usersDto.add(user);
        }
        
        return usersDto;
    }
    
    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> obtenerUsuarioById(@Valid @PathVariable("usuarioId") String usuarioId){
        Usuario user = usuarioServiceImpl.obtenerUsuarioById(usuarioId)
                .orElseThrow(()-> new RuntimeException("Usuario no Encontrado"));
        ModelMapper mapper = new ModelMapper();
        UsuarioDto userDto = mapper.map(user, UsuarioDto.class);
        return ResponseEntity.ok(userDto);
    }

}

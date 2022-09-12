package com.software_electrohogar_backend.software_electrohogar_backend.controllers;

import com.software_electrohogar_backend.software_electrohogar_backend.exceptions.UsuarioNotFoundException;
import com.software_electrohogar_backend.software_electrohogar_backend.models.ERol;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Rol;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;
import com.software_electrohogar_backend.software_electrohogar_backend.payload.request.LoginRequest;
import com.software_electrohogar_backend.software_electrohogar_backend.payload.request.SignupRequest;
import com.software_electrohogar_backend.software_electrohogar_backend.payload.response.JwtResponse;
import com.software_electrohogar_backend.software_electrohogar_backend.payload.response.MessageResponse;
import com.software_electrohogar_backend.software_electrohogar_backend.security.jwt.JwtUtils;
import com.software_electrohogar_backend.software_electrohogar_backend.security.service.UserDetailsImpl;
import com.software_electrohogar_backend.software_electrohogar_backend.serviceImplements.RolServiceImpl;
import com.software_electrohogar_backend.software_electrohogar_backend.serviceImplements.UsuarioServiceImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    RolServiceImpl rolServiceImpl;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        if (!usuarioServiceImpl.existByUsername(loginRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Usuario no encontrado"));
        } else {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetailsImpl.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt, userDetailsImpl.getId(), userDetailsImpl.getUsername(), userDetailsImpl.getEmail(), roles));
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) throws Exception {
        if (usuarioServiceImpl.existByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Usuario ya Registrado"));
        }

        if (usuarioServiceImpl.existByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email ya Registrado"));
        }

        Usuario user = new Usuario();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setNombre(signupRequest.getNombre());
        user.setApellido(signupRequest.getApellido());
        user.setEmail(signupRequest.getEmail());
        user.setTelefono(signupRequest.getTelefono());
        user.setEnabled(true);

        Set<String> strRoles = signupRequest.getRoles();
        Set<Rol> roles = new HashSet<>();

        if (user.getRoles() == null) {
            Rol userRol = rolServiceImpl.buscarByNombre(ERol.ROL_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Rol No Encontrado"));
            roles.add(userRol);
        } else {
            strRoles.forEach(rol -> {
                switch (rol) {
                    case "admin":
                        Rol adminRol = rolServiceImpl.buscarByNombre(ERol.ROL_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Rol No Encontrado"));
                        roles.add(adminRol);
                        break;
                    default:
                        Rol userRol = rolServiceImpl.buscarByNombre(ERol.ROL_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Rol No Encontrado"));
                        roles.add(userRol);

                }
            });
        }
        user.setRoles(roles);
        usuarioServiceImpl.guardarUsuario(user);
        return ResponseEntity.ok(new MessageResponse("Usuario Registrado con exito"));

    }

}

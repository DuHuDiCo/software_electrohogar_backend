
package com.software_electrohogar_backend.software_electrohogar_backend.serviceImplements;

import com.software_electrohogar_backend.software_electrohogar_backend.models.ERol;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Rol;
import com.software_electrohogar_backend.software_electrohogar_backend.repository.RolRepository;
import com.software_electrohogar_backend.software_electrohogar_backend.services.RolService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService{
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Optional<Rol> buscarByNombre(ERol nombre) {
        return rolRepository.findByNombre(nombre);
    }

}

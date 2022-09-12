

package com.software_electrohogar_backend.software_electrohogar_backend.services;

import com.software_electrohogar_backend.software_electrohogar_backend.models.ERol;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Rol;
import java.util.Optional;


public interface RolService {
    
    public Optional<Rol> buscarByNombre(ERol name);

}



package com.software_electrohogar_backend.software_electrohogar_backend.repository;

import com.software_electrohogar_backend.software_electrohogar_backend.models.ERol;
import com.software_electrohogar_backend.software_electrohogar_backend.models.Rol;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RolRepository extends MongoRepository<Rol, String>{
    
    Optional<Rol> findByNombre(ERol nombre);

}



package com.software_electrohogar_backend.software_electrohogar_backend.repository;

import com.software_electrohogar_backend.software_electrohogar_backend.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    
    Usuario findByUsername(String Username);

    
   

}

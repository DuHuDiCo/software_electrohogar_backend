
package com.software_electrohogar_backend.software_electrohogar_backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Rol {
    
    @Id
    private String id;
    private ERol nombre;

    public Rol() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Enum getNombre() {
        return nombre;
    }

    public void setNombre(ERol nombre) {
        this.nombre = nombre;
    }
    
    
    
}

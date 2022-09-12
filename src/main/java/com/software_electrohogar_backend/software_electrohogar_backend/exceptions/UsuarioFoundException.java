
package com.software_electrohogar_backend.software_electrohogar_backend.exceptions;


public class UsuarioFoundException extends Exception{
    
    public UsuarioFoundException(){
        super("el usuario con ese username ya existe en la base de datos, vuelva a intentar");
    }
    
    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }
}

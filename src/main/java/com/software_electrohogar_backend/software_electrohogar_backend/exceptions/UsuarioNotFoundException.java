package com.software_electrohogar_backend.software_electrohogar_backend.exceptions;

public class UsuarioNotFoundException extends Exception{

    public UsuarioNotFoundException() {
        super("el usuario con ese username no existe en la base de datos, vuelva a intentar");
    }

    public UsuarioNotFoundException(String mensaje) {
        super(mensaje);
    }

}

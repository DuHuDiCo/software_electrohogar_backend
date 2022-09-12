
package com.software_electrohogar_backend.software_electrohogar_backend.models;


public class JwtResponse {
    
    
    private String token;

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}

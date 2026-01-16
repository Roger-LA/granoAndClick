package com.granoAndClick.granoAndClick.dto;


public class ChangePassword {
    private String correo;  
    private String telefono; 
    private String password; 
    private String nPassword; 

    // Getters y setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getnPassword() {
        return nPassword;
    }

    public void setnPassword(String nPassword) {
        this.nPassword = nPassword;
    }
}


package com.example.demopatrones.entities;

public class UsuariosBuilder {
    private Usuario usuario = new Usuario();

    private UsuariosBuilder(){}

    public UsuariosBuilder(String nombreUsuario){
        usuario.nombreUsuario = nombreUsuario;
    }

    public UsuariosBuilder conNombre(String nombre){
        usuario.nombre = nombre;
        return this;
    }

    public UsuariosBuilder conApellidos(String apellidos){
        usuario.apellidos = apellidos;
        return this;
    }

    public UsuariosBuilder conEmail(String email){
        usuario.email = email;
        return this;
    }

    public UsuariosBuilder conNivelDeAcceso(int nivelDeAcceso){
        usuario.nivelAcceso = nivelDeAcceso;
        return this;
    }

    public Usuario build(){
        return usuario;
    }
}

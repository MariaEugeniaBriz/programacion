package com.example.demopatrones.datasourcesOrepositories;

import com.example.demopatrones.entities.Usuario;

import java.util.ArrayList;


public class UsuariosDBMemoria implements UsuariosDB{
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public ArrayList<Usuario> obtener(){
        return usuarios;
    }

    public Usuario buscar(Usuario usuario){
        for (Usuario usuarioActual : obtener()){
            if (usuarioActual.nombreUsuario.equalsIgnoreCase(usuario.nombreUsuario)){
                return usuarioActual;
            }
        }
        return null;
    }

    public void insertar(Usuario usuario){
        for (Usuario usuarioActual : usuarios){
            if (usuarioActual.nombreUsuario.toLowerCase().equals(usuario.nombreUsuario.toLowerCase())){
                return;
            }
        }

        usuarios.add(usuario);
    }

    public void borrar(Usuario usuario){
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).nombreUsuario.equalsIgnoreCase(usuario.nombreUsuario)){
                usuarios.remove(i);
            }
        }
    }
}

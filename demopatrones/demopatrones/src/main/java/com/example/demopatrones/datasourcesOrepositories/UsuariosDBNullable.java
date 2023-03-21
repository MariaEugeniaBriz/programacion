package com.example.demopatrones.datasourcesOrepositories;

import com.example.demopatrones.entities.Usuario;

import java.util.ArrayList;

public class UsuariosDBNullable implements UsuariosDB{
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public ArrayList<Usuario> obtener(){
        return null;
    }

    public Usuario buscar(Usuario usuario){
      return null;
    }

    public void insertar(Usuario usuario){

    }

    public void borrar(Usuario usuario){
    }
}

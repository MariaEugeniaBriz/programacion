package com.example.demopatrones.datasourcesOrepositories;

import com.example.demopatrones.entities.Usuario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuariosDBFichero implements UsuariosDB{

    public String ficheroDatos;

    public UsuariosDBFichero(){ this.ficheroDatos = "usuarios.txt"; }

    public ArrayList<Usuario> obtener(){
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(new File(ficheroDatos));

            while (scanner.hasNext()){
                String usuarioActual = scanner.next();
                String []partes = usuarioActual.split(",");

                Usuario usuario = new Usuario();
                usuario.nombreUsuario = partes[0];
                usuario.nombre = partes[1];
                usuario.apellidos = partes[2];
                usuario.email = partes[3];
                usuario.nivelAcceso = Integer.parseInt(partes[4]);

                usuarios.add(usuario);
            }
            scanner.close();
        }catch (Exception e){
        }
        return usuarios;
    }

    public Usuario buscar(Usuario usuario){
        ArrayList<Usuario> usuarios = obtener();

        for (Usuario usuarioActual : usuarios){
            if (usuarioActual.nombreUsuario.equalsIgnoreCase(usuario.nombreUsuario)){
                return usuarioActual;
            }
        }
        return null;
    }

    public void insertar(Usuario usuario){
        try {
            //escribe al final del fichero
            FileOutputStream fileOutputStream = new FileOutputStream(ficheroDatos, true);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(separarUsuarioPorComas(usuario));
            printStream.flush();
//flush: en ciertos momentos los writered son buffered (escribiendo en memoria, pero no ha hecho efectivo
//el cambio en el disco). flush para que guarde inmediatamente en el disco los cambios que tenga en memoria
            printStream.close();
        }catch (Exception e){
        }
    }

    private String separarUsuarioPorComas(Usuario usuario){
        return usuario.nombreUsuario + ","
                + usuario.nombre + ","
                + usuario.apellidos + ","
                + usuario.email + ","
                + usuario.nivelAcceso;
    }

    public void borrar(Usuario usuario){
        ArrayList<Usuario> usuarios = obtener();

        for (int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).nombreUsuario.equalsIgnoreCase(usuario.nombreUsuario)){
                usuarios.remove(i);
            }
        }

        try{
            PrintStream printStream = new PrintStream(ficheroDatos);

            for (Usuario usuarioActual : usuarios){
                String usuarioComas = separarUsuarioPorComas(usuarioActual);
                printStream.println(usuarioComas);
            }
            printStream.flush();
            printStream.close();
        }catch (Exception e){
        }
    }
}

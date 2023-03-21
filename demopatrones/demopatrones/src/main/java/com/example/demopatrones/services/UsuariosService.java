package com.example.demopatrones.services;

import com.example.demopatrones.datasourcesOrepositories.UsuariosDB;
import com.example.demopatrones.datasourcesOrepositories.UsuariosDBFactory;
import com.example.demopatrones.datasourcesOrepositories.UsuariosDBMemoria;
import com.example.demopatrones.entities.Usuario;
import com.example.demopatrones.entities.UsuariosBuilder;
import org.glassfish.hk2.api.Self;
import org.jvnet.hk2.annotations.Service;

import java.util.ArrayList;

@Service
public class UsuariosService {

    UsuariosDB usuariosDB;

    public void Usuarios(String tipoDeBBDD){
        UsuariosDBFactory user = new UsuariosDBFactory(tipoDeBBDD);
        this.usuariosDB = user.getDatabaseInstance();}

    private void Usuarios(){}

    public ArrayList<Usuario> listarUsuarios(){ return usuariosDB.obtener(); }

    public Usuario obtenerUsuario(String userName){
        Usuario usuario = new Usuario();
        usuario.nombreUsuario = userName;

        return usuariosDB.buscar(usuario);
    }

    public void crearUsuario(Usuario usuario){
        if(usuariosDB.buscar(usuario) != null){
            return;
        }

        Usuario usuarioFiltrado = crearUsuarioReal(usuario);

        usuariosDB.insertar(usuarioFiltrado);
    }

    public void borrarUsuario(String userName){
        Usuario usuario = new Usuario();
        usuario.nombreUsuario = userName;
        usuariosDB.borrar(usuario);
    }

    private Usuario crearUsuarioReal(Usuario usuario){
        UsuariosBuilder usuariosBuilder = new UsuariosBuilder(usuario.nombreUsuario);

        return usuariosBuilder
                //conNombre(usuario.nombre.length() > 0 ? usuario.nombre : "Sin nombre")
                .conNombre(usuario.nombre)
                .conApellidos(usuario.apellidos)
                .conEmail(usuario.email)
                //conNivelDeAcceso(usuario.nivelAcceso > 0 ? usuario.nivelAcceso : 0)
                .conNivelDeAcceso(usuario.nivelAcceso)
                .build();
    }
}
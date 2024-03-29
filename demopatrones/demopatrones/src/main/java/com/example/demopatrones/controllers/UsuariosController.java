package com.example.demopatrones.controllers;

import com.example.demopatrones.entities.Usuario;
import com.example.demopatrones.services.UsuariosService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@Path("/")
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService){this.usuariosService = usuariosService;}

    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listarTodos(){return usuariosService.listarUsuarios();}

    @GET
    @Path("/usuarios/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario listarUno(@PathParam("nombre") String nombre){return usuariosService.obtenerUsuario(nombre);}

    @POST
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearUsuario(Usuario usuario){
        usuariosService.crearUsuario(usuario);

        return Response.created(
                URI.create("/usuarios/" + usuario.nombreUsuario)
        ).build();
    }

    @DELETE
    @Path("/usuarios/{nombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borrarUsuario(@PathParam("nombre") String nombre){
        usuariosService.borrarUsuario(nombre);

        return Response.ok().build();
    }
}

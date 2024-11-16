package edu.upc.dsa.services;


import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Api(value = "/", description = "Endpoint to Service")
@Path("/")

public class Service {
    private Manager manager;

    public Service(){
        this.manager = ManagerImpl.getInstance();

        if (manager.sizeUsuarios()==0) {
            this.manager.addUsuario("123a", "Pep", "Casals de la Cruz",
                    "pepe@gmail.com", "01/01/1999");
            this.manager.addUsuario("996r", "Jordi", "Zara Pilota",
                    "jordi@gmail.com", "08/09/1999");
            this.manager.addUsuario("381l", "Mar", "Cel Terra",
                    "mar@gmail.com", "14/11/2009");
        }

        if(manager.sizePuntos()==0){
            this.manager.addPuntoInteres(ElementType.COIN, 3,4);
            this.manager.addPuntoInteres(ElementType.DOOR, 0,5);
            this.manager.addPuntoInteres(ElementType.GRASS, 1,2);
        }
    }

    @GET
    @ApiOperation(value = "get all Usuario", notes = "Get usuarios en orden alfabetico")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {

        List<Usuario> usuarios = this.manager.listarUsuarios();

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {};
        return Response.status(201).entity(entity).build();

    }

    @POST
    @ApiOperation(value = "create a new Usuario", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/usuarios")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {

        if (usuario.getId()==null|| usuario.getCognoms()==null||usuario.getNom()==null
                ||usuario.getCorreu()==null||usuario.getDataNaixement() ==null)
            return Response.status(500).build();

        this.manager.addUsuario(usuario.getId(),usuario.getNom(),usuario.getCognoms(),
                usuario.getCorreu(),usuario.getDataNaixement());
        return Response.status(201).entity(usuario).build();
    }

    @GET
    @ApiOperation(value = "consultar Usuario", notes = "Consultar los datos del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="String"),
    })
    @Path("/usuarios/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuario(@PathParam("id") String id) {
        Usuario u = this.manager.consultarUsuario(id);
        if(u==null) return Response.status(404).build();
        else return Response.status(201).entity(u).build();

    }

    @PUT
    @ApiOperation(value = "Registrar un usuario que ha pasado por un punto de interes", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario registrado correctamente", responseContainer="String"),
            @ApiResponse(code = 404, message = "Usuario o coordenadas no existentes")
    })
    @Path("/usuarios/{id}/puntos/{x}/{y}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response usuarioPuntoInteres(@PathParam("id") String id,
                                        @PathParam("x") int x,
                                        @PathParam("y") int y) {
        int n = this.manager.usuarioPuntoInteres(id, x, y);
        if(n==0) return Response.status(201).build();
        else return Response.status(404).build();

    }

    @GET
    @ApiOperation(value = "Listar los usuarios que han pasado por un punto", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Usuario.class, responseContainer="String"),
            @ApiResponse(code = 404, message = "Punto no existente")
    })
    @Path("/puntos/{x}/{y}/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuariosPunto(@PathParam("x") int x,
                                        @PathParam("y") int y) {
        Set<Usuario> usuariosPunto = this.manager.listarUsuariosPunto(x, y);
        GenericEntity<Set<Usuario>> entity = new GenericEntity<Set<Usuario>>(usuariosPunto) {};
        if(entity == null) return Response.status(404).build();
        else return Response.status(201).entity(entity).build();

    }

    @POST
    @ApiOperation(value = "create a new Punto", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=PuntoInteres.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/puntos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPunto(PuntoInteres punto) {
        if (punto.getElementType()==null)
            return Response.status(500).build();

        this.manager.addPuntoInteres(punto.getElementType(), punto.getHorizontal(), punto.getVertical());
        return Response.status(201).entity(punto).build();
    }

    @GET
    @ApiOperation(value = "Puntos por los que ha pasado el usuario", notes = "Consultar los puntos del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="String"),
    })
    @Path("/usuarios/{id}/puntos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPuntos(@PathParam("id") String id) {
        List<PuntoInteres> p = this.manager.consultarPuntos(id);
        GenericEntity<List<PuntoInteres>> entity = new GenericEntity<List<PuntoInteres>>(p) {};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "Puntos de un tipo", notes = "Consultar los puntos de un ElementType")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", responseContainer="String"),
    })
    @Path("/puntos/{elementType}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response consultarPuntosElementType(@PathParam("elementType") ElementType elementType){
        String p = this.manager.listarPuntosElementType(elementType);
        return Response.status(201).entity(p).build();

    }

}

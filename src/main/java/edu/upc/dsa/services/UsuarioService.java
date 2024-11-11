package edu.upc.dsa.services;


import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/usuarios", description = "Endpoint to Usuario Service")
@Path("/usuarios")

public class UsuarioService {
    private Manager manager;

    public UsuarioService(){
        this.manager = ManagerImpl.getInstance();
        if (manager.sizeUsuarios()==0)
        this.manager.addUsuario("123a","Pep", "Casals de la Cruz",
                "pepe@gmail.com","01/01/1999");
        this.manager.addUsuario("996r","Jordi", "Zara Pilota",
                "jordi@gmail.com","08/09/1999");
        this.manager.addUsuario("381l","Mar", "Cel Terra",
                "mar@gmail.com","14/11/2009");
    }

    @GET
    @ApiOperation(value = "get all Usuario", notes = "Get usuarios en orden alfabeticpço")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {

        List<Usuario> usuarios = this.manager.listarUsuarios();

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @POST
    @ApiOperation(value = "create a new Usuario", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {

        if (usuario.getId()==null|| usuario.getCognoms()==null||usuario.getNom()==null
                ||usuario.getCorreu()==null||usuario.getDataNaixement() ==null)
            return Response.status(500).entity(usuario).build();

        this.manager.addUsuario(usuario.getId(),usuario.getNom(),usuario.getCognoms(),
                usuario.getCorreu(),usuario.getDataNaixement());
        return Response.status(201).entity(usuario).build();
    }

    @GET
    @ApiOperation(value = "consultar Usuario", notes = "Consultar los datos del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="String"),
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {

        List<Usuario> usuarios = this.manager.listarUsuarios();

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {};
        return Response.status(201).entity(entity).build()  ;

    }

}

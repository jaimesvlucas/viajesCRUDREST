/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Destinos;
import modelo.ViajesCRUD;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("rest")
@RequestScoped
public class RestViajes {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestViajes
     */
    public RestViajes() {
    }

    /**
     * Retrieves representation of an instance of controlador.RestViajes
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/destino/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Destinos getDestinoJson(@PathParam("id") int id) {
        Destinos miProducto = ViajesCRUD.getDestino(id);
        return miProducto;
    }
    
    @GET
    @Path("/destinos/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Destinos> getDestinos() {
        List<Destinos> miDestino = ViajesCRUD.getDestinos();
        return miDestino;
    }
    
    @PUT
    @Path("/destino/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Destinos updateDestinos(Destinos d) {
        ViajesCRUD.actualizaDestino(d);
        return d;
    }
    
    @POST
    @Path("/destino/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void setDestino(Destinos d) {
        ViajesCRUD.insertaDestino(d);
    }
    
    @DELETE
    @Path("/destino/{id}")
    public void deleteDestino(@PathParam("id") int id) {
        ViajesCRUD.destroyDestino(id);
    }

    /**
     * PUT method for updating or creating an instance of RestViajes
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}

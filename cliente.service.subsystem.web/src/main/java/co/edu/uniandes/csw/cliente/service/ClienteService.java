package co.edu.uniandes.csw.cliente.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Cliente")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteService extends _ClienteService {
@GET
	@Path("services/{name}")
    @Produces(MediaType.TEXT_PLAIN)
     public String getClienteId(@PathParam("name")String name){
         return ""+clienteLogicService.getClienteId(name).getId();
     }
     
     @GET
     @Path("servicejson/{name}")
        public String getClientebyName(@PathParam("name")String name){
         return ""+clienteLogicService.getClienteId(name).getId();
     }

}
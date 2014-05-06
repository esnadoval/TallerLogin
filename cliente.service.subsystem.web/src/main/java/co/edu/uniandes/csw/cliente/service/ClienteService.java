package co.edu.uniandes.csw.cliente.service;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
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

    /**
     * Servicio que retorna un texto con el id de un cliente basado en su nombre
     * este servicio lo utiliza el carrito de compras para poder buscar en su
     * base de datos.
     *
     * @param name el nombre del cliente
     * @return texto con el id
     */
    @GET
    @Path("services/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getClienteId(@PathParam("name") String name) {
        return "" + clienteLogicService.getClienteId(name).getId();
    }

    /**
     * Servicio que retorna un cliente dado su nombre
     *
     * @param name el nombre del cliente
     * @return json con el cliente
     */
    @GET
    @Path("servicejson/{name}")
    public ClienteDTO getClientebyName(@PathParam("name") String name) {
        return clienteLogicService.getClienteId(name);
    }

}

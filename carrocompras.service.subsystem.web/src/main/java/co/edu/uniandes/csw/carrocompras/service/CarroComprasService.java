package co.edu.uniandes.csw.carrocompras.service;

import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/CarroCompras")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarroComprasService extends _CarroComprasService {
@GET
@Path("cliente/{name}")
public List<CarroComprasDTO> getCarroComprassCliente(@PathParam("name")String name) {
    return carroComprasLogicService.getCarroComprassCliente(name);
}
}
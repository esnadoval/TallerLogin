
package co.edu.uniandes.csw.carrocompras.logic.ejb;

import co.edu.uniandes.csw.carrocompras.logic.api.ICarroComprasLogicService;
import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import com.sun.jersey.api.client.Client;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
@Default
@Stateless
@LocalBean
public class CarroComprasLogicService extends _CarroComprasLogicService implements ICarroComprasLogicService {
     public static String URL_SERVICIO = "http://localhost:8080/cliente.service.subsystem.web";
    public List<CarroComprasDTO> getCarroComprassCliente(String name) {
         try {
            
            Client client = Client.create();
            /**
             * SE CONSUME EL NUEVO SERVICIO DEL CLIENTE (del componente cliente) PARA OBTENER EL ID DEL CLIENTE.

             */
            WebResource webResource = client.resource(URL_SERVICIO + "/webresources/Cliente/services/" + name);
            String resp = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN).get(String.class);
         //Finalmente, se consulta por el id del cliente.
            return persistance.getCarroComprassCliente(Long.parseLong(resp));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
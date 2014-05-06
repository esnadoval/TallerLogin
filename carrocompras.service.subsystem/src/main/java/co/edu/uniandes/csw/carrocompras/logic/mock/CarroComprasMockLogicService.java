
package co.edu.uniandes.csw.carrocompras.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.carrocompras.logic.api.ICarroComprasLogicService;
import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import java.util.List;

@Alternative
@Singleton
public class CarroComprasMockLogicService extends _CarroComprasMockLogicService implements ICarroComprasLogicService {

    public List<CarroComprasDTO> getCarroComprassCliente(String name) {
        return null;
    }
	
}
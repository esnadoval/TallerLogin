package co.edu.uniandes.csw.carrocompras.persistence;

import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.carrocompras.persistence.api.ICarroComprasPersistence;
import co.edu.uniandes.csw.carrocompras.persistence.converter.CarroComprasConverter;
import java.util.List;
import javax.ejb.LocalBean;
import javax.persistence.Query;

@Default
@Stateless
@LocalBean
public class CarroComprasPersistence extends _CarroComprasPersistence implements ICarroComprasPersistence {

    public List<CarroComprasDTO> getCarroComprassCliente(Long id) {
        Query q = entityManager.createQuery("select u from CarroComprasEntity u where u.clienteId = " + id);
        return CarroComprasConverter.entity2PersistenceDTOList(q.getResultList());
    }
}

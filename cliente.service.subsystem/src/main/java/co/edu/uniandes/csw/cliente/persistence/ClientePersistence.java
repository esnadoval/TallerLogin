package co.edu.uniandes.csw.cliente.persistence;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.LocalBean;
import javax.persistence.Query;

@Default
@Stateless
@LocalBean
public class ClientePersistence extends _ClientePersistence implements IClientePersistence {

    public ClienteDTO getClienteId(String name) {
		Query q = entityManager.createQuery("select u from ClienteEntity u where u.name = '"+name+"'");
		if(q.getResultList().isEmpty()){
                    return null;
                }else{
                    return ClienteConverter.entity2PersistenceDTO((ClienteEntity)q.getResultList().get(0));
                }
                 
	}
    
    public ClienteDTO createCliente(ClienteDTO cliente) {
        try {
            cliente.setPassword(MD5(cliente.getPassword()));
        } catch (Exception e) {
        }
        ClienteEntity entity = ClienteConverter.persistenceDTO2Entity(cliente);
        entityManager.persist(entity);
        return ClienteConverter.entity2PersistenceDTO(entity);
    }

    private static String convertedToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < data.length; i++) {
            int halfOfByte = (data[i] >>> 4) & 0x0F;
            int twoHalfBytes = 0;

            do {
                if ((0 <= halfOfByte) && (halfOfByte <= 9)) {
                    buf.append((char) ('0' + halfOfByte));
                } else {
                    buf.append((char) ('a' + (halfOfByte - 10)));
                }

                halfOfByte = data[i] & 0x0F;

            } while (twoHalfBytes++ < 1);
        }
        return buf.toString();
    }

    public static String MD5(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5 = new byte[64];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5 = md.digest();
        return convertedToHex(md5);
    }
}

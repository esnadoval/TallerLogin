
package co.edu.uniandes.csw.carrocompras.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _CarroComprasEntity {

	@Id
	@GeneratedValue(generator = "CarroCompras")
	private Long id;
	private String name;
	private Long clienteId;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Long getClienteId(){
		return clienteId;
	}
	
	public void setClienteId(Long clienteId){
		this.clienteId = clienteId;
	}
}
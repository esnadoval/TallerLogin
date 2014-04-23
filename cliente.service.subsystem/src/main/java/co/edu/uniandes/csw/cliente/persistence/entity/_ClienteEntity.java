
package co.edu.uniandes.csw.cliente.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ClienteEntity {

	private String name;
	@Id
	@GeneratedValue(generator = "Cliente")
	private Long id;
	private String password;
	private String role;

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getRole(){
		return role;
	}
	
	public void setRole(String role){
		this.role = role;
	}
}
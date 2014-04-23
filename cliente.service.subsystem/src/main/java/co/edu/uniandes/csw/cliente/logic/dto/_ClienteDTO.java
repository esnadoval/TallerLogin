
package co.edu.uniandes.csw.cliente.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _ClienteDTO {

	private String name;
	private Long id;
	private String password;
	private String Role;

	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return Role;
	}
 
	public void setRole(String role) {
		this.Role = role;
	}
	
}
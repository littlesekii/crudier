package api.littlesekii.crudier.resources.person.model;

import java.io.Serializable;

import api.littlesekii.crudier.exception.InvalidDataException;
import io.github.littlesekii.aritana.util.Validation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name =  "t_Person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person")
	@SequenceGenerator(name = "person", sequenceName = "seq_t_Person", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(length = 11, nullable = false, unique = true)
	private String register;

	public Person() {
		
	}
	
	public Person(String name, String register) {
		this.id = null;
		setName(name);
		setRegister(register);
	}
	
	public Person(Long id, String name, String register) {
		this.id = id;
		setName(name);
		setRegister(register);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		
		if (!Validation.validCPF(register)) 
			throw new InvalidDataException("Invalid value to register field. CPF is not valid.");
		
		this.register = register;
	}
	
}

package br.com.sistemadeparquimetro.DDD.dominio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Veiculo {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo_sequence")
	@SequenceGenerator(name = "veiculo_sequence", sequenceName = "veic_seq")
	private Long id;
	private String placa;
	private String modelo;
	private Long condutor_id;
	
	public Veiculo(String placa, String modelo) {
		super();
		this.placa = placa;
		this.modelo = modelo;
	}

	public Veiculo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setCondutor_id(Long condutor_id) {
		this.condutor_id = condutor_id;
	}

	public Long getCondutor_id() {
		return condutor_id;
	}


}

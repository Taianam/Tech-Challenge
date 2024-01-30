package br.com.sistemadeparquimetro.DDD.dominio.DTO;

import java.util.List;

import br.com.sistemadeparquimetro.DDD.dominio.model.Condutor;
import br.com.sistemadeparquimetro.DDD.dominio.model.Veiculo;

public class CondutorDTO {

	private Long id;
	private String nome;
	private String endereco;
	private String email;
	private String telefone;
	private List<Veiculo> veiculos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	
	public CondutorDTO coverterEntidadeEmDto( Condutor condutor) {
		
		var condutorDto  = new CondutorDTO();
		
		condutorDto.setId(condutor.getId());
		condutorDto.setEmail(condutor.getEmail());
		condutorDto.setEndereco(condutor.getEndereco());
		condutorDto.setNome(condutor.getNome());
		condutorDto.setTelefone(condutor.getTelefone());
		condutorDto.setVeiculos(condutor.getVeiculos());
		return condutorDto;
	}
	
	public Condutor coverterDtoEmEntidade( CondutorDTO condutorDto) {
		
		var condutor  = new Condutor();
		
		condutor.setEmail(condutorDto.getEmail());
		condutor.setEndereco(condutorDto.getEndereco());
		condutor.setNome(condutorDto.getNome());
		condutor.setTelefone(condutorDto.getTelefone());
		condutor.setVeiculos(condutorDto.getVeiculos());
		return condutor;
	}
}

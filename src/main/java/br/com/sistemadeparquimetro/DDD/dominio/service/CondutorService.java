package br.com.sistemadeparquimetro.DDD.dominio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemadeparquimetro.DDD.dominio.DTO.CondutorDTO;
import br.com.sistemadeparquimetro.DDD.dominio.model.Veiculo;
import br.com.sistemadeparquimetro.DDD.infraestrutura.CondutorRepository;
import br.com.sistemadeparquimetro.DDD.infraestrutura.VeiculoRepository;

@Service
public class CondutorService {

	
	@Autowired
	CondutorRepository condutorRepository;
	
	@Autowired
	VeiculoRepository veiculoRepository;
	CondutorDTO condutorDto = new CondutorDTO();

	
	

	public CondutorDTO buscarcCondutorPorId(Long id) {
		var condutor = condutorRepository.findById(id);
		if (condutor.isPresent()) {
			return condutorDto.coverterEntidadeEmDto(condutor.get());
		}
		return null;
	}

	public List<CondutorDTO> buscarCondutores() {
		var condutor = condutorRepository.findAll();
		return condutor.stream().map(condutorDto::coverterEntidadeEmDto)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public CondutorDTO salvarCondutor(CondutorDTO condutorDTO ) {
		var condutor = condutorDto.coverterDtoEmEntidade(condutorDTO);
		var listaDeVeiculos = new ArrayList<Veiculo>();
 		var condutorSalvo = condutorRepository.save(condutor);
		
		for (Veiculo veiculo : condutor.getVeiculos()) {
			veiculo.setCondutor_id(condutorSalvo.getId());
			 listaDeVeiculos.add(veiculoRepository.save(veiculo));		}
		condutorSalvo.setVeiculos(listaDeVeiculos);
		return this.condutorDto.coverterEntidadeEmDto(condutorSalvo);
	}


	public void deleteById(Long id) {
		condutorRepository.deleteById(id);
	}
	
}

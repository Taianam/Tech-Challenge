package br.com.sistemadeparquimetro.DDD.aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sistemadeparquimetro.DDD.dominio.DTO.CondutorDTO;
import br.com.sistemadeparquimetro.DDD.dominio.service.CondutorService;

@Controller
@RequestMapping(value = "/api/condutores")
public class CondutorController {
	
	@Autowired
	private CondutorService condutorService  ;

	@GetMapping
	public ResponseEntity<List<CondutorDTO>> obter() {
		var condutores = this.condutorService.buscarCondutores();
		return new ResponseEntity<List<CondutorDTO>>(condutores, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<CondutorDTO> obterPorId(@PathVariable(value = "id") Long id) {
		var condutor = this.condutorService.buscarcCondutorPorId(id);
		if (condutor == null) {
			return new ResponseEntity<CondutorDTO>(condutor, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CondutorDTO>(condutor, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<CondutorDTO> adicionar(
			@RequestBody CondutorDTO CondutorDTO) {
		var condutor = this.condutorService.salvarCondutor(CondutorDTO);
		return new ResponseEntity<CondutorDTO>(condutor, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CondutorDTO> deletar(@PathVariable(value = "id") Long id) {
		this.condutorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}

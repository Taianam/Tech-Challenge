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

import br.com.sistemadeparquimetro.DDD.dominio.DTO.RegistroTempoDTO;
import br.com.sistemadeparquimetro.DDD.dominio.service.RegistroTempoService;

@Controller
@RequestMapping(value = "/api/registro_tempo")
public class RegistroTempoController {

	@Autowired
	private RegistroTempoService registroTempoService;

	@GetMapping
	public ResponseEntity<List<RegistroTempoDTO>> obter() {
		var tempoes = this.registroTempoService.buscarListaRegistroTempo();
		return new ResponseEntity<List<RegistroTempoDTO>>(tempoes, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<RegistroTempoDTO> obterPorId(@PathVariable(value = "id") Long id) {
		var tempo = this.registroTempoService.buscarTempoPorId(id);
		if (tempo == null) {
			return new ResponseEntity<RegistroTempoDTO>(tempo, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RegistroTempoDTO>(tempo, HttpStatus.OK);

	}

	@GetMapping("/relatorio/{id}")
	public ResponseEntity<byte[]> obterRelatorio(@PathVariable(value = "id") Long id) {
		var tempo = this.registroTempoService.exportarRecibo(id);
		if (tempo == null) {
			return new ResponseEntity<byte[]>(tempo, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<byte[]>(tempo, HttpStatus.OK);

	}

	@GetMapping("/notifica_por_email/{id_relatorio}")
	public ResponseEntity<String> notificaPorEmail(@PathVariable(value = "id_relatorio") Long id) {
		var mensagem = this.registroTempoService.notificarPorEmail(id);
		if (mensagem == null) {
			return new ResponseEntity<String>(mensagem, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(mensagem, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<RegistroTempoDTO> adicionar(@RequestBody RegistroTempoDTO registroTempoDTO) {
		var tempo = this.registroTempoService.salvarTempo(registroTempoDTO);
		return new ResponseEntity<RegistroTempoDTO>(tempo, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RegistroTempoDTO> deletar(@PathVariable(value = "id") Long id) {
		this.registroTempoService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}

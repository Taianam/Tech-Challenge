package br.com.sistemadeparquimetro.DDD.dominio.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import br.com.sistemadeparquimetro.DDD.aplicacao.Mailler;
import br.com.sistemadeparquimetro.DDD.aplicacao.MenssagemEmail;
import br.com.sistemadeparquimetro.DDD.dominio.DTO.RegistroTempoDTO;
import br.com.sistemadeparquimetro.DDD.dominio.exceptions.ResourceNotFoundException;
import br.com.sistemadeparquimetro.DDD.infraestrutura.CondutorRepository;
import br.com.sistemadeparquimetro.DDD.infraestrutura.RegistroTempoRepository;
import br.com.sistemadeparquimetro.DDD.infraestrutura.VeiculoRepository;

@Service
public class RegistroTempoService {

	@Autowired
	RegistroTempoRepository registroTempoRepository;
	@Autowired
	CondutorRepository condutorRepository;
	@Autowired
	VeiculoRepository veiculoRepository;
	@Autowired
	private Mailler mailler;

	
	RegistroTempoDTO registroTempoDTO = new RegistroTempoDTO();

	public RegistroTempoDTO buscarTempoPorId(Long id) {
		var tempo = registroTempoRepository.findById(id);
		if (tempo.isPresent()) {
			return registroTempoDTO.coverterEntidadeEmDto(tempo.get());
		}
		return null;
	}

	public List<RegistroTempoDTO> buscarListaRegistroTempo() {
		var tempo = registroTempoRepository.findAll();
		return tempo.stream().map(registroTempoDTO::coverterEntidadeEmDto)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public RegistroTempoDTO salvarTempo(RegistroTempoDTO registroTempoDTO) {

		if (registroTempoDTO.getIdTempo() == 1 && registroTempoDTO.getHorariofim() == null) {
			throw new ResourceNotFoundException("Informa o tempo final");
		} else {
			var tempo = RegistroTempoDTO.coverterDtoEmEntidade(registroTempoDTO);
			var tempoSalvo = registroTempoRepository.save(tempo);

			return this.registroTempoDTO.coverterEntidadeEmDto(tempoSalvo);
		}
	}
	
	 public byte[] exportarRecibo(Long id) {
		 var registroTempo = buscarTempoPorId(id);
		 DecimalFormat df = new DecimalFormat("0.00");
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 File file = null;

		 try {
			 Duration duration = Duration.between(registroTempo.getHorarioInicio(), registroTempo.getHorariofim());

		 file = File.createTempFile("relatorio", ".txt");

		 var output = new OutputStreamWriter( new FileOutputStream(file), StandardCharsets.UTF_8);

		 StringBuilder sb = new StringBuilder();

		 sb.append("Recibo Parquimetro  " + dtf.format(LocalDate.now()));
		 sb.append("Total de horas");
		 sb.append("\r\n");
		 sb.append(duration.toHoursPart()+":" +duration.toMinutesPart());		 
		 sb.append("\r\n");
		 sb.append("Tarifa: 3 reais a hora");	
		 sb.append("\r\n");
		 sb.append("Valor PAGO");
		 sb.append("\r\n");
		 sb.append(df.format(duration.toMinutesPart()<=30? duration.toHoursPart() * 3: duration.toHoursPart() + 1* 3));
		 sb.append("\r\n");
		 sb.append("Obrigada pela preferencia");

		 output.write(sb.toString());

		 output.close();

		 var arquivo = new ByteArrayResource(Files.readAllBytes(file.toPath())).getByteArray();

		 return arquivo;
		

		 } catch (Exception e) {
		 return null;

		 } finally {
		 file.delete();
		 }

		 }
	 
	 public String notificarPorEmail(Long id) {

		 
		 	var registroTempo =  buscarTempoPorId(id);
		 	var veiculo = veiculoRepository.findById(registroTempo.getVeiculoId());	
		 	
			var verificarEmail = condutorRepository.findById(veiculo.get().getCondutor_id());
				
				if (verificarEmail.isEmpty()){
					
					return "Email invalido!";		
				}else {
					
					var mensagem = "Relatorio por email";
					var enviar = new MenssagemEmail("Parquimetro", mensagem, Arrays.asList(verificarEmail.get().getEmail()));
					mailler.enviar(enviar);
					return "Email enviado com sucesso, verefique sua caixa de email!";
				}
					
					
			}

	public void deleteById(Long id) {
		registroTempoRepository.deleteById(id);
	}

}

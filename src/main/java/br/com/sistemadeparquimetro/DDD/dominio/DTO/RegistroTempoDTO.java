package br.com.sistemadeparquimetro.DDD.dominio.DTO;

import java.time.LocalDateTime;

import br.com.sistemadeparquimetro.DDD.dominio.model.RegistroTempo;
import br.com.sistemadeparquimetro.DDD.dominio.model.TipoPagamento;
import br.com.sistemadeparquimetro.DDD.dominio.model.TipoTempo;

public class RegistroTempoDTO {

	private Long id;
	private LocalDateTime horarioInicio;
	private LocalDateTime horariofim;
	private Long idPagamento;
	private TipoPagamento pagamento;
	private Long idTempo;
	private TipoTempo tempo;
	private Long veiculoId;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(LocalDateTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public LocalDateTime getHorariofim() {
		return horariofim;
	}

	public void setHorariofim(LocalDateTime horariofim) {
		this.horariofim = horariofim;
	}

	public TipoPagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(TipoPagamento pagamento) {
		this.pagamento = pagamento;
	}

	public TipoTempo getTempo() {
		return tempo;
	}

	public void setTempo(TipoTempo tempo) {
		this.tempo = tempo;
	}


	public Long getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(Long veiculoId) {
		this.veiculoId = veiculoId;
	}

	
	public Long getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Long getIdTempo() {
		return idTempo;
	}

	public void setIdTempo(Long idTempo) {
		this.idTempo = idTempo;
	}

	public RegistroTempoDTO coverterEntidadeEmDto( RegistroTempo tempo) {
		
		var tempoDto  = new RegistroTempoDTO();
		
		tempoDto.setId(tempo.getId());
		tempoDto.setHorarioInicio(LocalDateTime.now());
		tempoDto.setPagamento(TipoPagamento.getTipo(tempo.getIdPagamento()));
		tempoDto.setTempo(TipoTempo.getTipo(tempo.getIdTempo()));
		tempoDto.setHorariofim(tempo.getHorariofim());
		tempoDto.setVeiculoId(tempo.getVeiculoId());
		return tempoDto;
	}
	
	public static RegistroTempo coverterDtoEmEntidade( RegistroTempoDTO tempoDto) {
		
		var tempo  = new RegistroTempo();
		
		tempo.setId(tempoDto.getId());
		tempo.setHorarioInicio(LocalDateTime.now());
		tempo.setIdPagamento(tempoDto.getIdPagamento());
		tempo.setIdTempo(tempoDto.getIdTempo());
		tempo.setPagamento(TipoPagamento.getTipo(tempoDto.getIdPagamento()));
		tempo.setTempo(TipoTempo.getTipo(tempoDto.getIdTempo()));
		tempo.setHorariofim(tempoDto.getHorariofim());
		tempo.setVeiculoId(tempoDto.getVeiculoId());

		return tempo;
	}
}

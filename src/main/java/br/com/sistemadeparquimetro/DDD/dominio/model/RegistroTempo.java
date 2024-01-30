package br.com.sistemadeparquimetro.DDD.dominio.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class RegistroTempo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tempo_sequence")
	@SequenceGenerator(name = "tempo_sequence", sequenceName = "temp_seq")
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

}

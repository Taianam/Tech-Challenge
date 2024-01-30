package br.com.sistemadeparquimetro.DDD.dominio.model;

public enum TipoTempo {

	FIXO(1l, "fixo"), HORA(2l, "hora");

	private Long id;
	private String descricao;

	private TipoTempo(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;

	}

	public static TipoTempo getTipo(Long id) {

		if (id == null) {
			return null;
		}

		for (TipoTempo tempo : TipoTempo.values()) {
			if (id.equals(tempo.id)) {
				return tempo;
			}
		}

		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

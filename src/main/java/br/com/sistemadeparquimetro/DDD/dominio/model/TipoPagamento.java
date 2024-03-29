package br.com.sistemadeparquimetro.DDD.dominio.model;

public enum TipoPagamento {

	PIX(1l, "pix"), CREDITO(2l, "credito"), DEBITO(3l, "descricao");

	private Long id;
	private String descricao;

	private TipoPagamento(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;

	}

	public static TipoPagamento getTipo(Long id) {

		if (id == null) {
			return null;
		}

		for (TipoPagamento pagamento : TipoPagamento.values()) {
			if (id.equals(pagamento.id)) {

				return pagamento;
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

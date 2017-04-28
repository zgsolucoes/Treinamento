package zgsolucoes.webinar.refatoracao1

class Procedimento {
	private TipoProcedimento tipoProcedimento

	Procedimento(TipoProcedimento tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento
	}

	TipoProcedimento getTipoProcedimento() {
		return this.tipoProcedimento
	}

	float obtenhaValor() {
		switch (tipoProcedimento) {
			case TipoProcedimento.BASICO:
				return 50.00
				break

			case TipoProcedimento.COMUM:
				return 150.00
				break

			case TipoProcedimento.AVANCADO:
				return 500.00
				break

			default:
				return 0
		}
	}
}

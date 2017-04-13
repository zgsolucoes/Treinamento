package zgsolucoes.webinar.refatoracao1

class Procedimento {
	private TipoProcedimento tipoProcedimento

	Procedimento(TipoProcedimento tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento
	}

	TipoProcedimento getTipoProcedimento() {
		return this.tipoProcedimento
	}
}

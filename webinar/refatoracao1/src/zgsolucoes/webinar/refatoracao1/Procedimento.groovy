package zgsolucoes.webinar.refatoracao1

abstract class Procedimento {
	static Procedimento novoProcedimento(TipoProcedimento tipoProcedimento) {
		if (tipoProcedimento == TipoProcedimento.COMUM) {
			return new ProcedimentoComum()
		} else if (tipoProcedimento == TipoProcedimento.BASICO) {
			return new ProcedimentoBasico()
		} else if (tipoProcedimento == TipoProcedimento.AVANCADO) {
			return new ProcedimentoAvancado()
		}

		throw new IllegalArgumentException("Tipo de procedimento não conhecido: $tipoProcedimento")
	}

	abstract float obtenhaValor()
}

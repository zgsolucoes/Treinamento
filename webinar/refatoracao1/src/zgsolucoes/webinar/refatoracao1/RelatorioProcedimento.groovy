package zgsolucoes.webinar.refatoracao1

import java.text.NumberFormat

class RelatorioProcedimento {
	@Delegate
	Prontuario prontuario

	NumberFormat formatter = NumberFormat.currencyInstance

	RelatorioProcedimento(Prontuario prontuario) {
		this.prontuario = prontuario
	}

	String getRelatorio() {
		StringBuilder conta = new StringBuilder(montaCabecalho())
		conta.append(montaDadosInternacao())
		conta.append(montaDadosDosProcedimentos())
		conta.append(montaRodape())
		return conta.toString()
	}

	private String montaCabecalho() {
		return """----------------------------------------------------------------------------------------------
A conta do(a) paciente $nomePaciente tem valor total de __ ${formatter.format(valorDiarias + valorTotalProcedimentos)} __

Conforme os detalhes abaixo:"""
	}

	private String montaDadosDosProcedimentos() {
		final Map<Class, List<Procedimento>> procedimentosAgrupados = procedimentos.groupBy { it.class }

		final int qtdeProcedimentosBasicos = procedimentosAgrupados[ProcedimentoBasico.class]?.size() ?: 0
		final int qtdeProcedimentosComuns = procedimentosAgrupados[ProcedimentoComum.class]?.size() ?: 0
		final int qtdeProcedimentosAvancados = procedimentosAgrupados[ProcedimentoAvancado.class]?.size() ?: 0

		String dadosProcedimentos = ""
		if (procedimentos.size() > 0) {
			dadosProcedimentos += "\n\nValor Total Procedimentos:\t\t${formatter.format(valorTotalProcedimentos)}"

			if (qtdeProcedimentosBasicos > 0) {
				dadosProcedimentos += "\n\t\t\t\t\t${qtdeProcedimentosBasicos} procedimento${qtdeProcedimentosBasicos > 1 ? 's' : ''} básico${qtdeProcedimentosBasicos > 1 ? 's' : ''}"
			}

			if (qtdeProcedimentosComuns > 0) {
				dadosProcedimentos += "\n\t\t\t\t\t${qtdeProcedimentosComuns} procedimento${qtdeProcedimentosComuns > 1 ? 's' : ''} comu${qtdeProcedimentosComuns > 1 ? 'ns' : 'm'}"
			}

			if (qtdeProcedimentosAvancados > 0) {
				dadosProcedimentos += "\n\t\t\t\t\t${qtdeProcedimentosAvancados} procedimento${qtdeProcedimentosBasicos > 1 ? 's' : ''} avançado${qtdeProcedimentosAvancados > 1 ? 's' : ''}"
			}
		}
		dadosProcedimentos
	}

	private String montaDadosInternacao() {
		if (!internacao) {
			return ""
		}

		return """

Valor Total Diárias:			${formatter.format(valorDiarias)}
					${internacao.qtdeDias} diária${internacao.qtdeDias > 1 ? 's' : ''} em ${internacao.tipo}"""
	}

	private static String montaRodape() {
		return """

Volte sempre, a casa é sua!
----------------------------------------------------------------------------------------------"""
	}
}

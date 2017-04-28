package zgsolucoes.webinar.refatoracao1

import java.text.NumberFormat

class Prontuario {
	private String nomePaciente
	private Internacao internacao
	private Set<Procedimento> procedimentos = new HashSet<Procedimento>()

	Prontuario(String nomePaciente) {
		this.nomePaciente = nomePaciente
	}

	void setInternacao(Internacao internacao) {
		this.internacao = internacao
	}

	void addProcedimento(Procedimento procedimento) {
		this.procedimentos.add(procedimento)
	}

	String imprimaConta() {
		/* Organiza dados */
		final float valorDiarias = internacao ? internacao.obtenhaValor() : 0
		final float valorTotalProcedimentos = procedimentos.sum { Procedimento procedimento -> procedimento.obtenhaValor() } as Float ?: 0

		final Map<TipoProcedimento, List<Procedimento>> procedimentosAgrupados = procedimentos.groupBy { it.tipoProcedimento }

		final int qtdeProcedimentosBasicos = procedimentosAgrupados[TipoProcedimento.BASICO]?.size() ?: 0
		final int qtdeProcedimentosComuns = procedimentosAgrupados[TipoProcedimento.COMUM]?.size() ?: 0
		final int qtdeProcedimentosAvancados = procedimentosAgrupados[TipoProcedimento.AVANCADO]?.size() ?: 0

		return montaString(valorDiarias, valorTotalProcedimentos, qtdeProcedimentosBasicos, qtdeProcedimentosComuns, qtdeProcedimentosAvancados)
	}

	private String montaString(float valorDiarias, float valorTotalProcedimentos, int qtdeProcedimentosBasicos, int qtdeProcedimentosComuns, int qtdeProcedimentosAvancados) {
		def formatter = NumberFormat.currencyInstance
		StringBuilder conta = new StringBuilder(montaCabecalho(formatter, valorDiarias, valorTotalProcedimentos))
		conta.append(montaDadosInternacao(formatter, valorDiarias))
		conta.append(montaDadosDosProcedimentos(formatter, valorTotalProcedimentos, qtdeProcedimentosBasicos, qtdeProcedimentosComuns, qtdeProcedimentosAvancados))
		conta.append(montaRodape())
		return conta.toString()
	}

	private String montaCabecalho(NumberFormat formatter, float valorDiarias, float valorTotalProcedimentos) {
		return """----------------------------------------------------------------------------------------------
A conta do(a) paciente $nomePaciente tem valor total de __ ${formatter.format(valorDiarias + valorTotalProcedimentos)} __

Conforme os detalhes abaixo:"""
	}

	private String montaDadosDosProcedimentos(NumberFormat formatter, float valorTotalProcedimentos, int qtdeProcedimentosBasicos, int qtdeProcedimentosComuns, int qtdeProcedimentosAvancados) {
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

	private String montaDadosInternacao(NumberFormat formatter, float valorDiarias) {
		if (!internacao) {
			return ""
		}

		return """

Valor Total Diárias:			${formatter.format(valorDiarias)}
					${internacao.qtdeDias} diária${internacao.qtdeDias > 1 ? 's' : ''} em ${internacao.tipoLeito == TipoLeito.APARTAMENTO ? 'apartamento' : 'enfermaria'}"""
	}

	private static String montaRodape() {
		return """

Volte sempre, a casa é sua!
----------------------------------------------------------------------------------------------"""
	}
}

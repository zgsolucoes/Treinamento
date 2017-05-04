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

	private float getValorDiarias() {
		return internacao ? internacao.obtenhaValor() : 0
	}

	private float getValorTotalProcedimentos() {
		procedimentos.sum { Procedimento procedimento -> procedimento.obtenhaValor() } as Float ?: 0
	}

	String imprimaConta() {
		/* Organiza dados */
		return montaString()
	}

	private String montaString() {
		def formatter = NumberFormat.currencyInstance
		StringBuilder conta = new StringBuilder(montaCabecalho(formatter))
		conta.append(montaDadosInternacao(formatter))
		conta.append(montaDadosDosProcedimentos(formatter))
		conta.append(montaRodape())
		return conta.toString()
	}

	private String montaCabecalho(NumberFormat formatter) {
		return """----------------------------------------------------------------------------------------------
A conta do(a) paciente $nomePaciente tem valor total de __ ${formatter.format(valorDiarias + valorTotalProcedimentos)} __

Conforme os detalhes abaixo:"""
	}

	private String montaDadosDosProcedimentos(NumberFormat formatter) {
		final Map<TipoProcedimento, List<Procedimento>> procedimentosAgrupados = procedimentos.groupBy { it.tipoProcedimento }

		final int qtdeProcedimentosBasicos = procedimentosAgrupados[TipoProcedimento.BASICO]?.size() ?: 0
		final int qtdeProcedimentosComuns = procedimentosAgrupados[TipoProcedimento.COMUM]?.size() ?: 0
		final int qtdeProcedimentosAvancados = procedimentosAgrupados[TipoProcedimento.AVANCADO]?.size() ?: 0

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

	private String montaDadosInternacao(NumberFormat formatter) {
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

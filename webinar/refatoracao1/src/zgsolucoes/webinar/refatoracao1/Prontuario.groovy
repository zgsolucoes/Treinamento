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
		def formatter = NumberFormat.currencyInstance

		float valorDiarias = contabilizaDiarias()
		float valorTotalProcedimentos = obtenhaTotalProcedimentos()
		int qtdeProcedimentosBasicos = obtenhaQtdeProcedimentos(TipoProcedimento.BASICO)
		int qtdeProcedimentosComuns = obtenhaQtdeProcedimentos(TipoProcedimento.COMUM)
		int qtdeProcedimentosAvancados = obtenhaQtdeProcedimentos(TipoProcedimento.AVANCADO)

		String conta = montaCabecalho(formatter, valorDiarias, valorTotalProcedimentos)
		conta += montaDadosInternacao(formatter, valorDiarias)
		conta += montaDadosDosProcedimentos(formatter, valorTotalProcedimentos, qtdeProcedimentosBasicos, qtdeProcedimentosComuns, qtdeProcedimentosAvancados)
		conta += montaRodape()

		return conta
	}

	private String montaCabecalho(NumberFormat formatter, float valorDiarias, float valorTotalProcedimentos) {
		String cabecalho = "----------------------------------------------------------------------------------------------"
		cabecalho += "\nA conta do(a) paciente $nomePaciente tem valor total de __ ${formatter.format(valorDiarias + valorTotalProcedimentos)} __"
		cabecalho += "\n\nConforme os detalhes abaixo:"
		cabecalho
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
		String dadosInternacao = ""
		if (internacao) {
			dadosInternacao += "\n\nValor Total Diárias:\t\t\t${formatter.format(valorDiarias)}"
			dadosInternacao += "\n\t\t\t\t\t${internacao.qtdeDias} diária${internacao.qtdeDias > 1 ? 's' : ''} em ${internacao.tipoLeito == TipoLeito.APARTAMENTO ? 'apartamento' : 'enfermaria'}"
		}
		return dadosInternacao
	}

	private static String montaRodape() {
		String rodape = "\n\nVolte sempre, a casa é sua!"
		rodape += "\n----------------------------------------------------------------------------------------------"
		return rodape
	}

	int obtenhaQtdeProcedimentos(TipoProcedimento tipoProcedimento) {
		return procedimentos.count { Procedimento procedimento -> procedimento.tipoProcedimento == tipoProcedimento }
	}

	float obtenhaTotalProcedimentos() {
		return procedimentos.sum { Procedimento procedimento -> procedimento.obtenhaValor() } as Float ?: 0
	}

	private float contabilizaDiarias() {
		return internacao ? internacao.obtenhaValor() : 0
	}
}

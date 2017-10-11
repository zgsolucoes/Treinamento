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

		float valorDiarias = internacao?.obtenhaValorDiarias() ?: 0
		float valorTotalProcedimentos = obtenhaTotalProcedimentos()
		int qtdeProcedimentosBasicos = obtenhaQtdeProcedimentosBasicos()
		int qtdeProcedimentosComuns = obtenhaQtdeProcedimentosComuns()
		int qtdeProcedimentosAvancados = obtenhaQtdeProcedimentosAvancados()

		return montaStrRelatorio(formatter, valorDiarias, valorTotalProcedimentos, qtdeProcedimentosBasicos, qtdeProcedimentosComuns, qtdeProcedimentosAvancados)
	}

	private String montaStrRelatorio(NumberFormat formatter, float valorDiarias, float valorTotalProcedimentos, int qtdeProcedimentosBasicos, int qtdeProcedimentosComuns, int qtdeProcedimentosAvancados) {
		String conta = "----------------------------------------------------------------------------------------------"
		conta += montaDadosCabecalho(formatter, valorDiarias, valorTotalProcedimentos)
		conta += montaDadosInternacao(formatter, valorDiarias)
		conta += montaDadosProcedimentos(formatter, valorTotalProcedimentos, qtdeProcedimentosBasicos, qtdeProcedimentosComuns, qtdeProcedimentosAvancados)
		conta += montaDadosCabecalho()

		return conta
	}

	private String montaDadosCabecalho() {
		String conta = "\n\nVolte sempre, a casa é sua!"
		conta += "\n----------------------------------------------------------------------------------------------"
		conta
	}

	private String montaDadosProcedimentos(NumberFormat formatter, float valorTotalProcedimentos, int qtdeProcedimentosBasicos, int qtdeProcedimentosComuns, int qtdeProcedimentosAvancados) {
		String conta = ""
		if (procedimentos.size() > 0) {
			conta += "\n\nValor Total Procedimentos:\t\t${formatter.format(valorTotalProcedimentos)}"

			if (qtdeProcedimentosBasicos > 0) {
				conta += "\n\t\t\t\t\t${qtdeProcedimentosBasicos} procedimento${qtdeProcedimentosBasicos > 1 ? 's' : ''} básico${qtdeProcedimentosBasicos > 1 ? 's' : ''}"
			}

			if (qtdeProcedimentosComuns > 0) {
				conta += "\n\t\t\t\t\t${qtdeProcedimentosComuns} procedimento${qtdeProcedimentosComuns > 1 ? 's' : ''} comu${qtdeProcedimentosComuns > 1 ? 'ns' : 'm'}"
			}

			if (qtdeProcedimentosAvancados > 0) {
				conta += "\n\t\t\t\t\t${qtdeProcedimentosAvancados} procedimento${qtdeProcedimentosBasicos > 1 ? 's' : ''} avançado${qtdeProcedimentosAvancados > 1 ? 's' : ''}"
			}
		}
		conta
	}

	private String montaDadosInternacao(NumberFormat formatter, float valorDiarias) {
		String conta = ""
		if (internacao) {
			conta += "\n\nValor Total Diárias:\t\t\t${formatter.format(valorDiarias)}"
			conta += "\n\t\t\t\t\t${internacao.qtdeDias} diária${internacao.qtdeDias > 1 ? 's' : ''} em ${internacao.tipoLeito == TipoLeito.APARTAMENTO ? 'apartamento' : 'enfermaria'}"
		}
		conta
	}

	private String montaDadosCabecalho(NumberFormat formatter, float valorDiarias, float valorTotalProcedimentos) {
		String conta = ""
		conta += "\nA conta do(a) paciente $nomePaciente tem valor total de __ ${formatter.format(valorDiarias + valorTotalProcedimentos)} __"
		conta += "\n\nConforme os detalhes abaixo:"
		conta
	}

	float obtenhaTotalProcedimentos() {
		float valorTotalProcedimentos = 0.00

		//Contabiliza os procedimentos
		for (Procedimento procedimento in procedimentos) {
			switch (procedimento.tipoProcedimento) {
				case TipoProcedimento.BASICO:
					valorTotalProcedimentos += 50.00
					break

				case TipoProcedimento.COMUM:
					valorTotalProcedimentos += 150.00
					break

				case TipoProcedimento.AVANCADO:
					valorTotalProcedimentos += 500.00
					break
			}
		}

		return valorTotalProcedimentos
	}

	int obtenhaQtdeProcedimentosBasicos() {
		return obtenhaQtdeProcedimentos(TipoProcedimento.BASICO)
	}

	int obtenhaQtdeProcedimentos(TipoProcedimento tipoProcedimento) {
		int qtdeProcedimentos = 0

		//Contabiliza os procedimentos
		for (Procedimento procedimento in procedimentos) {
			if (procedimento.tipoProcedimento == tipoProcedimento) {
				qtdeProcedimentos++
			}
		}

		return qtdeProcedimentos
	}


	int obtenhaQtdeProcedimentosComuns() {
		return obtenhaQtdeProcedimentos(TipoProcedimento.COMUM)
	}

	int obtenhaQtdeProcedimentosAvancados() {
		return obtenhaQtdeProcedimentos(TipoProcedimento.AVANCADO)
	}
}

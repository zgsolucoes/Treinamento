package zgsolucoes.webinar.refatoracao1

class Prontuario {
	String nomePaciente
	Internacao internacao
	Set<Procedimento> procedimentos = new HashSet<Procedimento>()

	Prontuario(String nomePaciente) {
		this.nomePaciente = nomePaciente
	}

	void setInternacao(Internacao internacao) {
		this.internacao = internacao
	}

	void addProcedimento(Procedimento procedimento) {
		this.procedimentos.add(procedimento)
	}

	float getValorDiarias() {
		return internacao ? internacao.obtenhaValor() : 0
	}

	float getValorTotalProcedimentos() {
		procedimentos.sum { Procedimento procedimento -> procedimento.obtenhaValor() } as Float ?: 0
	}

	String imprimaConta() {
		return new RelatorioProcedimento(this).relatorio
	}
}

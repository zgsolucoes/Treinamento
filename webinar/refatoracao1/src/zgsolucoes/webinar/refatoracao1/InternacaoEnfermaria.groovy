package zgsolucoes.webinar.refatoracao1

class InternacaoEnfermaria extends Internacao {
	float obtenhaValor() {
		float valorDiarias = 0

		if (qtdeDias <= 3) {
			valorDiarias += 40.00 * qtdeDias // Internação Básica
		} else if (qtdeDias <= 8) {
			valorDiarias += 35.00 * qtdeDias // Internação Média
		} else {
			valorDiarias += 30.00 * qtdeDias // Internação Grave
		}

		return valorDiarias
	}

	@Override
	String getTipo() {
		return "enfermaria"
	}
}

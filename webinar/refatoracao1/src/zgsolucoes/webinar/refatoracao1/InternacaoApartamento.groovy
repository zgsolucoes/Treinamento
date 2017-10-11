package zgsolucoes.webinar.refatoracao1

class InternacaoApartamento extends Internacao {

	@Override
	float obtenhaValor() {
		float valorDiarias = 0

		if (qtdeDias <= 3) {
			valorDiarias += 100.00 * qtdeDias // Internação Básica
		} else if (qtdeDias <= 8) {
			valorDiarias += 90.00 * qtdeDias  // Internação Média
		} else {
			valorDiarias += 80.00 * qtdeDias  // Internação Grave
		}

		return valorDiarias
	}

	@Override
	String getTipo() {
		return "apartamento"
	}
}

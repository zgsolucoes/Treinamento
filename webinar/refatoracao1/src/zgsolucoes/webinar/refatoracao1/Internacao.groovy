package zgsolucoes.webinar.refatoracao1

class Internacao {
	private TipoLeito tipoLeito
	private int qtdeDias

	Internacao(TipoLeito tipoLeito, int qtdeDias) {
		this.tipoLeito = tipoLeito
		this.qtdeDias = qtdeDias
	}

	TipoLeito getTipoLeito() {
		return this.tipoLeito
	}

	int getQtdeDias() {
		return this.qtdeDias
	}

	float obtenhaValorDiarias() {
		float valorDiarias = 0.0

		// Contabilizar as diárias
		switch (tipoLeito) {
			case TipoLeito.ENFERMARIA:
				if (qtdeDias <= 3) {
					valorDiarias += 40.00 * qtdeDias // Internação Básica
				} else if (qtdeDias <= 8) {
					valorDiarias += 35.00 * qtdeDias // Internação Média
				} else {
					valorDiarias += 30.00 * qtdeDias // Internação Grave
				}
				break

			case TipoLeito.APARTAMENTO:
				if (qtdeDias <= 3) {
					valorDiarias += 100.00 * qtdeDias // Internação Básica
				} else if (qtdeDias <= 8) {
					valorDiarias += 90.00 * qtdeDias  // Internação Média
				} else {
					valorDiarias += 80.00 * qtdeDias  // Internação Grave
				}
				break
		}
		return valorDiarias
	}
}

package zgsolucoes.webinar.refatoracao1

abstract class Internacao {
	protected int qtdeDias

	static Internacao novaInternacao(TipoLeito tipoLeito, int qtdeDias) {
		Internacao novaInternacao
		if (tipoLeito == TipoLeito.ENFERMARIA) {
			novaInternacao = new InternacaoEnfermaria()
		} else if (tipoLeito == TipoLeito.APARTAMENTO) {
			novaInternacao = new InternacaoApartamento()
		} else {
			throw new IllegalArgumentException("Tipo de leito desconhecido; $tipoLeito".toString())
		}

		novaInternacao.qtdeDias = qtdeDias

		return novaInternacao
	}

	int getQtdeDias() {
		return this.qtdeDias
	}

	abstract float obtenhaValor()

	abstract String getTipo()
}

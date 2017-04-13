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
}

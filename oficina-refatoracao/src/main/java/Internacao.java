public class Internacao {

	private TipoLeito tipoLeito;
	private int qtdeDias;

	public Internacao(TipoLeito tipoLeito, int qtdeDias) {
		this.tipoLeito = tipoLeito;
		this.qtdeDias = qtdeDias;
	}

	TipoLeito getTipoLeito() {
		return this.tipoLeito;
	}

	int getQtdeDias() {
		return this.qtdeDias;
	}

}

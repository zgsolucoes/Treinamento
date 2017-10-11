import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prontuario {

	private String nomePaciente;
	private Internacao internacao;
	private Set<Procedimento> procedimentos = new HashSet<>();

	public Prontuario(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setInternacao(Internacao internacao) {
		this.internacao = internacao;
	}

	public Internacao getInternacao() {
		return this.internacao;
	}

	public void addProcedimento(Procedimento procedimento) {
		this.procedimentos.add(procedimento);
	}

	public Set<Procedimento> getProcedimentos() {
		return this.procedimentos;
	}

	public String imprimaConta() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		String conta = "----------------------------------------------------------------------------------------------";

		float valorDiarias = 0.0f;

		// Contabilizar as diárias
		if (internacao != null) {
			switch (internacao.getTipoLeito()) {
				case ENFERMARIA:
					if (internacao.getQtdeDias() <= 3) {
						valorDiarias += 40.00 * internacao.getQtdeDias(); // Internação Básica
					} else if (internacao.getQtdeDias() <= 8) {
						valorDiarias += 35.00 * internacao.getQtdeDias(); // Internação Média
					} else {
						valorDiarias += 30.00 * internacao.getQtdeDias(); // Internação Grave
					}
					break;
				case APARTAMENTO:
					if (internacao.getQtdeDias() <= 3) {
						valorDiarias += 100.00 * internacao.getQtdeDias(); // Internação Básica
					} else if (internacao.getQtdeDias() <= 8) {
						valorDiarias += 90.00 * internacao.getQtdeDias();  // Internação Média
					} else {
						valorDiarias += 80.00 * internacao.getQtdeDias();  // Internação Grave
					}
					break;
			}
		}

		float valorTotalProcedimentos = 0.00f;
		int qtdeProcedimentosBasicos = 0;
		int qtdeProcedimentosComuns = 0;
		int qtdeProcedimentosAvancados = 0;

		//Contabiliza os procedimentos
		for (Procedimento procedimento : procedimentos) {
			switch (procedimento.getTipoProcedimento()) {
				case BASICO:
					qtdeProcedimentosBasicos++;
					valorTotalProcedimentos += 50.00;
					break;

				case COMUM:
					qtdeProcedimentosComuns++;
					valorTotalProcedimentos += 150.00;
					break;

				case AVANCADO:
					qtdeProcedimentosAvancados++;
					valorTotalProcedimentos += 500.00;
					break;
			}
		}

		conta += "\nA conta do(a) paciente " + nomePaciente + " tem valor total de __ " + formatter.format(valorDiarias + valorTotalProcedimentos) + " __";
		conta += "\n\nConforme os detalhes abaixo:";

		if (internacao != null) {
			conta += "\n\nValor Total Diárias:\t\t\t" + formatter.format(valorDiarias);
			conta += "\n\t\t\t\t\t" + internacao.getQtdeDias() + " diária" + (internacao.getQtdeDias() > 1 ? "s" : "")
					+ " em " + (internacao.getTipoLeito() == TipoLeito.APARTAMENTO ? "apartamento" : "enfermaria");
		}

		if (procedimentos.size() > 0) {
			conta += "\n\nValor Total Procedimentos:\t\t" + formatter.format(valorTotalProcedimentos);

			if (qtdeProcedimentosBasicos > 0) {
				conta += "\n\t\t\t\t\t" + qtdeProcedimentosBasicos + " procedimento" + (qtdeProcedimentosBasicos > 1 ? "s" : "")
						+ " básico" + (qtdeProcedimentosBasicos > 1 ? "s" : "");
			}

			if (qtdeProcedimentosComuns > 0) {
				conta += "\n\t\t\t\t\t" + qtdeProcedimentosComuns + " procedimento" + (qtdeProcedimentosComuns > 1 ? "s" : "")
						+ " comu" + (qtdeProcedimentosComuns > 1 ? "ns" : "m");
			}

			if (qtdeProcedimentosAvancados > 0) {
				conta += "\n\t\t\t\t\t" + qtdeProcedimentosAvancados + " procedimento" + (qtdeProcedimentosBasicos > 1 ? "s" : "")
						+ " avançado" + (qtdeProcedimentosAvancados > 1 ? "s" : "");
			}
		}

		conta += "\n\nVolte sempre, a casa é sua!";
		conta += "\n----------------------------------------------------------------------------------------------";

		return conta;
	}

	boolean b = false;

	public Prontuario carregueProntuario(String arquivoCsv) throws IOException {
		Prontuario prontuario = new Prontuario(null);

		Path path = Paths.get(arquivoCsv);

		Stream<String> linhas = Files.lines(path);

		linhas.forEach((str) -> {
			if (b == false) {
				b = true;
			} else {
				System.out.println(str);

				String[] dados = str.split(",");

				String nomePaciente = dados[0].trim();

				TipoLeito tipoLeito = dados[1] != null && !dados[1].trim().isEmpty() ? TipoLeito.valueOf(dados[1].trim()) : null;

				int qtdeDiasInternacao = dados[2] != null && !dados[2].trim().isEmpty() ? Integer.parseInt(dados[2].trim()) : -1;

				TipoProcedimento tipoProcedimento = dados[3] != null && !dados[3].trim().isEmpty() ? TipoProcedimento.valueOf(dados[3].trim()) : null;

				int qtdeProcedimentos = dados.length == 5 && dados[4] != null && !dados[4].trim().isEmpty() ? Integer.parseInt(dados[4].trim()) : -1;

				prontuario.nomePaciente = nomePaciente;

				if (tipoLeito != null && qtdeDiasInternacao > 0) {
					prontuario.internacao = new Internacao(tipoLeito, qtdeDiasInternacao);
				}

				if (tipoProcedimento != null && qtdeProcedimentos > 0) {
					while (qtdeProcedimentos > 0) {
						prontuario.addProcedimento(new Procedimento(tipoProcedimento));
						qtdeProcedimentos--;
					}
				}
			}
		});

		return prontuario;
	}

	List<String> l = new ArrayList<>();

	public String salveProntuario() throws IOException {

		l.add("nome_paciente,tipo_leito,qtde_dias_internacao,tipo_procedimento,qtde_procedimentos");

		String l1 = nomePaciente + ",";

		if (internacao != null) {
			l1 += internacao.getTipoLeito() + "," + internacao.getQtdeDias() + ",,";
			l.add(l1);
		}

		if (procedimentos.size() > 0) {
			Map<TipoProcedimento, Long> procedimentosAgrupados = procedimentos.stream().collect(
					Collectors.groupingBy(Procedimento::getTipoProcedimento, Collectors.counting()));

			List<TipoProcedimento> procedimentosOrdenados = new ArrayList<>(procedimentosAgrupados.keySet());
			Collections.sort(procedimentosOrdenados);

			for (TipoProcedimento chave : procedimentosOrdenados) {
				String l2 = nomePaciente + ",,," + chave + "," + procedimentosAgrupados.get(chave);
				l.add(l2);
			}
		}

		if (l.size() == 1) {
			l1 += ",,,";
			l.add(l1);
		}

		Path path = Paths.get(nomePaciente.replaceAll(" ", "_").concat(String.valueOf(System.currentTimeMillis())).concat(".csv"));

		Files.write(path, l);

		return path.toString();
	}
}

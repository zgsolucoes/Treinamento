package zgsolucoes.webinar.refatoracao1

import org.junit.Test

class ProntuarioTest {

	@Test
	void testAtendimentoPaulMcCartney() {
		Prontuario prontuario = new Prontuario("Paul McCartney")

		prontuario.addProcedimento(new Procedimento(TipoProcedimento.BASICO))
		prontuario.addProcedimento(new Procedimento(TipoProcedimento.AVANCADO))

		final String respostaEsperada = """----------------------------------------------------------------------------------------------
A conta do(a) paciente Paul McCartney tem valor total de __ R\$ 550,00 __

Conforme os detalhes abaixo:

Valor Total Procedimentos:		R\$ 550,00
					1 procedimento básico
					1 procedimento avançado

Volte sempre, a casa é sua!
----------------------------------------------------------------------------------------------"""

		println prontuario.imprimaConta()
		println respostaEsperada

		assert prontuario.imprimaConta() == respostaEsperada
	}

	@Test
	void testInternacaoNandoReis() {
		Prontuario prontuario = new Prontuario("Nando Reis")
		prontuario.setInternacao(new Internacao(TipoLeito.APARTAMENTO, 4))

		prontuario.addProcedimento(new Procedimento(TipoProcedimento.BASICO))
		prontuario.addProcedimento(new Procedimento(TipoProcedimento.COMUM))
		prontuario.addProcedimento(new Procedimento(TipoProcedimento.COMUM))
		prontuario.addProcedimento(new Procedimento(TipoProcedimento.AVANCADO))

		final String respostaEsperada = """----------------------------------------------------------------------------------------------
A conta do(a) paciente Nando Reis tem valor total de __ R\$ 1.210,00 __

Conforme os detalhes abaixo:

Valor Total Diárias:			R\$ 360,00
					4 diárias em apartamento

Valor Total Procedimentos:		R\$ 850,00
					1 procedimento básico
					2 procedimentos comuns
					1 procedimento avançado

Volte sempre, a casa é sua!
----------------------------------------------------------------------------------------------"""

		assert prontuario.imprimaConta() == respostaEsperada
	}
}

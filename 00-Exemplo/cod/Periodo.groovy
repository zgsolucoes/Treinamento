package br.com.fibonacci.utils

class Periodo {
	Date dataInicio
	Date dataFim

	int getQuantidadeDeDias() {
		dataFim - dataInicio + 1
	}

	String toString() {
		String dataInicio = DateHelper.obtenhaData_dd_MM_yyyy(dataInicio)
		String dataFim = DateHelper.obtenhaData_dd_MM_yyyy(dataFim)
		"Periodo $dataInicio a $dataFim, com $quantidadeDeDias dias"
	}
}
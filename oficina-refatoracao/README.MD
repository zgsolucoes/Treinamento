# Prontuário Eletrônico
Você deve imprimir o valor da conta de um prontuário de um paciente de uma clínica de saúde.

Deve ser possível exportar os dados de um prontuário em um arquivo CSV.

Deve ser possível importar os dados de um csv para um prontuário.

### Regras
O **cálculo do valor das diárias** deve seguir as seguintes regras:

| Tipo de leito	| Qtde de dias internado	| Valor	por dia	|
| ------------- |--------------------------:| -------------:|
| Enfermaria	| Até 3 dias 				| **R$ 40,00** 	|
| Enfermaria	| De 4 a 8 dias      		| **R$ 35,00** 	|
| Enfermaria	| 9 dias ou mais     		| **R$ 30,00** 	|
| Apartamento	| Até 3 dias   				| **R$ 100,00** |
| Apartamento	| De 4 a 8 dias       		| **R$ 90,00** 	|
| Apartamento	|  9 dias ou mais      		| **R$ 80,00** 	|
	
O **cálculo do valor dos procedimentos** deve seguir as seguintes regras:

| Tipo de procedimento	| Valor			|
| ----------------------|--------------:|
| Básico				| **R$ 50,00** 	|
| Intermediário			| **R$ 150,00**	|
| Avançado				| **R$ 500,00**	|


## Exemplo de relatório gerado

```

----------------------------------------------------------------------------------------------

A conta do(a) paciente Nando Reis tem valor total de __ R$ 1.210,00 __

Conforme os detalhes abaixo:

Valor Total Diárias: 		R$ 360,00 
				4 diárias em apartamento

Valor Total Procedimentos: 	R$ 850,00 
				1 procedimento avançado
				2 procedimentos comuns
				1 procedimento básico

Volte sempre, a casa é sua!
----------------------------------------------------------------------------------------------
```
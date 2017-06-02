# Treinamento

Repositório para armazenar treinamentos da ZG Soluções.

## Dependências

- LaTeX (sudo apt-get install texlive texlive-lang-portuguese texlive-latex-extra texlive-science texlive-extra-utils texlive-publishers)
- Python
- Pygments (pip install Pygments)
- Inkscape (sudo apt-get install inkscape)

## Preparando o Projeto

### Instalar o tema da ZG:

./install_lib.sh

### Preparar o editor

O projeto usa o Minted para highlight de código e esse usa o Pygments, que é externo ao LaTeX. Para que funcione normalmente, deve-se adicionar a opção -shell-escape no comando do LaTeX, ficando:

pdflatex -shell-escape

Para facilitar, adicione na opção de compilação do editor escolhido. No Kile, deve-se entrar em Settings > Configure Kile > Tools > Build > PDFLaTeX > Options e adicionar a opção.

### Compile o projeto de exemplo

Para testar que a configuração foi feita com sucesso, abra o 00-Exemplo/exemplo.tex e o compile. Esse projeto exemplifica como fazer algumas tarefas comuns nas apresentações.

## Figuras e gráficos do draw.io

Exportar para PNG com: Transparent Background e 200% de zoom. Salvar o diagrama em XML na pasta 'source' para tornar possível futura alteração.

## Tabelas

Utilize o site: http://www.tablesgenerator.com

Troque "Default table style" por "Booktab table style".

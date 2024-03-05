**Simulador de Máquina de Turing em Java**

Este simulador de Máquina de Turing em Java permite a execução de instruções definidas em um arquivo JSON, processando uma palavra de entrada a partir de um arquivo de texto (entrada.txt). A máquina opera sobre uma fita, cujo estado final (aceitação ou rejeição) é indicado no terminal com o código 1 para aceitação e 0 para rejeição. A saída da fita é escrita em um arquivo de texto (saida.txt).

O arquivo JSON de especificações (spec.json) contém informações sobre os estados, transições, símbolos de entrada e saída, e a direção de movimento da cabeça da fita. Cada transição é definida por um estado atual, um símbolo lido, um novo estado, um novo símbolo a ser escrito na fita e a direção de movimento da cabeça.

O simulador utiliza a biblioteca Gson para ler o arquivo JSON de especificações e mapeá-lo para objetos Java, facilitando o processamento das instruções da Máquina de Turing.

**Compilação e Execução:**

**Compilação:**

No terminal, navegue até o diretório onde estão seus arquivos .java e o arquivo gson-2.8.8.jar. Utilize o comando javac com a opção -classpath para especificar o caminho para o arquivo gson-2.8.8.jar e compile todos os arquivos .java presentes no diretório:

```javac -classpath /caminho/do/seu/projeto/lib/gson-2.8.8.jar *.java```

**Execução:**

Após a compilação, execute o programa usando o comando java com a mesma opção -classpath para especificar o caminho para o arquivo gson-2.8.8.jar e o nome da classe principal (TURING neste caso), sem a extensão .java:

'''java -classpath /caminho/do/seu/projeto/lib/gson-2.8.8.jar TURING'''

Isso processará as instruções da Máquina de Turing conforme especificado no arquivo JSON de especificações (spec.json) e a palavra de entrada do arquivo de texto (entrada.txt), gerando a saída da fita no arquivo saida.txt e exibindo no terminal se a fita foi aceita (1) ou rejeitada (0).

Certifique-se de ajustar os caminhos conforme a estrutura do seu projeto.

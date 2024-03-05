import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.*;

public class TURING {
    int CONT;
    char FITA[];
    Especificacoes especificacoes;

    public static void main(String[] args) {
        TURING MAQ = new TURING();

        File specFile = new File("spec.json");
        File outputFile = new File("saida.txt");
        try {
            // Limpa o arquivo de saída
            if (outputFile.exists()) {
                outputFile.delete();
            }

            FileReader specReader = new FileReader(specFile);
            MAQ.especificacoes = Especificacoes.fromJson(specReader);
            specReader.close();

            File inputFile = new File("entrada.txt");
            Scanner ENTRADA = new Scanner(inputFile);

            while (ENTRADA.hasNextLine()) {
                String PALAVRA = ENTRADA.nextLine();
                String resultado = MAQ.Iniciar(PALAVRA);

                if (resultado != null) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
                    writer.write(resultado);
                    writer.newLine();
                    writer.close();
                } else {
                    System.err.println("Erro ao processar a entrada: resultado nulo.");
                }
            }
            ENTRADA.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler/escrever arquivo: " + e.getMessage());
        }
    }

    public String Iniciar(String SENTENCA) {
        CONT = 0;
        FITA = SENTENCA.toCharArray();
        return executar();
    }

    public String executar() {
        int estadoAtual = especificacoes.getInitial();
        while (estadoAtual != especificacoes.getEstadoFinal()) {
            if (CONT >= 0 && CONT < FITA.length) {
                char simboloAtual = FITA[CONT];
                Transicao transicao = encontrarTransicao(estadoAtual, simboloAtual);
                if (transicao != null) {
                    FITA[CONT] = transicao.getNovoSimbolo();
                    if (transicao.getDirecao() == Direcao.R) {
                        CONT++;
                    } else if (transicao.getDirecao() == Direcao.L) {
                        CONT--;
                    }
                    estadoAtual = transicao.getProximoEstado();
                } else {
                    return qerro();
                }
            } else {
                return qerro();
            }
        }
        StringBuilder PALAVRA = new StringBuilder();
        for (int i = 0; i < FITA.length - 1; i++) {
            PALAVRA.append(FITA[i]);
        }

        System.err.println(1);
        return PALAVRA.toString();
    }

    public Transicao encontrarTransicao(int estadoAtual, char simboloAtual) {
        for (Transicao transicao : especificacoes.getTransitions()) {
            if (transicao.getEstadoAtual() == estadoAtual && transicao.getSimboloLido() == simboloAtual) {
                return transicao;
            }
        }
        return null;
    }

    public String qerro() {
        StringBuilder PALAVRA = new StringBuilder();
        for (int i = 0; i < FITA.length - 1; i++) {
            PALAVRA.append(FITA[i]);
        }

        System.err.println(0);
        return PALAVRA.toString();
    }

    public static class Especificacoes {
        private int initial;
        private int estadoFinal;
        private Transicao[] transitions;

        public static Especificacoes fromJson(FileReader json) {
            Gson gson = new Gson();
            return gson.fromJson(json, Especificacoes.class);
        }

        public int getInitial() {
            return initial;
        }

        public int getEstadoFinal() {
            return estadoFinal;
        }

        public Transicao[] getTransitions() {
            return transitions;
        }
    }

    public static class Transicao {
        private int from;
        private char read;
        private int to;
        private char white;
        private Direcao dir;

        public int getEstadoAtual() {
            return from;
        }

        public char getSimboloLido() {
            return read;
        }

        public int getProximoEstado() {
            return to;
        }

        public char getNovoSimbolo() {
            return white;
        }

        public Direcao getDirecao() {
            return dir;
        }
    }

    public enum Direcao {
        L, R
    }
}

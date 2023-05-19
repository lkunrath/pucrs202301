package atividade_de_aula;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private int numeroVertices;
    private int numeroArestas;
    private ArrayList<Integer>[] listaAdjacencia;

    public Grafo(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        this.numeroArestas = 0;

        listaAdjacencia = new ArrayList[numeroVertices];
        for (int i = 0; i < this.numeroVertices; i++) {
            listaAdjacencia[i] = new ArrayList<>();
        }
    }

    public void adicionarAresta(int v, int a) {
        listaAdjacencia[v].add(a);
        listaAdjacencia[a].add(v);
        numeroArestas++;
    }

    public boolean existeAresta(int v, int a) {
        boolean va = listaAdjacencia[v].indexOf(a) >= 0;
        boolean av = listaAdjacencia[a].indexOf(v) >= 0;
        return va || av;
    }

    public void removerAresta(int v, int a) {
        int va = listaAdjacencia[v].indexOf(a);
        listaAdjacencia[v].remove(va);
        int av = listaAdjacencia[a].indexOf(v);
        listaAdjacencia[a].remove(av);
    }

    public ArrayList<Integer> verticesAdjacentes(int v) {
        return listaAdjacencia[v];
    }

    public void buscarEmProfundidade(int origem) {
        boolean[] visitados = new boolean[this.numeroVertices];
        buscarEmProfundidadeRecursivamente(origem, visitados);
    }

    private void buscarEmProfundidadeRecursivamente(int vertice, boolean[] visitados) {
        visitados[vertice] = true;
        ArrayList<Integer> adjacentes = this.verticesAdjacentes(vertice);
        for (int vizinho : adjacentes) {
            if (!visitados[vizinho]) {
                System.out.println("Vou visitar o " + vizinho);
                buscarEmProfundidadeRecursivamente(vizinho, visitados);
            } else {
                System.out.println("Não vou visitar o " + vizinho + " pois ele já foi visitado");
            }
        }
    }

    public String toDot() {
        String resultado = "graph G { " + System.lineSeparator();
        for (int i = 0; i < numeroVertices; i++) {
            resultado = resultado + "\t" + i + ";" + System.lineSeparator();
        }
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                resultado += "\t" + i + "--" + listaAdjacencia[i].get(j) + ";" + System.lineSeparator();
            }
        }
        resultado += "}";
        return resultado;
    }

    public List<List<Integer>> converterNomesParaListaInteiros(List<String> nomes) {
        List<List<Integer>> conexoes = new ArrayList<>();

        for (int i = 0; i < nomes.size(); i++) {
            List<Integer> listaInteiros = new ArrayList<>();

            for (int j = 0; j < nomes.size(); j++) {
                if (i != j && existeAresta(i, j)) {
                    listaInteiros.add(j);
                }
            }
            conexoes.add(listaInteiros);
        }
        return conexoes;
    }

    public List<String> pessoasComMaisAmigos(List<String> nomes) {
        List<String> pessoasComMaisAmigos = new ArrayList<>();
        int maxAmigos = 0;
    
        for (int i = 0; i < nomes.size(); i++) {
            List<Integer> conexoesPessoa = listaAdjacencia[i];
            int numAmigos = conexoesPessoa.size();
    
            if (numAmigos > maxAmigos) {
                maxAmigos = numAmigos;
                pessoasComMaisAmigos.clear();
                pessoasComMaisAmigos.add(nomes.get(i));
            } else if (numAmigos == maxAmigos) {
                pessoasComMaisAmigos.add(nomes.get(i));
            }
        }
    
        return pessoasComMaisAmigos;
    }

    public List<String> pessoasComMenosAmigos(List<String> nomes) {
        List<String> pessoasComMenosAmigos = new ArrayList<>();
        int minAmigos = Integer.MAX_VALUE;
    
        for (int i = 0; i < nomes.size(); i++) {
            List<Integer> conexoesPessoa = listaAdjacencia[i];
            int numAmigos = conexoesPessoa.size();
    
            if (numAmigos < minAmigos) {
                minAmigos = numAmigos;
                pessoasComMenosAmigos.clear();
                pessoasComMenosAmigos.add(nomes.get(i));
            } else if (numAmigos == minAmigos) {
                pessoasComMenosAmigos.add(nomes.get(i));
            }
        }
    
        return pessoasComMenosAmigos;
    }
    
    public void listarConexoesComDistanciaX(int pessoaX, List<String> nomes) {
        System.out.println("Pessoas conectadas com " + nomes.get(pessoaX) + " com distância 1:");
        List<Integer> conexoesPessoaX = listaAdjacencia[pessoaX];
        for (int pessoaConexao : conexoesPessoaX) {
            System.out.println("- " + nomes.get(pessoaConexao));
        }
    
        System.out.println("Pessoas conectadas com " + nomes.get(pessoaX) + " com distância 2:");
        for (int pessoaConexao : conexoesPessoaX) {
            List<Integer> conexoesPessoaConexao = listaAdjacencia[pessoaConexao];
            for (int pessoaConexaoDist2 : conexoesPessoaConexao) {
                if (pessoaConexaoDist2 != pessoaX && !conexoesPessoaX.contains(pessoaConexaoDist2)) {
                    System.out.println("- " + nomes.get(pessoaConexaoDist2));
                }
            }
        }
    }    

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < this.numeroVertices; i++) {
            ret += i + " { ";
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                ret += listaAdjacencia[i].get(j) + " | ";
            }
            ret = ret + " } " + System.lineSeparator();
        }
        return ret;
    }
}
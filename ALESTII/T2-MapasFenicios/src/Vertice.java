public class Vertice {
    private int indice;
    private int linha;
    private int coluna;
    private char caracter;

    public Vertice(int indice, int linha, int coluna, char caracter) {
        this.indice = indice;
        this.linha = linha;
        this.coluna = coluna;
        this.caracter = caracter;
    }

    public int getIndice() {
        return indice;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setIndice(int indiceVertice) {
    }
}
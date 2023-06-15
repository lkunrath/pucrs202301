public class App {
    public static void main(String[] args) {
        Mapa mapa = new Mapa("Mapas/mapa0.txt");
        Dijkstra dij = new Dijkstra(mapa.grafo, 2);
        dij.imprimirResultado();

    }
}

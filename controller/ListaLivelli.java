package controller;

public enum ListaLivelli {
    LIVELLO1("src/livelli/livello1.txt", "src/livelli/nemico1.txt"),
    LIVELLO2("src/livelli/livello2.txt", "src/livelli/nemico2.txt"),
    LIVELLO3("src/livelli/livello3.txt", "src/livelli/nemico3.txt"),
    LIVELLO4("src/livelli/livello4.txt", "src/livelli/nemico4.txt"),
    LIVELLO5("src/livelli/livello5.txt", "src/livelli/nemico5.txt"),
    LIVELLO6("src/livelli/livello6.txt", "src/livelli/nemico6.txt"),
    LIVELLO7("src/livelli/livello7.txt", "src/livelli/nemico7.txt"),
    LIVELLO8("src/livelli/livello8.txt", "src/livelli/nemico8.txt");

    private final String pathLivello, pathNemici;

    ListaLivelli(String pathLivello, String pathNemici) {
        this.pathLivello = pathLivello;
        this.pathNemici = pathNemici;
    }
    public String getPathLivello() {
        return pathLivello;
    }
    public String getPathNemici() {
        return pathNemici;
    }
}

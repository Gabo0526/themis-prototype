public class TopoApp {

    public static void main(String[] args) {
        String RESET = "\033[0m"; // Restablecer color
        String GREEN = "\033[0;32m"; // Verde
        String CYAN = "\033[0;36m"; // Cyan
        String BLUE = "\033[0;34m"; // Azul
        String RED = "\033[0;31m"; // Rojo
        String YELLOW = "\033[0;33m";// Amarillo
        String PURPLE = "\033[0;316m";// Púrpura

        long t0 = System.currentTimeMillis();

        Graph theGraph = new Graph();

        // Primer Semestre
        theGraph.addVertex(CYAN + "Algebra Lineal", 3); // 0
        theGraph.addVertex(CYAN + "Calculo en una variable", 3); // 1
        theGraph.addVertex(CYAN + "Mecanica Newtoniana", 4); // 2
        theGraph.addVertex(CYAN + "Programacion I", 4); // 3
        theGraph.addVertex(CYAN + "Comunicacion Oral y Escrita", 1); // 4

        // Segundo Semestre

        theGraph.addVertex(BLUE + "Ecuaciones Diferenciales Ordinarias", 3); // 5
        theGraph.addVertex(BLUE + "Mat Computacionales y Teo Computacion", 4); // 6
        theGraph.addVertex(BLUE + "Fundamentos de Electronica", 3); // 7
        theGraph.addVertex(BLUE + "Programacion II", 4); // 8
        theGraph.addVertex(BLUE + "Analisis Socioeconomico y Politico del Ecuador", 1); // 9

        // Tercer Semestre

        theGraph.addVertex(RED + "Probabilidad y Estadistica", 3); // 10
        theGraph.addVertex(RED + "Sistemas Operativos", 3); // 11
        theGraph.addVertex(RED + "Arquitectura de Computadores", 2); // 12
        theGraph.addVertex(RED + "EDA I", 3); // 13
        theGraph.addVertex(RED + "Fundamentos de Redes y Conectividad", 3); // 14
        theGraph.addVertex(RED + "Asignatura de Artes y Humanidades", 1); // 15

        // Cuarto Semestre

        theGraph.addVertex(
                YELLOW + "Ingenieria de Software y Requerimientos\nFundamentos de Bases de Datos\nFundamentos de Sistemas de Informacion",
                10); // 16
        theGraph.addVertex(YELLOW + "Compiladores y Lenguajes", 2); // 17
        // theGraph.addVertex("Fundamentos de Sistemas de Informacion"); // 18
        theGraph.addVertex(YELLOW + "EDA II", 2); // 18
        // theGraph.addVertex("Fundamentos de Bases de Datos"); // 20
        theGraph.addVertex(YELLOW + "Asignatura de Economia y Sociedad", 1); // 19

        // Quinto Semestre

        theGraph.addVertex(PURPLE + "Gestion Organizacional", 1); // 20
        theGraph.addVertex(PURPLE + "Diseño de Software", 3); // 21
        theGraph.addVertex(PURPLE + "Computacion Grafica", 3); // 22
        theGraph.addVertex(PURPLE + "Inteligencia Aritificial y Aprendizaje Automatico", 3); // 23
        theGraph.addVertex(PURPLE + "Bases de Datos Distribuidas", 3); // 24
        theGraph.addVertex(PURPLE + "Practicas de Servicio Comunitario", 2); // 25

        theGraph.addEdge(0, 5);
        theGraph.addEdge(1, 5);
        theGraph.addEdge(3, 6);
        theGraph.addEdge(3, 8);
        theGraph.addEdge(1, 10);
        theGraph.addEdge(3, 11);
        theGraph.addEdge(7, 12);
        theGraph.addEdge(8, 13);
        theGraph.addEdge(6, 17);
        theGraph.addEdge(13, 16);
        theGraph.addEdge(16, 21);
        theGraph.addEdge(18, 22);
        theGraph.addEdge(18, 23);
        theGraph.addEdge(16, 24);
        theGraph.addEdge(13, 18);

        System.out.println(RESET + "\nPrimer Semestre: " + CYAN + "CYAN" + RESET + "\nSegundo semestre: " + BLUE
                + "BLUE"
                + RESET + "\nTercer semestre: " + RED + "RED" + RESET + "\nCuarto semestre: " + YELLOW + "YELLOW"
                + RESET
                + "\nQuinto semestre: " + PURPLE + "PURPLE\n\n" + RESET);

        theGraph.showAdjMatrix();
        theGraph.showDegrees();

        theGraph.pureKahnAlgorithm();

        long tf = System.currentTimeMillis();

        System.out.printf(GREEN + "El coste computacional es de %f milisegundos\n\n", (tf - t0) / 1e6);
    } // end main()
} // end class TopoApp
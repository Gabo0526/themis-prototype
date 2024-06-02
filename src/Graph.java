import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class Graph {

    private final int MAX_VERTS = 51;
    private Vertex vertexList[]; // list of vertices
    private int[] inDegrees;
    private int[] outDegrees;
    private int adjMat[][]; // adjacency matrix
    private int nVerts; // current number of vertices
    private Queue<Integer> semesterIndexes;
    private Queue<Integer> topologicalSort;

    // -------------------------------------------------------------
    public Graph() // constructor
    {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        inDegrees = new int[MAX_VERTS];
        outDegrees = new int[MAX_VERTS];
        semesterIndexes = new LinkedList<>();
        topologicalSort = new LinkedList<>();
    } // end constructor
      // -------------------------------------------------------------

    public void addVertex(String subject, int credits) {
        vertexList[nVerts++] = new Vertex(subject, credits);
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    // -------------------------------------------------------------
    public void displayVertex(int v) {
        System.out.print(vertexList[v].subject);
    }

    // -------------------------------------------------------------
    public void pureKahnAlgorithm(Scanner scanner) {
        getInDegrees();
        getOutDegrees();

        Queue<Integer> queue = new LinkedList<>();

        for (int i = semesterIndexes.poll(); i < semesterIndexes.peek(); i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {

            boolean isApproved;

            int currentVertex;

            if (!topologicalSort.isEmpty()) {
                System.out.println("Aprobaste " + vertexList[topologicalSort.peek()].subject + "?\ns/n");
                currentVertex = topologicalSort.poll();
            } else {
                System.out.println("Aprobaste " + vertexList[queue.peek()].subject + "?\ns/n");
                currentVertex = queue.poll();
            }

            isApproved = (Objects.equals(scanner.nextLine(), "s")) ? true : false;

            if (!isApproved) {

                queue.offer(currentVertex);
                System.out.println(vertexList[currentVertex].subject);

            } else {
                System.out.println(vertexList[currentVertex].subject);

                for (int i = 0; i < nVerts; i++) {
                    if (adjMat[currentVertex][i] != 0) {
                        inDegrees[i] -= 1;

                        if (inDegrees[i] == 0) {
                            queue.offer(i);
                        }
                    }
                }
            }
        }
    }

    public void getInDegrees() {

        for (int j = 0; j < nVerts; j++) {
            int inDegree = 0;

            for (int i = 0; i < nVerts; i++) {
                if (adjMat[i][j] != 0)
                    inDegree++;
            }

            inDegrees[j] = inDegree;
        }
    }

    public void getOutDegrees() {

        for (int i = 0; i < nVerts; i++) {
            int outDegree = 0;

            for (int j = 0; j < nVerts; j++) {
                if (adjMat[i][j] != 0)
                    outDegree++;
            }

            outDegrees[i] = outDegree;
        }
    }

    public void showAdjMatrix() {
        for (int i = 0; i < nVerts; i++) {

            for (int j = 0; j < nVerts; j++) {
                System.out.print(adjMat[i][j] + " ");
            }

            System.out.println();
        }
    }

    public void showDegrees() {
        System.out.println("Los grados de entrada son: " + Arrays.toString(inDegrees));

        System.out.println("Los grados de salida son; " + Arrays.toString(outDegrees));
    }

    public void addSemesterIndex(int index) {
        semesterIndexes.offer(index);
    }

    public void showFirstSubjects() {
        System.out.println("Las materias del primer nivel son:");

        for (int i = 0; i < semesterIndexes.peek(); i++) {
            topologicalSort.offer(i);
            System.out.println(vertexList[i].subject);
        }
    }
} // end class Graph

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class Graph {

    private final int MAX_VERTS = 51;
    private int nVerts; // current number of vertices
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][]; // adjacency matrix
    private int[] inDegrees;
    private int[] outDegrees;
    private Queue<Integer> semesterIndexes;

    // -------------------------------------------------------------
    public Graph() // constructor
    {
        nVerts = 0;
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        inDegrees = new int[MAX_VERTS];
        outDegrees = new int[MAX_VERTS];
        semesterIndexes = new LinkedList<>();
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
    public void modifiedKahnAlgorithm(Scanner scanner) {
        getInDegrees();
        getOutDegrees();

        Deque<Integer> queue = new LinkedList<Integer>();

        if (semesterIndexes.peek() == 0) {
            semesterIndexes.poll();
            semesterIndexes.poll();

            for (int i = 0; i < semesterIndexes.peek(); i++) {
                if (inDegrees[i] == 0) {
                    queue.offer(i);
                }
            }
        } else {

            for (int i = semesterIndexes.poll(); i < semesterIndexes.peek(); i++) {
                if (inDegrees[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        while (!queue.isEmpty()) {

            System.out.println("Aprobaste " + vertexList[queue.peek()].subject + "?\ns/n");
            int currentVertex = queue.poll();

            vertexList[currentVertex].coursed = (Objects.equals(scanner.nextLine(), "s")) ? true : false;

            if (!vertexList[currentVertex].coursed) {

                queue.addFirst(currentVertex);
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
} // end class Graph

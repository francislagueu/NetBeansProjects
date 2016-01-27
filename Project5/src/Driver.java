
public class Driver {

    public static void main(String[] args){
        Graph g = new Graph(8);
        for(int i = 0; i < g.numVertices; i++)
            g.addVertex(i);

        g.addEdge(0, 4, 5);
        g.addEdge(1, 2, 4);
        g.addEdge(2, 4, 6);
        g.addEdge(3, 5, 7);
        g.addEdge(4, 7, 8);
        g.addEdge(5, 3, 9);
        g.addEdge(6, 0, 2);
        g.addEdge(7, 1, 8);
        g.addEdge(2, 5, 7);

        g.print();
        g.printPath();
    }


}

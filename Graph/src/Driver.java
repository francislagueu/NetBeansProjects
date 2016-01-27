
public class Driver {

    public static void main(String[] args){

        Graph g = new Graph(8);
        for(int i = 0; i<g.max; i++){
            g.addVertex(i);
        }

        g.addEdge(0, 4, 6);
        g.addEdge(1, 2, 5);
        g.addEdge(2, 6, 8);
        g.addEdge(3, 7, 9);
        g.addEdge(4, 5, 3);
        g.addEdge(5, 3, 7);
        g.addEdge(6, 1, 4);
        g.addEdge(7, 0, 8);
        g.addEdge(0, 3, 2);
        g.addEdge(1, 5, 4);
        g.addEdge(2, 7, 9);
        g.addEdge(3, 6, 1);
        g.addEdge(4, 2, 8);
        g.addEdge(5, 0, 6);
        g.addEdge(6, 1, 5);
        g.addEdge(7, 4, 3);

        System.out.println("Adjacency Matrix of the Graph:");
        System.out.println();
        g.print();
        System.out.println();
        System.out.println("Shortest Path from Source to Destination:");
        System.out.println();
        g.printPath(4);

    }
}

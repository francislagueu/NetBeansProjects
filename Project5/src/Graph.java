
public class Graph {

    private int[][] adjMatrix;//Adjacency matrix
    private int INF = 2000;
    protected int numVertices;//number of vertices in the graph
    private Dijkstra[] path;//array to hold the path
    private Vertex[] Vertices;//array to hold vertices in the graph
    private int current;//vertex currently on
    private int next;//next vertex from current vertex
    private int count;//number of vertex in the array
    private int MAX;//maximum number of vertex

    Graph(int max){
        this.MAX = max;
        Vertices = new Vertex[MAX];
        adjMatrix = new int[MAX][MAX];
        numVertices = 0;
        count = 0;
        path = new Dijkstra[MAX];
        for(int i=0; i<MAX; i++)
            for(int j=0; j<MAX; j++)
                adjMatrix[i][j] = INF;//set the path between vertices to infinity

    }

    public void addVertex(int item){
        Vertices[numVertices++] = new Vertex(item);
    }

    public void addEdge(int start, int end, int weight){
        adjMatrix[start][end]=weight;
        adjMatrix[end][start]=weight;
    }

    public void Dijkstra(int s){
        Vertices[s].visited = true;
        count = 1;
        for(int i = 0; i<numVertices; i++){
            int temp = adjMatrix[s][i];
            path[i]=new Dijkstra(s, temp);
        }
        while(count<numVertices){
            int index = findMin();
            int dist = path[index].distance;
            if(dist == INF)
                break;
            else{
                current = index;
                next = path[index].distance;
            }
            Vertices[current].visited = true;
            count++;
            Path();
        }
        printPath();
    }

    public int findMin(){
        int dist = INF;
        int index = 0;
        for(int i = 0; i<numVertices; i++){
           if(!Vertices[i].visited && path[i].distance<dist){
               dist = path[i].distance;
               index = i;
           }
        }
        return index;
    }
    public void Path(){
        int j = 0;
        while(j<numVertices){
            if(Vertices[j].visited){
                j++;
                continue;
            }
            int k = adjMatrix[current][j];
            int m = next + k;
            int d = path[j].distance;

            if(m<d){
                path[j].vertex = current;
                path[j].distance = m;
            }
            j++;
        }
    }
    public void printPath(){
        for(int i = 0; i<numVertices; i++){
            System.out.print(Vertices[i].data + "-->");
            if(path[i].distance == INF)
                System.out.print("INFINITY");
            else
                System.out.print(path[i].distance);
            int ver = Vertices[path[i].vertex].data;
            System.out.print("( "+ ver + ") ");
        }
        System.out.print("");
    }

    public void print(){
        for ( int i = 0; i< numVertices; i++) {

            System.out.print("  "+i );
        }
        System.out.println();
        for(int i = 0; i< numVertices; i++){
            System.out.print(i + " ");
            for(int j = 0; j< numVertices; j++)
                System.out.print(adjMatrix[i][j]+"  ");
            System.out.println();
        }
    }


    //Helper classes
    private class Dijkstra{
        public int distance;//weight from this vertex
        public int vertex;//this is a parent vertex
        Dijkstra(int v, int d){
            this.distance = d;
            this.vertex = v;
        }
    }

    private class Vertex{
        public int data;
        public boolean visited;

        Vertex(int item){
            this.data = item;
            this.visited = false;
        }
    }
}

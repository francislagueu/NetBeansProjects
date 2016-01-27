
public class Graph {
    Vertex[] vertices;
    int max;
    int size;
    int[][] adjMatrix;
//Graph constructor
    Graph(int max){
        this.max = max;
        vertices = new Vertex[max];
        adjMatrix = new int[max][max];
        size=0;
    }
//function to add vertex in the array of vertices
    public void addVertex(int data){
        vertices[size++] = new Vertex(data);
    }
//function to add edges in the graph, undirect
    public void addEdge(int s, int d, int w){
        adjMatrix[s][d] = w;
        adjMatrix[d][s] = w;
        //set this vertex adjacency vertices
        vertices[s].adjacent = new Next(d, w, vertices[s].adjacent);
    }

//vertex helper class
    public class Vertex{
        int weight;
        int item;
        Next adjacent;
        State state;
//Vertex constructor
        Vertex(int item){
            this.item = item;
            weight = Integer.MAX_VALUE;//set the weight of the vertices to infinity
            state = State.NEW;//set the state of the vertices as NEW
        }
//function to compare two vertices
        public int compareTo(Vertex v){
            if(this.weight == v.weight)
                return 0;
            if(this.weight < v.weight)
                return -1;
            return 1;
        }
    }
//to check the state of the vertex
    public enum State{
        NEW, IN, VISITED
    }
//helper class to find the next neighbor to the vertex
    public class Next{
        int index;
        Next next;
        int weight;
//Next neighbor constructor
        Next(int index, int weight, Next next){
            this.index = index;
            this.weight = weight;
            this.next = next;
        }

    }
//helper Heap class
    public class Heap{
        protected Vertex[] heap;
        protected int MAX;
        protected int size;
//Heap constructor
        Heap(int max){
            this.MAX = max;
            heap = new Vertex[max];
            size = 0;
        }
//function to push the vertex into the heap
        public void push(Vertex v){
            heap[size++] = v;
            heapifyUp(size-1);
        }
//Heapify function to rearrange the vertex in the heap
        public void heapifyUp(Vertex v){
            for(int i = 0; i<MAX; i++){
                if(v==heap[i]){
                    heapifyUp(i);//call the heapify of the position of the vertex
                    break;
                }
            }
        }
//find the right positon for the vertex to insert in the heap array
        public void heapifyUp(int pos){
            int current = pos;
            Vertex temp = heap[current];
            int index = (current -1)/2;
            Vertex parent = heap[index];
            while(temp.compareTo(parent)==-1){
                swap(current, index);
                current = index;
                if(current==0)
                    break;
                temp = heap[current];
                index = (current - 1)/2;
                parent = heap[index];
            }
        }
//swap position in the heap array
        public void swap(int a, int b){
            Vertex temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }
//check if the heap is empty 
        public boolean isEmpty(){
            return size == 0;
        }
//find the right  position for vertex after deletion
        public void heapifyDown(int pos){
            if(size==1)
                return;
            int current = pos;
            Vertex item = heap[current];
            int left = current*2 + 1;
            int right = current*2 + 2;
            int child;
            if(heap[left]==null)
                return;
            if(heap[right]==null)
                child = left;
            else if(heap[right].compareTo(heap[left])==-1)
                child = right;
            else
                child = left;
            Vertex data = heap[child];
            while (item.compareTo(data)==1){
                swap(current, child);
                current = child;
                item = heap[current];
                left = current*2+1;
                right = current*2+2;

                if(heap[left]==null)
                    return;
                if(heap[right]==null)
                    child = left;
                else if(heap[right].compareTo(heap[left])==-1)
                    child = right;
                else
                    child = left;
            }
        }

        public Vertex delete(){
            Vertex v = heap[0];//set the vertex equal to the first item in the
            //heap and swap its position with the second to the last position
            swap(0, size-1);
            heap[size-1] = null;//set the second to the last position to null
            size--;//decrement the size of the array
            heapifyDown(0);//heapify down the heap
            return v;//return the deleted vertex
        }
    }

    public void dijkstra(Vertex s){
        Heap heap = new Heap(max);//instantiate a new heap
        heap.push(s);//push in the start vertex
        s.state = State.IN;//set the state to IN
        s.weight =0; //set the weight to 0
        while(!heap.isEmpty()){//if the heap is not empty
            Vertex v = heap.delete();//delete the minimum item in the heap
            v.state = State.VISITED;//set its stat as visited
            Next n = v.adjacent;//find the next vertex adjacent to this vertex
            while(n!=null){
                if(vertices[n.index].state==State.NEW){//if the state of the vertex new
                    heap.push(vertices[n.index]);       //push into the heap
                    vertices[n.index].state = State.IN;//set the state as IN
                }
                if(vertices[n.index].weight > v.weight+n.weight){//if the weight of the
                    vertices[n.index].weight = v.weight + n.weight;//vertex is less than
                    //the weight of previous vertex plus the next vertex, update the weight
                    //of the next vertex
                    heap.heapifyUp(vertices[n.index]);//heapify the vertex up in the heap
                }

                n = n.next;//update the current neighbor vertex to its next neighbor
            }
        }
    }
//Print the shortest path between the source and every other vertex in the graph
    public void printPath(int s){
        dijkstra(vertices[s]);//call the dijkstra function of the graph
        for(int i = 0; i< max; i++){
            System.out.println("Distance from "+s+" to "+vertices[i].item +" : "
                    + vertices[i].weight);
        }
    }
//function to print the adjacency matrix of the graph
    public void print(){
        for(int i =0; i<vertices.length ;++i)
            System.out.print("  "+i);
        System.out.println();
        for(int i = 0; i<adjMatrix.length; ++i){
            System.out.print(i+" ");
            for(int j = 0; j <adjMatrix.length ; ++j)
                System.out.print(adjMatrix[i][j]+ "  ");
            System.out.println();
        }
    }
}

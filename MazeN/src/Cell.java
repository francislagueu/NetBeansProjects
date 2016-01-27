
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;


public class Cell
{
        public static int cellSize = 20;
        public static int padding = 5;
        public int[] walls  = {1, 1, 1, 1};
        public int row;
        public int col;
        public int weight;
        public boolean visited;
        public boolean isStart = false;
        public boolean isEnd = false;
        private Maze parent;
        
        private boolean debugMode = false;

        public Cell(Maze parent)
        {         
            this.parent = parent;
            this.visited = false;
            this.debugMode = parent.debugMode;
        }

        
        public boolean hasAllWalls()
        {
                for (int i = 0; i < 4; i++)
                {
                         if (walls[i] == 0)
                                 return false;
                }
                return true;
        }
        public void knockDownWall(int wall)
        {
                walls[wall] = 0;
        }
        public void knockDownWall(Cell cell)
        {
                // find adjacent wall
                int wallToRemove = findAdjacentWall(cell);
                walls[wallToRemove] = 0;

                // get opposite wall
                int opWallToRemove = (wallToRemove + 2) % 4;
                cell.walls[opWallToRemove] = 0;
        }
        public  int findAdjacentWall(Cell cell)
        {
                // If rows are the same
                if (cell.row == row) 
                {
                        if (cell.col < col)
                                return 0;
                        else
                                return 2;
                }
                // otherwise columns should be the same
                else 
                {
                        if (cell.row < row)
                                return 1;
                        else
                                return 3;
                }
        }
        public Vector findPath() {
            
            Vector vct = new Vector();
            Cell cell;
            
            
            //Store current cell coordinates
            int y = row;
            int x = col;
            
            if  ( this.walls[0] == 0 ) {

                cell = new Cell(parent);
                
                if ( debugMode )
                    System.out.println("T Row: " + y + " Col: " + x);
                
                if ( x - 1 >= 0 ) {
                    cell.col = x-1;
                    cell.row = y;
                    cell.weight = parent.getCell(cell.row,cell.col).weight;
                    cell.walls = parent.getCell(cell.row,cell.col).walls;
                    cell.visited = parent.getCell(cell.row,cell.col).visited;
                    vct.add(cell);
                    
                    if ( debugMode )
                        System.out.println("Top");
                }
            } 
            
            //left
            if  ( this.walls[1] == 0 ) {
                cell = new Cell(parent);   
                
                if ( debugMode )
                    System.out.println("L Row: " + y + " Col: " + x);
                
                if ( y - 1 >= 0 ) {
                    cell.row = y-1;
                    cell.col = x;
                    cell.weight = parent.getCell(cell.row,cell.col).weight;
                    cell.walls = parent.getCell(cell.row,cell.col).walls;
                    cell.visited = parent.getCell(cell.row,cell.col).visited;
                    vct.add(cell);
                    
                    if ( debugMode )
                        System.out.println("Left");
                }
            } 
            //bottom
            if  ( this.walls[2] == 0 ) {
                cell = new Cell(parent);
                
                if ( debugMode )
                    System.out.println("B Row: " + y + " Col: " + x);
                
                if ( x + 1 < parent.sizeX ) {
                    cell.row = y;
                    cell.col = x+1;
                    cell.weight = parent.getCell(cell.row,cell.col).weight;
                    cell.walls = parent.getCell(cell.row,cell.col).walls;
                    cell.visited = parent.getCell(cell.row,cell.col).visited;
                    vct.add(cell);
                    
                    if ( debugMode )
                        System.out.println("Bottom");
                }
            } 
            //right
            if  ( this.walls[3] == 0 ) {
                
                if ( debugMode )
                    System.out.println("R Row: " + y + " Col: " + x);
                
                cell = new Cell(parent);
                
                if ( y + 1 < parent.sizeY ) {
                    cell.row = y+1;
                    cell.col = x;
                    cell.weight = parent.getCell(cell.row,cell.col).weight;
                    cell.walls = parent.getCell(cell.row,cell.col).walls;
                    cell.visited = parent.getCell(cell.row,cell.col).visited;
                    vct.add(cell);
                    
                    if ( debugMode )
                        System.out.println("Right");
                }
            } 
            
            
            return vct;
        }
        
        public int getRandomWall()
        {
                int randomWall = (int)Math.round( Math.random() * 3 ) ;
                while ( (walls[randomWall] == 0)  
                ||		((row == 0) && (randomWall == 0)) ||
                                ((row == parent.sizeY - 1) && (randomWall == 2)) ||
                                ((col == 0) && (randomWall == 1)) ||
                                ((col == parent.sizeX - 1) && (randomWall == 3)) 
                           )
                {
                        randomWall =   (int)Math.round( Math.random() * 3 );
                }

                return randomWall;
        }
        public void paint(Graphics g)
        {
             
                g.setColor(Color.black);
        
                //top
                if (walls[0] == 1)
                {
                        g.drawLine(row*cellSize + padding, col*cellSize + padding, (row+1) * cellSize   + padding, col*cellSize +  + padding);
                }
                //left
                if (walls[1] == 1)
                {
                        g.drawLine(row*cellSize  + padding, col*cellSize + padding, row*cellSize + padding, (col+1)*cellSize + padding);
                }
                //bottom
                if (walls[2] == 1)
                {
                        g.drawLine(row*cellSize + padding, (col+1)*cellSize + padding, (row+1)*cellSize + padding, (col+1)*cellSize + padding);
                }
                //right
                if (walls[3] == 1)
                {
                        g.drawLine((row+1)*cellSize + padding , col*cellSize + padding, (row+1)*cellSize + padding, (col+1)*cellSize + padding);
                }

                //Draw starting point
                if ( isStart ) {
                    g.setColor( Color.blue );
                    g.fillRect(row*cellSize + padding, col*cellSize + padding, cellSize, cellSize);
                    g.setColor(Color.white);
                    g.drawString("S", row*cellSize+2*padding, col*cellSize+ 4*padding);
                //Draw endpoint
                } else if ( isEnd ) {
                    g.setColor( Color.red );
                    g.fillRect(row*cellSize + padding, col*cellSize + padding, cellSize, cellSize);
                    g.setColor(Color.black);
                    g.drawString("E", row*cellSize+2*padding, col*cellSize+ 4*padding);
                }


        }
        public String toString() {
            
            return "X:  " + col + " Y: " + row;
         }
}
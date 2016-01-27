
package mazegui;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Cell {
    
    
    public abstract class DepthCell{
    private static final int INIT_MARKS = 4;
    private final Mark END = Mark.get("end");
    private Set<Mark> marks = new HashSet<Mark>(INIT_MARKS);

    public abstract Shape getShape();
    public abstract Shape getWalls();
    public abstract Collection<DepthCell> neighbors();

    public void mark( Mark marker){
        marks.add(marker);
    }

    public  boolean hasMark(Mark marker){
        return marks.contains(marker);
    }

    }
    
    public class Mark {
    private final Map<String, Mark> TABLE = new HashMap<String, Mark>();
    private final String name;

    private Mark(String markName){
        name = markName;
    }

    public  synchronized Mark get(String name){
        Mark mark = TABLE.get(name);
        if(mark == null){
            mark = new Mark(name);
            TABLE.put(name, mark);
        }
        return mark;
    }
}
    
    private  Shape shape;
    private  int x;
    private  int y;
    private  int size;
    private  Collection<DepthCell> neighbors = new ArrayList<DepthCell>(4);

    private boolean left = true;
    private boolean top = true;

    public Cell( int posX,  int posY,  int scale){
        x = posX;
        y = posY;
        size = scale;
        shape = new Rectangle(x*scale, y*scale, size, size);
    }

    public  Shape getShape(){
        return shape;
    }

    public final Shape getWalls(){
        Path2D.Double walls = new Path2D.Double();
        if(left){
            Shape leftWall = new Line2D.Double(x*size, y*size,
                                                x*size, y*size);
            walls.append(leftWall, false);
        }
        if(top){
            Shape topWall = new Line2D.Double(x*size, y*size,
                                                x*size, y*size);
            walls.append(topWall, false);
        }

        return walls;
    }

    public final Collection<Cell.DepthCell> neighbors(){
        return new ArrayList<Cell.DepthCell>(neighbors);
    }

    final void addNeighbor( Cell cell){
        if(cell.x < y){
            left = false;
        }
        else if(cell.y < y){
            top = false;
        }
        neighbors.add(cell);
    }
    
}

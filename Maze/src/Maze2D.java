import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 * @author Victor
 */
public class Maze2D {
    public static void main(String[] args) throws IOException {
        Maze maze = new Maze(16, 17);
        BufferedImage im = maze.draw();
        ImageIO.write(im, "png", new File("maze5.png"));
    }

    public static final class Coordinate2D {
        private final Maze maze;
        private final int x, y;

        public Coordinate2D(Maze maze, int x, int y) {
            Objects.requireNonNull(maze);
            if (x < 0 || x >= maze.xSize || y < 0 || y >= maze.ySize) throw new IndexOutOfBoundsException();
            this.maze = maze;
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(maze, x, y);
        }

        @Override
        public boolean equals(Object another) {
            if (!(another instanceof Coordinate2D)) return false;
            Coordinate2D c4d = (Coordinate2D) another;
            return maze == c4d.maze && x == c4d.x && y == c4d.y;
        }

        public int squareDistance(Coordinate2D another) {
            Objects.requireNonNull(another);
            if (maze != another.maze) throw new IllegalArgumentException();
            int dx = Math.abs(x - another.x);
            int dy = Math.abs(y - another.y);
            return dx + dy;
        }

        public Coordinate2D minusX() { return x == 0              ? null : new Coordinate2D(maze, x - 1, y); };
        public Coordinate2D plusX()  { return x == maze.xSize - 1 ? null : new Coordinate2D(maze, x + 1, y); };
        public Coordinate2D minusY() { return y == 0              ? null : new Coordinate2D(maze, x, y - 1); };
        public Coordinate2D plusY()  { return y == maze.ySize - 1 ? null : new Coordinate2D(maze, x, y + 1); };
        public Maze getMaze() { return maze; }
    }

    public static final class Maze {
        private final int xSize, ySize;
        private final Map<Coordinate2D, Node> nodes;
        private final Node start;

        public Maze(int x, int y) {
            this.xSize = x;
            this.ySize = y;
            nodes = new HashMap<>(x * y);
            fill();
            this.start = chooseRandomNode();
            growMaze();
        }

        private void fill() {
            for (int b = 0; b < xSize; b++) {
                for (int c = 0; c < ySize; c++) {
                    Coordinate2D coord = new Coordinate2D(this, b, c);
                    nodes.put(coord, new Node(coord));
                }
            }
        }

        public Node nodeAt(Coordinate2D coord) {
            if (coord == null) return null;
            return nodes.get(coord);
        }

        private Node chooseRandomNode() {
            int n = (int) (Math.random() * xSize * ySize);
            return new ArrayList<>(nodes.values()).get(n);
        }

        private void growMaze() {
            List<Node> frontier = new ArrayList<>(xSize * ySize);
            frontier.add(start);
            start.linked = true;
            while (!frontier.isEmpty()) {
                Collections.shuffle(frontier);
                Node n = frontier.get(0);
                Node next = n.linkRandomUnlinkedNeighbour();
                if (next != null) {
                    frontier.add(next);
                } else {
                    frontier.remove(0);
                }
            }
        }

        public BufferedImage draw() {
            int cellWidth = 16;
            int cellHeight = 16;
            int boardWidth = cellWidth * (xSize + 1);
            int boardHeight = cellHeight * (ySize + 1);
            BufferedImage im = new BufferedImage(boardWidth + cellWidth - 1, boardHeight + cellHeight - 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = im.createGraphics();
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    Coordinate2D c = new Coordinate2D(this, x, y);
                    Node n = nodeAt(c);
                    int x1 = cellWidth * x + cellWidth - 1;
                    int y1 = cellHeight * y + cellHeight - 1;
                    int x2 = x1 + cellWidth;
                    int y2 = y1 + cellHeight;
                    g.setColor(Color.BLACK);
                    if (!n.isLinkedTo(n.minusY())) g.drawLine(x1, y1, x2, y1);
                    if (!n.isLinkedTo(n.plusY()))  g.drawLine(x1, y2, x2, y2);
                    if (!n.isLinkedTo(n.minusX())) g.drawLine(x1, y1, x1, y2);
                    if (!n.isLinkedTo(n.plusX()))  g.drawLine(x2, y1, x2, y2);
                }
            }
            return im;
        }
    }

    public static final class Node {
        private final Coordinate2D coord;
        private final List<Node> linkedNeighbours;
        private List<Node> neighbours;
        private boolean linked;

        public Node(Coordinate2D coord) {
            Objects.requireNonNull(coord);
            this.coord = coord;
            linkedNeighbours = new ArrayList<>(8);
        }

        public Node linkRandomUnlinkedNeighbour() {
            List<Node> list = new ArrayList<>(getNeighbours());
            list.removeIf(n -> n.linked);
            if (list.isEmpty()) return null;
            Collections.shuffle(list);
            Node next = list.get(0);
            next.getNeighbours();
            linkedNeighbours.add(next);
            next.linkedNeighbours.add(this);
            next.linked = true;
            return next;
        }

        @SuppressWarnings("ReturnOfCollectionOrArrayField")
        public List<Node> getNeighbours() {
            if (neighbours == null) {
                List<Node> nodes = new ArrayList<>(Arrays.asList(minusX(), plusX(), minusY(), plusY()));
                nodes.removeIf(x -> x == null);
                neighbours = Collections.unmodifiableList(nodes);
            }
            return neighbours;
        }

        public boolean isDeadEnd() {
            return linkedNeighbours.size() == 1;
        }

        public boolean isBranch() {
            return linkedNeighbours.size() > 2;
        }

        public boolean isLinkedTo(Node node) {
            return linkedNeighbours.contains(node);
        }

        public Maze getMaze() { return coord.getMaze(); }
        public Coordinate2D getCoord() { return coord; }
        public Node minusX() { return getMaze().nodeAt(coord.minusX()); };
        public Node plusX()  { return getMaze().nodeAt(coord.plusX());  };
        public Node minusY() { return getMaze().nodeAt(coord.minusY()); };
        public Node plusY()  { return getMaze().nodeAt(coord.plusY());  };
    }
}
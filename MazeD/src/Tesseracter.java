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
public class Tesseracter {
    public static void main(String[] args) throws IOException {
        TesseractMaze maze = new TesseractMaze(10, 10, 10, 10);
        BufferedImage im = maze.draw();
        ImageIO.write(im, "png", new File("maze.png"));
    }

    public static final class Coordinate4D {
        private final TesseractMaze maze;
        private final int w, x, y, z;

        public Coordinate4D(TesseractMaze maze, int w, int x, int y, int z) {
            Objects.requireNonNull(maze);
            if (w < 0 || w >= maze.wSize || x < 0 || x >= maze.xSize || y < 0 || y >= maze.ySize || z < 0 || z >= maze.zSize) throw new IndexOutOfBoundsException();
            this.maze = maze;
            this.w = w;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(maze, w, x, y, z);
        }

        @Override
        public boolean equals(Object another) {
            if (!(another instanceof Coordinate4D)) return false;
            Coordinate4D c4d = (Coordinate4D) another;
            return maze == c4d.maze && w == c4d.w && x == c4d.x && y == c4d.y && z == c4d.z;
        }

        public int squareDistance(Coordinate4D another) {
            Objects.requireNonNull(another);
            if (maze != another.maze) throw new IllegalArgumentException();
            int dw = Math.abs(w - another.w);
            int dx = Math.abs(x - another.x);
            int dy = Math.abs(y - another.y);
            int dz = Math.abs(z - another.z);
            return dw + dx + dy + dz;
        }

        public Coordinate4D minusW() { return w == 0              ? null : new Coordinate4D(maze, w - 1, x, y, z); };
        public Coordinate4D plusW()  { return w == maze.wSize - 1 ? null : new Coordinate4D(maze, w + 1, x, y, z); };
        public Coordinate4D minusX() { return x == 0              ? null : new Coordinate4D(maze, w, x - 1, y, z); };
        public Coordinate4D plusX()  { return x == maze.xSize - 1 ? null : new Coordinate4D(maze, w, x + 1, y, z); };
        public Coordinate4D minusY() { return y == 0              ? null : new Coordinate4D(maze, w, x, y - 1, z); };
        public Coordinate4D plusY()  { return y == maze.ySize - 1 ? null : new Coordinate4D(maze, w, x, y + 1, z); };
        public Coordinate4D minusZ() { return z == 0              ? null : new Coordinate4D(maze, w, x, y, z - 1); };
        public Coordinate4D plusZ()  { return z == maze.zSize - 1 ? null : new Coordinate4D(maze, w, x, y, z + 1); };
        public TesseractMaze getMaze() { return maze; }
    }

    public static final class TesseractMaze {
        private final int wSize, xSize, ySize, zSize;
        private final Map<Coordinate4D, Node> nodes;
        private final Node start;

        public TesseractMaze(int w, int x, int y, int z) {
            this.wSize = w;
            this.xSize = x;
            this.ySize = y;
            this.zSize = z;
            nodes = new HashMap<>(w * x * y * z);
            fill();
            this.start = chooseRandomNode();
            growMaze();
        }

        private void fill() {
            for (int a = 0; a < wSize; a++) {
                for (int b = 0; b < xSize; b++) {
                    for (int c = 0; c < ySize; c++) {
                        for (int d = 0; d < zSize; d++) {
                            Coordinate4D coord = new Coordinate4D(this, a, b, c, d);
                            nodes.put(coord, new Node(coord));
                        }
                    }
                }
            }
        }

        public Node nodeAt(Coordinate4D coord) {
            if (coord == null) return null;
            return nodes.get(coord);
        }

        private Node chooseRandomNode() {
            int n = (int) (Math.random() * wSize * xSize * ySize * zSize);
            return new ArrayList<>(nodes.values()).get(n);
        }

        private void growMaze() {
            List<Node> frontier = new ArrayList<>(wSize * xSize * ySize * zSize);
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
            int arrowSize = 3;
            int margin = 2;
            Color red = Color.RED;
            Color blue = Color.BLUE;
            Color yellow = new Color(128, 128, 0);
            Color green = new Color(0, 128, 0);
            BufferedImage im = new BufferedImage(wSize * boardWidth + cellWidth - 1, zSize * boardHeight + cellHeight - 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = im.createGraphics();
            for (int w = 0; w < wSize; w++) {
                for (int z = 0; z < zSize; z++) {
                    for (int x = 0; x < xSize; x++) {
                        for (int y = 0; y < ySize; y++) {
                            Coordinate4D c = new Coordinate4D(this, w, x, y, z);
                            Node n = nodeAt(c);
                            int x1 = cellWidth * x + boardWidth * w + cellWidth - 1;
                            int y1 = cellHeight * y + boardHeight * z + cellHeight - 1;
                            int x2 = x1 + cellWidth;
                            int y2 = y1 + cellHeight;
                            int x3 = (x1 + x2) / 2;
                            int y3 = (y1 + y2) / 2;
                            g.setColor(Color.BLACK);
                            if (!n.isLinkedTo(n.minusY())) g.drawLine(x1, y1, x2, y1);
                            if (!n.isLinkedTo(n.plusY()))  g.drawLine(x1, y2, x2, y2);
                            if (!n.isLinkedTo(n.minusX())) g.drawLine(x1, y1, x1, y2);
                            if (!n.isLinkedTo(n.plusX()))  g.drawLine(x2, y1, x2, y2);
                            if (n.isLinkedTo(n.minusW())) { // Board left, left arrow.
                                g.setColor(red);
                                for (int i = 0; i < arrowSize; i++) {
                                    g.drawLine(x1 + margin + i, y3 - i, x1 + margin + i, y3 + i);
                                }
                            }
                            if (n.isLinkedTo(n.plusW())) { // Board right, right arrow.
                                g.setColor(green);
                                for (int i = 0; i < arrowSize; i++) {
                                    g.drawLine(x2 - margin - i, y3 - i, x2 - margin - i, y3 + i);
                                }
                            }
                            if (n.isLinkedTo(n.minusZ())) { // Board up, up arrow.
                                g.setColor(blue);
                                for (int i = 0; i < arrowSize; i++) {
                                    g.drawLine(x3 - i, y1 + margin + i, x3 + i, y1 + margin + i);
                                }
                            }
                            if (n.isLinkedTo(n.plusZ())) { // Board down, down arrow.
                                g.setColor(yellow);
                                for (int i = 0; i < arrowSize; i++) {
                                    g.drawLine(x3 - i, y2 - margin - i, x3 + i, y2 - margin - i);
                                }
                            }
                        }
                    }
                }
            }
            return im;
        }
    }

    public static final class Node {
        private final Coordinate4D coord;
        private final List<Node> linkedNeighbours;
        private List<Node> neighbours;
        private boolean linked;

        public Node(Coordinate4D coord) {
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
                List<Node> nodes = new ArrayList<>(Arrays.asList(minusW(), plusW(), minusX(), plusX(), minusY(), plusY(), minusZ(), plusZ()));
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

        public TesseractMaze getMaze() { return coord.getMaze(); }
        public Coordinate4D getCoord() { return coord; }
        public Node minusW() { return getMaze().nodeAt(coord.minusW()); };
        public Node plusW()  { return getMaze().nodeAt(coord.plusW());  };
        public Node minusX() { return getMaze().nodeAt(coord.minusX()); };
        public Node plusX()  { return getMaze().nodeAt(coord.plusX());  };
        public Node minusY() { return getMaze().nodeAt(coord.minusY()); };
        public Node plusY()  { return getMaze().nodeAt(coord.plusY());  };
        public Node minusZ() { return getMaze().nodeAt(coord.minusZ()); };
        public Node plusZ()  { return getMaze().nodeAt(coord.plusZ());  };
    }
}
package AI;

import java.util.Comparator;
import java.util.PriorityQueue;
//import game.Map;

public class GenPath {

    int gridLength=20;
    private int xs;
    private int ys;
    int xe, ye;
    private int dir;
    int goStraight = 1;
    int turn1 = 2;
    int turn2 = 3;
    /*
     Direction
     0 North
     1 East
     2 South 
     3 West    
     */
//    private char grid[][];
    private char grid[][];
    private int path[][] = new int[gridLength][gridLength];
    private Node nodeArr[][] = new Node[gridLength][gridLength];
    Comparator<Node> comparator = new NodeComparator();
    PriorityQueue<Node> queue =
            new PriorityQueue<>(gridLength * gridLength, comparator);

    /**
     *
     * @param xs
     * @param ys
     * @param dir
     */
    public GenPath(int xs, int ys, int dir) {
//        gridLength = 10;
        this.grid = new char[][]{
            {'W', 'W', 'W', 'W', 'W', 'W', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'W', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'W', 'E', 'W', 'W', 'E', 'W'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'W', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'W', 'E', 'W', 'W', 'E', 'W', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'}};
//        grid = Map.getInstance().getMap();

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                path[i][j] = 0;
            }
        }
        this.xs = xs;
        this.ys = ys;
        this.dir = dir;
    }

    /**
     *
     */
    public GenPath() {
//        gridLength = 20;
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                path[i][j] = 0;
            }
        }
    }

    public void generatePath() {
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                nodeArr[i][j] = new Node();
                getNodeArr()[i][j].setColour('W');
                getNodeArr()[i][j].setD(-1);
                getNodeArr()[i][j].setP(null);
                getNodeArr()[i][j].setY(i);
                getNodeArr()[i][j].setX(j);
                getNodeArr()[i][j].setDirection(-1);
            }
        }

        getNodeArr()[getYs()][getXs()].setColour('G');
        getNodeArr()[getYs()][getXs()].setD(0);
        //nodeArr[ye][xe].setXp(-1);
        //nodeArr[ye][xe].setYp(-1);
        getNodeArr()[getYs()][getXs()].setDirection(getDir());

        queue.clear();

        queue.add(getNodeArr()[getYs()][getXs()]);

        while (!queue.isEmpty()) {
            Node current = queue.remove();

            int x = current.getX();
            int y = current.getY();
//            System.out.println(y + "," + x + " d=" + getNodeArr()[y][x].getD());

            if (x + 1 >= 0 && x + 1 < gridLength && grid[y][x + 1] == 'E') {
                if (getNodeArr()[y][x + 1].getColour() == 'W') {

                    getNodeArr()[y][x + 1].setColour('G');
                    if (getNodeArr()[y][x].getDirection() == 1) {
                        getNodeArr()[y][x + 1].setD(getNodeArr()[y][x].getD() + goStraight);
                    } else if (getNodeArr()[y][x].getDirection() == 3) {
                        getNodeArr()[y][x + 1].setD(getNodeArr()[y][x].getD() + turn1);
                    } else {
                        getNodeArr()[y][x + 1].setD(getNodeArr()[y][x].getD() + turn1);
                    }
                    getNodeArr()[y][x + 1].setDirection(1);
                    getNodeArr()[y][x + 1].setP(current);

//                    System.out.println("    Added " + y + "," + (x + 1) + " d=" + getNodeArr()[y][x + 1].getD());
                    queue.add(getNodeArr()[y][x + 1]);

                } else if (getNodeArr()[y][x + 1].getColour() == 'G' && queue.contains(getNodeArr()[y][x + 1])) {
                    int temp;
                    if (getNodeArr()[y][x].getDirection() == 1) {
                        temp = getNodeArr()[y][x].getD() + goStraight;
                    } else if (getNodeArr()[y][x].getDirection() == 3) {
                        temp = getNodeArr()[y][x].getD() + turn1;
                    } else {
                        temp = getNodeArr()[y][x].getD() + turn1;
                    }

                    if (temp < getNodeArr()[y][x + 1].getD()) {
//                        System.out.println("G " + y + "," + (x + 1) + " d=" + temp);
                        getNodeArr()[y][x + 1].setD(temp);
                        getNodeArr()[y][x + 1].setDirection(1);
                        getNodeArr()[y][x + 1].setP(current);
                    }
                }
            }

            if (x - 1 >= 0 && x - 1 < gridLength && grid[y][x - 1] == 'E') {
                if (getNodeArr()[y][x - 1].getColour() == 'W') {

                    getNodeArr()[y][x - 1].setColour('G');
                    if (getNodeArr()[y][x].getDirection() == 3) {
                        getNodeArr()[y][x - 1].setD(getNodeArr()[y][x].getD() + goStraight);
                    } else if (getNodeArr()[y][x].getDirection() == 1) {
                        getNodeArr()[y][x - 1].setD(getNodeArr()[y][x].getD() + turn1);
                    } else {
                        getNodeArr()[y][x - 1].setD(getNodeArr()[y][x].getD() + turn1);
                    }
                    getNodeArr()[y][x - 1].setDirection(3);
                    getNodeArr()[y][x - 1].setP(current);

//                    System.out.println("    Added " + y + "," + (x - 1) + " d=" + getNodeArr()[y][x - 1].getD());
                    queue.add(getNodeArr()[y][x - 1]);

                } else if (getNodeArr()[y][x - 1].getColour() == 'G' && queue.contains(getNodeArr()[y][x - 1])) {
                    int temp;
                    if (getNodeArr()[y][x].getDirection() == 1) {
                        temp = getNodeArr()[y][x].getD() + goStraight;
                    } else if (getNodeArr()[y][x].getDirection() == 3) {
                        temp = getNodeArr()[y][x].getD() + turn1;
                    } else {
                        temp = getNodeArr()[y][x].getD() + turn1;
                    }

                    if (temp < getNodeArr()[y][x - 1].getD()) {
//                        System.out.println("G " + y + "," + (x - 1) + " d=" + temp);
                        getNodeArr()[y][x - 1].setD(temp);
                        getNodeArr()[y][x - 1].setDirection(1);
                        getNodeArr()[y][x - 1].setP(current);
                    }
                }
            }

            if (y + 1 >= 0 && y + 1 < gridLength && grid[y + 1][x] == 'E') {
                if (getNodeArr()[y + 1][x].getColour() == 'W') {

                    getNodeArr()[y + 1][x].setColour('G');
                    if (getNodeArr()[y][x].getDirection() == 2) {
                        getNodeArr()[y + 1][x].setD(getNodeArr()[y][x].getD() + goStraight);
                    } else if (getNodeArr()[y][x].getDirection() == 0) {
                        getNodeArr()[y + 1][x].setD(getNodeArr()[y][x].getD() + turn1);
                    } else {
                        getNodeArr()[y + 1][x].setD(getNodeArr()[y][x].getD() + turn1);
                    }
                    getNodeArr()[y + 1][x].setDirection(1);
                    getNodeArr()[y + 1][x].setP(current);

//                    System.out.println("    Added " + (y + 1) + "," + (x) + " d=" + getNodeArr()[y + 1][x].getD());
                    queue.add(getNodeArr()[y + 1][x]);

                } else if (getNodeArr()[y + 1][x].getColour() == 'G' && queue.contains(getNodeArr()[y + 1][x])) {
                    int temp;
                    if (getNodeArr()[y][x].getDirection() == 1) {
                        temp = getNodeArr()[y][x].getD() + goStraight;
                    } else if (getNodeArr()[y][x].getDirection() == 3) {
                        temp = getNodeArr()[y][x].getD() + turn1;
                    } else {
                        temp = getNodeArr()[y][x].getD() + turn1;
                    }

                    if (temp < getNodeArr()[y + 1][x].getD()) {
//                        System.out.println("G " + (y + 1) + "," + (x) + " d=" + temp);
                        getNodeArr()[y + 1][x].setD(temp);
                        getNodeArr()[y + 1][x].setDirection(1);
                        getNodeArr()[y + 1][x].setP(current);
                    }
                }
            }

            if (y - 1 >= 0 && y - 1 < gridLength && grid[y - 1][x] == 'E') {
                if (getNodeArr()[y - 1][x].getColour() == 'W') {

                    getNodeArr()[y - 1][x].setColour('G');
                    if (getNodeArr()[y][x].getDirection() == 0) {
                        getNodeArr()[y - 1][x].setD(getNodeArr()[y][x].getD() + goStraight);
                    } else if (getNodeArr()[y][x].getDirection() == 2) {
                        getNodeArr()[y - 1][x].setD(getNodeArr()[y][x].getD() + turn1);
                    } else {
                        getNodeArr()[y - 1][x].setD(getNodeArr()[y][x].getD() + turn1);
                    }
                    getNodeArr()[y - 1][x].setDirection(1);
                    getNodeArr()[y - 1][x].setP(current);

//                    System.out.println("    Added " + (y - 1) + "," + (x) + " d=" + getNodeArr()[y - 1][x].getD());
                    queue.add(getNodeArr()[y - 1][x]);

                } else if (getNodeArr()[y - 1][x].getColour() == 'G' && queue.contains(getNodeArr()[y - 1][x])) {
                    int temp;
                    if (getNodeArr()[y][x].getDirection() == 1) {
                        temp = getNodeArr()[y][x].getD() + goStraight;
                    } else if (getNodeArr()[y][x].getDirection() == 3) {
                        temp = getNodeArr()[y][x].getD() + turn1;
                    } else {
                        temp = getNodeArr()[y][x].getD() + turn1;
                    }

                    if (temp < getNodeArr()[y - 1][x].getD()) {
//                        System.out.println("G " + (y - 1) + "," + (x) + " d=" + temp);
                        getNodeArr()[y - 1][x].setD(temp);
                        getNodeArr()[y - 1][x].setDirection(1);
                        getNodeArr()[y - 1][x].setP(current);
                    }
                }

            }

            getNodeArr()[y][x].setColour('B');
        }


////        path[current.getY()][current.getX()] = 1;
////        current = nodeArr[current.getYp()][current.getXp()];
////        path[current.getY()][current.getX()] = 1;
////        current = nodeArr[current.getYp()][current.getXp()];
////        path[current.getY()][current.getX()] = 1;
////        current = nodeArr[current.getYp()][current.getXp()];
////        path[current.getY()][current.getX()] = 1;
////        current = nodeArr[current.getYp()][current.getXp()];
////        path[current.getY()][current.getX()] = 1;
//        
//        
//        
//        System.out.println("adada");
//        for (int i = 0; i < gridLength; i++) {
//            for (int j = 0; j < gridLength; j++) {
//                System.out.print(nodeArr[i][j].getD() + " ");
////                  System.out.print(nodeArr[i][j].getD() + " , (" + nodeArr[i][j].getP().getX() + "," + nodeArr[i][j].getP().getY() + ")");
//            }
//            System.out.println();
//        }

    }

    public void plotPath(int xe, int ye) {
        this.xe = xe;
        this.ye = ye;
        path[ye][xe] = 1;
        Node current = nodeArr[ye][xe];

        int count = 0;
        while (current != null) {
            path[current.getY()][current.getX()] = 1;
            current = current.getP();
            count++;
            if (count > (gridLength * gridLength)) {
                for (int i = 0; i < gridLength; i++) {
                    for (int j = 0; j < gridLength; j++) {
                        path[i][j] = 0;
                    }
                }
                break;
            }
        }
        
        path[ys][xs] = 0;
        
        System.out.println("find path>");
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @return the xs
     */
    public int getXs() {
        return xs;
    }

    /**
     * @param xs the xs to set
     */
    public void setXs(int xs) {
        this.xs = xs;
    }

    /**
     * @return the ys
     */
    public int getYs() {
        return ys;
    }

    /**
     * @param ys the ys to set
     */
    public void setYs(int ys) {
        this.ys = ys;
    }

    /**
     * @return the dir
     */
    public int getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(int dir) {
        this.dir = dir;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    /**
     * @return the path
     */
    public int[][] getPath() {
        return path;
    }

    /**
     * @return the nodeArr
     */
    public Node[][] getNodeArr() {
        return nodeArr;
    }
}

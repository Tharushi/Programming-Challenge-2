package classSlick;

public class Vertex {
    private Vertex predicessor;
    private int heuristicVal;
    private int distance;
    int cost;
    boolean visited;
    boolean invalid;
    int xCord;
    int yCord;
    private int colour;
    String testVal;

    public Vertex(int x,int y) {
        this.predicessor=null;
        this.heuristicVal=10000;
        this.distance=10000;
        visited=false;
        invalid=false;
        this.xCord=x;
        this.yCord=y;
        this.colour=-1;//-1 for white,0 for gray, 1 for black
        testVal="O";
        cost=10000;
        
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getHeuristicVal() {
        return heuristicVal;
    }

    public void setHeuristicVal(int heuristicVal) {
        this.heuristicVal = heuristicVal;
    }

    public Vertex getPredicessor() {
        return predicessor;
    }

    public void setPredicessor(Vertex predicessor) {
        this.predicessor = predicessor;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }
     
}

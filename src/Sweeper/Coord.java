package Sweeper;

public class Coord {

    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coord){
            Coord to = (Coord) obj;
            return to.getX() == x && to.getY() == y ;
        }

        return super.equals(obj);
    }
}

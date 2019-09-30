package Sweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ranges {

    private Coord size;
    private static Ranges ranges;
    private ArrayList<Coord> coords;
    private Random random = new Random();

    public Coord getSize() {
        return size;
    }

    public void setSize(Coord size) {
        this.size = size;

        coords = new ArrayList<>();
        for (int y = 0; y < this.size.getY(); y++) {
            for (int x = 0; x < this.size.getX(); x++) {
                coords.add(new Coord(x, y));
            }
        }
    }

    private Ranges(){
        //singleton
    }

    public static Ranges getRanges(){
        if (Ranges.ranges == null){
            Ranges.ranges = new Ranges();
        }

        return Ranges.ranges;
    }

    public ArrayList<Coord> getCoords() {
        return coords;
    }

    public boolean inRange (Coord coord){
        return  coord.getX() >= 0 && coord.getX() < size.getX() &&
                coord.getY() >= 0 && coord.getY() < size.getY();
    }

    public Coord getRandomCoord(){
        return new Coord(random.nextInt(size.getX()), random.nextInt(size.getY()));
    }

    public List<Coord> getCoordsAroundBomb(Coord coord){
        Coord aroundCoord;
        List<Coord> list = new ArrayList<>();

        for (int x = coord.getX() - 1; x <= coord.getX() + 1; x++) {
            for (int y = coord.getY() - 1; y <= coord.getY() + 1; y++) {
                if (inRange(aroundCoord = new Coord(x, y))){
                    if (!aroundCoord.equals(coord)){
                        list.add(aroundCoord);
                    }
                }
            }
        }
        return list;
    }
}

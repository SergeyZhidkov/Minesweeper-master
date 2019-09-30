package Sweeper;

public class Game {

    private BombLayout bomb;
    private FlagLayout flag;

    private GameState state;

    public GameState getState() {
        return state;
    }

    public Game(int cols, int rows, int initBombs) {
        Ranges.getRanges().setSize(new Coord(cols, rows));
        bomb = new BombLayout(initBombs);
        flag = new FlagLayout();
    }

    public Box getBox (Coord coord){

        if (flag.get(coord) == Box.OPENED){
            return bomb.get(coord);
        }
        else
            return flag.get(coord);
    }

    public void start() {
        bomb.start();
        flag.start();

        //start the game
        state = GameState.PLAYED;
    }

    public void pressLeftButton(Coord coord) {

        //flag.setOpenedToBox (coord);
        
        if (!gameOver()){
            openBox(coord);
            checkWinner(); 
        }
        
    }

    private boolean gameOver() {

        if (state == GameState.PLAYED)
            return false;
        else {
            start();
            return true;
        }
    }

    private void checkWinner(){
        if (state == GameState.PLAYED){
            if (flag.getNumberOfClosedBoxes() == bomb.getTotalBombsNumber()){
                state = GameState.WINNED;
            }
        }
    }

    private void openBox(Coord coord) {

        switch (flag.get(coord)){
            case OPENED: setOpenedToClosedBoxesAroundNumber(coord); return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get(coord)){
                    case ZERO: openAllBoxAround(coord); return;
                    case BOMB: openAllBombs(coord); return;
                    default: flag.setOpenedToBox(coord); return;
                }
        }
    }

    private void setOpenedToClosedBoxesAroundNumber(Coord coord) {
        if (bomb.get(coord) != Box.BOMB)
            if (flag.getCountOfFlagedBoxes(coord) == bomb.get(coord).getNumber())
                for (Coord around : Ranges.getRanges().getCoordsAroundBomb(coord)) {
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
                }


    }

    private void openAllBombs(Coord coord) {
        state = GameState.BOMBED;
        flag.setBombedToBox(coord);

        for (Coord bombedCoord : Ranges.getRanges().getCoords()) {
            if (bomb.get(bombedCoord) == Box.BOMB){
                flag.setOpenedOpenedToClosedBombBox(bombedCoord);
            }else
                flag.setNoBombToFlagedSafeBox (bombedCoord);
        }

    }

    private void openAllBoxAround(Coord coord) {

        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getRanges().getCoordsAroundBomb(coord)) {
            openBox(around);
        }

    }

    public void pressRightButton(Coord coord) {

        flag.toggleFlagedToBox (coord);
    }
}

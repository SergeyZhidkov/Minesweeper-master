package Sweeper;

class FlagLayout {
    private Matrix flagMatrix;
    private int numberOfCloseBoxes;

    void start(){
        flagMatrix = new Matrix(Box.CLOSED);
        numberOfCloseBoxes = Ranges.getRanges().getSize().getX() * Ranges.getRanges().getSize().getY();
    }

    Box get (Coord coord){
        return flagMatrix.getMatrix(coord);
    }

    void setOpenedToBox(Coord coord) {

        flagMatrix.setMatrix(coord, Box.OPENED);
        numberOfCloseBoxes --;
    }


    void toggleFlagedToBox(Coord coord) {

        switch (flagMatrix.getMatrix(coord)){

            case FLAGED: setClosedToBox(coord);
                break;
            case CLOSED: setFlagedToBox(coord);
                break;
        }
    }

    private void setFlagedToBox(Coord coord) {

        flagMatrix.setMatrix(coord, Box.FLAGED);

    }

    private void setClosedToBox(Coord coord) {

        flagMatrix.setMatrix(coord, Box.CLOSED);
    }

    int getNumberOfClosedBoxes() {
        return numberOfCloseBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMatrix.setMatrix(coord, Box.BOMBED);
    }

    void setOpenedOpenedToClosedBombBox(Coord coord) {
        if (flagMatrix.getMatrix(coord) == Box.CLOSED )
            flagMatrix.setMatrix(coord, Box.OPENED);
    }

    void setNoBombToFlagedSafeBox(Coord coord) {
        if (flagMatrix.getMatrix(coord) == Box.FLAGED)
            flagMatrix.setMatrix(coord, Box.NOBOMB);
    }

    int getCountOfFlagedBoxes(Coord coord) {
        int counter = 0;

        for (Coord around : Ranges.getRanges().getCoordsAroundBomb(coord)) {
            if (flagMatrix.getMatrix(around) == Box.FLAGED){
                counter++;
            }
        }
            return counter;
    }
}

package Sweeper;

class BombLayout {

    private Matrix map;
    private int totalBombs;

    BombLayout(int totalBombs){
        this.totalBombs = totalBombs;
        fixBombNumber();
    }

    void start(){
        map = new Matrix(Box.ZERO);

        /**
         * placing all bombs to the field
         * number of bombs == totalBombs
         */
        for (int i = 0; i < totalBombs; i++) {
            addBombOnMap();
        }
    }

    private void fixBombNumber(){
        int maxValue =  Ranges.getRanges().getSize().getX()  *
                        Ranges.getRanges().getSize().getY() / 2;

        /**
         * the number of all bombs on the field can't be more then
         * the number of cells on the field
         *
         * max value of all bombs would be not more than half of the field size
         */
        if (maxValue < totalBombs){
            totalBombs = maxValue;
        }
    }

    private void addBombOnMap() {

        while (true){
            Coord coord = Ranges.getRanges().getRandomCoord();

            if (Box.BOMB == map.getMatrix(coord))
                continue;

            map.setMatrix(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
            }
        }

    private void incNumbersAroundBomb(Coord coord) {

        for (Coord roundCoord : Ranges.getRanges().getCoordsAroundBomb(coord)) {
            /**
             * if it is not a bomb than add a number
             * else leave like it is
             */
            if (Box.BOMB != map.getMatrix(roundCoord)){
                map.setMatrix(roundCoord, map.getMatrix(roundCoord).getNextBoxNumber());
            }
        }
    }


    Box get (Coord coord){
        return map.getMatrix(coord);
    }


    public int getTotalBombsNumber() {
        return totalBombs;
    }
}

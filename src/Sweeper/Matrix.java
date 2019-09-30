package Sweeper;

class Matrix {

    private Box [][] matrix;

    Matrix(Box defaultBox) {
        this.matrix = new Box[Ranges.getRanges().getSize().getX()][Ranges.getRanges().getSize().getY()];

        for (Coord coord : Ranges.getRanges().getCoords()) {
            this.matrix [coord.getX()][coord.getY()] = defaultBox;
        }
    }

    Box getMatrix (Coord coord){

        /**
         * check if coord on the field
         */

        if (Ranges.getRanges().inRange(coord)){
            return matrix[coord.getX()][coord.getY()];
        }
        else return null;
    }

    void setMatrix (Coord coord, Box box){

        /**
         * check if coord on the field
         */

        if (Ranges.getRanges().inRange(coord)){
            matrix [coord.getX()][coord.getY()] = box;
        }
    }
}

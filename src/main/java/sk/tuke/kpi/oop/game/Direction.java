package sk.tuke.kpi.oop.game;
//import sk.tuke.kpi.oop.game.characters.Ripley;
//import sk.tuke.kpi.oop.game.Movable;

public enum Direction {

    EAST(1, 0),
    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHWEST(-1, 1),
    NORTHEAST(1, 1),
    NONE(0, 0);


    private int dx = 0;
    private int dy = 0;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public float getAngle() {
        switch (this) {
            case EAST:
                return 270;
            case NORTH:
                return 0;
            case SOUTH:
                return 180;
            case WEST:
                return 90;
            case SOUTHEAST:
                return 225;
            case SOUTHWEST:
                return 135;
            case NORTHWEST:
                return 45;
            case NORTHEAST:
                return 315;
            default:
                return 0;
        }
    }

    public int getDx() {return dx;}

    public int getDy() {return dy;}

    public Direction combine(Direction other) {
        Direction direction = NONE;
        Direction direction2;
        int combineX = 0;
        int combineY = 0;
        if (this != other) {

            if (getDy() == other.getDy()) {
                combineY = getDy();

            } else if (getDy() != other.getDy()) {
                combineY = getDy() + other.getDy();
            }

            if (getDx() == other.getDx()) {combineX = getDx();}
            else if (getDx() != other.getDx()) {combineX = getDx() + other.getDx();}

            for (Direction value : Direction.values()) {
                if (value.getDx() == combineX &&  combineY == value.getDy()){
                    direction2 = value;
                    return direction2;
                }
            }
        } else return this;
        return direction;
    }

    public static Direction fromAngle(float angle) {
        if(angle == 270){
            return EAST;
        }
        else if (angle == 0){
            return NORTH;
        }
        else if (angle == 180){
            return SOUTH;
        }
        else if (angle == 90){
            return WEST;
        }
        else if (angle == 225){
            return SOUTHEAST;
        }
        else if (angle == 135){
            return SOUTHWEST;
        }
        else if (angle == 45){
            return NORTHWEST;
        }
        else if (angle == 315){
            return NORTHEAST;
        }
        else return NONE;
    }

}


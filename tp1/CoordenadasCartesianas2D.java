package fiuba.paradigmas.tp1;

import java.util.Objects;

final public class CoordenadasCartesianas2D{
    private int x;
    private int y;
    private int hashCode;

    public CoordenadasCartesianas2D(int x, int y) {
        this.x = x;
        this.y = y;
        this.hashCode = Objects.hash(x, y);
    }

    public CoordenadasCartesianas2D Sumar(CoordenadasCartesianas2D otras_coords){
        return new CoordenadasCartesianas2D(this.x+otras_coords.x, this.y+otras_coords.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordenadasCartesianas2D that = (CoordenadasCartesianas2D) o;
        return x == that.x && y == that.y;
    }


    @Override
    public int hashCode(){
        return this.hashCode;
    }

    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }


}
package util;

public class Vector2D {
    private double x, y;


    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance() {
        return MU.getDistance(0, 0, x, y);
    }

    public double dot(Vector2D v1) {
        return x * v1.x + y * v1.y;
    }

    public void normalize(){
        x/=getDistance();
        y/=getDistance();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

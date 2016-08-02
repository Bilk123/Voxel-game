package util;

/**
 * Created by Blake on 7/25/2016.
 */
public class Vector3D {
    private double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void set(double hx, double hy, double hz) {
        this.x = hx;
        this.y = hy;
        this.z = hz;
    }


    @Override
    public String toString() {
        return "(" + (int)x + ", " + (int)y + ", " + (int)z + ")";
    }

    public void add(Vector3D vec) {
        x+=vec.x;
        y+=vec.y;
        z+=vec.z;
    }


}

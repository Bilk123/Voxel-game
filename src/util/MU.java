package util;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MU {

    @Contract("_, _, _ -> !null")
    public static Vector2D rotate(int a, int pnts, double rotate) {
        return new Vector2D(cos(360 / pnts * (a + 1) + rotate), sin(360 / pnts * (a + 1) + rotate));
    }

    @NotNull
    @Contract("_ -> !null")
    public static Vector2D rotate(double rotate) {
        return new Vector2D(cos(rotate), sin(rotate));
    }

    @NotNull
    @Contract("_ -> !null")
    public static Vector2D zoom(double zoom) {
        return new Vector2D(sin(zoom), sin(zoom));
    }

    @Contract("_ -> !null")
    @NotNull
    public static Vector2D rotatex(double rotate) {
        return new Vector2D(sin(rotate), 1);
    }

    @NotNull
    @Contract("_ -> !null")
    public static Vector2D rotatey(double rotate) {
        return new Vector2D(1, sin(rotate));
    }

    public static double cos(double degree) {
        return Math.cos(Math.toRadians(degree));
    }

    public static double sin(double degree) {
        return Math.sin(Math.toRadians(degree));
    }

    @SuppressWarnings("unused")
    public static double tan(double degree) {
        return Math.tan(Math.toRadians(degree));
    }

    public static double arctan(double theta) {
        return Math.atan(theta);
    }

    @SuppressWarnings("unused")
    public static double arcsin(double theta) {
        return Math.asin(theta);
    }

    @SuppressWarnings("unused")
    public static double arccos(double theta) {
        return Math.acos(theta);
    }

    @SuppressWarnings("unused")
    public static double sec(double degree) {
        return 1 / cos(degree);
    }

    @SuppressWarnings("unused")
    public static double cosec(double degree) {
        return 1 / sin(degree);
    }

    @SuppressWarnings("unused")
    public static double cot(double degree) {
        return 1 / tan(degree);
    }

    @Contract(pure = true)
    public static int makeSquareI(boolean shift, int i, int size) {
        if (i < 0) {
            return 0;
        }
        double quad = size / 4.0;
        if (!shift) {
            if (i > 0 && i <= quad) {
                return 0;
            }
            if (i > quad && i <= 2 * quad) {
                return 1;
            }
            if (i > 2 * quad && i <= 3 * quad) {
                return 1;
            }
            if ((i > 3 * quad && i <= 4 * quad) || i == 0) {
                return 0;
            }
        } else {
            if (i > 0 && i <= quad) {
                return 0;
            }
            if (i > quad && i <= 2 * quad) {
                return 0;
            }
            if (i > 2 * quad && i <= 3 * quad) {
                return 1;
            }
            if ((i > 3 * quad && i <= 4 * quad) || i == 0) {
                return 1;
            }
        }
        return 0;
    }

    @Contract(pure = true)
    public static int inverseBit(int i) {
        if (i == 0 || i == 1) {
            return Math.abs(i - 1);
        } else {
            return 0;
        }
    }

    @Contract(pure = true)
    public static double square(double val) {
        return val * val;
    }

    @Contract(pure = true)
    public static boolean makeSquareB(boolean shift, int i, int size) {
        if (i < 0) {
            return false;
        }
        double quad = size / 4.0;
        if (!shift) {
            if (i > 0 && i <= quad) {
                return false;
            }
            if (i > quad && i <= 2 * quad) {
                return true;
            }
            if (i > 2 * quad && i <= 3 * quad) {
                return true;
            }
            if ((i > 3 * quad && i <= 4 * quad) || i == 0) {
                return false;
            }
        } else {
            if (i > 0 && i <= quad) {
                return false;
            }
            if (i > quad && i <= 2 * quad) {
                return false;
            }
            if (i > 2 * quad && i <= 3 * quad) {
                return true;
            }
            if ((i > 3 * quad && i <= 4 * quad) || i == 0) {
                return true;
            }
        }
        return false;
    }

    @Contract(pure = true)
    public static boolean makeHalvesB(boolean inverse, int i, int size) {
        double half = size / 8.0;
        if ((i > 0 && i <= 3 * half) || (i > 7 * half && i <= size)) {
            return !inverse;
        }
        return i > 3 * half && i <= 7 * half && inverse;
    }

    @Contract(pure = true)
    public static double getRadiusCircum(double circumference) {
        return circumference / (2.0 * Math.PI);
    }

    @SuppressWarnings("unused")
    public static double getRadiusArea(double area) {
        return Math.sqrt(area / Math.PI);
    }

    @Contract(pure = true)
    public static double getAreaRadius(double radius) {
        return Math.PI * square(radius);
    }

    @Contract(pure = true)
    public static double getAreaCircum(double circumference) {
        return square(circumference) / (4.0 * Math.PI);
    }

    @SuppressWarnings("unused")
    public static double getCircumArea(double area) {
        return 2.0 * Math.PI * Math.sqrt(area / Math.PI);
    }

    @Contract(pure = true)
    public static double getCircumRadius(double radius) {
        return 2.0 * Math.PI * radius;
    }

    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(square(x1 - x2) + square(y1 - y2));
    }

    public static double getDistance(Vector3D v1, Vector3D v2) {
        return Math.sqrt(square(v1.getX() - v2.getX()) + square(v1.getY() - v2.getY()) + square(v1.getZ() - v2.getZ()));
    }

    @Contract(pure = true)
    public static double getPercent(double val1, double val2) {
        return val1 / val2;
    }

    @Contract(pure = true)
    public static double min(double val1, double val2) {
        return val1 <= val2 ? val1 : val2;
    }

    public static double max(double val1, double val2) {
        return val1 >= val2 ? val1 : val2;
    }

    @Contract(pure = true)
    public static int extractDigit(int index, int binNumber) {
        return (binNumber & (0b1 << (index))) >> (index);
    }

    public static Vector3D multiply(Vector3D vec, double v) {

        double x = vec.getX() * v;
        double y = vec.getY() * v;
        double z = vec.getZ() * v;
        return new Vector3D(x, y, z);
    }

    public static double degreeBetweenVectors(Vector2D v1, Vector2D v2) {
        double dotp = v1.dot(v2);
        return Math.toDegrees(arccos(dotp / (v1.getDistance() * v2.getDistance())));
    }

    public static Vector3D add(Vector3D v1, Vector3D v2) {
        return new Vector3D(v1.getX() + v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ());
    }

    public static boolean intersects(Vector3D amin, Vector3D amax, Vector3D bmin, Vector3D bmax) {
        return (amin.getX() <= bmax.getX() && amax.getX() >= bmin.getX()) &&
                (amin.getY() <= bmax.getY() && amax.getY() >= bmin.getY()) &&
                (amin.getZ() <= bmax.getZ() && amax.getZ() >= bmin.getZ());
    }
}

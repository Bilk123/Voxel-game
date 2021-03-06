package Frame;

import org.jetbrains.annotations.NotNull;
import util.MU;
import util.PU;
import util.Vector3D;

import java.awt.*;

public class Cube {

    private int x;
    private int y;
    private int z;
    private int colorHex;
    private int[][] a, b;
    private boolean tb, bf, lr;
    private Polygon[] cube;
    private Color color[];
    private Color top, bot, back, front, left, right;
    private Grid grid;
    private Vector3D cmin, cmax;

    private Model env;

    public Cube(@NotNull Model env, int x, int y, int z, int red, int green, int blue) {
        initCube(env, x, y, z, red, green, blue);
    }

    private void initCube(@NotNull Model env, int x, int y, int z, int red, int green, int blue) {
        this.env = env;
        this.grid = env.getGrid();
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = new Color[3];
        colorHex = (red << 16) | (green << 8) | (blue);
        back = PU.saturateColor(new Color(colorHex), 0.5);
        bot = PU.saturateColor(new Color(colorHex), 0.6);
        left = PU.saturateColor(new Color(colorHex), 0.7);
        right = PU.saturateColor(new Color(colorHex), 0.8);
        top = PU.saturateColor(new Color(colorHex), 0.9);
        front = new Color(colorHex);
        this.color[0] = top;
        this.color[1] = front;
        this.color[2] = right;
        cube = new Polygon[3];
        a = new int[3][4];
        b = new int[3][4];
        cube[0] = new Polygon();
        cube[1] = new Polygon();
        cube[2] = new Polygon();
        cmin = new Vector3D(x, y, z);
        cmax = new Vector3D(x + 1, y + 1, z + 1);
    }

    public void updateCube() {
        {
            int drawTop;
            if (grid.getRotateY() < 0) {
                drawTop = 0;
                color[0] = bot;
                if (cubeBelow()) {
                    tb = false;
                    cube[0].reset();
                } else {
                    tb = true;

                    for (int i = 0; i < 4; i++) {
                        a[0][i] = (int) grid.getPts()[z + drawTop][x + MU.makeSquareI(false, i, 4)][y + MU.makeSquareI(true, i, 4)].getVecs().getX();
                        b[0][i] = (int) grid.getPts()[z + drawTop][x + MU.makeSquareI(false, i, 4)][y + MU.makeSquareI(true, i, 4)].getVecs().getY();
                    }
                    cube[0].xpoints = a[0];
                    cube[0].ypoints = b[0];
                    cube[0].reset();
                    cube[0].npoints = 4;
                }
            } else {
                drawTop = 1;
                color[0] = top;
                if (cubeAbove()) {
                    tb = false;
                    cube[0].reset();
                } else {
                    tb = true;
                    for (int i = 0; i < 4; i++) {
                        a[0][i] = (int) grid.getPts()[z + drawTop][x + MU.makeSquareI(false, i, 4)][y + MU.makeSquareI(true, i, 4)].getVecs().getX();
                        b[0][i] = (int) grid.getPts()[z + drawTop][x + MU.makeSquareI(false, i, 4)][y + MU.makeSquareI(true, i, 4)].getVecs().getY();
                    }
                    cube[0].xpoints = a[0];
                    cube[0].ypoints = b[0];
                    cube[0].reset();
                    cube[0].npoints = 4;
                }
            }
        }
        {
            int shift;
            if ((grid.getRotate() >= 0 && grid.getRotate() <= 90) || (grid.getRotate() >= 270 && grid.getRotate() <= 360)) {
                shift = 1;
                color[1] = back;
                if (cubeFront()) {
                    bf = false;
                    cube[1].reset();
                } else {
                    bf = true;
                    for (int i = 0; i < 4; i++) {
                        a[1][i] = (int) grid.getPts()[z + MU.makeSquareI(true, i, 4)][x + MU.makeSquareI(false, i, 4)][y + shift].getVecs().getX();
                        b[1][i] = (int) grid.getPts()[z + MU.makeSquareI(true, i, 4)][x + MU.makeSquareI(false, i, 4)][y + shift].getVecs().getY();
                    }
                    cube[1].xpoints = a[1];
                    cube[1].ypoints = b[1];
                    cube[1].reset();
                    cube[1].npoints = 4;
                }
            } else {
                shift = 0;
                color[1] = front;
                if (cubeBack()) {
                    bf = false;
                    cube[1].reset();
                } else {
                    bf = true;
                    for (int i = 0; i < 4; i++) {
                        a[1][i] = (int) grid.getPts()[z + MU.makeSquareI(true, i, 4)][x + MU.makeSquareI(false, i, 4)][y + shift].getVecs().getX();
                        b[1][i] = (int) grid.getPts()[z + MU.makeSquareI(true, i, 4)][x + MU.makeSquareI(false, i, 4)][y + shift].getVecs().getY();
                    }
                    cube[1].xpoints = a[1];
                    cube[1].ypoints = b[1];
                    cube[1].reset();
                    cube[1].npoints = 4;
                }
            }
        }
        {
            int shift;
            if (grid.getRotate() >= 0 && grid.getRotate() <= 180) {
                shift = 1;
                color[2] = left;
                if (cubeLeft()) {
                    lr = false;
                    cube[2].reset();
                } else {
                    lr = true;
                    for (int i = 0; i < 4; i++) {
                        a[2][i] = (int) grid.getPts()[z + MU.makeSquareI(true, i, 4)][x + shift][y + MU.makeSquareI(false, i, 4)].getVecs().getX();
                        b[2][i] = (int) grid.getPts()[z + MU.makeSquareI(true, i, 4)][x + shift][y + MU.makeSquareI(false, i, 4)].getVecs().getY();
                    }
                    cube[2].xpoints = a[2];
                    cube[2].ypoints = b[2];
                    cube[2].reset();
                    cube[2].npoints = 4;
                }
            } else {
                shift = 0;
                color[2] = right;
                if (cubeRight()) {
                    lr = false;
                    cube[2].reset();
                } else {
                    lr = true;
                    for (int i = 0; i < 4; i++) {
                        a[2][i] = (int) grid.getPts()[z + MU.makeSquareI(true, i, 4)][x + shift][y + MU.makeSquareI(false, i, 4)].getVecs().getX();
                        b[2][i] = (int) grid.getPts()[z + MU.makeSquareI(true, i, 4)][x + shift][y + MU.makeSquareI(false, i, 4)].getVecs().getY();
                    }
                    cube[2].xpoints = a[2];
                    cube[2].ypoints = b[2];
                    cube[2].reset();
                    cube[2].npoints = 4;
                }
            }
        }
    }

    public void fillCube(@NotNull Graphics2D g2d) {
        if (tb) {
            g2d.setColor(color[0]);
            g2d.fill(cube[0]);
        }
        if (bf) {
            g2d.setColor(color[1]);
            g2d.fill(cube[1]);
        }
        if (lr) {
            g2d.setColor(color[2]);
            g2d.fill(cube[2]);
        }
        g2d.setColor(Color.BLACK);

    }

    private boolean cubeAbove() {// checks for a cube above its self
        return z + 1 < grid.getHeight() - 1 && env.getCubes()[z + 1][x][y] != null;
    }

    private boolean cubeBelow() {// checks for a cube below its self
        return z - 1 >= 0 && env.getCubes()[z - 1][x][y] != null;
    }

    private boolean cubeLeft() {// checks for a cube to the left of its self
        return x + 1 < grid.getSide() - 1 && env.getCubes()[z][x + 1][y] != null;
    }

    private boolean cubeRight() {// checks for a cube to the right of its self
        return x - 1 >= 0 && env.getCubes()[z][x - 1][y] != null;
    }

    private boolean cubeFront() {// checks for a cube in front of its self
        return y + 1 < grid.getSide() - 1 && env.getCubes()[z][x][y + 1] != null;
    }

    private boolean cubeBack() {// checks for a cube behind its self
        return y - 1 >= 0 && env.getCubes()[z][x][y - 1] != null;
    }

    @NotNull
    @Override
    public String toString() {
        return x + " : " + y + " : " + z;
    }

    public int getColorHex() {
        return colorHex;
    }

    public Polygon[] getFaces() {
        return cube;
    }

    public void setColor(int red, int green, int blue) {

        back = new Color((int) (red * 0.5), (int) (green * 0.5), (int) (blue * 0.5));
        bot = new Color((int) (red * 0.6), (int) (green * 0.6), (int) (blue * 0.6));
        left = new Color((int) (red * 0.7), (int) (green * 0.7), (int) (blue * 0.7));
        right = new Color((int) (red * 0.8), (int) (green * 0.8), (int) (blue * 0.8));
        top = new Color((int) (red * 0.9), (int) (green * 0.9), (int) (blue * 0.9));
        front = new Color((red), (green), (blue));
        colorHex = (red << 16) | (green << 8) | blue;
        this.color[0] = top;
        this.color[1] = front;
        this.color[2] = right;
    }

    public void setColor(Color c) {
        int red = c.getRed();
        int blue = c.getBlue();
        int green = c.getGreen();
        back = new Color((int) (red * 0.5), (int) (green * 0.5), (int) (blue * 0.5));
        bot = new Color((int) (red * 0.6), (int) (green * 0.6), (int) (blue * 0.6));
        left = new Color((int) (red * 0.7), (int) (green * 0.7), (int) (blue * 0.7));
        right = new Color((int) (red * 0.8), (int) (green * 0.8), (int) (blue * 0.8));
        top = new Color((int) (red * 0.9), (int) (green * 0.9), (int) (blue * 0.9));
        front = new Color((red), (green), (blue));
        colorHex = (red << 16) | (green << 8) | blue;
        this.color[0] = top;
        this.color[1] = front;
        this.color[2] = right;
    }

    public boolean isPointInsideAABB(Vector3D point) {
        return (point.getX() >= cmin.getX() && point.getX() <= cmax.getX()) &&
                (point.getY() >= cmin.getY() && point.getY() <= cmax.getY()) &&
                (point.getZ() >= cmin.getZ() && point.getZ() <= cmax.getZ());
    }

    public boolean isBoxInsideAABB(Vector3D min, Vector3D max) {
        return (cmin.getX() <= max.getX() && cmax.getX() >= min.getX()) &&
                (cmin.getY() <= max.getY() && cmax.getY() >= min.getY()) &&
                (cmin.getZ() <= max.getZ() && cmax.getZ() >= min.getZ());
    }
}

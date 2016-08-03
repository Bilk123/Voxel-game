package Frame;

import org.jetbrains.annotations.NotNull;
import util.MU;
import util.Vector2D;
import util.Vector3D;

import java.awt.*;


/**
 * Created by Blake on 7/31/2016.
 */
public class Model {
    //  private final @NotNull SortEvent searchX;//an interface object, which is used for order of searching for which cube is being hovered on/clicked on;
    //  private final @NotNull SortEvent searchY;//an interface object, which is used for order of searching for which cube is being hovered on/clicked on;
    public static Vector2D globalX, globalY;
    public static Vector3D globalZ;

    static {
        globalX = new Vector2D(1, 0);
        globalY = new Vector2D(0, 1);
        globalZ = new Vector3D(0, 0, 1);
    }

    @NotNull
    protected final Grid grid;    //the 3D space and grid which is used to display cubes, see the Grid class
    private final @NotNull Rectangle pnt;//used for user
    private final @NotNull PaintEvent paintY;// an interface object, which is used for order of painting to ensure no
    protected Cube[][][] cubes;   //stores the cube data of the canvas
    protected boolean square;
    protected boolean shiftSquare;
    protected @NotNull PaintEvent paintX;// an interface object, which is used for order of painting
    private int[][][] normalBuffer;
    private int side, height;   // the sides length and height of the canvas

    public Model(int side, int height) {  //constructor
        grid = new Grid(side, height, EditorScreen.s_maxWidth / 2, EditorScreen.s_maxHeight / 2);//instantiates the grid in the centre of the screen
        cubes = new Cube[height - 1][side - 1][side - 1];//instantiates the cube array according the grid
        normalBuffer = new int[height - 1][side - 1][side - 1];
        pnt = new Rectangle(0, 0, 1, 1);// instantiates the rectangle used for user input
        square = MU.makeSquareB(false, (int) grid.getRotate(), 360);//see the MU(math utilities) class for information
        shiftSquare = MU.makeSquareB(true, (int) grid.getRotate(), 360);//""    ""  ""  ""  ""  ""  ""  ""  ""
        this.side = side;
        this.height = height;
        paintY = (PaintEvent e, int z, int y, Graphics2D g2d) -> {
            if (!square) {                                      // depending on the angle of the camera the order which the cubes of the cubes are painted changes
                for (int yi = 0; yi < grid.getSide() - 1; yi++) {
                    e.event(null, z, yi, g2d);
                }
            } else {
                for (int yi = grid.getSide() - 2; yi >= 0; yi--) {
                    e.event(null, z, yi, g2d);
                }
            }
        };
        paintX = (PaintEvent e, int z, int y, Graphics2D g2d) -> {
            if (!shiftSquare) {                                 // depending on the angle of the camera the order which the cubes of the cubes are painted changes
                for (int xi = 0; xi < grid.getSide() - 1; xi++) {
                    if (!(cubes[z][xi][y] == null)) {
                        cubes[z][xi][y].fillCube(g2d);
                    }
                }
            } else {
                for (int xi = grid.getSide() - 2; xi >= 0; xi--) {
                    if (!(cubes[z][xi][y] == null)) {
                        cubes[z][xi][y].fillCube(g2d);
                    }
                }
            }
        };

     /*   searchY = (SortEvent e, int z, int y) -> {
            if (square) {                                      // depending on the angle of the camera the order which the cubes of the cubes are painted changes
                for (int yi = 0; yi < grid.getSide() - 1; yi++) {
                    e.event(null, z, yi);
                    if (detected) {
                        break;
                    }
                }
            } else {
                for (int yi = grid.getSide() - 2; yi >= 0; yi--) {
                    e.event(null, z, yi);
                    if (detected) {
                        break;
                    }
                }
            }
        };
        searchX = (SortEvent e, int z, int y) -> {
            if (shiftSquare) {                                 // depending on the angle of the camera the order which the cubes of the cubes are painted changes
                for (int xi = 0; xi < grid.getSide() - 1; xi++) {
                    if (!(cubes[z][xi][y] == null)) {
                        if (cubes[z][xi][y].contains(mx, my)) {
                            detected = true;
                            hx = xi;
                            hy = y;
                            hz = z;
                        }
                    }
                }
            } else {
                for (int xi = grid.getSide() - 2; xi >= 0; xi--) {
                    if (!(cubes[z][xi][y] == null)) {
                        if (cubes[z][xi][y].contains(mx, my)) {
                            detected = true;
                            hx = xi;
                            hy = y;
                            hz = z;
                        }
                    }
                }
            }
        };*/
        //addSphere(0,0,0,10,10,10);

    }

    private void paintZ(@NotNull PaintEvent e, Graphics2D g2d) {// the final dimension of ordering the cubes to be painted
        if (grid.getRotateY() < 0) {
            for (int zi = grid.getHeight() - 2; zi > -1; zi--) {
                e.event(paintX, zi, 0, g2d);
            }
        } else {
            for (int zi = 0; zi < grid.getHeight() - 1; zi++) {
                e.event(paintX, zi, 0, g2d);
            }
        }
    }

   /* private void searchZ(@NotNull SortEvent e) {
        detected = false;
        if (!(grid.getRotateY() < 0)) {
            for (int zi = grid.getHeight() - 2; zi > -1; zi--) {
                e.event(searchX, zi, 0);
                if (detected) {
                    break;
                }
            }
        } else {
            for (int zi = 0; zi < grid.getHeight() - 1; zi++) {
                e.event(searchX, zi, 0);
                if (detected) {
                    break;
                }
            }
        }
    }*/

    public void reset() { // removes all cubes in the canvas
        for (int z = 0; z < grid.getHeight() - 1; z++) {
            for (int y = 0; y < grid.getSide() - 1; y++) {
                for (int x = 0; x < grid.getSide() - 1; x++) {
                    cubes[z][x][y] = null;
                }
            }
        }
    }

    public void setCube(int x, int y, int z, int red, int green, int blue) {// sets a single cube in the canvas with a specified colour
        if (checkIfInBounds(x, y, z)) {
            cubes[z][x][y] = new Cube(this, x, y, z, red, green, blue);
            normalBuffer[z][x][y] = (red << 16) | (green << 8) | blue;
        }
    }

    public void addSphere(int x, int y, int z, double width, double length, double height) { // creates a white sphere in the canvas
        for (int i = 1; i < 20; i++) {
            for (int theta = 0; theta < 720; theta += 5) {
                for (int pi = -360; pi < 360; pi += 5) {
                    int xi = (int) (Math.round((x + width / 2) + ((width * i / 20.0) / 2 * MU.cos(theta / 2.0) * MU.cos(pi / 2.0))));
                    int yi = (int) (Math.round((y + length / 2) + ((length * i / 20.0) / 2 * MU.sin(theta / 2.0) * MU.cos(pi / 2.0))));
                    int zi = (int) (Math.round((z + height / 2) + ((height * i / 20.0) / 2 * MU.sin(pi / 2.0))));
                    setCube(xi, yi, zi, 255, 255, 255);
                }
            }
        }
    }

    public boolean checkIfInBounds(int x, int y, int z) {// checks if the given coordinates falls in the canvas
        return (!(x < 0) && !(x >= grid.getSide() - 1) && !(y < 0) && !(y >= grid.getSide() - 1) && !(z < 0) && !(z >= grid.getHeight() - 1));
    }

    public boolean checkForCube(int x, int y, int z) {// checks if there is a cube at given coordinates
        return checkIfInBounds(x, y, z) && cubes[z][x][y] != null;
    }

    public void paintGuiComponent(@NotNull Graphics2D g2d) { //overridden from guicomponent which eventually gets fed into the screen painter in the EditorScreen class
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);                  //settings for optimization
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2d.setColor(Color.black);

        paintZ(paintY, g2d);
        g2d.setColor(new Color(1f, 1f, 1f, 1f));
    }

    protected void update() {// is called every frame
        grid.update();
        square = MU.makeSquareB(false, (int) grid.getRotate(), 360);
        shiftSquare = MU.makeSquareB(true, (int) grid.getRotate(), 360);
        for (int zi = 0; zi < grid.getHeight() - 1; zi++) {
            for (int yi = 0; yi < grid.getSide() - 1; yi++) {
                for (int xi = 0; xi < grid.getSide() - 1; xi++) {
                    if (cubes[zi][xi][yi] != null) {
                        cubes[zi][xi][yi].updateCube();
                    }

                }
            }
        }
        // searchZ(searchY);
    }

    @NotNull
    public Grid getGrid() {
        return grid; // returns the 3d spcae points;
    }

    @NotNull
    public Cube[][][] getCubes() {
        return cubes; // returns the 3d array which holds the cube data
    }

    public int getSide() {
        return side; //returns the length, which equal to the width of the canvas
    }

    public int getCanvasHeight() {
        return height;// returns the height of the canvas
    }

    public void setRotate(double rotate) {
        grid.setRotate(rotate);// sets the degree at which the canvas is horizontally rotated
    }

    public void setRotatey(double rotate) {
        grid.setRotatey(rotate);// sets the degree at which the canvas is vertically rotated
    }

    public void setZoom(int zoom) {
        grid.setZoom(zoom);//sets how zoomed in or out the canvas is
    }

    public void removeCubeAt(int x, int y, int z) {
        if (checkForCube(x, y, z)) {
            if (checkIfInBounds(x, y, z)) {
                cubes[z][x][y] = null;
            }
        }
    }

    public int[][][] getBuffer() {
        return normalBuffer;
    }

    public void setBuffer(int[][][] newBuffer) {
        normalBuffer = newBuffer;
        int r, g, b;
        for (int z = 0; z < grid.getHeight() - 1; z++) {
            for (int y = 0; y < grid.getSide() - 1; y++) {
                for (int x = 0; x < grid.getSide() - 1; x++) {
                    if (normalBuffer[z][x][y] >= 0) {
                        r = (normalBuffer[z][x][y] & 0xff0000) >> 16;
                        g = (normalBuffer[z][x][y] & 0xff00) >> 8;
                        b = (normalBuffer[z][x][y] & 0xff);
                        setCube(x, y, z, r, g, b);
                    } else {
                        cubes[z][x][y] = null;
                    }
                }
            }
        }

    }

    public void display() {
        for (int z = 0; z < height - 1; z++) {
            for (int y = 0; y < side - 1; y++) {
                for (int x = 0; x < side - 1; x++) {
                    System.out.print((normalBuffer[z][x][y]) + "\t");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

}

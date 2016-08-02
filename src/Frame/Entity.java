package Frame;

import backend.Project;
import org.jetbrains.annotations.NotNull;
import util.MU;
import util.Vector2D;
import util.Vector3D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Blake on 7/31/2016.
 */
public abstract class Entity extends Model {
    protected Vector3D loc;
    protected Vector3D movement;
    protected Level level;
    private Vector3D cp1, cp2;

    public Entity(Level l, Project modelData, double x, double y, double z) {
        super(modelData.getSide(), modelData.getCanvasHeight());
        level = l;
        loc = new Vector3D(x, y, z);
        movement = new Vector3D(0, 0, 0);
        cp1 = new Vector3D(loc.getX() + 0.5, loc.getY(), loc.getZ() + 0.5);
        cp2 = new Vector3D(loc.getX() + 0.5, loc.getY(), loc.getZ() + 1.5);
        int red, green, blue;
        for (int xi = 0; xi < modelData.getSide() - 1; xi++) {
            for (int yi = 0; yi < modelData.getSide() - 1; yi++) {
                for (int zi = 0; zi < modelData.getCanvasHeight() - 1; zi++) {
                    if (modelData.getCubeData()[zi][xi][yi] > 0) {
                        red = (modelData.getCubeData()[zi][xi][yi] & 0xff0000) >> 16;
                        green = (modelData.getCubeData()[zi][xi][yi] & 0xff00) >> 8;
                        blue = (modelData.getCubeData()[zi][xi][yi] & 0xff);
                        setCube(xi, yi, zi, red, green, blue);
                    }
                }
            }
        }
    }

    @Override
    protected void update() {
        super.update();
        for (int z = (int) loc.getZ(); z < loc.getZ() + 2; z++) {
            for (int x = 0; x < level.getEnv().getSide() - 1; x++) {
                for (int y = 0; y < level.getEnv().getSide() - 1; y++) {
                    if (level.getEnv().checkForCube(x, y, z)) {
                        if (level.getEnv().getCubes()[z][x][y].isPointInsideAABB(cp1) || level.getEnv().getCubes()[z][x][y].isPointInsideAABB(cp2)) {
                            movement.set(0, 0, 0);
                        }
                    }
                }
            }
        }
        loc.add(MU.multiply(movement, 16 / 1000.0), true);
        Vector2D sLoc = Grid.getPointInSpace(level.getEnv().getGrid(), loc.getX(), loc.getY(), loc.getZ());
        getGrid().setLocation((int) sLoc.getX(), (int) sLoc.getY());

    }

    protected abstract void hover(MouseEvent e);

    protected abstract void keyPress(@NotNull KeyEvent ke);

    protected abstract void keyReleased(KeyEvent ke);

    protected abstract void drag(MouseEvent e);

    protected abstract void onTrigger(TriggerEvent e);

    protected abstract void paint(Graphics2D g2d);

}

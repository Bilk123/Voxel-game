package Frame;

import backend.Project;
import org.jetbrains.annotations.NotNull;
import util.MU;
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

    public Entity(Level l, Project modelData, double x, double y, double z) {
        super(modelData.getSide(), modelData.getCanvasHeight());
        level = l;
        loc = new Vector3D(x, y, z);
        movement = new Vector3D(0, 0, 0);
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
        Vector3D pLoc = loc;
        loc.add(MU.multiply(movement, 16 / 1000.0));
        double x_, y_;
        x_ = level.getEnv().getGrid().getPts()[(int) loc.getZ()][(int) loc.getX()][(int) loc.getY()].getVecs().getX();
        y_ = level.getEnv().getGrid().getPts()[(int) loc.getZ()][(int) loc.getX()][(int) loc.getY()].getVecs().getY();
        getGrid().setLocation((int) x_, (int) y_);

    }

    protected abstract void hover(MouseEvent e);

    protected abstract void keyPress(@NotNull KeyEvent ke);

    protected abstract void keyReleased(KeyEvent ke);

    protected abstract void drag(MouseEvent e);

    protected abstract void onTrigger(TriggerEvent e);

    protected abstract void paint(Graphics2D g2d);

}

package Frame;

import backend.Project;
import org.jetbrains.annotations.NotNull;
import util.MU;
import util.Vector3D;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Blake on 7/31/2016.
 */
public abstract class Entity extends Model {
    private Vector3D loc;
    private Vector3D movement;
    private Vector3D forces;

    public Entity(Project modelData, double x, double y, double z) {
        super(modelData.getSide(), modelData.getCanvasHeight());
        loc = new Vector3D(x, y, z);
        movement = new Vector3D(0, 0, 0);
        forces = new Vector3D(0,0,0);
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
        movement.add(MU.multiply(forces,16 / 1000.0));
        loc.add(MU.multiply(movement,16/1000.0));
        //getGrid().setLocation();

    }

    protected abstract void keyPress(@NotNull KeyEvent ke);

    protected abstract void drag(MouseEvent e);

    protected abstract void onTrigger(TriggerEvent e);

}

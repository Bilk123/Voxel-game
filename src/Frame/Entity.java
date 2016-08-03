package Frame;

import backend.Project;
import org.jetbrains.annotations.NotNull;
import util.MU;
import util.Vector2D;
import util.Vector3D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public abstract class Entity extends Model {
    protected Vector3D loc;
    protected Vector3D movement;
    protected Level level;
    protected AnimationController a;
    protected int health;
    protected double speed;
    protected TriggerEvent te;
    protected Vector3D cp1, cp2;
    protected boolean dispose = false;
    protected int ID;

    public Entity(Level l, Project modelData, double x, double y, double z) {
        super(modelData.getSide(), modelData.getCanvasHeight());
        level = l;
        loc = new Vector3D(x, y, z);
        movement = new Vector3D(0, 0, 0);
        cp1 = new Vector3D(0, 0, 0);
        cp2 = new Vector3D(0, 0, 0);
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
        wallCollisions();
        Vector2D sLoc = Grid.getPointInSpace(level.getEnv().getGrid(), loc.getX(), loc.getY(), loc.getZ());
        getGrid().setLocation((int) sLoc.getX(), (int) sLoc.getY());
        if (a != null) {
            a.update();
            int r, g, b;
            int currentFrame = a.getPlayingAnimation().getCurrentFrame();
            if (!a.getPlayingAnimation().hasUpdated()) {
                for (int i = 0; i < a.getPlayingAnimation().getChanges()[currentFrame].size(); i++) {
                    if (a.getPlayingAnimation().getChanges()[currentFrame].get(i) < 0) {
                        removeCubeAt((int) a.getPlayingAnimation().getChangeLoc()[currentFrame].get(i).getX(), (int) a.getPlayingAnimation().getChangeLoc()[currentFrame].get(i).getY(), (int) a.getPlayingAnimation().getChangeLoc()[currentFrame].get(i).getZ());
                    } else {
                        r = (a.getPlayingAnimation().getChanges()[currentFrame].get(i) & 0xff0000) >> 16;
                        g = (a.getPlayingAnimation().getChanges()[currentFrame].get(i) & 0xff00) >> 8;
                        b = (a.getPlayingAnimation().getChanges()[currentFrame].get(i) & 0xff);
                        setCube((int) a.getPlayingAnimation().getChangeLoc()[currentFrame].get(i).getX(), (int) a.getPlayingAnimation().getChangeLoc()[currentFrame].get(i).getY(), (int) a.getPlayingAnimation().getChangeLoc()[currentFrame].get(i).getZ(), r, g, b);
                    }
                }
                a.getPlayingAnimation().isUpdated(true);
            }
        }
        for (int i = 0; i < level.getEntities().size(); i++) {
            if (entityCollision(level.getEntities().get(i))) {
                if (te != null) {
                    te.event(level.getEntities().get(i));
                }
            }
        }
    }

    protected void wallCollisions() {
        boolean add = true;
        Vector3D check = MU.add(loc, MU.multiply(movement, 16 / 1000.0));
        cp1.set(check.getX() - 1.1, check.getY() - 1.1, check.getZ());
        cp2.set(check.getX() - 0.1, check.getY() - 0.1, check.getZ() + 2);
        for (int z = (int) loc.getZ(); z < loc.getZ() + 2; z++) {
            for (int x = 0; x < level.getEnv().getSide() - 1; x++) {
                for (int y = 0; y < level.getEnv().getSide() - 1; y++) {
                    if (level.getEnv().checkForCube(x, y, z)) {
                        if (level.getEnv().getCubes()[z][x][y].isBoxInsideAABB(cp1, cp2)) {
                            add = false;
                        }
                    }
                }
            }
        }
        if (add) {
            loc.add(MU.multiply(movement, 16 / 1000.0), true);
        }

    }

    public boolean entityCollision(Entity e) {
        return MU.intersects(cp1, cp2, e.cp1, e.cp2);
    }

    protected void hover(MouseEvent e) {

    }

    protected void mousePress(MouseEvent e) {

    }

    protected void keyPress(@NotNull KeyEvent ke) {

    }

    protected void keyReleased(KeyEvent ke) {

    }

    protected void drag(MouseEvent e) {

    }

    protected void paint(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 0.3f));
        g2d.fillOval((int) (Grid.getPointInSpace(level.getEnv().getGrid(), loc.getX(), loc.getY(), loc.getZ()).getX() - 15), (int) (Grid.getPointInSpace(level.getEnv().getGrid(), loc.getX(), loc.getY(), loc.getZ()).getY() - 10), 30, 20);
    }

}

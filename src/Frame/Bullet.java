package Frame;

import backend.Project;
import util.MU;
import util.Vector3D;

import java.awt.*;

/**
 * Created by Blake on 8/3/2016.
 */
public class Bullet extends Entity {
    private int damage = 25;

    public Bullet(Level l, Project bulletModel, double x, double y, double z) {
        super(l, bulletModel, x, y, z);
        setZoom(4);
        a = new AnimationController(new Animation("idle", 10, bulletModel));
        speed = 100 + 50 * (l.getEntities().size() / 4);
        health = 1;
        ID = 2;
        te = (Entity e) -> {
            if (e.ID != 2 && e.ID != 0) {
                e.health -= damage;
                dispose = true;
            }
        };
        l.addEntities(this);
    }

    @Override
    protected void update() {
        super.update();
        if (loc.getX() > level.getEnv().getSide() || loc.getY() > level.getEnv().getSide()) {
            dispose = true;
        }
    }

    @Override
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
        } else {
            dispose = true;
        }
    }

    @Override
    protected void paint(Graphics2D g2d) {

    }
}

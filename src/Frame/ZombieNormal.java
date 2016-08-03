package Frame;

import backend.Project;
import org.jetbrains.annotations.NotNull;
import util.MU;
import util.Vector2D;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by Blake on 8/3/2016.
 */
public class ZombieNormal extends Entity {
    private int damage = 10;

    public ZombieNormal(Level l, Project modelData, double x, double y, double z) {
        super(l, modelData, x, y, z);
        speed = 2.2;
        a = new AnimationController(
                new Animation("walking", (int) ((5 - speed) * (10 - new Random().nextInt(5))),
                        ModelManager.getModel("zombieWalk1"),
                        ModelManager.getModel("zombieWalk3"),
                        ModelManager.getModel("zombieWalk2"),
                        ModelManager.getModel("zombieWalk3")),
                new Animation("idle", (int) (5 - speed) * 10,
                        ModelManager.getModel("zombieWalk3"),
                        ModelManager.getModel("zombieWalk3")));
        health = 50;
        ID = 1;
        setZoom(7);
    }


    @Override
    protected void update() {
        super.update();
        double dx = level.getPlayer().getGrid().getX() - getGrid().getX();
        double dy = level.getPlayer().getGrid().getY() - getGrid().getY();
        Vector2D sLoc = new Vector2D(dx, dy);
        double theta = MU.degreeBetweenVectors(Model.globalX, sLoc);
        if (dy < 0) {
            theta *= -1;
            theta += 360;
        }
        if (dx == 0 && dy == 0) {
            theta = 0;
            movement.set(0, 0, 0);
        }
        getGrid().setRotate(theta);
        double x_ = MU.cos(getGrid().getRotate() + level.getEnv().getGrid().getRotate() + 270);
        double y_ = MU.sin(getGrid().getRotate() + level.getEnv().getGrid().getRotate() + 270);
        movement.set(speed * x_, speed * y_, 0);
        if (MU.getDistance(loc, level.getPlayer().loc) < 1.5) {
            movement.set(0, 0, 0);
            a.playAnimation("idle");
        } else {
            a.playAnimation("walking");
        }
        if (a.getPlayingAnimation().getCurrentFrame() == 0 || a.getPlayingAnimation().getCurrentFrame() == 2) {
            movement.set(0, 0, 0);
        }

        if (health <= 0) {
            dispose = true;
        }

    }

    @Override
    protected void hover(MouseEvent e) {

    }

    @Override
    protected void keyPress(@NotNull KeyEvent ke) {

    }

    @Override
    protected void keyReleased(KeyEvent ke) {

    }

    @Override
    protected void drag(MouseEvent e) {

    }


}

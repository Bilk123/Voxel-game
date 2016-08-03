package Frame;

import backend.Project;
import org.jetbrains.annotations.NotNull;
import util.MU;
import util.Vector2D;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Blake on 7/31/2016.
 */
public class Player extends Entity {

    private int mx, my;

    public Player(Level l, Project modelData, double x, double y, double z) {
        super(l, modelData, x, y, z);
        a = new AnimationController(
                new Animation("walking", 10,
                        ModelManager.getModel("playerWalk1"),
                        ModelManager.getModel("idlePlayer"),
                        ModelManager.getModel("playerWalk2"),
                        ModelManager.getModel("idlePlayer")),
                new Animation("idle", -1,
                        ModelManager.getModel("idlePlayer")));

        setZoom(6);
        a.playAnimation("idle");
        speed = 2 + l.getEntities().size() / 4;
        health = 100;
        ID = 0;
    }


    @Override
    protected void hover(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        double dx = mx - getGrid().getX();
        double dy = (my - getGrid().getY());
        Vector2D loc = new Vector2D(dx, dy);
        double theta = MU.degreeBetweenVectors(Model.globalX, loc);
        if (dy < 0) {
            theta *= -1;
            theta += 360;
        }
        if (dx == 0 && dy == 0) {
            theta = 0;
            movement.set(0, 0, 0);
        }

        getGrid().setRotate(theta);
    }

    @Override
    protected void update() {
        super.update();
        speed = 2 + level.getEntities().size() / 4;
    }

    @Override
    protected void keyPress(@NotNull KeyEvent ke) {
        int kc = ke.getKeyCode();
        double x_, y_;
        if (kc == KeyEvent.VK_W) {
            a.playAnimation("walking");
            x_ = MU.cos(getGrid().getRotate() + level.getEnv().getGrid().getRotate() + 270);
            y_ = MU.sin(getGrid().getRotate() + level.getEnv().getGrid().getRotate() + 270);
            movement.set(speed * x_, speed * y_, 0);
        }
      /*  if (kc == KeyEvent.VK_S) {
            a.playAnimation("walking");
            x_ = MU.cos(getGrid().getRotate()+level.getEnv().getGrid().getRotate()+270);
            y_ = MU.sin(getGrid().getRotate()+level.getEnv().getGrid().getRotate()+270);
            movement.set(-speed*x_,-speed*y_,0);
        }*/
        if (kc == KeyEvent.VK_A) {
            a.playAnimation("walking");
        }
        if (kc == KeyEvent.VK_D) {
            a.playAnimation("walking");
        }
    }

    @Override
    protected void keyReleased(KeyEvent ke) {
        a.playAnimation("idle");
        movement.set(0, 0, 0);
    }

    @Override
    protected void drag(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        double dx = mx - getGrid().getX();
        double dy = (my - getGrid().getY());
        Vector2D loc = new Vector2D(dx, dy);
        double theta = MU.degreeBetweenVectors(Model.globalX, loc);
        if (dy < 0) {
            theta *= -1;
            theta += 360;
        }
        if (dx == 0 && dy == 0) {
            theta = 0;
        }
        getGrid().setRotate(theta);
    }

    @Override
    protected void mousePress(MouseEvent e) {
        Bullet b = new Bullet(level, ModelManager.getModel("bullet"), loc.getX(), loc.getY(), loc.getZ() + 1);
        b.movement.set(b.speed * MU.cos(grid.getRotate() - 45) * MU.sin(grid.getRotateY()), b.speed * MU.sin(grid.getRotate() - 45) * MU.sin(grid.getRotateY()), 0);
        b.setRotate(grid.getRotate());
    }


}

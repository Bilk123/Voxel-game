package Frame;

import backend.Project;
import org.jetbrains.annotations.NotNull;
import util.MU;
import util.Vector2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Blake on 7/31/2016.
 */
public class Player extends Entity {
    private AnimationController a;
    private int mx, my;
    private double speed = 3.5;
    public Player(Level l, Project modelData, double x, double y, double z) {
        super(l, modelData, x, y, z);
        a = new AnimationController(
                new Animation("walking", 10,
                        Project.loadProject("data\\models\\playerWalk1"),
                        Project.loadProject("data\\models\\idlePlayer"),
                        Project.loadProject("data\\models\\playerWalk2"),
                        Project.loadProject("data\\models\\idlePlayer")),
                new Animation("idle", -1,
                        Project.loadProject("data\\models\\idlePlayer")));

        setZoom(7);
        a.playAnimation("idle");
    }

    @Override
    protected void update() {
        super.update();
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
        }
        getGrid().setRotate(theta);
    }

    @Override
    protected void keyPress(@NotNull KeyEvent ke) {
        int kc = ke.getKeyCode();
        double x_,y_;
        if (kc == KeyEvent.VK_W) {
            a.playAnimation("walking");
            x_ = MU.cos(getGrid().getRotate()+level.getEnv().getGrid().getRotate()+270);
            y_ = MU.sin(getGrid().getRotate()+level.getEnv().getGrid().getRotate()+270);
            movement.set(speed*x_,speed*y_,0);
        }
        if (kc == KeyEvent.VK_S) {
            a.playAnimation("walking");
            x_ = MU.cos(getGrid().getRotate()+level.getEnv().getGrid().getRotate()+270);
            y_ = MU.sin(getGrid().getRotate()+level.getEnv().getGrid().getRotate()+270);
            movement.set(-speed*x_,-speed*y_,0);
        }
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
        movement.set(0,0,0);
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
    protected void onTrigger(TriggerEvent e) {
    }

    @Override
    protected void paint(Graphics2D g2d) {
        for(int i =0;i<10;i++){
            g2d.setColor(new Color(0,0,0,i/70.0f));
            g2d.fillOval(getGrid().getX()-i,(int)(getGrid().getY()-0.5*i),(int)(1.8*i),i);
        }
    }


}

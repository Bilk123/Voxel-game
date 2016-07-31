package Frame;

import backend.Project;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Blake on 7/31/2016.
 */
public class Player extends Entity {
    private Animation a;
    private int mx, my;
    private int flag =0;

    public Player(Project modelData, double x, double y, double z) {
        super(modelData, x, y, z);
        a = new Animation("walking",500,
                Project.loadProject("data\\models\\playerWalk1"),
                Project.loadProject("data\\models\\playerWalk2"));
        setZoom(5);
    }

    @Override
    protected void update() {
        super.update();
        a.update();
        if(a.getCurrentFrame()!=flag){
            setBuffer(a.getFrames()[a.getCurrentFrame()].getCubeData());
        }

        flag = a.getCurrentFrame();
    }

    @Override
    protected void keyPress(@NotNull KeyEvent ke) {
        int kc = ke.getKeyCode();
        if (kc == KeyEvent.VK_W) {
            setBuffer(a.getFrames()[2].getCubeData());
        }
        if (kc == KeyEvent.VK_S) {
            setBuffer(a.getFrames()[1].getCubeData());
        }
        if (kc == KeyEvent.VK_A) {
            setBuffer(a.getFrames()[0].getCubeData());
        }
        if (kc == KeyEvent.VK_D) {

        }
        if (kc == KeyEvent.VK_SPACE) {

        }
    }

    @Override
    protected void drag(MouseEvent e) {
        int dx = mx - e.getX();
        int dy = my - e.getY();



        mx = e.getX();
        my = e.getY();
            getGrid().rotate(dx / 2);    // rotates the canvas vertically and horizontally


    }

    @Override
    protected void onTrigger(TriggerEvent e) {

    }


}

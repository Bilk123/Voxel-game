package Frame;

import backend.Project;
import guiTools.GuiComponent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

/**
 * Created by Blake on 7/31/2016.
 */
public class Level extends GuiComponent {
    private Map env;
    private Player p;
    private ZombieNormal zn, zn1, zn2, zn3, zn4, zn5;
    private ArrayList<Entity> entities;

    protected Level(String fileName) {
        super(0, 0, EditorScreen.s_maxWidth, EditorScreen.s_maxHeight);
        entities = new ArrayList<>();
        p = new Player(this, ModelManager.getModel("idlePlayer"), 10, 5, 1);
        zn = new ZombieNormal(this, ModelManager.getModel("zombieWalk3"), 23, 23, 1);
        zn1 = new ZombieNormal(this, ModelManager.getModel("zombieWalk3"), 23, 3, 1);
        zn2 = new ZombieNormal(this, ModelManager.getModel("zombieWalk3"), 3, 3, 1);
        zn3 = new ZombieNormal(this, ModelManager.getModel("zombieWalk3"), 10, 23, 1);
        zn4 = new ZombieNormal(this, ModelManager.getModel("zombieWalk3"), 23, 10, 1);
        addEntities(p, zn, zn1, zn2, zn3, zn4);

        env = new Map(Project.loadProject("data\\levels\\Zombie Map"), entities);
    }

    @Override
    protected void paintGuiComponent(Graphics2D g2d) {
        env.paintGuiComponent(g2d);
       /* for(int i =0;i<entities.size();i++){
            entities.get(i).paint(g2d);
            entities.get(i).paintGuiComponent(g2d);

        }*/
    }

    public void addEntities(Entity... e) {
        for (int i = 0; i < e.length; i++) {
            entities.add(e[i]);
        }
    }

    @Override
    protected void update() {
        env.update();
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).dispose) {
                entities.remove(i);
            } else {
                entities.get(i).update();
            }
        }
    }

    @Override
    protected void hover(MouseEvent e) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).hover(e);
        }
    }

    @Override
    protected void drag(MouseEvent e) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).drag(e);
        }
    }

    @Override
    protected void scroll(MouseWheelEvent mwe) {

    }

    @Override
    protected void keyPress(KeyEvent e) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).keyPress(e);
        }
    }

    @Override
    protected void mousePress(MouseEvent e) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).mousePress(e);
        }
    }

    @Override
    protected void mouseRelease(MouseEvent e) {

    }

    @Override
    protected void keyReleased(KeyEvent e) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).keyReleased(e);
        }
    }

    public Map getEnv() {
        return env;
    }

    public Player getPlayer() {
        return p;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}

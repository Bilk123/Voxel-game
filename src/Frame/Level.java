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
    private ArrayList<Entity> entities;
    protected Level(String fileName) {
        super(0, 0, EditorScreen.s_maxWidth, EditorScreen.s_maxHeight);
        entities = new ArrayList<>();
        p = new Player(this, Project.loadProject("data\\models\\idlePlayer"),0,0,0);
        entities.add(p);
        env = new Map(Project.loadProject("data\\levels\\"+fileName));
    }

    @Override
    protected void paintGuiComponent(Graphics2D g2d) {
        env.paintGuiComponent(g2d);
        for(int i =0;i<entities.size();i++){
            entities.get(i).paint(g2d);
            entities.get(i).paintGuiComponent(g2d);

        }
    }

    @Override
    protected void update() {
        env.update();
        for(int i =0;i<entities.size();i++){
            entities.get(i).update();
        }
    }

    @Override
    protected void hover(MouseEvent e) {
        for(int i=0;i<entities.size();i++){
            entities.get(i).hover(e);
        }
    }

    @Override
    protected void drag(MouseEvent e) {
        for(int i=0;i<entities.size();i++){
            entities.get(i).drag(e);
        }
    }

    @Override
    protected void scroll(MouseWheelEvent mwe) {

    }

    @Override
    protected void keyPress(KeyEvent e) {
        for(int i=0;i<entities.size();i++){
            entities.get(i).keyPress(e);
        }
    }

    @Override
    protected void mousePress(MouseEvent e) {

    }

    @Override
    protected void mouseRelease(MouseEvent e) {

    }

    @Override
    protected void keyReleased(KeyEvent e){
        for(int i=0;i<entities.size();i++){
            entities.get(i).keyReleased(e);
        }
    }

    public Map getEnv() {
        return env;
    }
}

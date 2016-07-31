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
    private Model env;
    private Player p;
    private ArrayList<Entity> entities;
    protected Level(String fileName) {
        super(0, 0, EditorScreen.s_maxWidth, EditorScreen.s_maxHeight);
        loadEnvironment(fileName);
        entities = new ArrayList<>();
        p = new Player(Project.loadProject("data\\models\\idlePlayer"),0,0,0);
        entities.add(p);

    }

    public void loadEnvironment(String fileName) {
        Project p = Project.loadProject(fileName);
        env = new Model(p.getSide(), p.getCanvasHeight());
        int red, green, blue;
        for (int x = 0; x < p.getSide(); x++) {
            for (int y = 0; y < p.getSide(); y++) {
                for (int z = 0; z < p.getCanvasHeight(); z++) {
                    if (p.getCubeData()[z][x][y] != -1) {
                        red = (p.getCubeData()[z][x][y] & 0xff0000) >> 16;
                        green = (p.getCubeData()[z][x][y] & 0xff00) >> 8;
                        blue = (p.getCubeData()[z][x][y] & 0xff);
                        env.setCube(x, y, z, red, green, blue);
                    }
                }
            }
        }
    }

    @Override
    protected void paintGuiComponent(Graphics2D g2d) {
     //   env.paintGuiComponent(g2d);
        for(int i =0;i<entities.size();i++){
            entities.get(i).paintGuiComponent(g2d);
        }
    }

    @Override
    protected void update() {
     //   env.update();
        for(int i =0;i<entities.size();i++){
            entities.get(i).update();
        }
    }

    @Override
    protected void hover(MouseEvent e) {

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
}

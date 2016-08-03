package Frame;

import backend.Project;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Blake on 8/1/2016.
 */
public class Map extends Model {
    public Map(Project map, ArrayList<Entity> entities) {
        super(map.getSide(), map.getCanvasHeight());
        setBuffer(map.getCubeData());
        setZoom(30);
        setRotate(45);
        paintX = (PaintEvent e, int z, int y, Graphics2D g2d) -> {
            if (!shiftSquare) {                                 // depending on the angle of the camera the order which the cubes of the cubes are painted changes
                for (int xi = 0; xi < grid.getSide() - 1; xi++) {
                    for (int i = 0; i < entities.size(); i++) {
                        if ((entities.get(i).loc.getX() > xi && entities.get(i).loc.getX() <= xi + 1) &&
                                (entities.get(i).loc.getY() > y && entities.get(i).loc.getY() <= y + 1) &&
                                ((entities.get(i).loc.getZ() + 1 > z && entities.get(i).loc.getZ() + 1 <= z + 1) || (entities.get(i).loc.getZ() > z && entities.get(i).loc.getZ() <= z + 1))) {

                            entities.get(i).paint(g2d);
                            entities.get(i).paintGuiComponent(g2d);
                        }
                        if (!(cubes[z][xi][y] == null)) {
                            cubes[z][xi][y].fillCube(g2d);
                        }
                    }
                }
            }
        };
    }

    @Override
    protected void update() {
        super.update();
        getGrid().setLocation(EditorScreen.s_maxWidth / 2, EditorScreen.s_maxHeight / 2);
    }

    @Override
    public void paintGuiComponent(@NotNull Graphics2D g2d) {
        super.paintGuiComponent(g2d);
    }
}

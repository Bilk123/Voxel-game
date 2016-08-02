package Frame;

import backend.Project;

/**
 * Created by Blake on 8/1/2016.
 */
public class Map extends Model{
    public Map(Project map) {
        super(map.getSide(), map.getCanvasHeight());
        setBuffer(map.getCubeData());
        setZoom(45);
        setRotate(45);
    }

    @Override
    protected void update() {
        super.update();
        getGrid().setLocation(EditorScreen.s_maxWidth/2,EditorScreen.s_maxHeight/2);
    }
}

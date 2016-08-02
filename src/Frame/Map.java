package Frame;

import backend.Project;

/**
 * Created by Blake on 8/1/2016.
 */
public class Map extends Model{
    public Map(Project map) {
        super(map.getSide(), map.getCanvasHeight());
        setBuffer(map.getCubeData());
        setZoom(15);
        setRotate(45);
    }
}

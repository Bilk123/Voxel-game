package Frame;

import backend.Project;
import util.Vector3D;

import java.util.ArrayList;

/**
 * Created by Blake on 7/31/2016.
 */
public class Animation {
    private Project[] frames;
    private ArrayList<Vector3D>[] changesLoc;
    private ArrayList<Integer>[] changes;
    private String animationName;
    private int currentFrame = 0;
    private int delayMs;
    private int tick;
    private boolean isUpdated = false;

    public Animation(String name, int delayMs, Project... f1) {
        animationName = name;
        this.delayMs = delayMs;
        frames = f1;
        int side = f1[0].getSide() - 1;
        int height = f1[0].getCanvasHeight() - 1;
        changesLoc = new ArrayList[f1.length];
        changes = new ArrayList[f1.length];
        for(int i=0;i<f1.length;i++){
            changes[i] = new ArrayList<>();
            changesLoc[i] = new ArrayList<>();
        }

        for (int i = 0; i < f1.length - 1; i++) {
            for (int x = 0; x < side; x++) {
                for (int y = 0; y < side; y++) {
                    for (int z = 0; z < height; z++) {
                       if(f1[i].getCubeData()[z][x][y] != f1[i+1].getCubeData()[z][x][y]){
                           changesLoc[i].add(new Vector3D(x,y,z));
                           changes[i].add(f1[i+1].getCubeData()[z][x][y]);
                       }
                    }
                }
            }

        }
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                for (int z = 0; z < height; z++) {
                    if(f1[f1.length-1].getCubeData()[z][x][y] != f1[0].getCubeData()[z][x][y]){
                        changesLoc[f1.length-1].add(new Vector3D(x,y,z));
                        changes[f1.length-1].add(f1[0].getCubeData()[z][x][y]);
                    }
                }
            }
        }
    }

    public void update() {
        if(delayMs>=0) {
            tick += Main.tickRate;
            if (tick >= delayMs * 10) {
                isUpdated = false;
                currentFrame++;
                if (currentFrame >= frames.length) {
                    currentFrame = 0;
                }
                tick = 0;
            }
        }
    }

    public String getAnimationName() {
        return animationName;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void reset() {
        currentFrame = 0;
        isUpdated = false;
    }
    public ArrayList<Integer>[] getChanges() {
        return changes;
    }

    public ArrayList<Vector3D>[] getChangeLoc() {
        return changesLoc;
    }

    public boolean hasUpdated() {
        return isUpdated;
    }

    public void isUpdated(boolean b) {
        isUpdated = b;
    }
}

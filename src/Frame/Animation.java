package Frame;

import backend.Project;

/**
 * Created by Blake on 7/31/2016.
 */
public class Animation {
    private Project[] frames;
    private String animationName;
    private int currentFrame =0;
    private int delayMs;
    private int tick;
    public Animation(String name,int delayMs,Project...f1){
        animationName = name;
        this.delayMs =delayMs;
        frames = f1;
    }

    public void update(){
        tick+=Main.tickRate;
        if(currentFrame >= frames.length){
            currentFrame=0;
        }
        else if(tick>= delayMs*10){
            currentFrame++;
            tick =0;
        }
        System.out.println(currentFrame);

    }


    public Project[] getFrames(){
        return frames;
    }

    public String getAnimationName() {
        return animationName;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}

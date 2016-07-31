package Frame;


import java.util.HashMap;

/**
 * Created by Blake on 7/31/2016.
 */
public class AnimationController {
    private Animation[] animations;
    private int[][][] model;
    private HashMap<String, Integer> animationAccessor;
    private int playingAnimation;

    public AnimationController(Animation... animations) {
        this.animations = animations;
        animationAccessor = new HashMap<>();
        for (int i = 0; i < animations.length; i++) {
            animationAccessor.put(animations[i].getAnimationName(),i);
        }
    }

    public void update() {
        for(int i =0;i<animations.length;i++){
            if(playingAnimation == i){
                animations[i].update();
            }
        }
    }

    public void playAnimation(String tag){
        System.out.println(playingAnimation);
        playingAnimation = animationAccessor.get(tag);
    }

    public int[][][] getModel() {
        return model;
    }
}

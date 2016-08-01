package Frame;

import java.util.HashMap;

/**
 * Created by Blake on 7/31/2016.
 */
public class AnimationController {
    private Animation[] animations;
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
        animations[playingAnimation].update();
    }

    public void playAnimation(String tag){
        playingAnimation = animationAccessor.get(tag);
        if(playingAnimation != animationAccessor.get(tag)){
            getPlayingAnimation().reset();
        }
    }

    public Animation getPlayingAnimation() {
        return animations[playingAnimation];
    }
}

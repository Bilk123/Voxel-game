package Frame;

import backend.Project;

import java.util.HashMap;

/**
 * Created by Blake on 8/3/2016.
 */
public class ModelManager {
    private static HashMap<String, Project> models;

    static {
        models = new HashMap<>();
        models.put("Zombie map", Project.loadProject("data\\levels\\Zombie Map 2"));
        models.put("bullet", Project.loadProject("data\\models\\bullet"));
        models.put("idlePlayer", Project.loadProject("data\\models\\idlePlayer"));
        models.put("playerWalk1", Project.loadProject("data\\models\\playerWalk1"));
        models.put("playerWalk2", Project.loadProject("data\\models\\playerWalk2"));
        models.put("zombieWalk1", Project.loadProject("data\\models\\zombieWalk1"));
        models.put("zombieWalk2", Project.loadProject("data\\models\\zombieWalk2"));
        models.put("zombieWalk3", Project.loadProject("data\\models\\zombieWalk3"));
    }

    public static Project getModel(String tag) {
        return models.get(tag);
    }
}

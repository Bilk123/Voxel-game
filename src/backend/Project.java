package backend;

import Frame.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Project implements Serializable {
    private int[][][] cubeData;
    private int side, height;
    private static final long serialVersionUID = -5638513059997628790L;

    public Project(int[][][] cubes, int side, int height) {

        cubeData = new int[height - 1][side - 1][side - 1];
        for (int x = 0; x < side - 1; x++) {
            for (int y = 0; y < side - 1; y++) {
                for (int z = 0; z < height - 1; z++) {
                    if (cubes[z][x][y] != 0) {
                        cubeData[z][x][y] = cubes[z][x][y];
                    } else {
                        cubeData[z][x][y] = -1;
                    }
                }
            }
        }
        this.side = side;
        this.height = height;
    }

    public static Project loadProject(String fileName) {
        Project env = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Main.getResource(fileName + ".vem"))) {
            env = (Project) (objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (env == null) {
            System.out.println("Aw shit buddy");
        }

        return env;
    }

    public int[][][] getCubeData() {
        return cubeData;
    }

    public int getSide() {
        return side;
    }

    public int getCanvasHeight() {
        return height;
    }

    public void display() {
        for (int z = 0; z < height - 1; z++) {
            for (int y = 0; y < side - 1; y++) {
                for (int x = 0; x < side - 1; x++) {
                    System.out.print((cubeData[z][x][y])+"\t");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

}

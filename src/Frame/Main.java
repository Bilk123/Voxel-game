package Frame;


import java.awt.*;
import java.io.*;

public class Main {

    public static Robot programRobot;
    public static final int tickRate = 10;
    private static EditorScreen editor;
    public static String appPath;

    public static void main(String[] args) throws InterruptedException, IOException {
        appPath = System.getProperty("user.dir");
        editor = new EditorScreen();
        try {
            programRobot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //noinspection InfiniteLoopStatement
        while (true) {
            editor.repaint(tickRate);
        }
    }

    public static InputStream getResource(String fileName) {
        //noinspection EqualsBetweenInconvertibleTypes
        if (!ClassLoader.getSystemResourceAsStream(fileName).equals("null")) {
            System.out.println("resource loaded: " + fileName);
        } else {
            System.out.println("resource: " + fileName + " could not be found");
        }
        return ClassLoader.getSystemResourceAsStream(fileName);
    }



}

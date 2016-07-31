package Frame;

import guiTools.GuiComponent;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;


public class ComponentManager extends JComponent {
    @NotNull
    private final ArrayList<GuiComponent> guiComponents;
    private Level lvl;
    public ComponentManager() {
        guiComponents = new ArrayList<>();
        lvl = new Level("data\\levels\\house");
        addComponent(lvl);
    }

    private void addComponent(GuiComponent component) {
        guiComponents.add(component);
    }

    private void update() {
        guiComponents.forEach(GuiComponent::updateAll);

    }

    public void hover(MouseEvent e) {
        guiComponents.stream().filter(guiComponent -> !guiComponent.getDisabled()).forEach(guiComponent -> guiComponent.mouseMove(e));
    }

    public void drag(MouseEvent e) {
        guiComponents.stream().filter(guiComponent -> !guiComponent.getDisabled()).forEach(guiComponent -> guiComponent.mouseDrag(e));
    }

    public void keyPress(KeyEvent e) {
        guiComponents.stream().filter(guiComponent -> !guiComponent.getDisabled()).forEach(guiComponent -> guiComponent.keyPressedSC(e));
    }

    public void mouseWheel(MouseWheelEvent e) {
        guiComponents.stream().filter(guiComponent -> !guiComponent.getDisabled()).forEach(guiComponent -> guiComponent.mouseWheel(e));
    }

    public void mousePress(MouseEvent e) {
        guiComponents.stream().filter(guiComponent -> !guiComponent.getDisabled()).forEach(guiComponent -> guiComponent.mousePressSC(e));
    }

    public void mouseRelease(MouseEvent e) {
        guiComponents.stream().filter(guiComponent -> !guiComponent.getDisabled()).forEach(guiComponent -> guiComponent.mouseReleaseSC(e));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        update();
        Graphics2D g2d = (Graphics2D) g;
        for (GuiComponent guiComponent : guiComponents) {
            guiComponent.paintAll(g2d);
        }
    }
}

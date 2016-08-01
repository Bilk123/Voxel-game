package guiTools;

import org.jetbrains.annotations.NotNull;
import util.PU;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public abstract class GuiComponent {

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    private double shadowSize;
    @NotNull
    protected final Rectangle bounds;
    protected Color bgColor;
    @NotNull
    protected final ArrayList<GuiComponent> subComponents;
    private double p = 0.98;
    private final boolean drawBackground;
    private boolean disabled = false;

    protected GuiComponent(double x, double y, double width, double height) {

        drawBackground = false;
        shadowSize = 0;
        bounds = new Rectangle((int) x, (int) y, (int) width, (int) height);
        setDimensions(width, height);
        setLocation(x, y);
        subComponents = new ArrayList<>();
    }

    private void paintBackground(@NotNull Graphics2D g2d) {
        if (drawBackground) {
            PU.castShadow(g2d, bounds, (int) shadowSize, bgColor);
        }
    }



    private void paintSubComponents(@NotNull Graphics2D g2d) {
        for (GuiComponent subComponent : subComponents) {
                subComponent.paintAll(g2d);
        }
    }

    public void paintAll(@NotNull Graphics2D g2d) {
            paintBackground(g2d);
            paintGuiComponent(g2d);
            paintSubComponents(g2d);
    }

    void setShadowSize(double shadowSize) {
        this.shadowSize = shadowSize;
    }

    public void mouseMove(MouseEvent e) {
        hover(e);
        subComponents.stream().filter(subComponent -> !subComponent.getDisabled()).forEach(subComponent -> subComponent.hover(e));
    }


    public void mouseDrag(MouseEvent e) {
        drag(e);
        subComponents.stream().filter(subComponent -> !subComponent.getDisabled()).forEach(subComponent -> subComponent.drag(e));
    }

    public void mouseWheel(MouseWheelEvent e) {
        scroll(e);
        subComponents.stream().filter(subComponent -> !subComponent.getDisabled()).forEach(subComponent -> subComponent.scroll(e));
    }

    public void keyPressedSC(KeyEvent e) {
        keyPress(e);
        subComponents.stream().filter(subComponent -> !subComponent.getDisabled()).forEach(subComponent -> subComponent.keyPress(e));
    }

    public void keyReleasedSC(KeyEvent e) {
        keyReleased(e);
        subComponents.stream().filter(subComponent -> !subComponent.getDisabled()).forEach(subComponent -> subComponent.keyReleased(e));
    }

    public void mousePressSC(MouseEvent e) {
        mousePress(e);
        subComponents.stream().filter(subComponent -> !subComponent.getDisabled()).forEach(subComponent -> subComponent.mousePress(e));
    }

    public void mouseReleaseSC(MouseEvent e) {
        mouseRelease(e);
        subComponents.stream().filter(subComponent -> !subComponent.getDisabled()).forEach(subComponent -> subComponent.mouseRelease(e));
    }

    public void updateAll() {
        update();
        subComponents.forEach(GuiComponent::update);
    }

    protected void add(GuiComponent subComponent) {
        subComponents.add(subComponent);
    }

    private void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
        bounds.setLocation((int) x, (int) y);

    }

    private void setDimensions(double width, double height) {
        this.width = width;
        this.height = height;

        bounds.setSize((int) width, (int) height);
    }

    public void setBounds(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds.setBounds((int) x, (int) y, (int) width, (int) height);
    }

    public void setBounds(@NotNull Rectangle newBounds) {
        x = newBounds.getX();
        y = newBounds.getY();
        width = newBounds.getWidth();
        height = newBounds.getHeight();
        bounds.setBounds(newBounds);
    }

    public double getY() {
        return y;
    }

    @SuppressWarnings("unused")
    public double getX() {
        return x;
    }

    @SuppressWarnings("unused")
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @NotNull
    @Override
    public String toString() {
        return "---------------------------\nx: " + x + "\ny: " + y + "\nwidth: " + width + "\nheight: " + height + "\n# of subComponents: " + subComponents.size() + " \n---------------------------";
    }

    protected abstract void paintGuiComponent(Graphics2D g2d);

    protected abstract void update();

    protected abstract void hover(MouseEvent e);

    protected abstract void drag(MouseEvent e);

    protected abstract void scroll(MouseWheelEvent mwe);

    protected abstract void keyPress(KeyEvent e);

    protected abstract void keyReleased(KeyEvent e);

    protected abstract void mousePress(MouseEvent e);

    protected abstract void mouseRelease(MouseEvent e);

    public void setP(double p) {
        this.p = p;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public @NotNull Rectangle getBounds() {
        return bounds;
    }

    public void hide() {
        setBounds(0, 0, 0, 0);
    }
}

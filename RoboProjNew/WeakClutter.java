package RoboProjNew;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public final class WeakClutter extends Clutter {


    public WeakClutter() {
        xPosition = ThreadLocalRandom.current().nextInt(800);
        yPosition = ThreadLocalRandom.current().nextInt(600);
        radius = ThreadLocalRandom.current().nextInt(10,30);
        strength = ThreadLocalRandom.current().nextInt(5,15);
        isAlive = true;


    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(xPosition,yPosition, radius, radius);
        g.setColor(Color.RED);
        FontMetrics fontMetrics = g.getFontMetrics();
        String num = Integer.toString(strength);
        g.drawString(num, (int) ((double)(xPosition) - (((double) fontMetrics.stringWidth(num)) / 2.0d))+radius/2, yPosition + 5+radius/2);

    }
    public void step(double d) {
        if (this.strength < 1) {
            this.isAlive = false;
        }
    }
}
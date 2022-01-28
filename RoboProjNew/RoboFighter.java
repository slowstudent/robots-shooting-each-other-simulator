package RoboProjNew;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RoboFighter   {

    String name;
    int ammunition;
    int kills;
    int damage;
    Color color;
    boolean isAlive;
    int xPosition;
    int yPosition;
    Simulation s;
    int radius = 40;
    String lastProjectileFrom;


    public RoboFighter(String name, Simulation s) {
        this.s = s;
        color = Color.getHSBColor((float)Math.random(), 0.8f, 0.8f);
        isAlive = true;
        this.name = name;
        ammunition = 200;
        kills = 0;
        damage = 0;
        xPosition = ThreadLocalRandom.current().nextInt(100, 700);;
        yPosition = ThreadLocalRandom.current().nextInt(100, 500);;
    }

    public void fire() {
        int d = ThreadLocalRandom.current().nextInt(-5, 5);
        int d2 = ThreadLocalRandom.current().nextInt(-5, 5);
        if(d==0 && d2 ==0){
            d =1;
        }

        int i2 = this.ammunition;
        this.ammunition = i2 - 1;
        if (i2 > 0 && isAlive) {
            this.s.projectilesList.add(new Projectile(this.xPosition+radius/2, this.yPosition+radius/2, d, d2, 5, this));
        }
    }

    public void move() {
        if(this.damage>=200){
            this.isAlive = false;
        }
        if(this.isAlive){
            xPosition += ThreadLocalRandom.current().nextInt(-2,3);
            yPosition += ThreadLocalRandom.current().nextInt(-2,3);
            if(xPosition>=s.zoneWidth-radius){ xPosition = xPosition - (xPosition - s.zoneWidth + radius);}
            if(xPosition<=0){ xPosition = 0;}

            if(yPosition>=s.zoneHeight-radius){yPosition = yPosition - (yPosition - s.zoneHeight+radius);}
            if(yPosition<=0){ yPosition = 0;}

        }
    }


    public void draw(Graphics g){
        g.setColor(color);
        g.drawOval(xPosition, yPosition, radius, radius);
        g.drawOval(xPosition+1, yPosition+1, radius-2, radius-2);
        g.drawOval(xPosition+1, yPosition+1, radius-1, radius-1);

        String str = this.name;
        g.setColor(Color.BLACK);
        g.drawString(str, xPosition, yPosition);

        String num = Integer.toString(this.ammunition);
        g.setColor(Color.BLUE);
        g.drawString(num, xPosition+12, yPosition+12);

        String num2 = Integer.toString(this.damage);
        g.setColor(Color.RED);
        g.drawString(num2, xPosition+12, yPosition+24);

        String num3 = Integer.toString(this.kills);
        g.setColor(Color.DARK_GRAY);
        g.drawString(num3, xPosition+12, yPosition+36);
    }

}

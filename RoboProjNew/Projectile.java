package RoboProjNew;

import java.awt.*;

public class Projectile {
    int xPosition;
    int yPosition;
    double stepDeltaX;
    double stepDeltaY;
    int damagePower;
    boolean isAlive;
    private Color color;
    RoboFighter roboFighter;

    public Projectile(int xPosition, int yPosition, int xDirection, int yDirection, int damagePower, RoboFighter roboFighter) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.stepDeltaX = xDirection;
        this.stepDeltaY = yDirection;
        this.damagePower = damagePower;
        this.color = roboFighter.color;
        this.isAlive = true;
        this.roboFighter = roboFighter;

    }
    public void move() {
        this.xPosition += stepDeltaX;
        this.yPosition += stepDeltaY;
        if(this.xPosition<=0 || yPosition<=0 || xPosition>=800 || yPosition>=600){
            isAlive = false;
        } else{
            for(RoboFighter roboFighter : roboFighter.s.listOfRobots){
                if((roboFighter.xPosition+roboFighter.radius)>xPosition && (roboFighter.xPosition-roboFighter.radius)<xPosition &&
                   (roboFighter.yPosition+roboFighter.radius)>yPosition && (roboFighter.yPosition-roboFighter.radius)<yPosition &&
                    !(this.roboFighter==roboFighter)){
                    roboFighter.lastProjectileFrom = this.roboFighter.name;
                    roboFighter.damage+=this.damagePower;
                    roboFighter.s.projectilesList.remove(this);
                    if(roboFighter.damage>=200){
                        roboFighter.s.listOfRobots.remove(roboFighter);
                        roboFighter.s.killsList.add(roboFighter.lastProjectileFrom + " kills " + roboFighter.name);
                        this.roboFighter.kills++;

                    }
                }
            }
            for(Clutter clutter : roboFighter.s.listOfClutters){
                if((clutter.xPosition+clutter.radius)>xPosition && (clutter.xPosition-clutter.radius)<xPosition &&
                   (clutter.yPosition+clutter.radius)>yPosition && (clutter.yPosition-clutter.radius)<yPosition)
                {
                    clutter.strength-=this.damagePower;
                    roboFighter.s.projectilesList.remove(this);
                    if(clutter.strength<0){
                        roboFighter.s.listOfClutters.remove(clutter);
                    }
                }
            }
        }


    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(xPosition, yPosition, 7, 7);
    }

}

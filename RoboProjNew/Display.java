package RoboProjNew;

import java.awt.*;
import javax.swing.JPanel;


public final class Display extends JPanel implements Runnable{


    private final Simulation simulation;
    Thread gameThread;
    private Image image;
    private Graphics graphics;
    RoboFighter winningRobot;
    int winningDeterminer ;


    public Display(Simulation jVar) {
        this.simulation = jVar;
        setBackground(Color.YELLOW);
        this.setBackground(Color.WHITE);
        gameThread = new Thread(this);
        gameThread.start();
    }


    public Dimension getPreferredSize() {
        return new Dimension(this.simulation.zoneWidth, this.simulation.zoneHeight);
    }

    public void paint(Graphics g) {
        image = createImage(800, 600);
        graphics = image.getGraphics();
        g.drawImage(image, 0, 0, this);


        try{
            for(Clutter c : this.simulation.listOfClutters){
                if(c.strength<=0){
                    c.isAlive = false;
                }
                if(c.isAlive){
                    c.draw(g);
                }
            }
        } catch (Exception ignored) {}

        winningDeterminer = 200;
        for(RoboFighter r : this.simulation.listOfRobots){
            if(winningDeterminer>=r.damage){
                winningDeterminer = r.damage;
                winningRobot = r;
            }
            if(r.isAlive){
                r.draw(g);
            }

        }
        try {
            for(Projectile projectile : this.simulation.projectilesList){
                if(projectile.isAlive && this.simulation.projectilesList.size()>0){
                    projectile.draw(g);
                }else{
                    this.simulation.projectilesList.remove(projectile);
                }
            }
        } catch (Exception ignored) {}


        g.setColor(Color.BLACK);
        g.drawLine(0, 70, 160, 70);
        g.drawLine(160, 0, 160, 250);
        g.drawString("Winning  : " + winningRobot.name , 10, 20);
        g.drawString("Damage : " + winningRobot.damage, 10, 40);
        g.drawString("Kills        : " + winningRobot.kills , 10, 60);

        g.drawLine(0, 250, 160, 250);
        for (int i2 = 0; i2 < this.simulation.killsList.size(); i2++) {
            g.drawString( this.simulation.killsList.get(i2), 10, 93 + (i2 * 20));

        }
    }
    public void run() {
//game loop
        long lastTime = System.nanoTime();
        long tempTime = System.nanoTime() + 1000000000/4 ;
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long time = System.nanoTime();
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {

                if(time > tempTime){
                    for(RoboFighter r : this.simulation.listOfRobots){
                        r.move();
                        if(r.ammunition>0){
                            r.fire();
                        }

                        tempTime+= 1000000000/20;//should be 1000000000/15
                    }
                }

                for(RoboFighter r : this.simulation.listOfRobots){
                    r.move();
                }


                try{
                    if(this.simulation.projectilesList.size()>0){
                    for(Projectile projectile : this.simulation.projectilesList){
                        projectile.move();
                    }}
                } catch (Exception ignored) {}

                repaint();
                delta--;
            }
        }
    }
}
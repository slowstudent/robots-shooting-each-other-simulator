package RoboProjNew;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public int zoneWidth = 800;
    public int zoneHeight = 600;

    List<RoboFighter> listOfRobots = new ArrayList<RoboFighter>();
    List<Clutter> listOfClutters = new ArrayList<Clutter>();
    List<Projectile> projectilesList = new ArrayList<Projectile>();
    List<String> killsList = new ArrayList<String>();

    public Simulation() {
        for(int i = 0; i<30; i++){
            listOfClutters.add(new SturdyClutter());
            listOfClutters.add(new WeakClutter());
        }
        listOfRobots.add( new RoboFighter("Alemana", this));
        listOfRobots.add( new RoboFighter("Bellatrix", this));
        listOfRobots.add( new RoboFighter("Calhoun", this));
        listOfRobots.add( new RoboFighter("Diomedes", this));
        listOfRobots.add( new RoboFighter("Eferhilda", this));
        listOfRobots.add( new RoboFighter("Finnegan", this));
        listOfRobots.add( new RoboFighter("Gualterio", this));
        listOfRobots.add( new RoboFighter("Harbin", this));
    }
}

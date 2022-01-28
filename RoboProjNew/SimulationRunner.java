package RoboProjNew;

import javax.swing.*;
import java.awt.*;
public class SimulationRunner {

    public static void main(String[] strArr) {
        Simulation s = new Simulation();
        Display d = new Display(s);
        JFrame jFrame = new JFrame("RoboFighters - War Zone");
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setBackground(Color.white);
        jFrame.add(d);
        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}

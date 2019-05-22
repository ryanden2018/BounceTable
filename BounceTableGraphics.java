import javax.swing.*;
import java.awt.*;
import java.util.*;

class BounceTableGraphics extends JComponent {
  int WIDTH = 700;
  int HEIGHT = WIDTH;
  double MULTIPLIER = 1.01;
  int SMALL_DISK_RADIUS = 8;
  double DT = 5.0;
  BounceTableData btd;

  Vector<Double> historyX;
  Vector<Double> historyY;

  BounceTableGraphics() {
    setPreferredSize(new Dimension(WIDTH,HEIGHT));
    btd = new BounceTableData(WIDTH,MULTIPLIER,SMALL_DISK_RADIUS,DT);
    historyX = new Vector<Double>();
    historyY = new Vector<Double>();
  }

  @Override
  public void paintComponent(Graphics g) {
    btd.update();

    historyX.add(btd.puck.getCenterX());
    historyY.add(btd.puck.getCenterY());

    super.paintComponent(g);

    g.fillOval((int) (btd.bigDiskNW.getCenterX()-btd.bigDiskNW.getRadius()),
      (int) (btd.bigDiskNW.getCenterY()-btd.bigDiskNW.getRadius()),
      (int) (2.0*btd.bigDiskNW.getRadius()), (int) (2.0*btd.bigDiskNW.getRadius()) );
    g.fillOval((int) (btd.bigDiskNE.getCenterX()-btd.bigDiskNE.getRadius()),
      (int) (btd.bigDiskNE.getCenterY()-btd.bigDiskNE.getRadius()),
      (int) (2.0*btd.bigDiskNE.getRadius()), (int) (2.0*btd.bigDiskNE.getRadius()) );
    g.fillOval((int) (btd.bigDiskSW.getCenterX()-btd.bigDiskSW.getRadius()),
      (int) (btd.bigDiskSW.getCenterY()-btd.bigDiskSW.getRadius()),
      (int) (2.0*btd.bigDiskSW.getRadius()), (int) (2.0*btd.bigDiskSW.getRadius()) );
    g.fillOval((int) (btd.bigDiskSE.getCenterX()-btd.bigDiskSE.getRadius()),
      (int) (btd.bigDiskSE.getCenterY()-btd.bigDiskSE.getRadius()),
      (int) (2.0*btd.bigDiskSE.getRadius()), (int) (2.0*btd.bigDiskSE.getRadius()) );
  
    g.fillOval((int) (btd.puck.getCenterX()-btd.puck.getRadius()), 
      (int) (btd.puck.getCenterY()-btd.puck.getRadius()),
      (int) (2.0*btd.puck.getRadius()), (int) (2.0*btd.puck.getRadius()) );

    for(int i = 0; i < historyX.size(); i++) {
      g.fillOval((int) (double) historyX.elementAt(i), (int) (double) historyY.elementAt(i), 3, 3);
    }
  }
}

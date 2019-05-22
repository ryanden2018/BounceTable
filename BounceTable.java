import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BounceTable implements ActionListener {
  JFrame jfrm;
  BounceTableGraphics btg;

  BounceTable() {
    jfrm = new JFrame("BounceTable");
    btg = new BounceTableGraphics();

    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jfrm.add(btg,BorderLayout.CENTER);
    jfrm.pack();
    jfrm.setResizable(false);
    jfrm.setVisible(true);

    int delay = 100;
    Timer timer = new Timer(delay,this);
    while(true) {
      timer.start();
    }
  }

  public void actionPerformed(ActionEvent e) {
    btg.repaint();
  }

  public static void main(String args[]) {
    new BounceTable();
  }
}

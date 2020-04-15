

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Kostlivec_kopi extends JPanel {
	 private BufferedImage imag;
	 public int hp;
	 private int dmg;
	 static JProgressBar bar  = new JProgressBar();
	 
	 public Kostlivec_kopi(int hp, int dmg) {
		 try {
				imag = ImageIO.read(new File("C:\\Users\\Mira\\eclipse-workspace\\PetrikMartinekVetrovecHra\\src\\skl3.png"));
			} catch (IOException e) {

				e.printStackTrace();
			}
		 this.hp = hp;
		 this.dmg=dmg;
		 
		 bar.setMinimum(0);
		 bar.setMaximum(hp);
		 bar.setForeground(Color.RED);
		 bar.setBounds(0, 0, 170, 20);
		 bar.setValue(hp);
		 add(bar);
		 
	}
	 
	 
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(imag, 0, 0, this);
	}
}

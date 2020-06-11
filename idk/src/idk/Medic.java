package idk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Medic extends JPanel {
	 private BufferedImage imag;
	 public int hp;
	 public int dmg;
	 public int spd;
	 public int arm;
	 public boolean alive = true;
	 public boolean turn= false;
	 public String name = "Lecitel";
	 public JLabel t = new JLabel();

	 
	 static JProgressBar bar  = new JProgressBar();
	 
	 public Medic(int hp, int dmg, int spd, int arm) {
		 try {
				imag = ImageIO.read(new File("src\\idk\\healer.png"));
			} catch (IOException e) {

				e.printStackTrace();
			}
		
		 
		 this.hp = hp;
		 this.setDmg(dmg);
		 this.spd = spd;
		 this.arm = arm;
		 
		 bar.setMinimum(0);
		 bar.setMaximum(hp);
		 bar.setForeground(Color.RED);
		 
		 bar.setValue(hp);

		 

	}
	 
	 
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(imag, 0, 0, 150, 200, null);
	}



	public int getSpd() {
		return spd;
	}
	public int getDmg() {
		return dmg;
	}



	public void setDmg(int dmg) {
		this.dmg = dmg;
	}




}

package idk;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Kostlivec_ban extends JPanel {
	 private BufferedImage imag;
	 public int hp;
	 private int dmg;
	 private int spd;
	 private int arm;
	 public boolean alive = true;
	 public JLabel t = new JLabel();

	 
	 static JProgressBar bar  = new JProgressBar();
	 
	 public Kostlivec_ban(int hp, int dmg, int spd, int arm) {
		 try {
				imag = ImageIO.read(new File("src\\idk\\skeli praporcik.png"));
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
		 bar.setBounds(0, 0, 170, 20);
		 bar.setValue(hp);
		 add(bar);
		 

	}
	 
	 
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(imag, 0, 0,  170, 200, null);
	}



	public int getDmg() {
		return dmg;
	}



	public void setDmg(int dmg) {
		this.dmg = dmg;
	}




}

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

public class Archer extends JPanel /*implements MouseListener*/ {
	 private BufferedImage imag;
	 public int hp;
	 public int dmg;
	 public int spd;
	 public int arm;
	 public boolean alive = true;
	 public boolean turn= false;
	 public String name = "Lukostrelec";
	 public JLabel t = new JLabel();

	 
	 static JProgressBar bar  = new JProgressBar();
	 
	 public Archer(int hp, int dmg, int spd, int arm) {
		 try {
				imag = ImageIO.read(new File("src\\idk\\archer.png"));
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
		 
		// addMouseListener(this);
		 t.setBounds(612, 410, 50, 100);
		
		 

	}
	 
	 
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(imag, 0, 0, 200, 200, null);
		// g2.drawImage(imag, 0, 0, 200, 200, null); ------------------------ tímhle dìlej re scale
		
	}



	public int getDmg() {
		return dmg;
	}



	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	public int getSpd() {
		return spd;
	}
	
	/*@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {//int hp, int dmg, int spd, int arm
		t.setText("<html>"+name +"<br/>zivoty " + hp + "<br/>poskozeni " + dmg + "<br/>rychlost " + spd + "<br/>zbroj <html>" + arm);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		t.setText("");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}*/




}

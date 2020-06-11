package idk;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Inventar extends JPanel{
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.scale(0.5, 0.5);
		g2.fillRect(0, 0, 1555, 220);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(100, 20, 100, 100);
		g2.fillRect(250, 20, 100, 100);
		g2.fillRect(1220, 0, 130, 210);
		g2.setColor(Color.YELLOW);
		g2.fillRect(90, 20, 10, 100);
		g2.fillRect(200, 20, 10, 100);
		g2.fillRect(90, 10, 120, 10);
		g2.fillRect(90, 120, 120, 10);
		g2.fillRect(240, 20, 10, 100);
		g2.fillRect(350, 20, 10, 100);
		g2.fillRect(240, 10, 120, 10);
		g2.fillRect(240, 120, 120, 10);
		
		
		g2.fillRect(210+1000, 0, 10, 220);
		g2.fillRect(350+1000, 0, 10, 220);
		g2.fillRect(210+1000, 0, 150, 10);
		g2.fillRect(210+1000, 210, 150, 10);
	}

}

class Slot1 extends JPanel{
	private BufferedImage image;
	public Slot1() {

		try {
			image = ImageIO.read(new File("src\\idk\\mec.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, 50, 50, null);
	}
}

class Slot2 extends JPanel{
	private BufferedImage image;
	public Slot2() {

		try {
			image = ImageIO.read(new File("src\\idk\\light.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, 50, 50, null);
	}
}
class Slot1A extends JPanel{
	private BufferedImage image;
	public Slot1A() {

		try {
			image = ImageIO.read(new File("src\\idk\\Bow.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, 50, 50, null);
	}
}


class Slot2A extends JPanel{
	private BufferedImage image;
	public Slot2A() {

		try {
			image = ImageIO.read(new File("src\\idk\\Poison.jpg"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, 50, 50, null);
	}
}


class Slot1M extends JPanel{
	private BufferedImage image;
	public Slot1M() {

		try {
			image = ImageIO.read(new File("src\\idk\\heal.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0,  50, 50, null);
	}
}


class Slot2M extends JPanel{
	private BufferedImage image;
	public Slot2M() {

		try {
			image = ImageIO.read(new File("src\\idk\\heal2.jpg"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0,  50, 50, null);
	}
}
class Gold extends JPanel{
	private BufferedImage image;
	private int penize = 0;
	 public JLabel count = new JLabel();
	public Gold(int penize) {

		try {
			image = ImageIO.read(new File("src\\idk\\gold.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setPenize(penize);
		
		count.setBounds(545, 460, 50, 50);
		count.setFont(new Font("Serif", Font.BOLD, 20));
		count.setForeground(Color.YELLOW);
		count.setText(String.valueOf(penize));
	
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, 50, 50, null);
	}

	public int getPenize() {
		return penize;
	}

	public void setPenize(int penize) {
		this.penize = penize;
	}
}


import java.awt.Color;
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
		g2.fillRect(0, 0, 1555, 220);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(100, 20, 100, 100);
		g2.setColor(Color.YELLOW);
		g2.fillRect(90, 20, 10, 100);
		g2.fillRect(200, 20, 10, 100);
		g2.fillRect(90, 10, 120, 10);
		g2.fillRect(90, 120, 120, 10);
		g2.fillRect(240, 20, 10, 100);
		g2.fillRect(350, 20, 10, 100);
		g2.fillRect(240, 10, 120, 10);
		g2.fillRect(240, 120, 120, 10);
	}

}

class Slot1 extends JPanel{
	private BufferedImage image;
	public Slot1() {

		try {
			image = ImageIO.read(new File("C:\\Users\\Mira\\eclipse-workspace\\PetrikMartinekVetrovecHra\\src\\mec.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, this);
	}
}

class Slot2 extends JPanel{
	private BufferedImage image;
	public Slot2() {

		try {
			image = ImageIO.read(new File("C:\\Users\\Mira\\eclipse-workspace\\PetrikMartinekVetrovecHra\\src\\light.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, this);
	}
}
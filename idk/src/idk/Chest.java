package idk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Chest extends JPanel {
	private BufferedImage imag;

	private Gold gold;



	
	public Chest() {
		 try {
				imag = ImageIO.read(new File("src\\idk\\chest.png"));
			} catch (IOException e) {

				e.printStackTrace();
			}
		 
		 
		 gold = new Gold(0);
		 
		

	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(imag, 0, 0, 100, 100, null);
		
	}



}



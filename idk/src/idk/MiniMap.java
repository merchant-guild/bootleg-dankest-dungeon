package idk;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class MiniMap extends JPanel {
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.scale(0.5, 0.5);
		g2.fillRect(0, 0, 350, 350);
		g2.setColor(Color.WHITE);
		g2.fillRect(60, 180, 40, 40);
		g2.fillRect(240 , 180, 40, 40);
		g2.fillRect(100, 190, 20, 20);
		g2.fillRect(120, 190, 20, 20);
		g2.fillRect(140, 190, 20, 20);
		g2.fillRect(160, 190, 20, 20);
		g2.fillRect(180, 190, 20, 20);
		g2.fillRect(200, 190, 20, 20);
		g2.fillRect(220, 190, 20, 20);
		g2.setColor(Color.BLACK);
		g2.drawRect(60, 180, 40, 40);
		g2.drawRect(240, 180, 40, 40);
		g2.drawRect(100, 190, 20, 20);
		g2.drawRect(120, 190, 20, 20);
		g2.drawRect(140, 190, 20, 20);
		g2.drawRect(160, 190, 20, 20);
		g2.drawRect(180, 190, 20, 20);
		g2.drawRect(200, 190, 20, 20);
		g2.drawRect(220, 190, 20, 20);
		
	
		

	}

}

class Pozice extends JPanel {
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.GREEN);
		g2.fillRect(0, 0, 20, 20);
		
	}
}


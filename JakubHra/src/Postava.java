import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Postava extends JPanel{

	protected int zivoty;
	
}

class Crusader extends Postava{
	
	Crusader(int zivoty){
		this.zivoty = zivoty;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRect(80, 180, 50 , 40 );//krk
		g2.setColor(Color.WHITE);
		g2.fillRect(70, 200, 80, 150);//trup
		g2.setColor(Color.GRAY);
		g2.fillOval(50,100, 100, 100);//hlavan
		g2.fillPolygon(new int[] {20,69,100,70 }, new int[] {200,110,100,200 }, 4);//helma
		g2.fillPolygon(new int[] {110,200,220,130}, new int[] {230,270,250,210 }, 4);//ruka
		g2.setColor(new Color(139,69,19));//brown
		g2.fillRect(202, 250, 10, 30);//mec
		g2.setColor(Color.GRAY);
		g2.fillRect(190,240,30, 30);//dlan
		g2.drawRect(70, 200, 80, 150);
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(92, 155, 60, 30);//hledi
		g2.fillOval(85, 145, 20, 20);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(118, 133, 30, 10);
		g2.fillRect(100, 160, 10, 20);
		g2.fillRect(115, 160, 10, 20);
		g2.fillRect(130, 160, 10, 20);
		g2.setColor(Color.GRAY);
		g2.fillOval(80, 195, 55, 55);//rameno
		g2.fillRect(70, 350, 38, 130);//nohy
		g2.fillRect(115, 350, 38, 130);
		g2.fillRect(108, 350, 20, 20);
		g2.setColor(new Color(139,69,19));//brown
		g2.fillOval(197, 270, 20, 20);//mec
		g2.fillRect(185, 230, 40, 10);
		g2.setColor(new Color(135,206,250));//light blue
		g2.fillRect(195, 60, 20, 170);
		g2.fillPolygon(new int[] {195,215,205 }, new int[] {60,60,40}, 3);

	}
}
class Kostlivec extends Postava{
	Kostlivec(int zivoty){
		this.zivoty = zivoty;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillOval(120-50,100, 100, 100);//hlava
		g2.fillRect(160-50, 180, 20, 150);//pater
		g2.fillRect(120-50, 230, 100, 10);//žebra
		g2.fillRect(120-50, 260, 100, 10);
		g2.fillRect(120-50, 290, 100, 10);
		g2.fillRect(190-50, 390, 20, 80);//nohy
		g2.fillRect(140-50, 390, 20, 80);
		g2.setColor(new Color(139,69,19));//Brown
		g2.fillRect(130-50, 310, 40, 80);//kalhoty
		g2.fillRect(180-50, 310, 40, 80);
		g2.fillRect(160-50, 310, 20, 30);
		g2.setColor(Color.BLACK);
		g2.fillOval(140-50, 120, 20, 20);//oko
		g2.fillRect(123-50, 160, 10,10);//pusa
		g2.fillRect(143-50, 160, 10,10);
		g2.fillRect(133-50, 160, 10,10);
		g2.fillRect(153-50, 160, 10,10);
		g2.setColor(Color.WHITE);
		g2.fillRect(122-50, 155, 10,10);//ruka
		g2.fillRect(143-50, 155, 10,10);
		g2.fillRect(60-50, 210, 120, 15);
		g2.setColor(new Color(139,69,19));//brown
		g2.fillRect(65-50, 40, 15, 400);//kopi
		g2.setColor(Color.WHITE);
		g2.fillRect(60-50, 200, 30, 30);//dlan
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillPolygon(new int[] {55-50, 90-50, 72-50}, new int[] {60, 60, 20}, 3);//hrot kopi
		g2.fillRect(65-50, 60, 15, 25);
	
		
	}
}
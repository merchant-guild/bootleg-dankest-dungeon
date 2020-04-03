import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * tøída hra by v sobì nemìla mít combat, combat a s ním spojené vykreslování by mìla bıt vlastní tøída volána støídou hra kdy v dungeonu narazíš na nepøátele
 * (pokud tam ten dungeon bude)
 * všechni hrdinové a nepøátelé dìdí z postavy
 * postavy se inicializují na zaèátku boje a mají svoje pozice podle kterıch se nìjak urèuje na koho míøí útoky, ještì nevím jak
 * u jsem dnes línı a posílám ti tento polotovar
 */
public class Hra extends JFrame implements MouseListener, Runnable{
	 static ImageIcon utok = new ImageIcon("C:\\moje\\sword.png");
	  static JLabel mec = new JLabel(utok);

	static  PointerInfo a = MouseInfo.getPointerInfo();
	private   Point mouse = a.getLocation();
	// private int x = (int) b.getX();
	//private  int y = (int) b.getY();
	  
	private Kostlivec1 skeli;
	private Kostlivec2 skeli2;
	private Kostlivec3 skeli3;
	private Kostlivec4 skeli4;
	
	private Crusader crusader;
	private Crusader crusader2;
	private Crusader crusader3;
	private Crusader crusader4;
	
	
	
	private HUD hud;
	
	static boolean myTurn = true;   
	static boolean turnEnemy = false;   
	static boolean selcted = false;
	int zivot = 100;
	int poskozeni;
	
	
	

	
	public static void main(String[] args) {
	new Hra();
	}
	
	public Hra() {
	addMouseListener(this);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	 setVisible(true);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setLayout(null);
	 
	 
	 
	 skeli = new Kostlivec1(zivot);
	 skeli.setBounds(1060, 300, 230, 500);//213
	 add(skeli);
	 
	 skeli2 = new Kostlivec2(zivot);
	 skeli2.setBounds(1280, 300, 230, 500);
	 add(skeli2);
	 
	 skeli3 = new Kostlivec3(zivot);
	 skeli3.setBounds(1500, 300, 230, 500);
	 add(skeli3);
	 
	 skeli4 = new Kostlivec4(zivot);
	 skeli4.setBounds(1700, 300, 230, 500);
	 add(skeli4);
	 
	 crusader = new Crusader(100);
	 crusader.setBounds(720, 300, 230, 500);
	 add(crusader);
	 
	 crusader2 = new Crusader(100);
	 crusader2.setBounds(490, 300, 230, 500);
	 add(crusader2);
	 
	 crusader3 = new Crusader(100);
	 crusader3.setBounds(260, 300, 230, 500);
	 add(crusader3);
	 
	 crusader4 = new Crusader(100);
	 crusader4.setBounds(30, 300, 230, 500);
	 add(crusader4);

	 
	 hud = new HUD();
	 hud.setBounds(0,800,1920,280);
	 add(hud);
	 	
	 mec.setBounds(345, 820, 100, 100);
	 add(mec);
	 
	//skeliUtok();
	
	
	//try
	EventQueue.invokeLater(new Runnable() {

		@Override
		public void run() {
			
			
		}
		
	});
//end of try	

	 
	 }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		/*
		if(myTurn == true) {
			System.out.println("fuck ye");
	

			if(arg0.getX()>mec.getX()+20 & arg0.getY()>mec.getY()+20 & arg0.getX()<(20+mec.getX()+mec.getWidth()) & arg0.getY()<(mec.getY()+mec.getHeight()+20 )) {
			System.out.println("hel je");
			selcted = true;
			
		}else{
		if(arg0.getX()>skeli.getX() & arg0.getY()>skeli.getY() & arg0.getX()<(skeli.getX()+skeli.getWidth()) & arg0.getY()<(skeli.getY()+skeli.getHeight()) & selcted==true) {
			crusUtok();
			}else {
				if(arg0.getX()>skeli2.getX() & arg0.getY()>skeli2.getY() & arg0.getX()<(skeli2.getX()+skeli2.getWidth()) & arg0.getY()<(skeli2.getY()+skeli2.getHeight()) & selcted==true) {
					crusUtok2();
				
				}else {	if(arg0.getX()>skeli3.getX() & arg0.getY()>skeli3.getY() & arg0.getX()<(skeli3.getX()+skeli3.getWidth()) & arg0.getY()<(skeli3.getY()+skeli3.getHeight()) & selcted==true) {
					crusUtok3();
					
				}else {	if(arg0.getX()>skeli4.getX() & arg0.getY()>skeli4.getY() & arg0.getX()<(skeli4.getX()+skeli4.getWidth()) & arg0.getY()<(skeli4.getY()+skeli4.getHeight()) & selcted==true) {
					crusUtok4();
					
				}	
		}
		
		}
		}
		}
		}*/
	}
	
	
		
		
	
	
	


	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		
	}

	@Override
	public void run() {

	}
	/*

	
	public void skeliUtok() {
		if(skeli.barskeli.getValue()>=0) {
		if(crusader.barcrus.getValue()>=0 & turnEnemy==true) {
			
			crusader.barcrus.setValue(crusader.barcrus.getValue()-10);//update helth baru
			 int dead = crusader.barcrus.getValue();
			 turnEnemy= false;
			 
			 if(dead<=0) {
					remove(crusader);
					repaint();
					}else {
						myTurn=true;
					}
			 
			 }
		}else {
		
			myTurn=true;
		}
		
	}
	
	
	public void crusUtok() {

		if(skeli.barskeli.getValue()>=0) {
			skeli.barskeli.setValue(skeli.barskeli.getValue()-25);//update helth baru
			 int dead = skeli.barskeli.getValue();
			 selcted = false;
			 myTurn = false;
			 turnEnemy = true;
			if(dead<=0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				remove(skeli);
				repaint();
				 turnEnemy= false;
				 myTurn=true;
				
			
				}else {
				
				
					skeliUtok();
					
				}
			}
		
	}
	
	public void crusUtok2() {

		if(skeli2.barskeli.getValue()>=0) {
			skeli2.barskeli.setValue(skeli2.barskeli.getValue()-25);//update helth baru
			 int dead = skeli2.barskeli.getValue();
			 selcted = false;
			 myTurn = false;
			 turnEnemy = true;
			if(dead<=0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				remove(skeli2);
				repaint();
				 turnEnemy= false;
				 myTurn=true;
			
				}else {
				
				
					skeliUtok();
					
				}
			}
		
	}
	
	public void crusUtok3() {

		if(skeli3.barskeli.getValue()>=0) {
			skeli3.barskeli.setValue(skeli3.barskeli.getValue()-25);//update helth baru
			 int dead = skeli3.barskeli.getValue();
			 selcted = false;
			 myTurn = false;
			 turnEnemy = true;
			if(dead<=0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				remove(skeli3);
				repaint();
				 turnEnemy= false;
				 myTurn=true;
			
				}else {
				
				
					skeliUtok();
					
				}
			}
		
	}
	
	public void crusUtok4() {

		if(skeli4.barskeli.getValue()>=0) {
			skeli4.barskeli.setValue(skeli4.barskeli.getValue()-25);//update helth baru
			 int dead = skeli4.barskeli.getValue();
			 selcted = false;
			 myTurn = false;
			 turnEnemy = true;
			if(dead<=0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				remove(skeli4);
				repaint();
				 turnEnemy= false;
				 myTurn=true;
			
				}else {
				
				
					skeliUtok();
					
				}
			}
		
	}
	*/
	

}




class Kostlivec1 extends JPanel  {

	private int zivot;
	static JProgressBar barskeli  = new JProgressBar();

	public Kostlivec1(int zivot) {
		barskeli.setMaximum(100);
		barskeli.setMinimum(0);
		barskeli.setBounds(30, 0, 170, 20);
		barskeli.setForeground(Color.RED);
		barskeli.setValue(zivot);
		 add(barskeli);
	

	}
	
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	//	g.drawImage(image, 0, 0, this);
	g2.setColor(Color.WHITE);
		g2.fillOval(120-50,100, 100, 100);//hlava
		g2.fillRect(160-50, 180, 20, 150);//pater
		g2.fillRect(120-50, 230, 100, 10);//ebra
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


	public int getZivot() {
		return zivot;
	}


	public void setZivot(int zivot) {
		this.zivot = zivot;
	}






}

class Kostlivec2 extends JPanel  {
	private int zivot;
	static JProgressBar barskeli  = new JProgressBar();

	public Kostlivec2(int zivot) {
		
		barskeli.setMaximum(100);
		barskeli.setMinimum(0);
		barskeli.setBounds(30, 0, 170, 20);
		barskeli.setForeground(Color.RED);
		barskeli.setValue(zivot);
		 add(barskeli);
	

	}
	
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillOval(120-50,100, 100, 100);//hlava
		g2.fillRect(160-50, 180, 20, 150);//pater
		g2.fillRect(120-50, 230, 100, 10);//ebra
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


	public int getZivot() {
		return zivot;
	}


	public void setZivot(int zivot) {
		this.zivot = zivot;
	}






}

class Kostlivec3 extends JPanel  {
	private int zivot;
	static JProgressBar barskeli  = new JProgressBar();

	public Kostlivec3(int zivot) {
		
		barskeli.setMaximum(100);
		barskeli.setMinimum(0);
		barskeli.setBounds(30, 0, 170, 20);
		barskeli.setForeground(Color.RED);
		barskeli.setValue(zivot);
		 add(barskeli);
	

	}
	
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillOval(120-50,100, 100, 100);//hlava
		g2.fillRect(160-50, 180, 20, 150);//pater
		g2.fillRect(120-50, 230, 100, 10);//ebra
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


	public int getZivot() {
		return zivot;
	}


	public void setZivot(int zivot) {
		this.zivot = zivot;
	}






}

class Kostlivec4 extends JPanel  {
	private int zivot;
	static JProgressBar barskeli  = new JProgressBar();

	public Kostlivec4(int zivot) {
		
		barskeli.setMaximum(100);
		barskeli.setMinimum(0);
		barskeli.setBounds(30, 0, 170, 20);
		barskeli.setForeground(Color.RED);
		barskeli.setValue(zivot);
		 add(barskeli);
	

	}
	
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillOval(120-50,100, 100, 100);//hlava
		g2.fillRect(160-50, 180, 20, 150);//pater
		g2.fillRect(120-50, 230, 100, 10);//ebra
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


	public int getZivot() {
		return zivot;
	}


	public void setZivot(int zivot) {
		this.zivot = zivot;
	}






}


class Crusader1 extends JPanel{

	static JProgressBar barcrus  = new JProgressBar();
	public Crusader1(int dmg) {
		
		barcrus.setBounds(30, 0, 170, 20);
		barcrus.setForeground(Color.RED);
		barcrus.setValue(100);
		 add(barcrus);
		
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




class HUD extends JPanel{

	
	public HUD() {
	

	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRect(0, 0, 1920, 20);//hud.setBounds(0,800,1920,280);
		g2.fillRect(0, 0, 20, 280);
		g2.fillRect(1885, 0, 20, 280);
		g2.fillRect(320, 20, 20, 280);
		g2.fillRect(0, 0, 320, 320);//kraje
		g2.fillRect(1600, 0, 320, 320);
		
	}
}




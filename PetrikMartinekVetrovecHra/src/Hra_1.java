/*
 * toto je kombinovaná práce Miroslava Martínka, Jakuba Petøíka a Filipa Vìtrovce
 * v této verzi fungují nìkteré základní koncepty naší hry
 * ještì je potøeba vytvoøit vìtvitý dungeon, funkèní tahový souboj, obsah dungeonu(napø. pøedmìty nebo nìjaké eventy) a dotáhnout funkèní do konce
 * možná by také neuškodilo kdyby ten kód nebyl takovej bordel
 * toto vydeo by mìlo vše vysvìtlit: https://www.youtube.com/watch?v=dQw4w9WgXcQ
 * za gramatické chyby nebere žádný z èlenù zodpovìdnost
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.event.MouseInputListener;

public class Hra_1 extends JFrame implements KeyListener,MouseInputListener {
	public GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public int width = gd.getDisplayMode().getWidth();
	public int height = gd.getDisplayMode().getHeight();
	private int halfh = height/2;
	private Pohyb move;
	private MiniMap map;
	private Pozice pozice;
	private Toolbar t;Toolbar l;Toolbar u;Toolbar d;
	private int poziceX = 1625;
	private int poziceY = 880;
	private boolean mistnost = true;
	private int sirka = 8;int vyska =3;
	private int[][] room = new int[vyska][sirka];
	private int x = 1;int y = 1;
	private JLabel popisek = new JLabel("<html>Výtej v tutoriálu naší hry. V tutoriálu se nauèíš základní ovládání a koncept hry.<br/>Svojí postavu mùžeš ovládat šipkama.Z místností se dostaneš pomocí tlaèítka vpravo<html>");
	private int kde;
	private int vpravoX=1500;int vlevoY=950;
	private Slot1 slot1;
	private Slot2 slot2;
	private Inventar inventar;
	private Kostlivec_kopi skeli;
	private boolean inCombat = false;
	private boolean selected = false;
	private int slotSelected;
	private int dead;
	
	
	public Hra_1() {
		
		for (int i = 0; i < vyska; i++) {
			for (int j = 0; j < sirka; j++) {
				room[i][j] = 1;
			}
		}
		for (int j = 0; j < sirka; j++) {
			room[0][j] = 0;//vytváøí horní stranu
		}
		for (int j = 0; j < sirka; j++) {
			room[vyska-1][j] = 0;//vytváøí dolní stranu
		}
		for (int j = 0; j < vyska; j++) {
			room[j][0] = 0;//vytváøí levou stranu
		}
		for (int j = 0; j < vyska; j++) {
			room[j][sirka-1] = 0;//vytváøí pravou stranu
		}
		room[1][1]=2;
		room[1][7]=2;
		
		setSize(width, height);
		addKeyListener(this);
		addMouseListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		 
		popisek.setBounds(0, 0, 1000, 100);
		popisek.setFont(new Font("Serif", Font.BOLD, 20));
		add(popisek);
		 
		pozice = new Pozice();
		pozice.setBounds(1625, 880, 20, 20);
		add(pozice);
		
		t= new Toolbar("vpravo");
		t.setBounds(vpravoX, vlevoY, 50, 50);
		add(t);
		l= new Toolbar("vlevo");
		l.setBounds(vpravoX-100, vlevoY, 50, 50);
		add(l);
		u= new Toolbar("nahoru");
		u.setBounds(vpravoX, vlevoY-100, 50, 50);
		add(u);
		d= new Toolbar("dolù");
		d.setBounds(vpravoX-100, vlevoY-100, 50, 50);
		add(d);
			
		map = new MiniMap();
		map.setBounds(1555, 690, 350, 350);
		add(map);
		
		slot2 = new Slot2();
		slot2.setBounds(250, 840, 100, 100);
		add(slot2);
		
		slot1 = new Slot1();
		slot1.setBounds(100, 840, 100, 100);
		add(slot1);
		
		inventar = new Inventar();
		inventar.setBounds(0, 820, 1555, 220);
		add(inventar);
	
		
			
		move = new Pohyb(150,20);
		move.setBounds(100,300, 500, 600);
		add(move);
		
		skeli = new Kostlivec_kopi(80,10);
		add(skeli);
		
		setVisible(true);
		
		System.out.println(room);
		getContentPane().setBackground(Color.red);
		

	}
	
	
	public void enter() {
	if(x > 1 && x < 7) {
		getContentPane().setBackground(Color.white);
		popisek.setText("na minimapì mùžeš sledovat sùvj pohyb");
	}else if(x==7) {
			getContentPane().setBackground(Color.blue);
		}else if (x == 1) {
			getContentPane().setBackground(Color.red);
		}
		
	}
	public void enterCombat() {
		if(x == 7) {
			inCombat = true;
			move.setLocation(600, 280);
			popisek.setText("Pøed tebou se nachází neškodný kostlivec na kterém si mùžeš vyskoušt boj. Prozatím máš dva druhy útokù. Jsou ohranièeny žlutì na hlavním panelu.Použiješ je tak že nejdøív klikneš na schopnost a poté na nepøítele");
			skeli.setBounds(1100,280,340,540);
			move.add(move.bar);
		
		
			
		}
	}
	
	
	
	
	public static void main(String[] args) {
		
		new Hra_1();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(inCombat == false) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){//Pohyb dopøedu
			move.setLocation(move.getX()+10, move.getY());
			repaint();
			if(x == 7  && move.getX() == 1530) {
				
				move.setLocation(1520,move.getY() );
				popisek.setText("Došel si na kraj oblasti nemùžeš pokraèovat");
		
				
				}else if(move.getX() == 1530 && x != 1){
				
				move.setLocation(100,move.getY() );
					pozicePlus();
					pozice.setLocation(poziceX, poziceY);
					popisek.setText("");
					x = x +1;
					System.out.println(x);
					enter();
					
					
					
			
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {// Pohyb zpátky
			move.setLocation(move.getX()-10, move.getY());
			repaint();
			
			if(x == 1  && move.getX() == 50) {
				
				move.setLocation(60,move.getY() );
				popisek.setText("Došel si na kraj oblasti nemùžeš pokraèovat");
				
			}else if(move.getX() == 50 ){
			
				move.setLocation(1500,move.getY() );
				poziceMinus();
				pozice.setLocation(poziceX, poziceY);
				popisek.setText("");
				x = x -1;
				System.out.println(x);
				enter();
			
				
				}
			}
		}
		enterCombat();

		}
		
		

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public void pozicePlus() {
		poziceX = poziceX + 30;

	}
	
	
	public void poziceMinus() {
		poziceX = poziceX - 30;

	}
	public int KdeClick(int clickx, int clicky) {
		if (t.contains(clickx-vpravoX, clicky-vlevoY-25)) {
			kde = 1;//kliknuti na vpravo
		}
		if (t.contains(clickx-vpravoX+100, clicky-vlevoY-25)) {
			kde = 2;//kliknuti na vpravo
		}
		if (t.contains(clickx-vpravoX, clicky-vlevoY+75)) {
			kde = 3;//kliknuti na vpravo
		}
		if (t.contains(clickx-vpravoX+100, clicky-vlevoY+75)) {
			kde = 4;//kliknuti na vpravo
		}
		
		
		
		return kde;
		
	}
	public void selectEnemy() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		if( x > skeli.getX() && x < (skeli.getX()+340) && y > skeli.getY() && y < (skeli.getY()+540) && selected == true ) {
			switch(slotSelected) {
			case 1:{ 
				skeli.bar.setValue(skeli.bar.getValue() - (move.dmg+5) );
				 dead = skeli.bar.getValue();
				 selected = false;
				 if(dead<=0) {
				remove(skeli);
				repaint();
				inCombat = false;
				 }}break;
			case 2: { 
			skeli.bar.setValue(skeli.bar.getValue() - (move.dmg-3) );
			move.bar.setValue(move.bar.getValue()+10);
			 dead = skeli.bar.getValue();
			 selected = false;
			 if(dead<=0) {
			remove(skeli);
			repaint();
			inCombat = false;
			 }}break;
			
			}
		}
		
		
	}
	
	
	
	public void mys() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		//System.out.println(y);
		//System.out.println(x);
		
		if( x > slot1.getX() && x < (slot1.getX()+120) && y > slot1.getY() && y < (slot1.getY()+120) ) {
			slotSelected = 1;
			selected = true;
			
		}else if( x > slot2.getX() && x < (slot2.getX()+120) && y > slot2.getY() && y < (slot2.getY()+120) ) {
			slotSelected = 2;
			selected = true;
		
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(inCombat == true) {
			mys();
			selectEnemy();
		}else if(room[y][x]==2) {
		switch (KdeClick(e.getX(), e.getY())) {
		case 1:
			if(room[y][x+1]==1) {
				System.out.println("detekovan");
				
				move.setLocation(100,move.getY() );
				pozicePlus();
				pozice.setLocation(poziceX, poziceY);
				popisek.setText("");
				x = x +1;
				System.out.println(x);
				enter();
			}
			break;
		case 2:
			if(room[y][x-1]==1) {
				System.out.println("detekovan");
				
				move.setLocation(100,move.getY() );
				poziceMinus();
				pozice.setLocation(poziceX, poziceY);
				popisek.setText("");
				x = x -1;
				System.out.println(x);
				enter();
			}

		default:
			break;
		}}
		/*
		if (KdeClick(e.getX(), e.getY())==1 && x == 0) {
			System.out.println("detekovan");
			
			move.setLocation(100,move.getY() );
			pozicePlus();
			pozice.setLocation(poziceX, poziceY);
			popisek.setText("");
			x = x +1;
			System.out.println(room);
			enter();
		}
		if (KdeClick(e.getX(), e.getY())==2 && x == 6) {
			System.out.println("detekovan");
			
			move.setLocation(1500,move.getY() );
			poziceMinus();
			pozice.setLocation(poziceX, poziceY);
			popisek.setText("");
			System.out.println(room);
			x = x -1;
			enter();
		}if (KdeClick(e.getX(), e.getY())==3) {
			System.out.println("detekovan");
			
		}
		if (KdeClick(e.getX(), e.getY())==4) {
			System.out.println("detekovan");
			
		}*/
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




}
class Pohyb extends JPanel  {
	 private BufferedImage image;
	 public int hp;
	 public int dmg;
	 static JProgressBar bar  = new JProgressBar();

	 public Pohyb(int hp, int dmg) {
		 try {
			image = ImageIO.read(new File("C:\\Users\\Mira\\eclipse-workspace\\PetrikMartinekVetrovecHra\\src\\crusader.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		 this.dmg = dmg;
		 this.hp = hp;
		 
		bar.setMaximum(hp);
		bar.setMinimum(0);
		bar.setValue(hp);
		bar.setForeground(Color.RED);
		bar.setBounds(0, 0, 170, 20);
	}
	 
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, this);
		

	}


}
//tlacitka
class Toolbar extends JPanel {
	private String popis;
	public Toolbar(String popis) {
		this.popis = popis;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.CYAN);
		g2.fillRect(0, 0, 50, 50);
		g2.setColor(Color.BLACK);
		g2.drawString(popis, 10, 10);
		
	}

	
}
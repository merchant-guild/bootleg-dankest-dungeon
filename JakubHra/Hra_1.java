

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
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
import javax.swing.event.MouseInputListener;

public class Hra_1 extends JFrame implements KeyListener,MouseInputListener {
	public GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public int width = gd.getDisplayMode().getWidth();
	public int height = gd.getDisplayMode().getHeight();
	private int halfh = height/2;
	private Pohyb move;
	private MiniMap map;
	private Pozice pozice;
	private Toolbar t;
	private int poziceX = 1625;
	private int poziceY = 880;
	private boolean mistnost = true;
	private int[][] room = new int[6][0];
	private int x = 0;int y = 0;
	private JLabel popisek = new JLabel("hello");
	private int kde;
	private int vpravoX=1500;int vlevoY=900;
	
	public Hra_1() {
		
		
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
			
		map = new MiniMap();
		map.setBounds(1555, 690, 350, 350);
		add(map);
	
		t= new Toolbar("vpravo");
		t.setBounds(vpravoX, vlevoY, 50, 50);
		add(t);
			
		move = new Pohyb();
		move.setBounds(100,300, 500, 600);
		add(move);
		
		setVisible(true);
		
		System.out.println(room);
		getContentPane().setBackground(Color.red);
		

	}
	
	
	public void enter() {
	if(x > 0 && x < 6) {
		getContentPane().setBackground(Color.white);
	}else if(x==6) {
			getContentPane().setBackground(Color.blue);
		}else if (x == 0) {
			getContentPane().setBackground(Color.red);
		}
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		new Hra_1();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){//Pohyb dopøedu
			move.setLocation(move.getX()+10, move.getY());
			repaint();
			if(x == 6  && move.getX() == 1530) {
				
				move.setLocation(1520,move.getY() );
				popisek.setText("Došel si na kraj oblasti nemùžeš pokraèovat");
		
				
				}else if(move.getX() == 1530 ){
				
				move.setLocation(100,move.getY() );
					pozicePlus();
					pozice.setLocation(poziceX, poziceY);
					popisek.setText("");
					x = x +1;
					System.out.println(room);
					enter();
					
					
					
			
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {// Pohyb zpátky
			move.setLocation(move.getX()-10, move.getY());
			repaint();
			
			if(x == 0  && move.getX() == 50) {
				
				move.setLocation(60,move.getY() );
				popisek.setText("Došel si na kraj oblasti nemùžeš pokraèovat");
				
			}else if(move.getX() == 50 ){
			
				move.setLocation(1500,move.getY() );
				poziceMinus();
				pozice.setLocation(poziceX, poziceY);
				popisek.setText("");
				System.out.println(room);
				x = x -1;
				enter();
			
				
				}
			}
		

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
		poziceX = poziceX - 20;

	}
	public int KdeClick(int clickx, int clicky) {
		if (t.contains(clickx-vpravoX, clicky-vlevoY-25)) {
			kde = 1;//kliknuti na vpravo
		}
		
		
		
		
		return kde;
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
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

	 public Pohyb() {
		 try {
			image = ImageIO.read(new File("C:\\Users\\Mira\\eclipse-workspace\\JakubHra\\src\\crusader.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	 
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 0, this);
		

	}


}
class Toolbar extends JPanel {
	private String popis;
	public Toolbar(String popis) {
		this.popis = popis;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 50, 50);
		g2.setColor(Color.BLACK);
		g2.drawString(popis, 10, 10);
		
	}

	
}
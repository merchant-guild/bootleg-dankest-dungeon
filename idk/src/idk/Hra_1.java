package idk;



/*
 * toto je kombinovaná práce Miroslava Martínka, Jakuba Petøíka a Filipa Vìtrovc
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
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.event.MouseInputListener;

public class Hra_1 extends JFrame implements KeyListener,MouseInputListener {
	public int width = 960;//gd.getDisplayMode().getWidth();
	public int height = 540;//gd.getDisplayMode().getHeight();
	private int halfh = height/2;
	private Pohyb move;
	private MiniMap map;
	private Pozice pozice;
	private Chest bedna;
	private Medic medic;
	private Archer archer;
	private Undead undead;
	private Boss boss;
	private Kostlivec_kus skeli_kus;
	private Kostlivec_ban skeli_ban;
	private Skeli_heavy_kus skeli_heavy_kus;
	private Halberd halberd;
	private Toolbar t;Toolbar l;Toolbar u;Toolbar d;
	private int poziceX = 812;
	private int poziceY = 440;
	private boolean mistnost = true;
	private int sirka = 9;int vyska =3;
	private int[][] room = new int[vyska][sirka];
	private int x = 1;int y = 1;
	private JLabel popisek = new JLabel("<html>Výtej v tutoriálu naší hry. V tutoriálu se nauèíš základní ovládání a koncept hry.<br/>Svojí postavu mùžeš ovládat šipkama.Z místností se dostaneš pomocí tlaèítka východ(V) a chùzí k okraji<html>");
	private int kde;
	private int vpravoX=750;int vlevoY=475;
	private Slot1 slot1;
	private Slot2 slot2;
	private Slot1A slot1A;
	private Slot2A slot2A;
	private Slot1M slot1M;
	private Slot2M slot2M;
	private Back back;
	private Inventar inventar;
	private Kostlivec_kopi skeli;
	private boolean inCombat = false;
	private boolean selected = false;
	private int slotSelected;
	private int dead;
	private int pocetEnemy = 0;
	private boolean mistnost1Clear = false;
	private Gold gold;
	private boolean yourTurn = true;
	private int Chestpoz;
	private int stack;
	private boolean wave = true;
	
	
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
		setResizable(false);
		setLayout(null);
		 
		popisek.setBounds(0, 0, 500, 100);
		popisek.setFont(new Font("Serif", Font.BOLD, 20));
		popisek.setForeground(Color.RED);
		add(popisek);
		 
		pozice = new Pozice();
		pozice.setBounds(812, 440, 10, 10);
		add(pozice);
		
		t= new Toolbar("V");
		t.setBounds(vpravoX, vlevoY, 25, 25);
		add(t);
		l= new Toolbar("Z");
		l.setBounds(vpravoX-50, vlevoY, 25, 25);
		add(l);
		u= new Toolbar("S");
		u.setBounds(vpravoX, vlevoY-50, 25, 25);
		add(u);
		d= new Toolbar("J");
		d.setBounds(vpravoX-50, vlevoY-50, 25, 25);
		add(d);
			
		map = new MiniMap();
		map.setBounds(777, 345, 175, 175);
		add(map);
		
		slot2 = new Slot2();

		add(slot2);
		
		slot1 = new Slot1();
		add(slot1);
		
		slot2A = new Slot2A();
		add(slot2A);
		
		slot1A = new Slot1A();
		add(slot1A);
		
		slot2M = new Slot2M();
		add(slot2M);
		
		slot1M = new Slot1M();
		add(slot1M);
		
		gold = new Gold(0);
		gold.setBounds(525, 420, 50, 50);
		add(gold.count);
		add(gold);
		
		skeli = new Kostlivec_kopi(80,10,2,1);
		add(skeli.t);
		add(skeli);
		
		undead = new Undead(150,20,6,6);
		add(undead);
		
		boss = new Boss(300, 20, 10, 10);
		add(boss.t);
		add(boss);
		
		skeli_heavy_kus = new Skeli_heavy_kus(120, 25, 5, 5);
		skeli_heavy_kus.setBounds(0, 0, 0, 0);
		add(skeli_heavy_kus);
		add(skeli_heavy_kus);
		
		halberd = new Halberd(150,15,4,5);
		add(halberd.t);
		add(halberd);
		
		
		skeli_kus = new Kostlivec_kus(80,15,3,1);
		add(skeli_kus.t);
		add(skeli_kus);
		
		skeli_ban = new Kostlivec_ban(100,2,6,3);
		add(skeli_ban.t);
		add(skeli_ban);
		
		archer = new Archer(100,30,7,1);
		archer.setBounds(150, 180, 190, 200);
		add(archer.t);
		add(archer);
		
		medic = new Medic(100,8,5,2);
		medic.setBounds(30, 180, 130, 190);
		add(medic.bar);
		add(medic.t);
		add(medic);
		
		move = new Pohyb(150,20,4,5);
		move.setBounds(300,170, 190, 270);
		add(move.t);
		add(move);
	
		bedna = new Chest();
		
		add(bedna);
		
			
	
		
		inventar = new Inventar();
		inventar.setBounds(0, 410, 777, 110);
		add(inventar);
		
	
		back = new Back();
		back.setBounds(0, 0, width, height);
		add(back);
		
		setVisible(true);
		
		System.out.println(room);
		getContentPane().setBackground(Color.red);
		
		Chestspawn();
		System.out.println(width);
		System.out.println(height);

	}
	public void changeSpell() {
		if(move.turn==true) {
			slot2.setBounds(125, 420, 50, 50);
			slot1.setBounds(50, 420, 50, 50);
			slot1A.setBounds(0, 0, 0, 0);
			slot2A.setBounds(0, 0, 0, 0);
			slot1M.setBounds(0, 0, 0, 0);
			slot2M.setBounds(0, 0, 0, 0);
		}else if (archer.turn==true) {
			slot1A.setBounds(50, 420, 50, 50);
			slot2A.setBounds(125, 420, 50, 50);
			slot1.setBounds(0, 0, 0, 0);
			slot2.setBounds(0, 0, 0, 0);
			slot1M.setBounds(0, 0, 0, 0);
			slot2M.setBounds(0, 0, 0, 0);
		}else if (medic.turn==true) {
			slot1M.setBounds(50, 420, 50, 50);
			slot2M.setBounds(125, 420, 50, 50);
			slot1.setBounds(0, 0, 0, 0);
			slot2.setBounds(0, 0, 0, 0);
			slot1A.setBounds(0, 0, 0, 0);
			slot2A.setBounds(0, 0, 0, 0);
		}
	}
	
	public void Chestspawn() {
		Random rand = new Random();
		Chestpoz = rand.nextInt((7-1)+1)+1;
		System.out.println(Chestpoz);
		
	}
	
	public void drawChest() {
		if(Chestpoz == x) {
			bedna.setBounds(350, 280,100, 100);
		}else {
			 bedna.setBounds(0, 0, 0, 0);
		}
	}
	
	
	public void enter() {
		drawChest();
	if(x > 1 && x < 7) {
		getContentPane().setBackground(Color.white);
		popisek.setText("na minimapì mùžeš sledovat svùj pohyb");
	}else if(x==7) {
			getContentPane().setBackground(Color.blue);
		}else if (x == 1) {
			getContentPane().setBackground(Color.red);
		}
		
	}
	public void enterCombat() {
		if(x == 7 && inCombat == false) {
			move.turn=true;
			inCombat = true;
			changeSpell();
			pocetEnemy =  3;
			medic.setLocation(20, 170);
			archer.setLocation(160,170);
			move.setLocation(310,180);
			popisek.setText("<html> Prozatím máš dva druhy útokù.Jsou ohranièeny žlutì na hlavním panelu.<br/>Použiješ je tak že nejdøív klikneš na schopnost a poté na nepøítele<html>");
			skeli.setBounds(470,190,170,270);
			skeli_kus.setBounds(600,170,170,270);
			skeli_ban.setBounds(780,170,170,270);
			archer.add(archer.bar);
			move.add(move.bar);
			medic.bar.setBounds(50, 180, 120, 15);
		
			mistnost1Clear = true;
	
			
		}
	}
	
	
	
	
	public static void main(String[] args) {
		
		new Hra_1();
	}
	
	//-------------------------------------------------------------------------klávesnice---------------------------------------------------------------
	
	@Override
	public void keyPressed(KeyEvent e) {
		roomClear();
		if(inCombat == false) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){//Pohyb dopøedu
			medic.setLocation(medic.getX()+5, medic.getY());
			archer.setLocation(archer.getX()+5, archer.getY());
			move.setLocation(move.getX()+5, move.getY());
			repaint();
			/*if(x == 7  && move.getX() == 765) {
				
				move.setLocation(760,move.getY() );
				popisek.setText("Došel si na kraj oblasti nemùžeš pokraèovat");
		
				
				}else */if(room[y][x] ==1 && move.getX()>=765){
					medic.setLocation(30,medic.getY() );
					archer.setLocation(150,archer.getY() );
					move.setLocation(300,move.getY() );
					pozicePlus();
					pozice.setLocation(poziceX, poziceY);
					popisek.setText("");
					x = x +1;
					System.out.println(x);
					enter();
					
					
					
				}
			switch (KdeClick()) {
			
			case 1:
				if(room[y][x+1]==1 && (room[y][x] ==2 && move.getX()>=765)) {//exception 
					System.out.println("detekovan");
					medic.setLocation(30,medic.getY() );
					archer.setLocation(150,archer.getY() );
					move.setLocation(300,move.getY() );
					pozicePlus();
					pozice.setLocation(poziceX, poziceY);
					popisek.setText("");
					x = x +1;
					System.out.println(x);
					enter();
					kde =0;
					
				}
				break;
			case 2:
				if(room[y][x-1]==1 && (room[y][x] ==2 && move.getX()>=765)) {
					System.out.println("detekovan");
					medic.setLocation(30,medic.getY() );
					archer.setLocation(150,archer.getY() );
					move.setLocation(300,move.getY() );
					poziceMinus();
					pozice.setLocation(poziceX, poziceY);
					popisek.setText("");
					x = x -1;
					System.out.println(x);
					enter();
					kde = 0;
				}

			default:
				break;
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {// Pohyb zpátky
			medic.setLocation(medic.getX()-5, medic.getY());
			archer.setLocation(archer.getX()-5, archer.getY());
			move.setLocation(move.getX()-5, move.getY());
			repaint();
			
			if((x == 1 || x==7)  && archer.getX() == 25) {
				move.setLocation(300, move.getY());
				archer.setLocation(150,archer.getY() );
				medic.setLocation(30,medic.getY() );
				popisek.setText("Došel si na kraj oblasti nemùžeš pokraèovat");
				
			}else if(archer.getX() == 25 ){
				archer.setLocation(550,archer.getY() );
				move.setLocation(750,move.getY() );
				medic.setLocation(400,medic.getY() );
				poziceMinus();
				pozice.setLocation(poziceX, poziceY);
				popisek.setText("");
				x = x -1;
				System.out.println(x);
				enter();
			
				
					}
			}
		
		} if(mistnost1Clear == false) {
			enterCombat();
		}
		}
		
		

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public void pozicePlus() {
		poziceX = poziceX + 15;

	}
	
	
	public void poziceMinus() {
		poziceX = poziceX - 15;

	}
	public int KdeClick() {
		/*
		if (t.contains(clickx-vpravoX, clicky-vlevoY-12)) {
			kde = 1;//kliknuti na vpravo
		}
		if (t.contains(clickx-vpravoX+50, clicky-vlevoY-12)) {
			kde = 2;//kliknuti na vpravo
		}
		if (t.contains(clickx-vpravoX, clicky-vlevoY+37)) {
			kde = 3;//kliknuti na vpravo
		}
		if (t.contains(clickx-vpravoX+50, clicky-vlevoY+37)) {
			kde = 4;//kliknuti na vpravo
		}
		*/
		if (t.isZmacknut()) {
			kde = 1;//kliknuti na vpravo
		}
		if (l.isZmacknut()) {
			kde = 2;//kliknuti na vpravo
		}
		if (u.isZmacknut()) {
			kde = 3;//kliknuti na vpravo
		}
		if (d.isZmacknut()) {
			kde = 4;//kliknuti na vpravo
		}
		t.setZmacknut(false);
		l.setZmacknut(false);
		u.setZmacknut(false);
		d.setZmacknut(false);
		return kde;
		
	}
	
	
	public void BossSpawn() {
		if(/*pocetEnemy <= 0 && boss.alive==true*/ skeli.alive == false && skeli_ban.alive == false && skeli_kus.alive == false   ) {
			boss.setBounds(600, 170, 170, 270);
			repaint();
			System.out.println("boss");
		}
	}
	
	public void roomClear() {
		if(pocetEnemy ==0  && boss.alive==false) {
			inCombat = false;
		}
	
	}
	
	
	public void selectEnemy() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		if(move.turn==true) {
			if(move.alive == false) {
				popisek.setText("Jeden z tvých hrdinù padl. Musíš restartovat hrz");
			}else if( x > skeli.getX() && x < (skeli.getX()+170) && y > skeli.getY() && y < (skeli.getY()+270) && selected == true ) {
			switch(slotSelected) {
			case 1:{ 
				skeli.bar.setValue(skeli.bar.getValue() - (move.dmg+5) );
				 dead = skeli.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=true;
				 move.turn=false;
				 changeSpell();
				 if(dead<=0) {
				yourTurn = true;
				remove(skeli);
				skeli.setBounds(0, 0, 0, 0);
				pocetEnemy -= 1;
				skeli.alive= false;
				BossSpawn();
				repaint();
				 }else {
					 skeliAA();}}break;
			case 2: { 
			skeli.bar.setValue(skeli.bar.getValue() - (move.dmg-3) );
			move.bar.setValue(move.bar.getValue()+10);
			 dead = skeli.bar.getValue();
			 selected = false;
			 yourTurn = false;
			 archer.turn=true;
			 move.turn=false;
			 changeSpell();
			 if(dead<=0) {
			yourTurn = true;
			remove(skeli);
			skeli.setBounds(0, 0, 0, 0);
			pocetEnemy -= 1;
			skeli.alive= false;
			BossSpawn();
			repaint();

			 }else {skeliAA();}}break;
			
			}
		}else if( x > skeli_kus.getX() && x < (skeli_kus.getX()+170) && y > skeli_kus.getY() && y < (skeli_kus.getY()+270) && selected == true ) {
			switch(slotSelected) {
			case 1:{ 
				skeli_kus.bar.setValue(skeli_kus.bar.getValue() - (move.dmg+5) );
				 dead = skeli_kus.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=true;
				 move.turn=false;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove(skeli_kus);
				skeli_kus.setBounds(0, 0, 0, 0);
				pocetEnemy -= 1;
				skeli_kus.alive= false;
				BossSpawn();
				repaint();
				 }else {
					 skeli_kusAA();}}break;
			case 2: { 
			skeli_kus.bar.setValue(skeli_kus.bar.getValue() - (move.dmg-3) );
			move.bar.setValue(move.bar.getValue()+10);
			 dead = skeli_kus.bar.getValue();
			 selected = false;
			 yourTurn = false;
			 archer.turn=true;
			 move.turn=false;
			 changeSpell();
			 if(dead<=0) {
				 yourTurn = true;
			remove(skeli_kus);
			skeli_kus.setBounds(0, 0, 0, 0);
			pocetEnemy -= 1;
			skeli_kus.alive= false;
			BossSpawn();
			repaint();

			 }else {skeli_kusAA();}}break;
			
			}
		}else if( x > skeli_ban.getX() && x < (skeli_ban.getX()+170) && y > skeli_ban.getY() && y < (skeli_ban.getY()+270) && selected == true ) {
			switch(slotSelected) {
			case 1:{ 
				skeli_ban.bar.setValue(skeli_ban.bar.getValue() - (move.dmg+5) );
				 dead = skeli_ban.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=true;
				 move.turn=false;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove(skeli_ban);
				skeli_ban.setBounds(0, 0, 0, 0);
				pocetEnemy -= 1;
				skeli_ban.alive= false;
				BossSpawn();
				repaint();
				 }else {
					 skeli_banAA();}}break;
			case 2: { 
			skeli_ban.bar.setValue(skeli_ban.bar.getValue() - (move.dmg-3) );
			move.bar.setValue(move.bar.getValue()+10);
			 dead = skeli_ban.bar.getValue();
			 selected = false;
			 yourTurn = false;
			 archer.turn=true;
			 move.turn=false;
			 changeSpell();
			 if(dead<=0) {
				 yourTurn = true;
			remove(skeli_ban);
			skeli_ban.setBounds(0, 0, 0, 0);
			pocetEnemy -= 1;
			skeli_ban.alive= false;
			BossSpawn();
			repaint();

			 }else {skeli_banAA();}}break;
			
			}
			
		}else if(x>undead.getX() && x <(undead.getX()+170) && y > undead.getY() && y < (undead.getY()+270) && selected == true ) {
			switch(slotSelected) {
			case 1:{ 
				undead.bar.setValue(undead.bar.getValue() - (move.dmg+5) );
				 dead = undead.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=true;
				 move.turn=false;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
					 wave=false;
				remove(undead);
				undead.setBounds(0, 0, 0, 0);
				pocetEnemy -= 1;
			
				BossSpawn();
				repaint();
				 }else {
					 undeadAA();}}break;
			case 2: { 
				undead.bar.setValue(undead.bar.getValue() - (move.dmg-3) );
				undead.bar.setValue(move.bar.getValue()+10);
			 dead = undead.bar.getValue();
			 selected = false;
			 yourTurn = false;
			 archer.turn=true;
			 move.turn=false;
			 changeSpell();
			 if(dead<=0) {
				 yourTurn = true;
				 wave=false;
			remove(undead);
			undead.setBounds(0, 0, 0, 0);
			pocetEnemy -= 1;
			
			BossSpawn();
			repaint();

			 }else {undeadAA();}}break;
			
			}
		}else if (x>boss.getX() && x <(boss.getX()+170) && y > boss.getY() && y < (boss.getY()+270) && selected == true) {
			switch(slotSelected) {
			case 1:{ 
				boss.bar.setValue(boss.bar.getValue() - (move.dmg+5) );
				 dead = boss.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=true;
				 move.turn=false;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
					 boss.setBounds(0, 0, 0, 0);
				remove(boss);
				pocetEnemy -= 1;
				
				BossSpawn();
				repaint();
				 }else {
					 bossAA();}}break;
			case 2: { 
				boss.bar.setValue(boss.bar.getValue() - (move.dmg-3) );
				boss.bar.setValue(move.bar.getValue()+10);
			 dead = boss.bar.getValue();
			 selected = false;
			 yourTurn = false;
			 archer.turn=true;
			 move.turn=false;
			 changeSpell();
			 if(dead<=0) {
				 yourTurn = true;
			remove(boss);
			undead.setBounds(0, 0, 0, 0);
			pocetEnemy -= 1;
			
			BossSpawn();
			repaint();

			 }else {bossAA();}}break;
			
			}
		}
		else if( x > skeli_heavy_kus.getX() && x < (skeli_heavy_kus.getX()+170) && y > skeli_heavy_kus.getY() && y < (skeli_heavy_kus.getY()+270) && selected == true ) {
			switch(slotSelected) {
			case 1:{ 
				skeli_heavy_kus.bar.setValue(skeli_heavy_kus.bar.getValue() - (move.dmg+5) );
				 dead = skeli_heavy_kus.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=true;
				 move.turn=false;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove(skeli_heavy_kus);
				skeli_heavy_kus.setBounds(0, 0, 0, 0);
				
				BossSpawn();
				repaint();
				 }else {
					 skeli_kusAA();}}break;
			case 2: { 
				skeli_heavy_kus.bar.setValue(skeli_heavy_kus.bar.getValue() - (move.dmg-3) );
			move.bar.setValue(move.bar.getValue()+10);
			 dead = skeli_heavy_kus.bar.getValue();
			 selected = false;
			 yourTurn = false;
			 archer.turn=true;
			 move.turn=false;
			 changeSpell();
			 if(dead<=0) {
				 yourTurn = true;
			remove(skeli_heavy_kus);
			skeli_heavy_kus.setBounds(0, 0, 0, 0);
			
			BossSpawn();
			repaint();

			 }else {skeli_kusAA();}}break;
			
			}	
			}else if( x > halberd.getX() && x < ( halberd.getX()+170) && y >  halberd.getY() && y < ( halberd.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					 halberd.bar.setValue( halberd.bar.getValue() - (move.dmg+5) );
					 dead =  halberd.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 archer.turn=true;
					 move.turn=false;
					 changeSpell();
					 if(dead<=0) {
						 yourTurn = true;
					remove( halberd);
					 halberd.setBounds(0, 0, 0, 0);
					
					BossSpawn();
					repaint();
					 }else {
						 skeli_kusAA();}}break;
				case 2: { 
					 halberd.bar.setValue( halberd.bar.getValue() - (move.dmg-3) );
				move.bar.setValue(move.bar.getValue()+10);
				 dead =  halberd.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=true;
				 move.turn=false;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove( halberd);
				 halberd.setBounds(0, 0, 0, 0);
			
				BossSpawn();
				repaint();

				 }else {skeli_kusAA();}}break;
				
				}
			}
		}else if(archer.turn==true) {
			
			if(archer.alive == false) {
				popisek.setText("Jeden z tvých hrdinù padl. Musíš restartovat hrz");
			}else
			
			if( x > skeli.getX() && x < (skeli.getX()+170) && y > skeli.getY() && y < (skeli.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					skeli.bar.setValue(skeli.bar.getValue() - (archer.dmg+5) );
					 dead = skeli.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 archer.turn=false;
					medic.turn=true;
					 changeSpell();
					 if(dead<=0) {
					yourTurn = true;
					remove(skeli);
					skeli.setBounds(0, 0, 0, 0);
					pocetEnemy -= 1;
					skeli.alive= false;
					BossSpawn();
					repaint();
					 }else {
						 skeliAA();}}break;
				case 2: { 
				skeli.bar.setValue(skeli.bar.getValue() - (archer.dmg-20 + (stack+10)) );
				stack+=1;
				 dead = skeli.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=false;
					medic.turn=true;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove(skeli);
				skeli.setBounds(0, 0, 0, 0);
				pocetEnemy -= 1;
				skeli.alive= false;
				BossSpawn();
				repaint();

				 }else {skeliAA();}}break;
				
				}
			}else if( x > skeli_kus.getX() && x < (skeli_kus.getX()+170) && y > skeli_kus.getY() && y < (skeli_kus.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					skeli_kus.bar.setValue(skeli_kus.bar.getValue() - (archer.dmg+5) );
					 dead = skeli_kus.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 archer.turn=false;
						medic.turn=true;
					 changeSpell();
					 if(dead<=0) {
						 yourTurn = true;
					remove(skeli_kus);
					skeli_kus.setBounds(0, 0, 0, 0);
					pocetEnemy -= 1;
					skeli_kus.alive= false;
					BossSpawn();
					repaint();
					 }else {
						 skeli_kusAA();}}break;
				case 2: { 
					skeli_kus.bar.setValue(skeli_kus.bar.getValue() - (archer.dmg-20 + (stack+10)) );
					stack+=1;
				 dead = skeli_kus.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=false;
					medic.turn=true;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove(skeli_kus);
				skeli_kus.setBounds(0, 0, 0, 0);
				pocetEnemy -= 1;
				skeli_kus.alive= false;
				BossSpawn();
				repaint();

				 }else {skeli_kusAA();}}break;
				
				}
				}else if( x > skeli_heavy_kus.getX() && x < (skeli_heavy_kus.getX()+170) && y > skeli_heavy_kus.getY() && y < (skeli_heavy_kus.getY()+270) && selected == true ) {
					switch(slotSelected) {
					case 1:{ 
						skeli_heavy_kus.bar.setValue(skeli_heavy_kus.bar.getValue() - (archer.dmg+5) );
						 dead = skeli_heavy_kus.bar.getValue();
						 selected = false;
						 yourTurn = false;
						 archer.turn=false;
							medic.turn=true;
						 changeSpell();
						 if(dead<=0) {
							 yourTurn = true;
						remove(skeli_heavy_kus);
						skeli_heavy_kus.setBounds(0, 0, 0, 0);
					
					
						BossSpawn();
						repaint();
						 }else {
							 skeli_kusAA();}}break;
					case 2: { 
						skeli_kus.bar.setValue(skeli_heavy_kus.bar.getValue() - (archer.dmg-20 + (stack+10)) );
						stack+=1;
					 dead = skeli_heavy_kus.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 archer.turn=false;
						medic.turn=true;
					 changeSpell();
					 if(dead<=0) {
						 yourTurn = true;
					remove(skeli_heavy_kus);
					skeli_heavy_kus.setBounds(0, 0, 0, 0);
					
					
					BossSpawn();
					repaint();

					 }else {skeli_kusAA();}}break;
					
					}
			}else if( x > skeli_ban.getX() && x < (skeli_ban.getX()+170) && y > skeli_ban.getY() && y < (skeli_ban.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					skeli_ban.bar.setValue(skeli_ban.bar.getValue() - (archer.dmg+5) );
					 dead = skeli_ban.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 archer.turn=false;
					medic.turn=true;
					 changeSpell();
					 if(dead<=0) {
						 yourTurn = true;
					remove(skeli_ban);
					skeli_ban.setBounds(0, 0, 0, 0);
					pocetEnemy -= 1;
					skeli_ban.alive= false;
					BossSpawn();
					repaint();
					 }else {
						 skeli_banAA();}}break;
				case 2: { 
					skeli_ban.bar.setValue(skeli_ban.bar.getValue() - (archer.dmg-20 + (stack+10)) );
					stack+=1;
				 dead = skeli_ban.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=false;
				medic.turn=true;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
					 skeli_ban.setBounds(0, 0, 0, 0);
				remove(skeli_ban);
				pocetEnemy -= 1;
				skeli_ban.alive= false;
				BossSpawn();
				repaint();

				 }else {skeli_banAA();}}break;
				
				}
			}else if(x>undead.getX() && x <(undead.getX()+170) && y > undead.getY() && y < (undead.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					undead.bar.setValue(undead.bar.getValue() - (archer.dmg+5) );
					 dead = undead.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 archer.turn=false;
					medic.turn=true;
					 changeSpell();
					 if(dead<=0) {
						 yourTurn = true;
						 wave=false;
						 undead.setBounds(0, 0, 0, 0);
					remove(undead);
					pocetEnemy -= 1;
					
					BossSpawn();
					repaint();
					 }else {
						 undeadAA();}}break;
				case 2: { 
					undead.bar.setValue(undead.bar.getValue() - (archer.dmg-20 + (stack+10)) );
					stack+=1;
				 dead = undead.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=false;
				medic.turn=true;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
					 wave=false;
					 
				remove(undead);
				 undead.setBounds(0, 0, 0, 0);
				 pocetEnemy -= 1;
			
				BossSpawn();
				repaint();

				 }else {undeadAA();}}break;
				
				}
			}else if(x>boss.getX() && x <(boss.getX()+170) && y > boss.getY() && y < (boss.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					boss.bar.setValue(boss.bar.getValue() - (archer.dmg+5) );
					 dead = boss.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 archer.turn=false;
					medic.turn=true;
					 changeSpell();
					 if(dead<=0) {
						 yourTurn = true;
					remove(boss);
					 boss.setBounds(0, 0, 0, 0);
					 pocetEnemy -= 1;

					BossSpawn();
					repaint();
					 }else {
						 bossAA();}}break;
				case 2: { 
					boss.bar.setValue(boss.bar.getValue() - (archer.dmg-20 + (stack+10)) );
					stack+=1;
				 dead = boss.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=false;
				medic.turn=true;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
					 boss.setBounds(0, 0, 0, 0);
				remove(boss);
				pocetEnemy -= 1;

				BossSpawn();
				repaint();

				 }else {bossAA();}}break;
				
				}
			}	
			}else if( x >halberd.getX() && x < (halberd.getX()+170) && y > halberd.getY() && y < (halberd.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					halberd.bar.setValue(halberd.bar.getValue() - (archer.dmg+5) );
					 dead = halberd.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 archer.turn=false;
						medic.turn=true;
					 changeSpell();
					 if(dead<=0) {
						 yourTurn = true;
					remove(halberd);
					halberd.setBounds(0, 0, 0, 0);
				

					BossSpawn();
					repaint();
					 }else {
						 skeli_kusAA();}}break;
				case 2: { 
					halberd.bar.setValue(halberd.bar.getValue() - (archer.dmg-20 + (stack+10)) );
					stack+=1;
				 dead = halberd.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 archer.turn=false;
					medic.turn=true;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove(halberd);
				halberd.setBounds(0, 0, 0, 0);
				BossSpawn();
				repaint();

				 }else {skeli_kusAA();}}break;
				
				}
		}else if(medic.turn==true) {
			if(medic.alive == false) {
				popisek.setText("Jeden z tvých hrdinù padl. Musíš restartovat hrz");
			}else
	
			if( x > move.getX() && x < (move.getX()+170) && y > move.getY() && y < (move.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					move.bar.setValue(move.bar.getValue() + 15 );
					 dead = skeli.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 medic.turn=false;
					 move.turn=true;
					 changeSpell();
					 if(dead<=0) {
					yourTurn = true;
					remove(skeli);
					pocetEnemy -= 1;
					repaint();
					 }else {
						 EnemyAA();}}break;
				case 2: { 
				archer.bar.setValue(archer.bar.getValue() + 5 );
				move.bar.setValue(move.bar.getValue()+ 5);
				medic.bar.setValue(medic.bar.getValue()+ 5);
				 dead = skeli.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 medic.turn=false;
				 move.turn=true;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove(skeli);
				pocetEnemy -= 1;
				repaint();

				 }else {EnemyAA();}}break;
				
				}
			}else if( x > archer.getX() && x < (archer.getX()+170) && y > archer.getY() && y < (archer.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					archer.bar.setValue(archer.bar.getValue() + 15 );
					 dead = skeli_kus.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 medic.turn=false;
					 move.turn=true;
					 changeSpell();
					 if(dead<=0) {
						 yourTurn = true;
					remove(skeli_kus);
					pocetEnemy -= 1;
					repaint();
					 }else {
						 EnemyAA();}}break;
				case 2: { 
					archer.bar.setValue(archer.bar.getValue() + 5 );
					move.bar.setValue(move.bar.getValue()+ 5);
					medic.bar.setValue(medic.bar.getValue()+ 5);
				 dead = skeli_kus.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 medic.turn=false;
				 move.turn=true;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove(skeli_kus);
				pocetEnemy -= 1;
				repaint();

				 }else {EnemyAA();}}break;
				
				}
			}else if( x > medic.getX() && x < (medic.getX()+170) && y > medic.getY() && y < (medic.getY()+270) && selected == true ) {
				switch(slotSelected) {
				case 1:{ 
					medic.bar.setValue(medic.bar.getValue() + 15 );
					 dead = skeli_ban.bar.getValue();
					 selected = false;
					 yourTurn = false;
					 medic.turn=false;
					 move.turn=true;
					 changeSpell();
					 if(dead<=0) {
						 yourTurn = true;
					remove(skeli_ban);
					pocetEnemy -= 1;
					repaint();
					 }else {
						 EnemyAA();}}break;
				case 2: { 
					archer.bar.setValue(archer.bar.getValue() + 5 );
					move.bar.setValue(move.bar.getValue()+ 5);
					medic.bar.setValue(medic.bar.getValue()+ 5);
				 dead = skeli_ban.bar.getValue();
				 selected = false;
				 yourTurn = false;
				 medic.turn=false;
				 move.turn=true;
				 changeSpell();
				 if(dead<=0) {
					 yourTurn = true;
				remove(skeli_ban);
				pocetEnemy -= 1;
				repaint();

				 }else {EnemyAA();}}break;
				
				}
			}
		}
		}
	
	
	
	public void EnemyAA() {
		Random rand = new Random(); 
		int value = rand.nextInt((3-1)+1)+1;
		switch(value) {
		case 1 : {
			move.bar.setValue(move.bar.getValue()-(skeli.getDmg()+move.arm));
			yourTurn= true;
			 dead = move.bar.getValue();
			if(dead<=0) {
				remove(move);
				repaint();
				 }
			
		}break;
		case 2 :{
			archer.bar.setValue(archer.bar.getValue()-(skeli.getDmg()+archer.arm));
			yourTurn= true;
			 dead = archer.bar.getValue();
				if(dead<=0) {
					remove(archer);
					repaint();
					 }
		}break;
		case 3 :{
			medic.bar.setValue(medic.bar.getValue()-(skeli.getDmg()+medic.arm));
			yourTurn= true;
			 dead = medic.bar.getValue();
				if(dead<=0) {
					remove(medic);
					remove(medic.bar);
					repaint();
					 }
		}break;
		}
		
		
		
	}
	
	public void skeliAA() {
		Random rand = new Random(); 
		int value = rand.nextInt((3-1)+1)+1;
		switch(value) {
		case 1 : {
			move.bar.setValue(move.bar.getValue()-(skeli.getDmg()+move.arm));
			yourTurn= true;
			 dead = move.bar.getValue();
			if(dead<=0) {
				remove(move);
				repaint();
				 }
		}break;
		case 2 :{
			archer.bar.setValue(archer.bar.getValue()-(skeli.getDmg()+archer.arm));
			yourTurn= true;
			 dead = archer.bar.getValue();
				if(dead<=0) {
					remove(archer);
					repaint();
					 }
		}break;
		case 3 :{
			medic.bar.setValue(medic.bar.getValue()-(skeli.getDmg()+medic.arm));
			yourTurn= true;
			 dead = medic.bar.getValue();
				if(dead<=0) {
					remove(medic);
					remove(medic.bar);
					repaint();
					 }
		}break;
		}
	
	}
	
	
	
	public void skeli_kusAA() {
		Random rand = new Random(); 
		int value = rand.nextInt((3-1)+1)+1;
		switch(value) {
		case 1 : {
			move.bar.setValue(move.bar.getValue()-(skeli_kus.getDmg()+move.arm));
			yourTurn= true;
			 dead = move.bar.getValue();
			if(dead<=0) {
				remove(move);
				repaint();
				 }
		}break;
		case 2 :{
			archer.bar.setValue(archer.bar.getValue()-(skeli_kus.getDmg()+archer.arm));
			yourTurn= true;
			 dead = archer.bar.getValue();
				if(dead<=0) {
					remove(archer);
					repaint();
					 }
		}break;
		case 3 :{
			medic.bar.setValue(medic.bar.getValue()-(skeli_kus.getDmg()+medic.arm));
			yourTurn= true;
			 dead = medic.bar.getValue();
				if(dead<=0) {
					remove(medic);
					remove(medic.bar);
					repaint();
					 }
		}break;
		}
	
	}
	
	public void skeli_banAA() {
		Random rand = new Random(); 
		int value = rand.nextInt((3-1)+1)+1;
		switch(value) {
		case 1 : {
			move.bar.setValue(move.bar.getValue()-(skeli_ban.getDmg()+move.arm));
			yourTurn= true;
			 dead = move.bar.getValue();
			if(dead<=0) {
				remove(move);
				repaint();
				 }
		}break;
		case 2 :{
			archer.bar.setValue(archer.bar.getValue()-(skeli_ban.getDmg()+archer.arm));
			yourTurn= true;
			 dead = archer.bar.getValue();
				if(dead<=0) {
					remove(archer);
					repaint();
					 }
		}break;
		case 3 :{
			medic.bar.setValue(medic.bar.getValue()-(skeli_ban.getDmg()+medic.arm));
			yourTurn= true;
			 dead = medic.bar.getValue();
				if(dead<=0) {
					remove(medic);
					remove(medic.bar);
					repaint();
					 }
		}break;
		}
	
	}
	
	
	public void undeadAA() {
		Random rand = new Random(); 
		int value = rand.nextInt((3-1)+1)+1;
		switch(value) {
		case 1 : {
			move.bar.setValue(move.bar.getValue()-(undead.getDmg()+move.arm));
			yourTurn= true;
			 dead = move.bar.getValue();
			if(dead<=0) {
				remove(move);
				repaint();
				 }
		}break;
		case 2 :{
			archer.bar.setValue(archer.bar.getValue()-(undead.getDmg()+archer.arm));
			yourTurn= true;
			 dead = archer.bar.getValue();
				if(dead<=0) {
					remove(archer);
					repaint();
					 }
		}break;
		case 3 :{
			medic.bar.setValue(medic.bar.getValue()-(undead.getDmg()+medic.arm));
			yourTurn= true;
			 dead = medic.bar.getValue();
				if(dead<=0) {
					remove(medic);
					remove(medic.bar);
					repaint();
					 }
		}break;
		}
	
	}
	
	public void bossAA() {
		Random rand = new Random(); 
		int value = rand.nextInt((3-1)+1)+1;
		switch(value) {
		case 1 : {
			move.bar.setValue(move.bar.getValue()-(boss.getDmg()+move.arm));
			boss.bar.setValue(boss.bar.getValue()+10);
			yourTurn= true;
			 dead = move.bar.getValue();
			if(dead<=0) {
				remove(move);
				repaint();
				 }
		}break;
		case 2 :{
			archer.bar.setValue(archer.bar.getValue()-(boss.getDmg()+archer.arm));
			boss.bar.setValue(boss.bar.getValue()+10);
			yourTurn= true;
			 dead = archer.bar.getValue();
				if(dead<=0) {
					remove(archer);
					repaint();
					 }
		}break;
		case 3 :{
			medic.bar.setValue(medic.bar.getValue()-(boss.getDmg()+medic.arm));
			boss.bar.setValue(boss.bar.getValue()+10);
			yourTurn= true;
			 dead = medic.bar.getValue();
				if(dead<=0) {
					remove(medic);
					remove(medic.bar);
					repaint();
					 }
		}break;
		}
	
	}

	
	
	//-----------------------------------------------------mys------------------------------------------------------------------------
	
	public void mys() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();

		
		if( x > slot1.getX() && x < (slot1.getX()+60) && y > slot1.getY() && y < (slot1.getY()+60) ) {
			slotSelected = 1;
			selected = true;
			
		}else if( x > slot2.getX() && x < (slot2.getX()+60) && y > slot2.getY() && y < (slot2.getY()+60) ) {
			slotSelected = 2;
			selected = true;
		
		}else if( x > slot1A.getX() && x < (slot1A.getX()+60) && y > slot1A.getY() && y < (slot1A.getY()+60) ) {
			slotSelected = 1;
			selected = true;
			
		}else if( x > slot2A.getX() && x < (slot2A.getX()+60) && y > slot2A.getY() && y < (slot2A.getY()+60) ) {
			slotSelected = 2;
			selected = true;
		
		}else if( x > slot1M.getX() && x < (slot1M.getX()+60) && y > slot1M.getY() && y < (slot1M.getY()+60) ) {
			slotSelected = 1;
			selected = true;
			
		}else if( x > slot2M.getX() && x < (slot2M.getX()+60) && y > slot2M.getY() && y < (slot2M.getY()+60) ) {
			slotSelected = 2;
			selected = true;
		
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		if(e.getX()> bedna.getX() && e.getX() < (bedna.getX()+100) && e.getY() > bedna.getY() && e.getY() < (bedna.getY()+100) ) {
			gold.setPenize(gold.getPenize()+100);
			 gold.count.setText(String.valueOf(gold.getPenize()));
			 bedna.setBounds(5000, 5000, 0, 0);
			 remove(bedna);
			 repaint();
			 Chestspawn();
		}else if(yourTurn == true) { if(inCombat == true) {
			mys();
			selectEnemy();
		}else if(room[y][x]==2) {
			System.out.println(e.getX());
			System.out.println(e.getY());
		/*switch (KdeClick(e.getX(), e.getY())) {
		
		case 1:
			if(room[y][x+1]==1) {//exception 
				System.out.println("detekovan");
				archer.setLocation(50,archer.getY() );
				move.setLocation(250,move.getY() );
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
				archer.setLocation(50,archer.getY() );
				move.setLocation(250,move.getY() );
				poziceMinus();
				pozice.setLocation(poziceX, poziceY);
				popisek.setText("");
				x = x -1;
				System.out.println(x);
				enter();
			}

		default:
			break;
		}*/}}
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
class Pohyb extends JPanel/* implements MouseInputListener*/{
	 private BufferedImage image;
	 public int hp;
	 public int dmg;
	 public int spd;
	 public int arm;
	 public boolean alive = true;
	 public boolean turn = false;
	 public String name = "rytir";
	 public JLabel t = new JLabel();
	 
	 static JProgressBar bar  = new JProgressBar();

	 public Pohyb(int hp, int dmg, int spd, int arm) {
		 try {
			image = ImageIO.read(new File("src\\idk\\knight.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		 this.dmg = dmg;
		 this.hp = hp;
		 this.spd = spd;
		 this.arm = arm;
		 
		
		 
		 
		bar.setMaximum(hp);
		bar.setMinimum(0);
		bar.setValue(hp);
		bar.setForeground(Color.RED);
		bar.setBounds(0, 0, 170, 20);
		
		//addMouseListener(this);
		t.setBounds(612, 410, 50, 100);
	}
	 
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(image, 0, 20, 200, 200, null);
		

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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}*/
	public int getSpd() {
		return spd;
	}


}
//tlacitka
class Toolbar extends JPanel implements MouseListener{
	private String popis;
	private boolean zmacknut = false;
	public Toolbar(String popis) {
		this.popis = popis;
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2.scale(0.5, 0.5);
		g2.setColor(Color.CYAN);
		g2.fillRect(0, 0, 25, 25);
		//g2.scale(3, 3);
		g2.setColor(Color.BLACK);
		g2.drawString(popis, 5, 13);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		zmacknut = true;
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

	public boolean isZmacknut() {
		return zmacknut;
	}

	public void setZmacknut(boolean zmacknut) {
		this.zmacknut = zmacknut;
	}

	
}
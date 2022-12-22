package MainProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Scanner;
import javax.sound.sampled.*;

class Buyu{
	private int x;
	private int y;
	public Buyu(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}


public class Oyun extends JPanel implements KeyListener,ActionListener{

	Timer timer =new Timer(5,this);
	
	private boolean game=true;
	private boolean started=false;
	private int gecen_sure=0;
	private int harcanan_buyu=0;
	private int voldehealth=100;
	private int harrypower=4;
	private int harryisabet;
	static final int SCREEN_WIDTH = 1300;
	static final int SCREEN_HEIGHT = 750;
	private BufferedImage image0;
	private BufferedImage image;
	private BufferedImage image2;
	private BufferedImage image4;
	private BufferedImage image5;
	private ArrayList<Buyu> buyuler = new ArrayList<>();
	private int buyubr =1;
	
	private int harryx=0;
	private int harrybr=20; 
	private int voldeX=450;
	private int voldebr=2;
	private int bellaX=0;
	private int bellabr=2;
	private int engellenenbüyü=0;
	private Clip clip;
	
	
	
	public boolean kontrolEt() {
		for(Buyu buyu: buyuler) {
			if(new Rectangle(buyu.getX(),buyu.getY(),10,20).intersects(new Rectangle(bellaX,20,10,20))) {
				engellenenbüyü++;
				voldehealth+=5;
			}
		
		if(new Rectangle(buyu.getX(),buyu.getY(),10,20).intersects(new Rectangle(voldeX,20,10,20))){
			voldehealth=voldehealth-harrypower;
			harryisabet++;
			if(checkvoldehealt()) {
				return true;
			}
			}
		}
		
		return false;
	}
	
	
	public boolean checkvoldehealt() {
		
		if(voldehealth<=0) {
			return true;
		}else {
			return false;
		}
	}
		

	
	
	
	public Oyun(Clip clip) {
		
	try {
		
		image0=ImageIO.read(new FileImageInputStream(new File("back.png")));
		image4=ImageIO.read(new FileImageInputStream(new File("basla.png")));
		image5=ImageIO.read(new FileImageInputStream(new File("bella.png")));
		image = ImageIO.read(new FileImageInputStream(new File("harry.png")));
		image2 =ImageIO.read(new FileImageInputStream(new File("volde.png")));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	timer.start();
	
	
	this.clip = clip;
	clip.start();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		
		if(started==false) {
		
		
		g.drawImage(image4, 0,0,image4.getHeight()*5,image4.getWidth()*2,this);
				
		}else {
			g.drawImage(image0,0, 0,image.getWidth()*4,image.getHeight()*2, this);
			g.drawImage(image5,bellaX,100,image.getWidth()/3,image.getHeight()/3,this);
			g.drawImage(image, harryx, 450, image.getWidth() / 5, image.getHeight() / 5, this);
			g.drawImage(image2, voldeX, 0, this);
		}
		// okuduðumuz imageý grafiðin ütünden çizdik
		
		gecen_sure+=5;
		
		
		g.setColor(Color.YELLOW);
		for(Buyu ates: buyuler) {
			g.fillRect(ates.getX(), ates.getY(), 10, 20);	
		}
		
		for(Buyu ates : buyuler) {
			
			if(ates.getY()<0) {
				buyuler.remove(ates);
			}
		}
		
		
		g.setColor(Color.blue);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Health: "+voldehealth, (SCREEN_WIDTH - metrics.stringWidth("Score: "+voldehealth))/2, g.getFont().getSize());
		
		
		
		
		if(kontrolEt()) {
			timer.stop();
			String message="EXPECTOO PATRONUUUMM \n"+
			               "Harcanan büyü : "+ harcanan_buyu +"\n"+
			               "Geçen süre: "+ ((gecen_sure/1000.0))+"\n"+
			               "Engellenen büyü:"+ engellenenbüyü+"\n"+
			               "Harry'nin isabet sayýsý:"+harryisabet;
			
			this.clip.stop();
			JOptionPane.showMessageDialog(this,message);
			
			
			
			System.exit(0);
			
		}
		
	}
	
	
	
	

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
		// repaint çaðrýldýðýnda paint çaðrýlýyor 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(Buyu buyu: buyuler) {
			buyu.setY(buyu.getY()-buyubr);
		}
		// ateþleri çizmemiz gerekli altta repaint var zaten o yüzden paintte ateþleri çizicez
		
		if(started==false) {
			 int harryx=0;// harry x den baþlicak
			 
			 int voldeX=450;
			
		}else {
			
		bellaX+=bellabr;
		if(bellaX>=750) {
			bellabr=-bellabr;
		}
		if(bellaX<0) {
			bellabr=-bellabr;
		}
			
		voldeX+=voldebr;
		if(voldeX>=750) {
			voldebr=-voldebr;
		}
		
		if(voldeX<=0) {
			voldebr=-voldebr;
		}
		}
		
		
		
		repaint();
		// her action perf çalýþtýðýnda repaint çalýþmalý o da painti çalýþtýrcak
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();// tuþlarla ilgili metot

		if(this.started==false) {
			if(c==KeyEvent.VK_ENTER) {
				this.started=true;
			}
		}
		
		if (c == KeyEvent.VK_LEFT) {

			if (harryx <= 0) {

				harryx = 0;

			} else {

				harryx -= harrybr;

			}

		} else if (c == KeyEvent.VK_RIGHT) {

			if (harryx >= 750) {

				harryx = 750;

			} else {

				harryx += harrybr;

			}
			
		}
		else if (c == KeyEvent.VK_CONTROL) {

			
			// 13 nedeni imageýn boþluðunu hesaba kattýk ateþ tam ortadan çýksýn diye
			buyuler.add(new Buyu(harryx+13,485));
			harcanan_buyu++;
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

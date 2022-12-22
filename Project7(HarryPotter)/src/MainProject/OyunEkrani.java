package MainProject;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import java.util.Scanner;
import javax.sound.sampled.*;

public class OyunEkrani extends JFrame {

	
	

	public OyunEkrani(String title) throws HeadlessException {
		super(title);
		// Klavye, ekran veya fareyi desteklemeyen bir ortamda klavyeye, ekrana veya fareye baðlý kod çaðrýldýðýnda atýlýr.
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		OyunEkrani ekran = new OyunEkrani("Harry Potter");
		
		ekran.setResizable(false);
		ekran.setFocusable(false);
		
		ekran.setSize(800,600);
		ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
	    File file = new File("harry.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
		
		
		
		
		Oyun oyun = new Oyun(clip); 
		
		oyun.requestFocus();// Jpanelin klavye iþlemlerini anlamasý için
		oyun.addKeyListener(oyun);
		oyun.setFocusable(true);// odaðý jpanele verdik
		oyun.setFocusTraversalKeysEnabled(false);// klavye iþlemlerini anlamasý için gerekli metod
		
		ekran.add(oyun);
		ekran.setVisible(true);// panel frame eklenince direkt olarak oluþsun
		/*
        File file = new File("harry.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        */
        
        //clip.start();
        	
        
        
        
	
	}
	
}
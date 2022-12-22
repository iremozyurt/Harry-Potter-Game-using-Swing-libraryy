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
		// Klavye, ekran veya fareyi desteklemeyen bir ortamda klavyeye, ekrana veya fareye ba�l� kod �a�r�ld���nda at�l�r.
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
		
		oyun.requestFocus();// Jpanelin klavye i�lemlerini anlamas� i�in
		oyun.addKeyListener(oyun);
		oyun.setFocusable(true);// oda�� jpanele verdik
		oyun.setFocusTraversalKeysEnabled(false);// klavye i�lemlerini anlamas� i�in gerekli metod
		
		ekran.add(oyun);
		ekran.setVisible(true);// panel frame eklenince direkt olarak olu�sun
		/*
        File file = new File("harry.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        */
        
        //clip.start();
        	
        
        
        
	
	}
	
}
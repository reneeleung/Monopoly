import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.Font.*;

public class choose extends JPanel{
  BufferedImage bcg1 = null;
  BufferedImage bcg2 = null;
  BufferedImage bcg3 = null;
  BufferedImage bcg4 = null;
  boolean blna;
    boolean blnb;
      boolean blnc;
        boolean blnd;

  
  // methods 
  public void paintComponent(Graphics g){
    Graphics2D graphic = (Graphics2D)g;
    if(blna==true){
      
      graphic.drawImage(bcg1,0,0,null);
    }else if(blnb==true){
      
      graphic.drawImage(bcg2,0,0,null);
    }else if(blnc==true){
      
      graphic.drawImage(bcg3,0,0,null);
    }else if(blnd==true){
      
      graphic.drawImage(bcg4,0,0,null);
    }
    
    
    
  }
  
  // constructor 
  public choose(){
    super();
    try{
      bcg1 = ImageIO.read(getClass().getResource("select1.png"));  
      bcg2 = ImageIO.read(getClass().getResource("select2.png"));  
      bcg3 = ImageIO.read(getClass().getResource("select3.png"));  
      bcg4 = ImageIO.read(getClass().getResource("select4.png"));  

      
      
    }
    catch(IOException e){
    }
  }
}
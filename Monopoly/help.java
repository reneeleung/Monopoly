import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.Font.*;

public class help extends JPanel{
  BufferedImage bcg = null;
  BufferedImage h2 = null;
  BufferedImage h3 = null;
  boolean bln2;
    boolean bln3;
  // methods
  public void paintComponent(Graphics g){
    Graphics2D graphic = (Graphics2D)g;


    if(bln2==true){
      graphic.drawImage(h2,0,0,null);

      bcg = null;
    }
    else if(bln3==true){
      graphic.drawImage(h3,0,0,null);

    }else{
      graphic.drawImage(bcg,0,0,null);
    }

  }

  // constructor
  public help(){
    super();
    try{
      bcg = ImageIO.read(getClass().getResource("images/help1.png"));
      h2 = ImageIO.read(getClass().getResource("images/help2.png"));
      h3 = ImageIO.read(getClass().getResource("images/help3.png"));  


    }
    catch(IOException e){
    }
  }
}

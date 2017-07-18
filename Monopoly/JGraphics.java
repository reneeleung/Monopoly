import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.Font.*;

public class JGraphics extends JPanel{
  BufferedImage bcg = null;
  BufferedImage d1 = null;
  BufferedImage d2 = null;
  BufferedImage d3 = null;
  BufferedImage d4 = null;
  BufferedImage d5 = null;
  BufferedImage d6 = null;
  boolean blndetention;
  BufferedImage deten = null;
  BufferedImage icon1 = null;
  BufferedImage icon2 = null;
  BufferedImage icon3 = null;
  BufferedImage icon4 = null;
  BufferedImage load[] = new BufferedImage[40];;
  BufferedReader themap;
  String strmap[][];
  String strline[];
  String strload;
  int intcount;
  int intcount2;
  int intran1;
  int intran2;
  int int1x=630;
  int int2x=630;
  int int3x=630;
  int int4x=630;
  int int1y=630;
  int int2y=630;
  int int3y=630;
  int int4y=630;
  int intxf;
  int intyf;
  int intlocation;
  boolean bln1yes;
  boolean bln2yes;
  boolean bln3yes;
  boolean bln4yes;
  boolean start1;
  boolean start2;
  boolean start3;
  boolean start4;
  boolean blnbuy;
  boolean blnbuyhouse;
  boolean blnbuyhotel;
  int intmoves;
  int intminus = 6;
  int intxr;
  int intyr;
  int intmoved;
  int intxlocation;
  int intylocation;
  BufferedImage ccc = null;
  BufferedImage buy = null;
  BufferedImage tradebcg = null;
  BufferedImage tradechoose;
  boolean cccstart;
  boolean blntradechoose;
  boolean blntradeforreal;
  BufferedImage imagebuyhotel;
  BufferedImage imagebuyhouse;

  // methods
  public void paintComponent(Graphics g){
    Graphics2D graphic = (Graphics2D)g;
    graphic.drawImage(bcg,0,0,null);

    if(intran1==1){
      graphic.drawImage(d1,900,280,null);
    }else if(intran1==2){
      graphic.drawImage(d2,900,280,null);
    }else if(intran1==3){
      graphic.drawImage(d3,900,280,null);
    }else if(intran1==4){
      graphic.drawImage(d4,900,280,null);
    }else if(intran1==5){
      graphic.drawImage(d5,900,280,null);
    }else if(intran1==6){
      graphic.drawImage(d6,900,280,null);
    }
    if(intran2==1){
      graphic.drawImage(d1,1030,280,null);
    }else if(intran2==2){
      graphic.drawImage(d2,1030,280,null);
    }else if(intran2==3){
      graphic.drawImage(d3,1030,280,null);
    }else if(intran2==4){
      graphic.drawImage(d4,1030,280,null);
    }else if(intran2==5){
      graphic.drawImage(d5,1030,280,null);
    }else if(intran2==6){
      graphic.drawImage(d6,1030,280,null);
    }
    if(blndetention==true){
      graphic.drawImage(deten,0,0,null);
    }



    strline = new String[19];
    strmap = new String[40][16];

    try{
      themap  = new BufferedReader (new FileReader("config/map.csv"));
    }catch(IOException e){
    }

    for(intcount =0; intcount<40;intcount++){
      try{
        strload = themap.readLine();
      }catch(IOException e){
      }
      strline = strload.split(",");

      for(intcount2=0;intcount2<16;intcount2++){
        try{
          strmap[intcount][intcount2]=strline[intcount2];
        }catch(Exception e){
        }
      }

    }


    for(intcount=0;intcount<40;intcount++){
      try{
        load[intcount]=ImageIO.read(getClass().getResource(strmap[intcount][1]));
      }catch(IOException e){
      }

    }


    for(intcount=0;intcount<40;intcount++){
      if(intcount>0&&intcount<10){
        graphic.drawImage(load[intcount],630-intcount*59,630,null);
      }else if(intcount>10&& intcount<20){
        graphic.drawImage(load[intcount],2,630-(intcount-10)*59,null);
      }else if(intcount>20&& intcount<30){
        graphic.drawImage(load[intcount],(intcount-20)*59+40,2,null);
      }
      else if(intcount> 30 && intcount<40){
        graphic.drawImage(load[intcount],632,(intcount-30)*59+40,null);
      }
      else if(intcount==0||intcount==10 ||intcount==20||intcount==30){
        if(intcount==0){
          graphic.drawImage(load[intcount],630,631,null);
        }else if(intcount==10){
          graphic.drawImage(load[intcount],4,630,null);
        }else if(intcount==20){
          graphic.drawImage(load[intcount],2,2,null);
        }else if(intcount==30){
          graphic.drawImage(load[intcount],630,2,null);
        }
      }
    }
    if(blnbuyhotel){

      try{
        buy = ImageIO.read(getClass().getResource("images/"+intlocation+".png"));
      }catch(IOException e){
      }
      graphic.drawImage(buy,0,0,null);
      graphic.drawImage(imagebuyhotel,0,0,null);


    }
    if(blnbuyhouse){


      try{
        buy = ImageIO.read(getClass().getResource("images/"+intlocation+".png"));
        imagebuyhouse = ImageIO.read(getClass().getResource("images/addhouse.png"));
      }catch(IOException e){
      }
      graphic.drawImage(buy,0,0,null);
      graphic.drawImage(imagebuyhouse,0,0,null);


    }
    if(blnbuy){
      if(strmap[intlocation][0].equals("Prp") || strmap[intlocation][0].equals("Pub") || strmap[intlocation][0].equals("Utl")){
        try{
          buy = ImageIO.read(getClass().getResource("images/"+intlocation+".png"));
        }catch(IOException e){
        }
        graphic.drawImage(buy,0,0,null);
      }
    }
    if(blntradechoose){
      graphic.drawImage(tradechoose,0,0,null);
    }
    if(blntradeforreal){
      graphic.drawImage(tradebcg,0,0,null);
    }
    if(cccstart){
      graphic.drawImage(ccc,0,0,null);
    }

    if(start1){
      if(intxr >50 && intyr == 630 && (int1x-intxr) +(intyr-int1y)< intmoves){
        intxr = intxr-20;
        graphic.drawImage(icon1,intxr,intyr,null);
      }else if(intxr==50 && intyr>50 && (int1x-intxr)+ (int1y-intyr) < intmoves){
        intyr = intyr-20;
        graphic.drawImage(icon1,intxr,intyr,null);
      }else if(intxr<630 && intyr==50 &&  (int1y-intyr)+(intxr-int1x) < intmoves){
        intxr = intxr+20;
        graphic.drawImage(icon1,intxr,intyr,null);
      }else if(intxr==630 && intyr<630 &&  (intyr-int1y)+(intxr-int1x) < intmoves){
        intyr = intyr+20;
        graphic.drawImage(icon1,intxr,intyr,null);
      }
      else{
        int1x = intxr;
        int1y = intyr;
        start1 = false;
      }
    }
    if(start2){

      if(intxr >50 && intyr == 630 && (int2x-intxr) +(intyr-int2y)< intmoves){

        intxr = intxr-20;
        graphic.drawImage(icon2,intxr,intyr,null);
      }else if(intxr==50 && intyr>50 && (int2x-intxr)+ (int2y-intyr) < intmoves){
        intyr = intyr-20;
        graphic.drawImage(icon2,intxr,intyr,null);
      }else if(intxr<630 && intyr==50 &&  (int2y-intyr)+(intxr-int2x) < intmoves){
        intxr = intxr+20;
        graphic.drawImage(icon2,intxr,intyr,null);
      }else if(intxr==630 && intyr<630 &&  (intyr-int2y)+(intxr-int2x) < intmoves){
        intyr = intyr+20;
        graphic.drawImage(icon2,intxr,intyr,null);
      }
      else{
        int2x = intxr;
        int2y = intyr;
        start2 = false;
      }

    }
    if(start3){
      if(intxr >50 && intyr == 630 && (int3x-intxr) +(intyr-int3y)< intmoves){
        intxr = intxr-20;
        graphic.drawImage(icon3,intxr,intyr,null);
      }else if(intxr==50 && intyr>50 && (int3x-intxr)+ (int3y-intyr) < intmoves){
        intyr = intyr-20;
        graphic.drawImage(icon3,intxr,intyr,null);
      }else if(intxr<630 && intyr==50 &&  (int3y-intyr)+(intxr-int3x) < intmoves){
        intxr = intxr+20;
        graphic.drawImage(icon3,intxr,intyr,null);
      }else if(intxr==630 && intyr<630 &&  (intyr-int3y)+(intxr-int3x) < intmoves){
        intyr = intyr+20;
        graphic.drawImage(icon3,intxr,intyr,null);
      }
      else{
        int3x = intxr;
        int3y = intyr;
        start3 = false;
      }

    }
    if(start4){
      if(intxr >50 && intyr == 630 && (int4x-intxr) +(intyr-int4y)< intmoves){
        intxr = intxr-20;
        graphic.drawImage(icon4,intxr,intyr,null);
      }else if(intxr==50 && intyr>50 && (int4x-intxr)+ (int4y-intyr) < intmoves){
        intyr = intyr-20;
        graphic.drawImage(icon4,intxr,intyr,null);
      }else if(intxr<630 && intyr==50 &&  (int4y-intyr)+(intxr-int4x) < intmoves){
        intxr = intxr+20;
        graphic.drawImage(icon4,intxr,intyr,null);
      }else if(intxr==630 && intyr<630 &&  (intyr-int4y)+(intxr-int4x) < intmoves){
        intyr = intyr+20;
        graphic.drawImage(icon4,intxr,intyr,null);
      }
      else{
        int4x = intxr;
        int4y = intyr;
        start4 = false;
      }

    }
    graphic.drawImage(icon1,int1x,int1y,null);
    graphic.drawImage(icon2,int2x,int2y,null);
    graphic.drawImage(icon3,int3x,int3y,null);
    graphic.drawImage(icon4,int4x,int4y,null);
  }

  // constructor
  public JGraphics(){
    super();
    try{
      bcg = ImageIO.read(getClass().getResource("images/bcg.png"));
      d1 = ImageIO.read(getClass().getResource("images/d1.png"));
      d2 = ImageIO.read(getClass().getResource("images/d2.png"));
      d3 = ImageIO.read(getClass().getResource("images/d3.png"));
      d4 = ImageIO.read(getClass().getResource("images/d4.png"));
      d5 = ImageIO.read(getClass().getResource("images/d5.png"));
      d6 = ImageIO.read(getClass().getResource("images/d6.png"));
      deten = ImageIO.read(getClass().getResource("images/deten.png"));
      icon1= ImageIO.read(getClass().getResource("images/pencil.png"));
      icon2= ImageIO.read(getClass().getResource("images/glue.png"));
      icon3= ImageIO.read(getClass().getResource("images/toilet.png"));
      icon4= ImageIO.read(getClass().getResource("images/eraser.png"));

      ccc = ImageIO.read(getClass().getResource("images/ccc.png"));
      tradechoose = ImageIO.read(getClass().getResource("images/tradechoose.png"));
      tradebcg = ImageIO.read(getClass().getResource("images/tradebcg.png"));
      imagebuyhotel = ImageIO.read(getClass().getResource("images/addhotel.png"));
      imagebuyhouse = ImageIO.read(getClass().getResource("images/addhouse.png"));
    }
    catch(IOException e){
    }
  }
}

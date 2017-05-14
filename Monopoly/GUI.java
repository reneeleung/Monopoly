import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.ImageIcon.*;
import javax.swing.text.*;
import javax.swing.table.*;
import java.net.*;
import java.io.*;
import javax.imageio.*;

public class GUI implements ActionListener{
  // properties
  JFrame theframe;
  JGraphics thepanel;
  mainmenu themainmenu;
  about about;
  help help;
  choose choose;
  int intPort = 0;
  int intloadcount;
  int intcount2;
  int intMyNumber;
  int intFieldTrip = 0;
  int intDetentionCount = 0;
  int intToTrade = 1;
  String strIPAddress = "";
  String strmap[][];
  String strline[];
  String strload;
  String strName = "me!!!!";
  String strPiece = "";
  String[][] strplayerinfo = new String[4][5];
  Timer thetimer;
  JScrollPane players;
  JTable playerslist;
  DefaultTableModel model;
  JTextArea textReceived;
  JScrollPane thescroll;
  JButton buttonplayers;
  JButton buttonsend;
  JButton mapcards[];
  JButton buttonroll;
  JButton buttontrade;
  JButton buttonend;
  JButton buttonnewgame;
  JButton buttonhelp;
  JButton buttonabout;
  JButton buttonquit;
  JButton buttonnext;
  JButton buttonnext2;
  JButton buttonstart;
  JButton player1;
  JButton player2;
  JButton player3;
  JButton player4;
  JButton server;
  JButton client;
  JButton buttonbuy;
  JButton buttonbuyhouse;
  JButton buttonbuyhotel;
  JButton buttondecline;
  JButton tradeplayer1;
  JButton tradeplayer2;
  JButton tradeplayer3;
  JButton tradeplayer4;
  JTextField IP;
  JTextField port;
  JTextField playername;
  JTextField textToSend;
  JTextField cardcontentfield;
  JLabel IPlabel;
  JLabel portlabel;
  SuperSocketMaster ssm;
  boolean blnstart;
  boolean blnconnected;
  BufferedReader themap;
  player me;
  BufferedReader theccc;
  String strccc[][];
  int intcardrandom;
  JTextField locationnum;
  JTextField marknum;
  JList tradelist;
  JScrollPane tradescroll;
  String strnamelist[]={"dubaiuqwd","dwqdwquyd","dwqydgquwy"};
  String strtradelist[][] = {{"ewe","2"},{"dqwdqw","3"},{"sade","5"}};
  JLabel labelproperty;
  JList propertylist;
  JScrollPane propertyscroll;
  JTable tradetable;
  bankrupt bankrupt;
  boolean allplayerdie;
  win win;



  // methods
  public void actionPerformed(ActionEvent evt){
    // timer
    if(evt.getSource() == thetimer){
      // repaint command for the map
      if(blnstart==true){
        thepanel.repaint();
      }
    }

    // in the main menu, if the user click on "New Game"
    if(evt.getSource()==buttonnewgame){
      // new panel for the screen
      choose = new choose();
      choose.setLayout(null);
      choose.setPreferredSize(new Dimension(1280,720));
      choose.blna=true;
      choose.blnb=false;
      choose.blnc=false;
      choose.blnd=false;
      theframe.setContentPane(choose);
      theframe.pack();
      choose.repaint();
      player2.setEnabled(false);
      player1.setEnabled(false);
      player3.setEnabled(false);
      player4.setEnabled(false);
      choose.add(player2);
      choose.add(player1);
      choose.add(player3);
      choose.add(player4);

      client = new JButton("Guest");
      client.setSize(140,45);
      client.setLocation(30,330);
      client.setFont(new Font("Nanum Brush Script", Font.PLAIN, 30));
      client.setEnabled(false);
      client.addActionListener(this);
      choose.add(client);
      server = new JButton("Host");
      server.setSize(140,45);
      server.setLocation(30,380);
      server.setFont(new Font("Nanum Brush Script", Font.PLAIN, 30));
      server.setEnabled(false);
      server.addActionListener(this);
      choose.add(server);

      IPlabel = new JLabel("IP Address");
      IPlabel.setSize(140,45);
      IPlabel.setLocation(30,450);
      IPlabel.setFont(new Font("Nanum Brush Script", Font.PLAIN, 30));
      choose.add(IPlabel);
      IP = new JTextField();
      IP.setSize(140,30);
      IP.setLocation(30,490);
      IP.setFont(new Font("Nanum Brush Script", Font.PLAIN, 20));
      IP.addActionListener(this);
      choose.add(IP);

      portlabel = new JLabel("Port Number");
      portlabel.setSize(140,45);
      portlabel.setLocation(30,540);
      portlabel.setFont(new Font("Nanum Brush Script", Font.PLAIN, 30));
      choose.add(portlabel);
      port = new JTextField();
      port.setSize(140,30);
      port.setLocation(30,580);
      port.setFont(new Font("Nanum Brush Script", Font.PLAIN, 20));
      port.addActionListener(this);
      choose.add(port);

      buttonstart = new JButton("Connect to the Game!");
      buttonstart.setSize(500,50);
      buttonstart.setLocation(820,660);
      buttonstart.setFont(new Font("Nanum Brush Script", Font.PLAIN, 34));
      buttonstart.setBorderPainted(false);
      buttonstart.setContentAreaFilled(false);
      buttonstart.setFocusPainted(false);
      buttonstart.setOpaque(false);
      buttonstart.addActionListener(this);
      buttonstart.setEnabled(false);
      choose.add(buttonstart);

      playername = new JTextField();
      playername.setFont(new Font("Nanum Brush Script", Font.PLAIN, 34));
      playername.setSize(334,55);
      playername.setLocation(900,540);
      playername.addActionListener(this);
      choose.add(playername);

    }
    if(evt.getSource() == client){
      System.out.println("Start client");
      ssm = new SuperSocketMaster(strIPAddress,intPort,this);
      blnconnected = ssm.connect();
      if(blnconnected){
        client.setEnabled(false);
        server.setEnabled(false);
      }
      try{
        me = new player(4, strName);
      }catch(IOException e){
        System.out.println("cannot create player");
      }
      System.out.println("Created me i am client!");
      player1.setEnabled(true);
      player2.setEnabled(true);
      player3.setEnabled(true);
      player4.setEnabled(true);
    }
    if(evt.getSource() == server){
      player1.setEnabled(true);
      player2.setEnabled(true);
      player3.setEnabled(true);
      player4.setEnabled(true);
      System.out.println("Start server");
      ssm = new SuperSocketMaster(intPort,this);
      System.out.println(ssm.getMyAddress());
      blnconnected = ssm.connect();
      if(blnconnected){
        client.setEnabled(false);
        server.setEnabled(false);
      }
      try{
        me = new player(4, strName);
      }catch(IOException e){
        System.out.println("cannot create player");
      }
      System.out.println("Created me i am server!");
    }
    if(evt.getSource() == IP){
      if(!IP.getText().equals("")){
        client.setEnabled(true);
        strIPAddress = IP.getText();
      }
    }
    if(evt.getSource() == port){
      if(!port.getText().equals("")){
        intPort = Integer.parseInt(port.getText());
        if(!strName.equals("me!!!!")){
          server.setEnabled(true);
        }
      }
    }
    if(evt.getSource() == playername){
      strName = playername.getText();
    }
    if(evt.getSource() == player1 || evt.getSource() == player2 || evt.getSource() == player3 || evt.getSource() == player4){
      if(!strName.equals("me!!!!") && intPort != 0){
        buttonstart.setEnabled(true);
        int intPiece = 0;
        if(evt.getSource()==player1){
          // in the game initiation menu, if the player choose the first icon (pencil)
          choose.blna=true;
          choose.blnb=false;
          choose.blnc=false;
          choose.blnd=false;
          choose.repaint();
          player2.setEnabled(false);
          player3.setEnabled(false);
          player4.setEnabled(false);
          me.intPlayer = 1;
          intMyNumber = 1;
          intPiece = 0;
        }else if(evt.getSource()==player2){
          // in the game initiation menu, if the player choose the second icon (glue)
          choose.blna=false;
          choose.blnb=true;
          choose.blnc=false;
          choose.blnd=false;
          choose.repaint();
          player1.setEnabled(false);
          player3.setEnabled(false);
          player4.setEnabled(false);
          me.intPlayer = 2;
          intMyNumber = 2;
          intPiece = 1;
        }else if(evt.getSource() == player3){
          // in the game initiation menu, if the player choose the third icon (toilet paper)
          choose.blna=false;
          choose.blnb=false;
          choose.blnc=true;
          choose.blnd=false;
          choose.repaint();
          player1.setEnabled(false);
          player2.setEnabled(false);
          player4.setEnabled(false);
          me.intPlayer = 3;
          intMyNumber = 3;
          intPiece = 2;
        }else if(evt.getSource() == player4){
          // in the game initiation menu, if the player choose the fourth icon (eraser)
          choose.blna=false;
          choose.blnb=false;
          choose.blnc=false;
          choose.blnd=true;
          choose.repaint();
          player1.setEnabled(false);
          player2.setEnabled(false);
          player3.setEnabled(false);
          me.intPlayer = 4;
          intMyNumber = 4;
          intPiece = 3;
        }
        me.intPlayer = intMyNumber;
        me.choosePiece(ssm, intMyNumber, intPiece);
        if(me.intPiece == 0){
          strPiece = "pencil";
        }else if(me.intPiece == 1){
          strPiece = "glue";
        }else if(me.intPiece == 2){
          strPiece = "toilet paper";
        }else if(me.intPiece == 3){
          strPiece = "eraser";
        }
        strplayerinfo[intMyNumber - 1][0] = strPiece;
        strplayerinfo[intMyNumber - 1][1] = me.strName;
        strplayerinfo[intMyNumber - 1][2] = ""+me.intMarks;
        strplayerinfo[intMyNumber - 1][3] = ""+me.intCurrentLocation;
        strplayerinfo[intMyNumber - 1][4] = ""+me.blnDetention;
        strnamelist = new String[28];
        strtradelist = new String[28][2];
        intcount2=0;
        int intcount = 0;
        for(intcount=0; intcount<=27; intcount++){
          if(me.strProperty[intcount][2].equals("T")){
            strnamelist[intcount2]=me.strProperty[intcount][0];
            strtradelist[intcount2][0]=me.strProperty[intcount][0];
            strtradelist[intcount2][1]=me.strProperty[intcount][1];
            intcount2++;
          }
        }
      }
    }
    if(evt.getSource() == locationnum){
      locationnum.setEnabled(false);
      marknum.setEnabled(true);
      int intLocation = Integer.parseInt(locationnum.getText());
      me.tradeProp(ssm,intToTrade,intLocation);
      //delete my property
      //update strmap and me.strproperty
      int intArrayRow = 0;
      int intCount = 0;
      for(intCount = 0; intCount <= 27; intCount++){
        if(Integer.parseInt(me.strProperty[intCount][1]) == intLocation){
          intArrayRow = intCount;
        }
      }
      me.strProperty[intArrayRow][2] = "F";
      me.sendPlayerInfo(ssm);
      strmap[intLocation][3] = ""+intToTrade;
    }
    if(evt.getSource() == marknum){
      marknum.setEnabled(false);
      thepanel.blntradeforreal = false;
      int intMark = Integer.parseInt(marknum.getText());
      me.tradeMarks(ssm,intToTrade,intMark);
      //update strplayerinfo (subtract intmarks)
      me.intMarks = me.intMarks - intMark;
      me.sendPlayerInfo(ssm);
    }
    if(evt.getSource() == buttonend){
      try{
        tradescroll.setLocation(10000,10000);
      }catch(NullPointerException e){
      }
      try{
        locationnum.setLocation(10000,10000);
      }catch(NullPointerException e){
      }
      try{
        marknum.setLocation(10000,10000);
      }catch(NullPointerException e){
      }
      try{
        cardcontentfield.setLocation(10000,10000);
      }catch(NullPointerException e){
      }

      thepanel.cccstart = false;
      if(me.intMarks <= 0){
        bankrupt = new bankrupt();
        bankrupt.setLayout(null);
        bankrupt.setPreferredSize(new Dimension(1280,720));
        theframe.setContentPane(bankrupt);
        theframe.pack();

      }

      // if win the game
      if(allplayerdie){
        win = new win();
        win.setLayout(null);
        win.setPreferredSize(new Dimension(1280,720));
        theframe.setContentPane(win);
        theframe.pack();
      }
      me.endTurn(ssm, intMyNumber);
      strplayerinfo[intMyNumber - 1][0] = strPiece;
      strplayerinfo[intMyNumber - 1][1] = me.strName;
      strplayerinfo[intMyNumber - 1][2] = ""+me.intMarks;
      strplayerinfo[intMyNumber - 1][3] = ""+me.intCurrentLocation;
      strplayerinfo[intMyNumber - 1][4] = ""+me.blnDetention;
      strnamelist = new String[28];
      strtradelist = new String[28][2];
      intcount2=0;
      int intcount = 0;
      for(intcount=0; intcount<=27; intcount++){
        if(me.strProperty[intcount][2].equals("T")){
          strnamelist[intcount2]=me.strProperty[intcount][0];
          strtradelist[intcount2][0]=me.strProperty[intcount][0];
          strtradelist[intcount2][1]=me.strProperty[intcount][1];
          intcount2++;
        }
      }
      buttonroll.setEnabled(false);
      buttontrade.setEnabled(false);
      buttonend.setEnabled(false);
    }
    if(evt.getSource() == buttonbuy){
      int intPayment;
      intPayment = Integer.parseInt(strmap[me.intCurrentLocation][4]);
      strmap[me.intCurrentLocation][3] = ""+intMyNumber;
      me.buy(ssm, intMyNumber, me.intCurrentLocation, intPayment);
      strplayerinfo[intMyNumber - 1][0] = strPiece;
      strplayerinfo[intMyNumber - 1][1] = me.strName;
      strplayerinfo[intMyNumber - 1][2] = ""+me.intMarks;
      strplayerinfo[intMyNumber - 1][3] = ""+me.intCurrentLocation;
      strplayerinfo[intMyNumber - 1][4] = ""+me.blnDetention;
      strnamelist = new String[28];
      strtradelist = new String[28][2];
      intcount2=0;
      int intcount = 0;
      for(intcount=0; intcount<=27; intcount++){
        if(me.strProperty[intcount][2].equals("T")){
          strnamelist[intcount2]=me.strProperty[intcount][0];
          strtradelist[intcount2][0]=me.strProperty[intcount][0];
          strtradelist[intcount2][1]=me.strProperty[intcount][1];
          intcount2++;
        }
      }
      thepanel.blnbuy = false;
      buttondecline.setLocation(10000,10000);
      buttonbuy.setLocation(10000,10000);
      thepanel.blnbuyhouse = false;
      thepanel.blnbuyhotel = false;
    }
    if(evt.getSource() == buttonbuyhouse){
      thepanel.blnbuyhouse = false;
      buttondecline.setLocation(10000,10000);
      buttonbuyhouse.setLocation(10000,10000);
      //check payment
      int intPayment = Integer.parseInt(strmap[me.intCurrentLocation][5]);
      me.build(ssm, intMyNumber, me.intCurrentLocation, intPayment, "house");
      strplayerinfo[intMyNumber - 1][0] = strPiece;
      strplayerinfo[intMyNumber - 1][1] = me.strName;
      strplayerinfo[intMyNumber - 1][2] = ""+me.intMarks;
      strplayerinfo[intMyNumber - 1][3] = ""+me.intCurrentLocation;
      strplayerinfo[intMyNumber - 1][4] = ""+me.blnDetention;
      strnamelist = new String[28];
      strtradelist = new String[28][2];
      intcount2=0;
      int intcount = 0;
      for(intcount=0; intcount<=27; intcount++){
        if(me.strProperty[intcount][2].equals("T")){
          strnamelist[intcount2]=me.strProperty[intcount][0];
          strtradelist[intcount2][0]=me.strProperty[intcount][0];
          strtradelist[intcount2][1]=me.strProperty[intcount][1];
          intcount2++;
        }
      }
    }
    if(evt.getSource() == buttonbuyhotel){
      thepanel.blnbuyhotel = false;
      buttondecline.setLocation(10000,10000);
      buttonbuyhotel.setLocation(10000,10000);
      //check payment
      int intPayment = Integer.parseInt(strmap[me.intCurrentLocation][6]);
      me.build(ssm, intMyNumber, me.intCurrentLocation, intPayment, "hotel");
      strplayerinfo[intMyNumber - 1][0] = strPiece;
      strplayerinfo[intMyNumber - 1][1] = me.strName;
      strplayerinfo[intMyNumber - 1][2] = ""+me.intMarks;
      strplayerinfo[intMyNumber - 1][3] = ""+me.intCurrentLocation;
      strplayerinfo[intMyNumber - 1][4] = ""+me.blnDetention;
      strnamelist = new String[28];
      strtradelist = new String[28][2];
      intcount2=0;
      int intcount = 0;
      for(intcount=0; intcount<=27; intcount++){
        if(me.strProperty[intcount][2].equals("T")){
          strnamelist[intcount2]=me.strProperty[intcount][0];
          strtradelist[intcount2][0]=me.strProperty[intcount][0];
          strtradelist[intcount2][1]=me.strProperty[intcount][1];
          intcount2++;
        }
      }
    }
    if(evt.getSource() == buttondecline){
      //exit offer
      thepanel.blnbuy = false;
      buttondecline.setLocation(10000,10000);
      buttonbuy.setLocation(10000,10000);
      buttonbuyhouse.setLocation(10000,10000);
      buttonbuyhotel.setLocation(10000,10000);
      thepanel.blnbuyhouse = false;
      thepanel.blnbuyhotel = false;
    }
    if(evt.getSource() == buttontrade){
      //only one character of the two traders need to press the trade button
      //select one character you want to trade with screen
      //construct tradeplayers
      if(evt.getSource() == buttontrade){
        thepanel.blntradechoose=true;
        tradeplayer1 = new JButton();
        tradeplayer1.setSize(80,220);
        tradeplayer1.setLocation(120,300);
        tradeplayer1.addActionListener(this);
        tradeplayer1.setOpaque(false);
        tradeplayer1.setContentAreaFilled(false);
        tradeplayer1.setBorderPainted(false);
        thepanel.add(tradeplayer1);
        if(intMyNumber == 1 || strplayerinfo[0][0] == null){
          tradeplayer1.setEnabled(false);
        }
        tradeplayer2 = new JButton();
        tradeplayer2.setSize(80,220);
        tradeplayer2.setLocation(240,300);
        tradeplayer2.addActionListener(this);
        tradeplayer2.setOpaque(false);
        tradeplayer2.setContentAreaFilled(false);
        tradeplayer2.setBorderPainted(false);
        thepanel.add(tradeplayer2);
        if(intMyNumber == 2 || strplayerinfo[1][0] == null){
          tradeplayer2.setEnabled(false);
        }
        tradeplayer3 = new JButton();
        tradeplayer3.setSize(80,220);
        tradeplayer3.setLocation(380,300);
        tradeplayer3.addActionListener(this);
        tradeplayer3.setOpaque(false);
        tradeplayer3.setContentAreaFilled(false);
        tradeplayer3.setBorderPainted(false);
        thepanel.add(tradeplayer3);
        if(intMyNumber == 3 || strplayerinfo[2][0] == null){
          tradeplayer3.setEnabled(false);
        }
        tradeplayer4 = new JButton();
        tradeplayer4.setSize(80,220);
        tradeplayer4.setLocation(520,300);
        tradeplayer4.addActionListener(this);
        tradeplayer4.setOpaque(false);
        tradeplayer4.setContentAreaFilled(false);
        tradeplayer4.setBorderPainted(false);
        thepanel.add(tradeplayer4);
        if(intMyNumber == 4 || strplayerinfo[3][0] == null){
          tradeplayer4.setEnabled(false);
        }
      }
      //"TRD" networking -> enter trade screen for both players
      //select a property of yours and enter the intmarks you are giving
      //(this would have been discussed already in chat box)
      //no request required, your selected property and and marks will be delivered

    }else if(evt.getSource() == tradeplayer1 || evt.getSource() == tradeplayer2 || evt.getSource() == tradeplayer3 || evt.getSource() == tradeplayer4){
      thepanel.blntradechoose= false;
      thepanel.blntradeforreal= true;

      locationnum = new JTextField();
      marknum = new JTextField();
      locationnum.setSize(150,50);
      locationnum.setLocation(400,280);
      locationnum.addActionListener(this);
      thepanel.add(locationnum);
      marknum.setSize(150,50);
      marknum.setLocation(400,460);
      marknum.setEnabled(false);
      marknum.addActionListener(this);
      thepanel.add(marknum);

      Object rowData2[][]=strtradelist;
      Object columnNames2[] = {"Property", "Location Number"};

      tradetable = new JTable(rowData2,columnNames2);
      tradetable.setSelectionBackground(new Color(128,0,0));
      tradetable.setBackground(new Color(216,174,26));
      tradetable.setFont(new Font("Meiryo", Font.PLAIN, 15));
      tradetable.setRowHeight(30);

      tradescroll = new JScrollPane(tradetable);
      tradescroll.setSize(200,330);
      tradescroll.setLocation(140,210);
      thepanel.add(tradescroll);

      if(evt.getSource() == tradeplayer1){
        intToTrade = 1;
        me.entertrade(ssm, intMyNumber, 1);
      }else if(evt.getSource() == tradeplayer2){
        intToTrade = 2;
        me.entertrade(ssm, intMyNumber, 2);
      }else if(evt.getSource() == tradeplayer3){
        intToTrade = 3;
        me.entertrade(ssm, intMyNumber, 3);
      }else if(evt.getSource() == tradeplayer4){
        intToTrade = 4;
        me.entertrade(ssm, intMyNumber, 4);
      }
    }
    if(evt.getSource() == ssm){
      String strLine = "";
      String strCommand[];
      strLine = ssm.readText();
      strCommand = strLine.split(",");
      if(strCommand[0].equals("CHT")){
        textReceived.append(strCommand[1]+": "+strCommand[2]+"\n");
        textReceived.setCaretPosition(textReceived.getDocument().getLength());
      }else if(strCommand[0].equals("TRD")){
        if(Integer.parseInt(strCommand[1]) == intMyNumber || Integer.parseInt(strCommand[2]) == intMyNumber){
          //load trade screen
          //load all properties intmynumber own by checking me.strProperty[][]
          //select property first -> me.tradeProp
          //read textfield of intmoney -> me.tradeMarks
          thepanel.blntradechoose= false;
          thepanel.blntradeforreal= true;

          locationnum = new JTextField();
          marknum = new JTextField();
          locationnum.setSize(150,50);
          locationnum.setLocation(400,280);
          thepanel.add(locationnum);
          marknum.setSize(150,50);
          marknum.setLocation(400,460);
          thepanel.add(marknum);

          Object rowData2[][]=strtradelist;
          Object columnNames2[] = {"Property", "Location Number"};

          tradetable = new JTable(rowData2,columnNames2);
          tradetable.setSelectionBackground(new Color(128,0,0));
          tradetable.setBackground(new Color(216,174,26));
          tradetable.setFont(new Font("Meiryo", Font.PLAIN, 15));
          tradetable.setRowHeight(30);

          tradescroll = new JScrollPane(tradetable);
          tradescroll.setSize(200,330);
          tradescroll.setLocation(140,210);
          thepanel.add(tradescroll);


        }
      }else if(strCommand[0].equals("CHB")){
        //update strmap (owner)
        //update strplayerinfo (set selected "t")
        int intArrayRow = 0;
        int intCount = 0;
        for(intCount = 0; intCount <= 27; intCount++){
          if(Integer.parseInt(me.strProperty[intCount][1]) == Integer.parseInt(strCommand[2])){
            intArrayRow = intCount;
          }
        }
        if(Integer.parseInt(strCommand[1]) == intMyNumber){
          me.strProperty[intArrayRow][2] = "T";
          me.sendPlayerInfo(ssm);
        }
        strmap[Integer.parseInt(strCommand[2])][3] = strCommand[1];
      }else if(strCommand[0].equals("CHA")){
        //update strplayerinfo (add intmarks)
        if(Integer.parseInt(strCommand[1]) == intMyNumber){
          me.intMarks = me.intMarks + Integer.parseInt(strCommand[2]);
          me.sendPlayerInfo(ssm);
        }
      }else if(strCommand[0].equals("MOV")){
        //animation moving of player in ALL SCREENS
        thepanel.intmoves = (Integer.parseInt(strCommand[3]) - Integer.parseInt(strCommand[2]))*58;
        if(Integer.parseInt(strCommand[1]) == 1){
          //pencil
          thepanel.intxr = thepanel.int1x;
          thepanel.intyr = thepanel.int1y;
          thepanel.start1=true;
        }else if(Integer.parseInt(strCommand[1]) == 2){
          //glue
          thepanel.intxr = thepanel.int2x;
          thepanel.intyr = thepanel.int2y;
          thepanel.start2=true;
        }else if(Integer.parseInt(strCommand[1]) == 3){
          //toilet paper
          thepanel.intxr = thepanel.int3x;
          thepanel.intyr = thepanel.int3y;
          thepanel.start3=true;
        }else if(Integer.parseInt(strCommand[1]) == 4){
          //eraser
          thepanel.intxr = thepanel.int4x;
          thepanel.intyr = thepanel.int4y;
          thepanel.start4=true;
        }
      }else if(strCommand[0].equals("BUY")){
        strmap[Integer.parseInt(strCommand[2])][3] = strCommand[1];
      }else if(strCommand[0].equals("END")){
        if(Integer.parseInt(strCommand[1]) == intMyNumber){
          buttonroll.setEnabled(false);
          buttontrade.setEnabled(false);
          buttonend.setEnabled(false);
        } else {
          if(tradescroll != null && tradetable != null && locationnum != null && marknum != null){
            tradescroll.setVisible(false);
            tradetable.setVisible(false);
            locationnum.setVisible(false);
            marknum.setVisible(false);
            thepanel.blntradeforreal= false;
          }

        }
        if(Integer.parseInt(strCommand[1]) == 4){
          strCommand[1] = "0";
        }
        int intNext = Integer.parseInt(strCommand[1]) + 1;
        if(intNext == intMyNumber){
          if(me.blnDetention == false){
            buttonroll.setEnabled(true);
            buttontrade.setEnabled(true);
            buttonend.setEnabled(true);
          }else{
            intDetentionCount++;
            ssm.sendText("END,"+intMyNumber);
            ssm.sendText("CHT,"+intMyNumber+",is in jail and skips a turn");
            if(intDetentionCount == 3){
              intDetentionCount = 0;
              me.blnDetention = false;
              thepanel.blndetention = false;
            }
          }
        }else if(strplayerinfo[Integer.parseInt(strCommand[1])][0] == null){
          ssm.sendText("END,"+ (Integer.parseInt(strCommand[1]) + 1));
          if(Integer.parseInt(strCommand[1]) < 3){
            if((Integer.parseInt(strCommand[1]) + 2) == intMyNumber){
              if(me.blnDetention == false){
                buttonroll.setEnabled(true);
                buttontrade.setEnabled(true);
                buttonend.setEnabled(true);
              }else{
                intDetentionCount++;
                ssm.sendText("END,"+intMyNumber);
                ssm.sendText("CHT,"+intMyNumber+",is in jail and skips a turn");
                if(intDetentionCount == 3){
                  intDetentionCount = 0;
                  me.blnDetention = true;
                }
              }
            }
          }else if(Integer.parseInt(strCommand[1]) == 3){
            strCommand[1] = "-1";
            if((Integer.parseInt(strCommand[1]) + 2) == intMyNumber){
              if(me.blnDetention == false){
                buttonroll.setEnabled(true);
                buttontrade.setEnabled(true);
                buttonend.setEnabled(true);
              }else{
                intDetentionCount++;
                ssm.sendText("END,"+intMyNumber);
                ssm.sendText("CHT,"+intMyNumber+",is in jail and skips a turn");
                if(intDetentionCount == 3){
                  intDetentionCount = 0;
                  me.blnDetention = true;
                }
              }
            }
          }else if(Integer.parseInt(strCommand[1]) == 4){
            strCommand[1] = "0";
            if((Integer.parseInt(strCommand[1]) + 2) == intMyNumber){
              if(me.blnDetention == false){
                buttonroll.setEnabled(true);
                buttontrade.setEnabled(true);
                buttonend.setEnabled(true);
              }else{
                intDetentionCount++;
                ssm.sendText("END,"+intMyNumber);
                ssm.sendText("CHT,"+intMyNumber+",is in jail and skips a turn");
                if(intDetentionCount == 3){
                  intDetentionCount = 0;
                  me.blnDetention = true;
                }
              }
            }
          }
        }
      }else if(strCommand[0].equals("DET")){
        //animation to detention
        int intplayernum = Integer.parseInt(strCommand[1]);
        if(intplayernum == intMyNumber){
          thepanel.blndetention = true;
        }
        if(intplayernum==1){
          thepanel.int1x = 50;
          thepanel.int1y = 630;

        }else if(intplayernum==2){
          thepanel.int2x = 50;
          thepanel.int2y = 630;

        }else if(intplayernum==3){
          thepanel.int3x = 50;
          thepanel.int3y = 630;
        }else if(intplayernum==4){
          thepanel.int4x = 50;
          thepanel.int4y = 630;
        }
      }else if(strCommand[0].equals("CHP")){
        if(Integer.parseInt(strCommand[2]) == 0){
          player1.setEnabled(false);
        }else if(Integer.parseInt(strCommand[2]) == 1){
          player2.setEnabled(false);
        }else if(Integer.parseInt(strCommand[2]) == 2){
          player3.setEnabled(false);
        }else if(Integer.parseInt(strCommand[2]) == 3){
          player4.setEnabled(false);
        }
      }else if(strCommand[0].equals("BHS")){
        strmap[Integer.parseInt(strCommand[2])][9] = ""+Integer.parseInt(strmap[Integer.parseInt(strCommand[2])][9]) + Integer.parseInt(strCommand[4]);

      }else if(strCommand[0].equals("BHT")){
        strmap[Integer.parseInt(strCommand[2])][14] = ""+Integer.parseInt(strmap[Integer.parseInt(strCommand[2])][14]) + Integer.parseInt(strCommand[4]);

      }else if(strCommand[0].equals("SEL")){

      }else if(strCommand[0].equals("SHS")){

      }else if(strCommand[0].equals("SHT")){

      }else if(strCommand[0].equals("PLI")){
        int intPlayerIcon = 0;
        if(strCommand[1].equals("pencil")){
          intPlayerIcon = 1;
        }else if(strCommand[1].equals("glue")){
          intPlayerIcon = 2;
        }else if(strCommand[1].equals("toilet paper")){
          intPlayerIcon = 3;
        }else if(strCommand[1].equals("eraser")){
          intPlayerIcon = 4;
        }
        int intCount = 0;
        for(intCount = 0; intCount <=4; intCount++){
          strplayerinfo[intPlayerIcon - 1][intCount] = strCommand[intCount + 1];
        }
      }else if(strCommand[0].equals("RNT")){
        if(Integer.parseInt(strCommand[1]) == intMyNumber){
          if(me.blnDetention == false){
            me.intMarks = me.intMarks + Integer.parseInt(strCommand[2]);
            me.sendPlayerInfo(ssm);
          }
        }
      }else if(strCommand[0].equals("AFT")){
        intFieldTrip = intFieldTrip + Integer.parseInt(strCommand[1]);
      }else if(strCommand[0].equals("CFT")){
        intFieldTrip = 0;
      }
    }
    // in the main menu, if the player choose "About"
    if(evt.getSource()==buttonabout){
      about = new about();
      about.setLayout(null);
      about.setPreferredSize(new Dimension(1280,720));
      theframe.setContentPane(about);
      theframe.pack();
      buttonnewgame = new JButton("Start");
      buttonnewgame.setSize(100,30);
      buttonnewgame.setLocation(1180,690);
      buttonnewgame.addActionListener(this);
      about.add(buttonnewgame);
    }
    // in the main menu, if the player choose "Help"
    if(evt.getSource()==buttonhelp){
      help = new help();
      help.setLayout(null);
      help.setPreferredSize(new Dimension(1280,720));
      theframe.setContentPane(help);
      theframe.pack();

      buttonnext = new JButton("Next");
      buttonnext.setSize(100,30);
      buttonnext.setLocation(1180,690);
      buttonnext.addActionListener(this);
      help.add(buttonnext);
    }
    // in the "Help" menu, if the reader finish reading the first page, go to the second page
    if(evt.getSource()==buttonnext){
      help.bln2= true;
      buttonnext2 = new JButton("Next");
      buttonnext2.setSize(100,30);
      buttonnext2.setLocation(1180,690);
      buttonnext2.addActionListener(this);
      buttonnext.setLocation(900000,6900000);
      help.add(buttonnext2);
      help.repaint();
    }
    // in the "Help" menu, if the reader finish reading the second page, go to the third page
    if(evt.getSource()==buttonnext2){
      help.bln3= true;
      help.bln2= false;
      buttonnewgame = new JButton("Start");
      buttonnewgame.setSize(100,30);
      buttonnewgame.setLocation(1180,690);
      buttonnewgame.addActionListener(this);
      buttonnext2.setLocation(900000,6900000);
      help.add(buttonnewgame);
      help.repaint();
    }
    // if the player chooses quit in the main menu, then close the frame
    if(evt.getSource() == buttonquit){
      System.exit(0);
    }
    // if the player finish choosing the character, then goes to the map and start the game
    if(evt.getSource() == buttonstart){
      strName = playername.getText();
      if(choose.blna==true){
        // 1 for pencil
        intMyNumber = 1;
      }else if(choose.blnb==true){
        // 2 for glue
        intMyNumber = 2;
      }else if(choose.blnc==true){
        // 3 for toilet paper
        intMyNumber = 3;
      }else if(choose.blnd==true){
        // 4 for eraser
        intMyNumber = 4;
      }
      ssm.sendText("END,0");
      blnstart=true;
      thepanel = new JGraphics();
      thepanel.setLayout(null);
      thepanel.setPreferredSize(new Dimension(1280,720));
      theframe.setContentPane(thepanel);
      theframe.pack();

      mapcards = new JButton[40];
      strline = new String[19];
      strmap = new String[40][16];
      try{
        themap  = new BufferedReader (new FileReader("map.csv"));
      }catch(IOException e){
      }
      intloadcount = 0;
      for(intloadcount =0; intloadcount<40;intloadcount++){
        try{
          strload = themap.readLine();
        }catch(IOException e){
        }
        strline = strload.split(",");

        for(intcount2=0;intcount2<16;intcount2++){
          try{
            strmap[intloadcount][intcount2]=strline[intcount2];
          }catch(Exception e){
          }
        }

      }

      for(intloadcount=0;intloadcount<40;intloadcount++){
        mapcards[intloadcount]= new JButton("");
      }

      for(intloadcount=0;intloadcount<40;intloadcount++){
        if(intloadcount>0&&intloadcount<10){
          mapcards[intloadcount].setSize(56,90);
          mapcards[intloadcount].setLocation(630-intloadcount*59,630);
          mapcards[intloadcount].setOpaque(false);
          mapcards[intloadcount].setContentAreaFilled(false);
          mapcards[intloadcount].setBorderPainted(false);


        }else if(intloadcount>10&& intloadcount<20){
          mapcards[intloadcount].setSize(90,55);
          mapcards[intloadcount].setLocation(2,630-(intloadcount-10)*59);
          mapcards[intloadcount].setOpaque(false);
          mapcards[intloadcount].setContentAreaFilled(false);
          mapcards[intloadcount].setBorderPainted(false);

        }else if(intloadcount>20&& intloadcount<30){
          mapcards[intloadcount].setSize(55,90);
          mapcards[intloadcount].setLocation((intloadcount-20)*59+40,2);
          mapcards[intloadcount].setOpaque(false);
          mapcards[intloadcount].setContentAreaFilled(false);
          mapcards[intloadcount].setBorderPainted(false);

        }
        else if(intloadcount> 30 && intloadcount<40){
          mapcards[intloadcount].setSize(90,55);
          mapcards[intloadcount].setLocation(632,(intloadcount-30)*59+40);
          mapcards[intloadcount].setOpaque(false);
          mapcards[intloadcount].setContentAreaFilled(false);
          mapcards[intloadcount].setBorderPainted(false);

        }
        else if(intloadcount==0||intloadcount==10 ||intloadcount==20||intloadcount==30){
          mapcards[intloadcount].setSize(90,90);
          mapcards[intloadcount].setOpaque(false);
          mapcards[intloadcount].setContentAreaFilled(false);
          mapcards[intloadcount].setBorderPainted(false);
          if(intloadcount==0){
            mapcards[intloadcount].setLocation(630,631);
          }else if(intloadcount==10){
            mapcards[intloadcount].setLocation(4,630);
          }else if(intloadcount==20){
            mapcards[intloadcount].setLocation(2,2);
          }else if(intloadcount==30){
            mapcards[intloadcount].setLocation(630,2);
          }
        }
        theframe.add(mapcards[intloadcount]);
      }


      thepanel.add(buttonroll);
      thepanel.add(buttontrade);
      thepanel.add(buttonend);

      Object rowData[][]=strplayerinfo;
      Object columnNames[] = {"Player Icon", "Name", "Mark"};

      playerslist = new JTable(rowData,columnNames);
      playerslist.setSelectionBackground(new Color(128,0,0));
      playerslist.setBackground(new Color(216,174,26));
      playerslist.setFont(new Font("Meiryo", Font.PLAIN, 14));
      playerslist.setRowHeight(25);


      players = new JScrollPane(playerslist);
      players.setSize(520,80);
      players.setLocation(740,370);
      theframe.add(players);

      labelproperty = new JLabel("Owned Property");
      labelproperty.setFont(new Font("Nanum Brush Script", Font.PLAIN, 30));
      labelproperty.setSize(200,50);
      labelproperty.setLocation(740,0);
      thepanel.add(labelproperty);

      propertylist = new JList(strnamelist);
      propertyscroll = new JScrollPane(propertylist);
      propertylist.setSize(520,170);
      propertyscroll.setSize(520,170);
      propertyscroll.setLocation(740,50);

      thepanel.add(propertyscroll);

      textToSend = new JTextField("Chat here! Press enter or the send button to send");
      textToSend.setSize(460,30);
      textToSend.setLocation(737,660);
      textToSend.addActionListener(this);

      textReceived = new JTextArea();

      thescroll = new JScrollPane(textReceived);
      thescroll.setSize(520,210);
      thescroll.setLocation(740,455);

      buttonsend = new JButton("Send");
      buttonsend.setSize(80,30);
      buttonsend.setLocation(1188,662);
      buttonsend.addActionListener(this);
      thepanel.add(buttonsend);

      thepanel.add(thescroll);
      thepanel.add(textToSend);
    }
    // when the player rolls the dices
    if(evt.getSource()==buttonroll){
      thepanel.intran1 = (int)(Math.random() * 5) + 1;
      thepanel.intran2 = (int)(Math.random() * 6) + 1;
      thepanel.intmoves = (thepanel.intran1+thepanel.intran2)*58;
      int intRoll = thepanel.intran1 + thepanel.intran2;
      me.roll(ssm, intMyNumber, intRoll);
      strplayerinfo[intMyNumber - 1][0] = strPiece;
      strplayerinfo[intMyNumber - 1][1] = me.strName;
      strplayerinfo[intMyNumber - 1][2] = ""+me.intMarks;
      strplayerinfo[intMyNumber - 1][3] = ""+me.intCurrentLocation;
      strplayerinfo[intMyNumber - 1][4] = ""+me.blnDetention;
      strnamelist = new String[28];
      strtradelist = new String[28][2];
      intcount2=0;
      int intcount = 0;
      for(intcount=0; intcount<=27; intcount++){
        if(me.strProperty[intcount][2].equals("T")){
          strnamelist[intcount2]=me.strProperty[intcount][0];
          strtradelist[intcount2][0]=me.strProperty[intcount][0];
          strtradelist[intcount2][1]=me.strProperty[intcount][1];
          intcount2++;
        }
      }
      buttonroll.setEnabled(false);
      if(intMyNumber == 1){
        thepanel.intxr = thepanel.int1x;
        thepanel.intyr = thepanel.int1y;
        thepanel.start1=true;
      }
      if(intMyNumber == 2){
        thepanel.intxr = thepanel.int2x;
        thepanel.intyr = thepanel.int2y;
        thepanel.start2=true;
      }
      if(intMyNumber == 3){
        thepanel.intxr = thepanel.int3x;
        thepanel.intyr = thepanel.int3y;
        thepanel.start3=true;
      }
      if(intMyNumber == 4){
        thepanel.intxr = thepanel.int4x;
        thepanel.intyr = thepanel.int4y;
        thepanel.start4=true;
      }
      if(me.intCurrentLocation == 30){
        thepanel.blndetention = true;
        int intplayernum = intMyNumber;
        if(intplayernum==1){
          thepanel.int1x = 50;
          thepanel.int1y = 630;

        }else if(intplayernum==2){
          thepanel.int2x = 50;
          thepanel.int2y = 630;

        }else if(intplayernum==3){
          thepanel.int3x = 50;
          thepanel.int3y = 630;
        }else if(intplayernum==4){
          thepanel.int4x = 50;
          thepanel.int4y = 630;
        }
      }
      //check owner if empty
      if(strmap[me.intCurrentLocation][3] == null){
        if(strmap[me.intCurrentLocation][0].equals("Crd")){
          //random chance or community cards
          //check payment
          thepanel.cccstart = true;
          intcardrandom = (int)(Math.random()*7+0);
          me.intMarks = me.intMarks + Integer.parseInt(strccc[intcardrandom][2]);
          strplayerinfo[intMyNumber - 1][0] = strPiece;
          strplayerinfo[intMyNumber - 1][1] = me.strName;
          strplayerinfo[intMyNumber - 1][2] = ""+me.intMarks;
          strplayerinfo[intMyNumber - 1][3] = ""+me.intCurrentLocation;
          strplayerinfo[intMyNumber - 1][4] = ""+me.blnDetention;
          me.sendPlayerInfo(ssm);
          ssm.sendText("CHT,"+intMyNumber+",got a card and was given(+) or paid(-) "+Integer.parseInt(strccc[intcardrandom][2]));
          cardcontentfield = new JTextField();
          cardcontentfield.setText(strccc[intcardrandom][1]);
          cardcontentfield.setSize(300,100);
          cardcontentfield.setLocation(220,290);
          thepanel.add(cardcontentfield);

        }
      }
      if(strmap[me.intCurrentLocation][3] != null){
        if(strmap[me.intCurrentLocation][3].equals("")){
          //check if location is property/utility/public facility
          if(!strmap[me.intCurrentLocation][0].equals("Cnr") && !strmap[me.intCurrentLocation][0].equals("Crd")){
            //load buy offer picture of the location
            //construct buybutton
            //construct buttondecline
            thepanel.blnbuy = true;
            thepanel.intlocation = me.intCurrentLocation;
            buttonbuy = new JButton();
            buttonbuy.setSize(70,70);
            buttonbuy.setLocation(250,550);
            buttonbuy.setOpaque(false);
            buttonbuy.setContentAreaFilled(false);
            buttonbuy.setBorderPainted(false);
            buttonbuy.addActionListener(this);

            thepanel.add(buttonbuy);
            buttondecline = new JButton();
            buttondecline.setSize(70,70);
            buttondecline.setLocation(400,550);
            buttondecline.setOpaque(false);
            buttondecline.setContentAreaFilled(false);
            buttondecline.setBorderPainted(false);
            buttondecline.addActionListener(this);
            thepanel.add(buttondecline);
          }else if(strmap[me.intCurrentLocation][2].equals("20")){
            me.intMarks = me.intMarks + intFieldTrip;
            strplayerinfo[intMyNumber - 1][0] = strPiece;
            strplayerinfo[intMyNumber - 1][1] = me.strName;
            strplayerinfo[intMyNumber - 1][2] = ""+me.intMarks;
            strplayerinfo[intMyNumber - 1][3] = ""+me.intCurrentLocation;
            strplayerinfo[intMyNumber - 1][4] = ""+me.blnDetention;
            strnamelist = new String[28];
            strtradelist = new String[28][2];
            intcount2=0;
            intcount = 0;
            for(intcount=0; intcount<=27; intcount++){
              if(me.strProperty[intcount][2].equals("T")){
                strnamelist[intcount2]=me.strProperty[intcount][0];
                strtradelist[intcount2][0]=me.strProperty[intcount][0];
                strtradelist[intcount2][1]=me.strProperty[intcount][1];
                intcount2++;
              }
            }
            me.sendPlayerInfo(ssm);
            intFieldTrip = 0;
            ssm.sendText("CFT,1");
          }
        }else if(!strmap[me.intCurrentLocation][3].equals("")){
          if(Integer.parseInt(strmap[me.intCurrentLocation][3]) != intMyNumber){
            //Paying rent
            int intPayment = 0;
            int intOwner = 0;
            if(strmap[me.intCurrentLocation][0].equalsIgnoreCase("Prp")){
              boolean blnFullset = false;
              intOwner = Integer.parseInt(strmap[me.intCurrentLocation][3]);
              //check if owner owns full set
              if(me.intCurrentLocation == 1 || me.intCurrentLocation == 37){
                if(!strmap[me.intCurrentLocation + 2][3].equals("")){
                  if(Integer.parseInt(strmap[me.intCurrentLocation + 2][3]) == intOwner){
                    blnFullset = true;
                  }
                }
              }else if(me.intCurrentLocation == 3 || me.intCurrentLocation == 39){
                if(!strmap[me.intCurrentLocation - 2][3].equals("")){
                  if(Integer.parseInt(strmap[me.intCurrentLocation - 2][3]) == intOwner){
                    blnFullset = true;
                  }
                }
              }else if(me.intCurrentLocation == 6 || me.intCurrentLocation == 11 || me.intCurrentLocation == 16 || me.intCurrentLocation == 21){
                if(!strmap[me.intCurrentLocation + 2][3].equals("") && !strmap[me.intCurrentLocation + 3][3].equals("")){
                  if(Integer.parseInt(strmap[me.intCurrentLocation + 2][3]) == intOwner && Integer.parseInt(strmap[me.intCurrentLocation + 3][3]) == intOwner){
                    blnFullset = true;
                  }
                }
              }else if(me.intCurrentLocation == 8 || me.intCurrentLocation == 13 || me.intCurrentLocation == 18 || me.intCurrentLocation == 23){
                if(!strmap[me.intCurrentLocation - 2][3].equals("") && !strmap[me.intCurrentLocation + 1][3].equals("")){
                  if(Integer.parseInt(strmap[me.intCurrentLocation - 2][3]) == intOwner && Integer.parseInt(strmap[me.intCurrentLocation + 1][3]) == intOwner){
                    blnFullset = true;
                  }
                }
              }else if(me.intCurrentLocation == 9 || me.intCurrentLocation == 14 || me.intCurrentLocation == 19 || me.intCurrentLocation == 24){
                if(!strmap[me.intCurrentLocation - 1][3].equals("") && !strmap[me.intCurrentLocation - 3][3].equals("")){
                  if(Integer.parseInt(strmap[me.intCurrentLocation - 1][3]) == intOwner && Integer.parseInt(strmap[me.intCurrentLocation - 3][3]) == intOwner){
                    blnFullset = true;
                  }
                }
              }else if(me.intCurrentLocation == 26 || me.intCurrentLocation == 31){
                if(!strmap[me.intCurrentLocation + 1][3].equals("") && !strmap[me.intCurrentLocation + 3][3].equals("")){
                  if(Integer.parseInt(strmap[me.intCurrentLocation + 1][3]) == intOwner && Integer.parseInt(strmap[me.intCurrentLocation + 3][3]) == intOwner){
                    blnFullset = true;
                  }
                }
              }else if(me.intCurrentLocation == 27 || me.intCurrentLocation == 32){
                if(!strmap[me.intCurrentLocation - 1][3].equals("") && !strmap[me.intCurrentLocation + 2][3].equals("")){
                  if(Integer.parseInt(strmap[me.intCurrentLocation - 1][3]) == intOwner && Integer.parseInt(strmap[me.intCurrentLocation + 2][3]) == intOwner){
                    blnFullset = true;
                  }
                }
              }else if(me.intCurrentLocation == 29 || me.intCurrentLocation == 34){
                if(!strmap[me.intCurrentLocation - 2][3].equals("") && !strmap[me.intCurrentLocation - 3][3].equals("")){
                  if(Integer.parseInt(strmap[me.intCurrentLocation - 2][3]) == intOwner && Integer.parseInt(strmap[me.intCurrentLocation - 3][3]) == intOwner){
                    blnFullset = true;
                  }
                }
              }
              if(strmap[me.intCurrentLocation][9].equals("1")){
                intPayment = Integer.parseInt(strmap[me.intCurrentLocation][10]);
              }else if(strmap[me.intCurrentLocation][9].equals("2")){
                intPayment = Integer.parseInt(strmap[me.intCurrentLocation][11]);
              }else if(strmap[me.intCurrentLocation][9].equals("3")){
                intPayment = Integer.parseInt(strmap[me.intCurrentLocation][12]);
              }else if(strmap[me.intCurrentLocation][9].equals("4")){
                intPayment = Integer.parseInt(strmap[me.intCurrentLocation][13]);
              }else if(!strmap[me.intCurrentLocation][14].equals("0")){
                intPayment = Integer.parseInt(strmap[me.intCurrentLocation][15]);
              }else if(blnFullset == true){
                intPayment = Integer.parseInt(strmap[me.intCurrentLocation][8]);
              }else{
                intPayment = Integer.parseInt(strmap[me.intCurrentLocation][7]);
              }
            }else if(strmap[me.intCurrentLocation][0].equalsIgnoreCase("Utl")){
              //check how many utilities owner owns
              //check roll number (nextlocation - currlocation)
              //4 * roll OR 10 * roll
              intOwner = Integer.parseInt(strmap[me.intCurrentLocation][3]);
              int intUtilOwn = 0;
              if(!strmap[12][3].equals("")){
                if(Integer.parseInt(strmap[12][3]) == intOwner){
                  intUtilOwn++;
                }
              }
              if(!strmap[28][3].equals("")){
                if(Integer.parseInt(strmap[28][3]) == intOwner){
                  intUtilOwn++;
                }
              }
              if(intUtilOwn == 1){
                intPayment = 4 * intRoll;
              }else if(intUtilOwn == 2){
                intPayment = 10 * intRoll;
              }
            }else if(strmap[me.intCurrentLocation][0].equalsIgnoreCase("Pub")){
              //check how many pub owner owns
              //25 * #pubowned
              intOwner = Integer.parseInt(strmap[me.intCurrentLocation][3]);
              int intPubOwn = 0;
              if(!strmap[5][3].equals("")){
                if(Integer.parseInt(strmap[5][3]) == intOwner){
                  intPubOwn++;
                }
              }
              if(!strmap[15][3].equals("")){
                if(Integer.parseInt(strmap[15][3]) == intOwner){
                  intPubOwn++;
                }
              }
              if(!strmap[25][3].equals("")){
                if(Integer.parseInt(strmap[25][3]) == intOwner){
                  intPubOwn++;
                }
              }
              if(!strmap[35][3].equals("")){
                if(Integer.parseInt(strmap[35][3]) == intOwner){
                  intPubOwn++;
                }
              }
              intPayment = intPubOwn * 25;
            }
            me.payRent(ssm, intMyNumber, intOwner, intPayment);
            strplayerinfo[intMyNumber - 1][0] = strPiece;
            strplayerinfo[intMyNumber - 1][1] = me.strName;
            strplayerinfo[intMyNumber - 1][2] = ""+me.intMarks;
            strplayerinfo[intMyNumber - 1][3] = ""+me.intCurrentLocation;
            strplayerinfo[intMyNumber - 1][4] = ""+me.blnDetention;
            strnamelist = new String[28];
            strtradelist = new String[28][2];
            intcount2=0;
            intcount = 0;
            for(intcount=0; intcount<=27; intcount++){
              if(me.strProperty[intcount][2].equals("T")){
                strnamelist[intcount2]=me.strProperty[intcount][0];
                strtradelist[intcount2][0]=me.strProperty[intcount][0];
                strtradelist[intcount2][1]=me.strProperty[intcount][1];
                intcount2++;
              }
            }
          }else if(Integer.parseInt(strmap[me.intCurrentLocation][3]) == intMyNumber){
            boolean blnBuildHouse = false;
            boolean blnBuildHotel = false;
            int intArrayRow = 0;
            int intCount = 0;
            for(intCount = 0; intCount <= 27; intCount++){
              if(Integer.parseInt(me.strProperty[intCount][1]) == me.intCurrentLocation){
                intArrayRow = intCount;
              }
            }
            if(intArrayRow == 0 || intArrayRow == 26){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow + 1][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 1 || intArrayRow == 27){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow - 1][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 3 || intArrayRow == 11 || intArrayRow == 14 || intArrayRow == 22){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow + 1][2].equals("T") && me.strProperty[intArrayRow + 2][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 4 || intArrayRow == 12 || intArrayRow == 15 || intArrayRow == 23){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow - 1][2].equals("T") && me.strProperty[intArrayRow + 1][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 5 || intArrayRow == 13 || intArrayRow == 16 || intArrayRow == 24){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow - 1][2].equals("T") && me.strProperty[intArrayRow - 2][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 6){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow + 2][2].equals("T") && me.strProperty[intArrayRow + 3][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 8){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow - 2][2].equals("T") && me.strProperty[intArrayRow + 1][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 9){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow - 1][2].equals("T") && me.strProperty[intArrayRow - 3][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 18){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow + 1][2].equals("T") && me.strProperty[intArrayRow + 3][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 19){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow - 1][2].equals("T") && me.strProperty[intArrayRow + 2][2].equals("T")){
                blnBuildHouse = true;
              }
            }else if(intArrayRow == 21){
              if(me.strProperty[intArrayRow][2].equals("T") && me.strProperty[intArrayRow - 3][2].equals("T") && me.strProperty[intArrayRow - 2][2].equals("T")){
                blnBuildHouse = true;
              }
            }
            if(Integer.parseInt(me.strProperty[intArrayRow][3]) >= 4 && Integer.parseInt(me.strProperty[intArrayRow][4]) == 0){
              blnBuildHotel = true;
            }
            if(Integer.parseInt(me.strProperty[intArrayRow][4]) > 0){
              //Already own one hotel
              blnBuildHouse = false;
              blnBuildHotel = false;
            }
            if(blnBuildHotel == true){
              thepanel.intlocation = me.intCurrentLocation;
              blnBuildHouse = false;
              //build hotel offer
              //construct buttonbuyhouse
              //construct buttondecline
              thepanel.blnbuyhotel = true;
              buttonbuyhotel = new JButton();
              buttonbuyhotel.setSize(70,70);
              buttonbuyhotel.setLocation(250,550);
              buttonbuyhotel.setOpaque(false);
              buttonbuyhotel.setContentAreaFilled(false);
              buttonbuyhotel.setBorderPainted(false);
              buttonbuyhotel.addActionListener(this);


              thepanel.add(buttonbuyhotel);
              buttondecline = new JButton();
              buttondecline.setSize(70,70);
              buttondecline.setLocation(400,550);
              buttondecline.setOpaque(false);
              buttondecline.setContentAreaFilled(false);
              buttondecline.setBorderPainted(false);
              buttondecline.addActionListener(this);
              thepanel.add(buttondecline);
            }else if(blnBuildHouse == true){
              //build house offer
              //construct buttonbuyhotel
              //construct buttondecline
              thepanel.intlocation = me.intCurrentLocation;
              thepanel.blnbuyhouse = true;
              buttonbuyhouse = new JButton();
              buttonbuyhouse.setSize(70,70);
              buttonbuyhouse.setLocation(250,550);
              buttonbuyhouse.setOpaque(false);
              buttonbuyhouse.setContentAreaFilled(false);
              buttonbuyhouse.setBorderPainted(false);
              buttonbuyhouse.addActionListener(this);


              thepanel.add(buttonbuyhouse);
              buttondecline = new JButton();
              buttondecline.setSize(70,70);
              buttondecline.setLocation(400,550);
              buttondecline.setOpaque(false);
              buttondecline.setContentAreaFilled(false);
              buttondecline.setBorderPainted(false);
              buttondecline.addActionListener(this);
              thepanel.add(buttondecline);
            }
          }
        }
      }else if(me.intCurrentLocation == 4 || me.intCurrentLocation == 38){
        //Paying fees to bank
        int intPayment = Integer.parseInt(strmap[me.intCurrentLocation][7]);
        me.intMarks = me.intMarks - intPayment;
        ssm.sendText("CHT,"+intMyNumber+",paid "+intPayment+" to the bank");
        ssm.sendText("AFT,"+intPayment);
        intFieldTrip = intFieldTrip + intPayment;
        me.sendPlayerInfo(ssm);
      }
    }
    if(evt.getSource() == textToSend){
      if(!textToSend.getText().equals("")){
        me.chat(ssm, intMyNumber, textToSend.getText());
        textReceived.append(intMyNumber+": "+textToSend.getText()+"\n");
        textReceived.setCaretPosition(textReceived.getDocument().getLength());
        textToSend.setText("");
      }
    }else if(evt.getSource() == buttonsend){
      if(!textToSend.getText().equals("")){
        me.chat(ssm, intMyNumber, textToSend.getText());
        textReceived.append(intMyNumber+": "+textToSend.getText()+"\n");
        textReceived.setCaretPosition(textReceived.getDocument().getLength());
        textToSend.setText("");
      }
    }
  }

  // constructor
  public GUI()  throws IOException{
    theframe = new JFrame("St.A-poly");
    theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    blnstart = false;
    themainmenu = new mainmenu();
    themainmenu.setLayout(null);
    themainmenu.setPreferredSize(new Dimension(1280,720));
    theframe.setContentPane(themainmenu);
    theframe.pack();

    buttonroll = new JButton("Roll");
    buttonroll.setSize(130,40);
    buttonroll.setLocation(780,230);
    buttonroll.addActionListener(this);
    buttonroll.setEnabled(false);

    buttontrade = new JButton("Trade");
    buttontrade.setSize(130,40);
    buttontrade.setLocation(930,230);
    buttontrade.addActionListener(this);
    buttontrade.setEnabled(false);

    buttonend = new JButton("End Turn");
    buttonend.setSize(130,40);
    buttonend.setLocation(1090,230);
    buttonend.addActionListener(this);
    buttonend.setEnabled(false);


    buttonnewgame = new JButton("New Game");
    buttonnewgame.setFont(new Font("Nanum Brush Script", Font.PLAIN, 34));
    buttonnewgame.setSize(300,100);
    buttonnewgame.setLocation(70,255);
    buttonnewgame.setBorderPainted(false);
    buttonnewgame.setContentAreaFilled(false);
    buttonnewgame.setFocusPainted(false);
    buttonnewgame.setOpaque(false);
    buttonnewgame.addActionListener(this);
    themainmenu.add(buttonnewgame);

    buttonhelp = new JButton("Help");
    buttonhelp.setFont(new Font("Nanum Brush Script", Font.PLAIN, 34));
    buttonhelp.setSize(300,100);
    buttonhelp.setLocation(360,255);
    buttonhelp.setBorderPainted(false);
    buttonhelp.setContentAreaFilled(false);
    buttonhelp.setFocusPainted(false);
    buttonhelp.setOpaque(false);
    buttonhelp.addActionListener(this);
    themainmenu.add(buttonhelp);

    buttonabout = new JButton("About");
    buttonabout.setFont(new Font("Nanum Brush Script", Font.PLAIN, 34));
    buttonabout.setSize(300,100);
    buttonabout.setLocation(668,255);
    buttonabout.setBorderPainted(false);
    buttonabout.setContentAreaFilled(false);
    buttonabout.setFocusPainted(false);
    buttonabout.setOpaque(false);
    buttonabout.addActionListener(this);
    themainmenu.add(buttonabout);

    buttonquit = new JButton("Quit");
    buttonquit.setFont(new Font("Nanum Brush Script", Font.PLAIN, 34));
    buttonquit.setSize(300,100);
    buttonquit.setLocation(950,255);
    buttonquit.setBorderPainted(false);
    buttonquit.setContentAreaFilled(false);
    buttonquit.setFocusPainted(false);
    buttonquit.setOpaque(false);
    buttonquit.addActionListener(this);
    themainmenu.add(buttonquit);

    player2 = new JButton();
    player2.setSize(200,220);
    player2.setLocation(610,70);
    player2.setBorderPainted(false);
    player2.setContentAreaFilled(false);
    player2.setFocusPainted(false);
    player2.setOpaque(false);
    player2.addActionListener(this);

    player1 = new JButton();
    player1.setSize(200,220);
    player1.setLocation(360,70);
    player1.setBorderPainted(false);
    player1.setContentAreaFilled(false);
    player1.setFocusPainted(false);
    player1.setOpaque(false);
    player1.addActionListener(this);

    player3 = new JButton();
    player3.setSize(200,220);
    player3.setLocation(360,340);
    player3.setBorderPainted(false);
    player3.setContentAreaFilled(false);
    player3.setFocusPainted(false);
    player3.setOpaque(false);
    player3.addActionListener(this);

    player4 = new JButton();
    player4.setSize(200,220);
    player4.setLocation(610,340);
    player4.setBorderPainted(false);
    player4.setContentAreaFilled(false);
    player4.setFocusPainted(false);
    player4.setOpaque(false);
    player4.addActionListener(this);

    try{
      theccc = new BufferedReader (new FileReader("ccc.csv"));
    }catch(IOException e){
    }
    strccc = new String[7][4];
    strline = new String[4];
    int intcount = 0;
    for(intcount =0; intcount<7;intcount++){
      try{
        strload = theccc.readLine();
      }catch(IOException e){
      }
      strline = strload.split(",");

      for(intcount2=0;intcount2<4;intcount2++){
        try{
          strccc[intcount][intcount2]=strline[intcount2];
        }catch(Exception e){
        }
      }
    }


    // timer
    thetimer = new Timer(1000/30,this);
    // Start the timer
    thetimer.start();

    theframe.setVisible(true);

  }

  public static void main(String[] args) throws IOException{
    GUI GUI = new GUI();
    GUI G2 = new GUI();
  }
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
/**
 * Player Object Class
 * This class is for creating player objects in the game St.A-poly
 */

public class player{
  //Properties
  /**
   * Order of the player (Player 1, Player 2, Player 3, Player 4)
   */
  int intPlayer;
  /**
   * Name of the player
   */ 
  String strName;
  /**
   * Number of marks remaining for the player
   */
  int intMarks;
  /**
   * Keeps track of the property, houses, hotels, or utilities the player owns
   */
  String strProperty[][];
  /**
   * Detention status of the player
   */
  boolean blnDetention;
  /**
   * Chess piece of the player
   */
  int intPiece;
  int intCurrentLocation;
  //Methods
  /**
   * Enters trade screen for two players
   */
  public void entertrade(SuperSocketMaster ssm, int intPlayer1, int intPlayer2){
    ssm.sendText("TRD,"+intPlayer1+","+intPlayer2);
  }
  /**
   * Offers or Requests another player the property selected
   */
  public void tradeProp(SuperSocketMaster ssm, int intPlayer, int intLocation){
    ssm.sendText("CHB,"+intPlayer+","+intLocation);
  }
  /**
   * Offers or Requests another player the marks entered
   */
  public void tradeMarks(SuperSocketMaster ssm, int intPlayer, int intMarks){
    ssm.sendText("CHA,"+intPlayer+","+intMarks);
  }
  /**
   * Makes a purchase of a property, utility, or public facility
   */
  public void buy(SuperSocketMaster ssm, int intPlayer, int intLocation, int intMarks){
    int intArrayRow = 0;
    int intCount = 0;
    for(intCount = 0; intCount <= 27; intCount++){
      if(Integer.parseInt(this.strProperty[intCount][1]) == intLocation){
       intArrayRow = intCount;
      }
    }
    this.strProperty[intArrayRow][2] = "T";
    this.intMarks = this.intMarks - intMarks;
    String strLocation = convertStrLocation(intLocation);
    ssm.sendText("BUY,"+intPlayer+","+intLocation+","+intMarks);
    ssm.sendText("CHT,"+intPlayer+",bought "+strLocation);
    this.sendPlayerInfo(ssm);
  }
  /**
   * Builds a house or a hotel
   */
  public void build(SuperSocketMaster ssm, int intPlayer, int intLocation, int intMarks, String strType){
    if(strType.equalsIgnoreCase("house")){
      int intArrayRow = 0;
      int intCount = 0;
      int intUpdateHouses = 0;
      for(intCount = 0; intCount <= 27; intCount++){
        if(Integer.parseInt(this.strProperty[intCount][1]) == intLocation){
          intArrayRow = intCount;
        }
      }
      intUpdateHouses = Integer.parseInt(this.strProperty[intArrayRow][3]) + 1;
      this.strProperty[intArrayRow][3] = ""+intUpdateHouses;
      this.intMarks = this.intMarks - intMarks;
      String strLocation = convertStrLocation(intLocation);
      ssm.sendText("BHS,"+intPlayer+","+intLocation+","+intMarks+",1");
      ssm.sendText("CHT,"+intPlayer+",bought a house at "+strLocation);
      this.sendPlayerInfo(ssm);
    }else if(strType.equalsIgnoreCase("hotel")){
      int intArrayRow = 0;
      int intCount = 0;
      int intUpdateHotels = 0;
      for(intCount = 0; intCount <= 27; intCount++){
        if(Integer.parseInt(this.strProperty[intCount][1]) == intLocation){
          intArrayRow = intCount;
        }
      }
      intUpdateHotels = Integer.parseInt(this.strProperty[intArrayRow][4]) + 1;
      this.strProperty[intArrayRow][3] = ""+intUpdateHotels;
      this.intMarks = this.intMarks - intMarks;
      String strLocation = convertStrLocation(intLocation);
      ssm.sendText("BHT,"+intPlayer+","+intLocation+","+intMarks+",1");
      ssm.sendText("CHT,"+intPlayer+",bought a hotel at "+strLocation);
      this.sendPlayerInfo(ssm);
    }
    
  }
  /**
   * Sells a property, utility, or public facility. 
   * Owner no longer owns the property and the Title Deed card
   */
  public void sell(SuperSocketMaster ssm, int intLocation, int intMarks){
  }
  /**
   * Sells a house at a specific property owned
   */
  public void sellHouse(SuperSocketMaster ssm, int intLocation, int intMarks){
  }
  /**
   * Sells a hotel at a specific property owned
   */
  public void sellHotel(SuperSocketMaster ssm, int intLocation, int intMarks){
  }
//  /**
//   * Generates two random integers from 1-6 on the dice
//   */
  public void roll(SuperSocketMaster ssm, int intPlayer, int intRoll){
    ssm.sendText("CHT,"+intPlayer+",rolled "+intRoll);
    this.move(ssm, intPlayer, this.intCurrentLocation, this.intCurrentLocation + intRoll);
  }
  /**
   * Sends a message that is entered in the text field to another players for communication
   */
  public void chat(SuperSocketMaster ssm, int intPlayer, String strMessage){
    ssm.sendText("CHT,"+intPlayer+","+strMessage);
  }
  /**
   * Moves chess piece of the player around the board from a location to another
   */
  public void move(SuperSocketMaster ssm, int intPlayer, int intCurrentLocation, int intNextLocation){
    if(intNextLocation >= 40){
      intNextLocation = intNextLocation - 40;
    }
    String strLocation = convertStrLocation(intNextLocation);
    ssm.sendText("MOV,"+intPlayer+","+intCurrentLocation+","+intNextLocation);
    ssm.sendText("CHT,"+intPlayer+",landed on "+strLocation);
    if(intNextLocation == 30){
      this.goToDetention(ssm, intPlayer); 
    }else if(intNextLocation - intCurrentLocation < 0){
      if(this.blnDetention == false){
        //Pass Go, collect 200 marks
        this.intMarks = this.intMarks + 200;
      }
    }
    this.intCurrentLocation = intNextLocation;
    this.sendPlayerInfo(ssm);
  }
  /**
   * Ends the current player's turn and proceeds to the next player's turn
   */
  public void endTurn(SuperSocketMaster ssm, int intPlayer){
    if(this.intMarks <= 0){
      
      //delete game account
      ssm.sendText("CHT,"+intPlayer+",is bankrupt");
      this.intPlayer = 0;
      this.sendPlayerInfo(ssm);
    }else{
      ssm.sendText("CHT,"+intPlayer+",ended the turn");  
    }
    ssm.sendText("END,"+intPlayer);  
  }
  /**
   * Sets the selected character as the player's chess piece on the board
   */
  public void choosePiece(SuperSocketMaster ssm, int intPlayer, int intPiece){
    this.intPiece = intPiece;
    this.sendPlayerInfo(ssm);
    ssm.sendText("CHP,"+intPlayer+","+intPiece);
  }
  /**
   * Sends current player to detention and avoids player to perform any actions for three turns
   */
  public void goToDetention(SuperSocketMaster ssm, int intPlayer){
    this.blnDetention = true;
    ssm.sendText("DET,"+intPlayer);
    this.sendPlayerInfo(ssm);
  }
  public void sendPlayerInfo(SuperSocketMaster ssm){
    String strPiece = "";
    if(this.intPlayer == 1){
      strPiece = "pencil";
    }else if(this.intPlayer == 2){
      strPiece = "glue";
    }else if(this.intPlayer == 3){
      strPiece = "toilet paper";
    }else if(this.intPlayer == 4){
      strPiece = "eraser";
    }
    ssm.sendText("PLI,"+strPiece+","+this.strName+","+this.intMarks+","+this.intCurrentLocation+","+this.blnDetention);
  }
  public void payRent(SuperSocketMaster ssm, int intPlayer, int intPlayerReceive, int intPayment){
    this.intMarks = this.intMarks - intPayment;
    ssm.sendText("CHT,"+intPlayer+",paid "+intPayment+" to "+intPlayerReceive+" for rent");
    ssm.sendText("RNT,"+intPlayerReceive+","+intPayment);
    this.sendPlayerInfo(ssm);
  }
//Constructor
  /**
   * Constructs a player with the properties for the game
   */
  public player(int intPlayer, String strName) throws IOException{
    this.intPlayer = intPlayer;
    this.strName = strName;
    this.intMarks = 1500;
    this.blnDetention = false;
    this.intCurrentLocation = 0;
    //Property, location#, T/F, #of houses, #of hotels
    this.strProperty = new String[28][5];
    BufferedReader thefile = null;
    String strLine = "";
    String strLin[];
    try{
      thefile = new BufferedReader(new FileReader("playerPropInfo.csv"));
    }catch(FileNotFoundException e){
    }
    int intCount = 0;
    for(intCount = 0; intCount <= 27; intCount++){
      strLine = thefile.readLine();
      strLin = strLine.split(",");
      this.strProperty[intCount][0] = strLin[0];
      this.strProperty[intCount][1] = strLin[1];
      this.strProperty[intCount][2] = "F";
      this.strProperty[intCount][3] = "0";
      this.strProperty[intCount][4] = "0";
      //System.out.println(this.intPlayer+","+this.strProperty[intCount][0]+","+this.strProperty[intCount][1]+","+this.strProperty[intCount][2]+","+this.strProperty[intCount][3]+","+this.strProperty[intCount][4]);
    }
    thefile.close();
  }
  
  //Utility method
  public static String convertStrLocation(int intNextLocation){
    String strLocation = "";
    if(intNextLocation == 0){
      strLocation = "GO";
    }else if(intNextLocation == 1){
      strLocation = "RELIGION 1";
    }else if(intNextLocation == 2 || intNextLocation == 17 || intNextLocation == 33){
      strLocation = "COMMUNITY CHEST";
    }else if(intNextLocation == 3){
      strLocation = "RELIGION 2";
    }else if(intNextLocation == 4){
      strLocation = "CIVVIES DAY";
    }else if(intNextLocation == 5){
      strLocation = "MAIN OFFICE";
    }else if(intNextLocation == 6){
      strLocation = "FRENCH 1";
    }else if(intNextLocation == 7 || intNextLocation == 22 || intNextLocation == 36){
      strLocation = "CHANCE";
    }else if(intNextLocation == 8){
      strLocation = "FRENCH 2";
    }else if(intNextLocation == 9){
      strLocation = "FRENCH 3";
    }else if(intNextLocation == 10){
      strLocation = "DETENTION VISIT";
    }else if(intNextLocation == 11){
      strLocation = "ENGLISH 1";
    }else if(intNextLocation == 12){
      strLocation = "WIFI";
    }else if(intNextLocation == 13){
      strLocation = "ENGLISH 2";
    }else if(intNextLocation == 14){
      strLocation = "ENGLISH 3";
    }else if(intNextLocation == 15){
      strLocation = "LIBRARY";
    }else if(intNextLocation == 16){
      strLocation = "BUSINESS 1";
    }else if(intNextLocation == 18){
      strLocation = "BUSINESS 2";
    }else if(intNextLocation == 19){
      strLocation = "BUSINESS 3";
    }else if(intNextLocation == 20){
      strLocation = "FREE FIELD TRIP";
    }else if(intNextLocation == 21){
      strLocation = "ARTS 1";
    }else if(intNextLocation == 23){
      strLocation = "ARTS 2";
    }else if(intNextLocation == 24){
      strLocation = "ARTS 3";
    }else if(intNextLocation == 25){
      strLocation = "CAFETERIA";
    }else if(intNextLocation == 26){
      strLocation = "MATH 1";
    }else if(intNextLocation == 27){
      strLocation = "MATH 2";
    }else if(intNextLocation == 28){
      strLocation = "CHROMEBOOK";
    }else if(intNextLocation == 29){
      strLocation = "MATH 3";
    }else if(intNextLocation == 30){
      strLocation = "GO TO DENTENTION";
    }else if(intNextLocation == 31){
      strLocation = "SCIENCE 1";
    }else if(intNextLocation == 32){
      strLocation = "SCIENCE 2";
    }else if(intNextLocation == 34){
      strLocation = "SCIENCE 3";
    }else if(intNextLocation == 35){
      strLocation = "GYM";
    }else if(intNextLocation == 37){
      strLocation = "TECH 1";
    }else if(intNextLocation == 38){
      strLocation = "YEARBOOK";
    }else if(intNextLocation == 39){
      strLocation = "TECH 2";
    }
    return strLocation;
  }
}
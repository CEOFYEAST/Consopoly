import java.util.ArrayList;

public class Tile{
  private int tileLocation;
  private int price;
  private int rent;
  private int rentArr[];
  private String innerText;
  private String tileColor = "\033[0;37m";

  //misc
  final String RED = "\033[0;31m            ";     // RED
  final String GREEN = "\033[0;32m            ";   // GREEN
  final String RESET = "\033[0m";  
  
  boolean isMortgaged = false;
  
  private Player owner;

  public static final String ANSI_RESET = "\033[0m";

  //sets tile type
  // 0 is property
  // 1 is tax
  // 2 is draw card
  // 3 is jail
  // 4 is collect (go and others)
  // 5 is nothing (parking space, visiting jail)
  private int type = 0;
  
  private ArrayList<House> houseList = new ArrayList<House>();
  private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
  
  public Tile(int tileLocation){
    this.tileLocation = tileLocation;
  }

  //mutators
  public String getInnerText(){
    return innerText;
  }
  public String getTileColor(){
    return tileColor;
  }
  public Player getOwner(){
    return owner;
  }
  public int getPrice(){
    return price;
  }
  public void setPrice(int price){
    this.price = price;
  }
  public int getType(){
    return type;
  }
  public boolean isMortgaged(){
    return isMortgaged;
  }
  public void setTileLocation(int tileLocation){
    this.tileLocation = tileLocation;
  }
  public int getTileLocation(){
    return tileLocation;
  }

  public void purchase(Player buyer){
    owner = buyer;
    System.out.println(buyer.getIcon() + " has purchased " + innerText + " for " + price + " dollars");
    buyer.minusMoney(price);
    buyer.addProperty(this);
  }
  public void payRent(Player payer){
    payer.minusMoney(this.getRent());
  }
  
  public int getRent(){
     if(innerText.contains("road") || innerText.contains("line")){
      int propertyCount = 0;
      ArrayList<Tile> tileProperty = owner.getTilePropertyList();
      for(int i = 0; i < tileProperty.size(); i++){
        if(tileProperty.get(i).innerText.contains("road") || innerText.contains("line")){
          propertyCount += 1;
        }
      }
      return 50 * propertyCount;
    } else{
    int rents[] = this.getRents();
    if(hotelList.size() > 0){
      return rents[5];
    } else if(houseList.size() == 4){
      return rents[4];
    } else if(houseList.size() == 3){
      return rents[3];
    } else if(houseList.size() == 2){
      return rents[2];
    } else if(houseList.size() == 1){
      return rents[1];
    }
    return rents[0];
    }
  }

  public int[] getRents(){
    if(innerText.contains("road") || innerText.contains("line")){
      rentArr = new int[]{50, 100, 150, 200};
    }
    return rentArr;
  }
  
  public String[] getTile(){
    switch (tileLocation) {
      case 0: 
        innerText = "Go -----> Collect 200 ";
        type = 4;
        break;
      case 1: 
        innerText = "Mediterranean Avenue";
        tileColor = "\033[38;2;135;82;62m";
        price = 60;
        rentArr = new int[]{2, 10, 30, 90, 160, 250}; 
        break;
      case 2: 
        innerText = "Community Chest";
        type = 2;
        break;
      case 3: 
        innerText = "Baltic Avenue";
        tileColor = "\033[38;2;135;82;62m";
        price = 60;
        rentArr = new int[]{4, 20 ,60, 180, 320, 450};
        break;
      case 4: 
        innerText = "Income Tax";
        type = 1;
        break;
      case 5: 
        innerText = "Reading Railroad";
        price = 200;
        break;
      case 6: 
        innerText = "Oriental Avenue";
        tileColor = "\033[38;2;120;172;255m";
        price = 100;
        rentArr = new int[]{6, 30, 90, 270, 400, 550};
        break;
      case 7: 
        innerText = "Chance";
        type = 2;
        break;
      case 8: 
        innerText = "Vermont Avenue";
        tileColor = "\033[38;2;120;172;255m";
        price = 100;
        rentArr = new int[]{6, 30, 90, 270, 400, 550};
        break;
      case 9: 
        innerText = "Connecticut Avenue";
        tileColor = "\033[38;2;120;172;255m";
        price = 120;
        rentArr = new int[]{8, 40, 100, 300, 450, 600};
        break;
      case 10: 
        innerText = "Jail / Just Visiting";
        type = 5;
        break;
      case 11: 
        innerText = "St. Charles Place";
        tileColor = "\033[38;2;255;153;240m";
        price = 140;
        rentArr = new int[]{10, 50, 150, 450, 625, 750};
        break;
      case 12: 
        innerText = "Electric Company";
        price = 150;
        break;
      case 13: 
        innerText = "States Avenue";
        tileColor = "\033[38;2;255;153;240m";
        price = 140;
        rentArr = new int[]{10, 50, 150, 450, 625, 750};
        break;
      case 14: 
        innerText = "Virginia Avenue";
        tileColor = "\033[38;2;255;153;240m";
        price = 160;
        rentArr = new int[]{12, 60, 180, 500, 700, 900};
        break;
      case 15: 
        innerText = "Pennsylvania Railroad";
        price = 200;
        break;
      case 16: 
        innerText = "St. James Place";
        tileColor = "\033[38;2;225;153;0m";
        price = 180;
        rentArr = new int[]{14, 70, 200, 550, 750, 950};
        break;
      case 17: 
        innerText = "Community Chest";
        type = 2;
        break;
      case 18: 
        innerText = "Tennessee Avenue";
        tileColor = "\033[38;2;225;153;0m";
        price = 180;
        rentArr = new int[]{14, 70, 200, 550, 750, 950};
        break;
      case 19: 
        innerText = "New York Avenue";
        tileColor = "\033[38;2;225;153;0m";
        price = 200;
        rentArr = new int[]{16, 80, 220, 600, 800, 1000};
        break;
      case 20: 
        innerText = "Free Parking";
        type = 5;
        break;
      case 21: 
        innerText = "Kentucky Avenue";
        tileColor = "\033[0;31m";
        price = 220;
        rentArr = new int[]{18, 90, 250, 700, 875, 1050};
        break;
      case 22: 
        innerText = "Chance";
        type = 2;
        break;
      case 23: 
        innerText = "Indiana Avenue";
        tileColor = "\033[0;31m";
        price = 220;
        rentArr = new int[]{18, 90, 250, 700, 875, 1050};
        break;
      case 24: 
        innerText = "Illinois Avenue";
        tileColor = "\033[0;31m";
        price = 240;
        rentArr = new int[]{20, 100, 300, 750, 925, 1100};
        break;
      case 25: 
        innerText = "B. & O. Railroad";
        price = 200;
        break;
      case 26: 
        innerText = "Atlantic Avenue";
        tileColor = "\033[0;33m";
        price = 260;
        rentArr = new int[]{22, 110, 330, 800, 975, 1150};
        break;
      case 27: 
        innerText = "Ventnor Avenue";
        tileColor = "\033[0;33m";
        price = 260;
        rentArr = new int[]{22, 110, 330, 800, 975, 1150};
        break;
      case 28: 
        innerText = "Water Works";
        price = 150;
        break;
      case 29: 
        innerText = "Marvin Gardens";
        tileColor = "\033[0;33m";
        price = 280;
        rentArr = new int[]{24, 120, 360, 850, 1025, 1200};
        break;
      case 30: 
        innerText = "Go To Jail";
        type = 3;
        break;
      case 31: 
        innerText = "Pacific Avenue";
        tileColor = "\033[38;2;62;135;81m";
        price = 300;
        rentArr = new int[]{26, 130, 390, 900, 100, 1275};
        break;
      case 32: 
        innerText = "North Carolina Avenue";
        tileColor = "\033[38;2;62;135;81m";
        price = 300;
        rentArr = new int[]{26, 130, 390, 900, 100, 1275};
        break;
      case 33: 
        innerText = "Community Chest";
        type = 2;
        break;
      case 34: 
        innerText = "Pennsylvania Avenue";
        tileColor = "\033[38;2;62;135;81m";
        price = 320;
        rentArr = new int[]{28, 150, 450, 1000, 1200, 1400};
        break;
      case 35: 
        innerText = "Short Line";
        price = 200;
        break;
      case 36: 
        innerText = "Chance";
        type = 2;
        break;
      case 37: 
        innerText = "Park Place";
        tileColor = "\033[38;2;72;0;255m";
        price = 350;
        rentArr = new int[]{35, 175, 500, 1100, 1300, 1500};
        break;
      case 38: 
        innerText = "Luxury Tax";
        type = 1;
        break;
      case 39: 
        innerText = "Boardwalk";
        tileColor = "\033[38;2;72;0;255m";
        price = 400;
        rentArr = new int[]{50, 200, 600, 1400, 1700, 2000};
        break;
    }
    
    //sets lines for formatting
    int spaceCount = 0;
    for(int i = 0; i < innerText.length(); i++){
      if(innerText.charAt(i) == ' '){
        spaceCount++;
      }
    }
    String topLine;
    String midLine;
    String bottomLine = "";
    if(spaceCount < 1){
      topLine = innerText.substring(0, innerText.length());
      midLine = "";
    } else if(spaceCount > 1){
      topLine = innerText.substring(0, innerText.indexOf(" "));
      midLine = innerText.substring(innerText.indexOf(" ") + 1, innerText.lastIndexOf(" "));
      bottomLine = innerText.substring(innerText.lastIndexOf(" ") + 1, innerText.length());
    } else {
      topLine = innerText.substring(0, innerText.indexOf(" "));
     midLine = innerText.substring(innerText.indexOf(" ") + 1, innerText.length());
    }
    String topLineSpace = Tile.spacer(topLine);
    String midLineSpace = Tile.spacer(midLine);
    String bottomLineSpace = Tile.spacer(bottomLine);
    
    //returns formatted tile
    String[] toReturn = new String[]{
      "┌────────────────┐", //0
      "│ " + tileColor + topLine + topLineSpace + ANSI_RESET + " │", //1
      "│ " + tileColor + midLine + midLineSpace + ANSI_RESET + " │", //2
      "│ " + tileColor + bottomLine + bottomLineSpace + ANSI_RESET + " │", //3
      "│────────────────│", //4
      "│                │", //5
      "│                │", //6
      "└────────────────┘"}; //7

    String oneLine = "│       " + GREEN + "──" + RESET + "       │";
    String twoLine =  "│    " + GREEN + "──    ──" + RESET + "    │";
    String threeLine = "│   " + GREEN + "──  ──  ──" + RESET + "   │";

    String hotelLine = "│       " + GREEN + "──" + RESET + "       │";

    if(hotelList.size() > 0){
      toReturn[5] = hotelLine;
    } else if(houseList.size() == 1){
      toReturn[5] = oneLine;
    } else if(houseList.size() == 2){
      toReturn[5] = twoLine;
    } else if(houseList.size() == 3){
      toReturn[5] = threeLine;
    } else if(houseList.size() == 4){
      toReturn[5] = twoLine;
      toReturn[6] = twoLine;
    } else if(houseList.size() == 5){
      toReturn[5] = threeLine;
      toReturn[6] = twoLine;
    } else if(houseList.size() == 6){
      toReturn[5] = threeLine;
      toReturn[6] = threeLine;
    }
    return toReturn;
	}

  public static String spacer(String str){
    int l = 14 - str.length();
    String spaces = "";
    for(int i = 0; i < l; i++){
      spaces += " ";
    }
    return spaces;
  }

  public void printTileDetails(){
    int rentList[] = this.getRents();
    System.out.println(" - Tile Price " + price);
    if(innerText.contains("Electric") || innerText.contains("Water")){
      System.out.println(" - Rent is 4 Times the Dice Roll that Landed the Player there");
    } else if(innerText.contains("road") || innerText.contains("Line")){
      System.out.println(" - Rent with One Railroad: " + rentList[0]);
      System.out.println(" - Rent with Two Railroads: " + rentList[1]);
      System.out.println(" - Rent with Three Railroads: " + rentList[2]);
      System.out.println(" - Rent with Four Railroads: " + rentList[3]);
    } else {
      for(int i = 0; i < 6; i++){
        String s = "";
        switch(i){
          case 0:
            s = " - Base Rent with No Properties: ";
            break;
          case 1:
            s = " - Rent with One House: ";
            break;
          case 2:
            s = " - Rent with Two Homes: ";
            break;
          case 3:
            s = " - Rent with Three Homes: ";
            break;
          case 4:
            s = " - Rent with Four Homes ";
            break;
          case 5:
            s = " - Rent with Hotel: ";
            break;
        }
        System.out.println(s + rentList[i]);
      }
    }
  }

  // public static String fixString(String str){
  //   return str.replaceAll("", "");
  //   //return str;
  //   //str.replaceAll(" ", "");
  //   //"(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]"
  // }

  // public static String[] fixStringArr(String[] arr){

  // }   
}





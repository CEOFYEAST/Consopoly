import java.util.ArrayList;
import java.util.*;

public class Player{
  private int money;
  private int position;
  private String icon;
  private boolean inJail = false;
  private int tileArray[][] =
    {
      {0, 0, 0},
      {0, 0, 0},
      {0, 0, 0},
      {0, 0, 0},
      {0, 0, 0},
      {0, 0, 0},
      {0, 0, 0},
      {0, 0, 0}
    };

  //property lists
  private ArrayList<House> houseList = new ArrayList<House>();
  private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
  private ArrayList<Tile> tileProperty = new ArrayList<Tile>();
  
  public Player(String icon){
    this.icon = icon;
    position = 0;
    money = 1500;
  }

  //mutators
  public int getMoney(){
    return money;
  }
  public void setMoney(int money){
    this.money = money;
  }
  public void minusMoney(int money){
    this.money -= money;
    System.out.println(icon + " has been deducted " + money + " dollars");
  }
  public void addMoney(int money){
    this.money += money;
    System.out.println(icon + " has been given " + money + " dollars");
  }
  public String getIcon(){
    return icon;
  }
  public void setIcon(String newIcon){
    icon = newIcon;
  }
  public int getPosition(){
    return position;
  }
  public void setPosition(int position){
    this.position = position;
    if(this.position > 39){
      this.position = 0;
    }
  }
  public ArrayList<House> getHouseList(){
    return houseList;
  }
  public ArrayList<Hotel> getHotelList(){
    return hotelList;
  }
  public ArrayList<Tile> getTilePropertyList(){
    return tileProperty;
  }
  public void addProperty(Tile tile){
    tileProperty.add(tile);
  }
  public void inJail(boolean inJail){
    this.inJail = inJail;
  }
  public int[][] getTileArray(){
    return tileArray;
  }

  // public void printProperty(){ 
  //   System.out.println("tilearray " + tileArray.length);
  //   for(int column = 0; column < 3; column++){
  //     for(int line = 0; line < 4; line++){
  //       for(int row = 0; row < tileArray.length; row++){
  //         if(tileArray[row][column] != 0){
  //           Tile currentTile = new Tile(tileArray[row][column]);
  //           String[] printTile = currentTile.getTile();
  //           if(row == 7){
  //             System.out.print(printTile[line] + "\n");
  //           } else{
  //             System.out.print(printTile[line] + "");
  //           }
  //         } else if(row == 7){
  //           System.out.print("\n");
  //         } else{
  //           System.out.print("                  ");
  //         }
  //       } 
  //     }
  //   }
  // }

  public String[] formatProperty(){
    int typeCount = 0;
    int rowZs[] = new int[8];
    ArrayList<Integer> columnList = new ArrayList<Integer>();
    ArrayList<Integer> columnOneList = new ArrayList<Integer>();
    ArrayList<Integer> columnTwoList = new ArrayList<Integer>();
    ArrayList<Integer> columnThreeList = new ArrayList<Integer>();
    for(int row = 0; row < tileArray.length; row++){
      int zCount = 0;
      for(int column = 0; column < 3; column++){
        if(tileArray[row][column] == 0){
          zCount++;
        } else{
          columnList.add(tileArray[row][column]);
        }
      }
      rowZs[row] = zCount;
      System.out.println("Z count " + zCount);
      if(zCount > 0){
        typeCount++;
      }
    }
    for(int i = 0; i < tileArray.length; i++){
     if(rowZs[i] == 0){
       columnOneList.add(columnList.get(0));
       columnList.remove(0);
       columnTwoList.add(columnList.get(0));
       columnList.remove(0);
       columnThreeList.add(columnList.get(0));
       columnList.remove(0);
     } else if(rowZs[i] == 1){
       columnOneList.add(columnList.get(0));
       columnList.remove(0);
       columnTwoList.add(columnList.get(0));
       columnList.remove(0);
       columnThreeList.add(0);
     } else if(rowZs[i] == 2){
       columnOneList.add(columnList.get(0));
       columnList.remove(0);
       columnTwoList.add(0);
       columnThreeList.add(0);
     } else {
     }
    }
    String inventoryArray[] = new String[72];
    int round = 0;
    for(int column = 0; column < 3; column++){
      ArrayList<Integer> currentColumn = new ArrayList<Integer>();
      if(column == 0){
        currentColumn = columnOneList;
      } else if(column == 1){
        currentColumn = columnTwoList;
      } else{
        currentColumn = columnThreeList;
      }
      for(int line = 0; line < 8; line++){
        String lineOn = "";
        int rowIndex = 0;
        for(int i: currentColumn){
          if(i == 0){
            if(line == 0){
              lineOn += "__________________";
            } else{
              lineOn += "|                |";
            }
          } else {
            Tile currentTile = new Tile(i);
            String printTile[] = currentTile.getTile();
            lineOn += printTile[line]; 
          }
        }
        //System.out.println(lineOn);
        inventoryArray[round] = lineOn;
        round++;
        //System.out.println("L * C " + round);
        //inventoryArray[] = lineOn;
      } 
    }
    return inventoryArray;
  }

  public void printProperty(){
    String inventoryArray[] = formatProperty();
    
    if(noANSILength(inventoryArray[1]) > 72){
      int boxNum = 7;
      for(int i = 0; i < 24; i++){
        if(i == 1 || i == 2 || i == 3 || i == 9 || i == 10 || i == 11 || i == 17 || i == 18 || i == 19){
          ArrayList<Integer> allIndex = new ArrayList<Integer>();
          ArrayList<Integer> bigLineIndex = new ArrayList<Integer>();
          ArrayList<Integer> smallLineIndex = new ArrayList<Integer>();
      
          int bigLineCount = 0;
          int smallLineCount = 0;
          for(int index = 0; index < inventoryArray[i].length(); index++){
            char cC= inventoryArray[i].charAt(index);
            if(cC == '│'){
              bigLineCount++;
              if(bigLineCount % 2 == 0){
                bigLineIndex.add(index);
              }
            }else if(cC == '|'){
              smallLineCount++;
              if(smallLineCount % 2 == 0){
                smallLineIndex.add(index);
              }
            }
          }
          for(int r: bigLineIndex){
            allIndex.add(r);
          }
          for(int e: smallLineIndex){
            allIndex.add(e);
          }
          
          Collections.sort(allIndex);
      
          int midIndex = allIndex.get(allIndex.size()/2 - 1);

          // Create a new LinkedHashSet
          Set<Integer> set = new LinkedHashSet<>();
          
          set.addAll(allIndex);

          allIndex.clear();
          allIndex.addAll(set);
          boxNum = allIndex.size();
          
          if(allIndex.size() % 2 != 0){
            System.out.println(inventoryArray[i]);
            inventoryArray[24 + i] = inventoryArray[i].substring(midIndex + 1, allIndex.get(allIndex.size() - 2));
            inventoryArray[48 + i] = inventoryArray[i].substring(allIndex.get(allIndex.size() - 2), inventoryArray[i].length());
            inventoryArray[i] = inventoryArray[i].substring(0, midIndex + 1);
          } else{
            inventoryArray[24 + i] = inventoryArray[i].substring(midIndex + 1, inventoryArray[i].length());
          inventoryArray[i] = inventoryArray[i].substring(0, midIndex + 1);
          }
        } 
        else{
          System.out.println("Size: " + boxNum);
          if(boxNum == 5){
            String cL = inventoryArray[i];
            inventoryArray[i] = cL.substring(0, 18*2 + 1);
            
            String twoAdd = cL.substring(18*2, 18*4 + 1);
            inventoryArray[24+i] = twoAdd;
            
            String threeAdd = cL.substring(18*4 + 1, 18*5 + 1);
            inventoryArray[48+i] = threeAdd;
          } else if(boxNum == 7){
            String cL = inventoryArray[i];
            inventoryArray[i] = cL.substring(0, 18*3);
            
            String twoAdd = cL.substring(18*3 , 18*6 + 1);
            inventoryArray[24+i] = twoAdd;
            
            String threeAdd = cL.substring(18*6 + 1, 18*7);
            inventoryArray[48+i] = threeAdd;
          } else{
            String cL = inventoryArray[i];
            inventoryArray[i] = cL.substring(0, cL.length()/2);
            String toAdd = cL.substring(cL.length()/2, cL.length());
            inventoryArray[24+i] = toAdd;
          }
        }
      }
    }
    for(String i: inventoryArray){
      System.out.println(i);
    }
  }
  
  public void setProperty(){
    for(int i = 0; i < tileProperty.size(); i++){
        Tile currentTile = tileProperty.get(i);
        int location = currentTile.getTileLocation();
        switch(location){
          case 1:
            tileArray[0][0] = location;
            break;     
          case 3:
            tileArray[0][1] = location;
            break;            
          case 6:
            tileArray[1][0] = location;
            break;            
          case 8:
            tileArray[1][1] = location;
            break;
          case 9:
            tileArray[1][2] = location;
            break;
          case 11:
            tileArray[2][0] = location;
            break;
          case 13:
            tileArray[2][1] = location;
            break;
          case 14:
            tileArray[2][2] = location;
            break;
          case 16:
            tileArray[3][0] = location;
            break;
          case 18:
            tileArray[3][1] = location;
            break;
          case 19:
            tileArray[3][2] = location;
            break;
          case 21:
            tileArray[4][0] = location;
            break;
          case 23:
            tileArray[4][1] = location;
            break;
          case 24:
            tileArray[4][2] = location;
            break;
          case 26:
            tileArray[5][0] = location;
            break;
          case 27:
            tileArray[5][1] = location;
            break;
          case 29:
            tileArray[5][2] = location;
            break;
          case 31:
            tileArray[6][0] = location;
            break;
          case 32:
            tileArray[6][1] = location;
            break;
          case 34:
            tileArray[6][2] = location;
            break;
          case 37:
            tileArray[7][0] = location;
            break;
          case 39:
            tileArray[7][1] = location;
            break;
        }
      }
    }

  public void trade(Player other){
    
  }

  public static int noANSILength(String str) {
    return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length();
  }

  public static String noANSI(String str) {
    return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "");
  }

}
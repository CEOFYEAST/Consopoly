import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class Board{
  //total player count
  private int playerCount;

  //player turn and their index
  private Player playerUp;
  private int playerUpIndex;
  
  //array containing ints representing every tile
  private int tilePos[] = new int[40];

  //array containing every tile as it's created 
  private Tile tiles[] = new Tile[40];

  //list of players
  private ArrayList<Player> playerList = new ArrayList<Player>();

  //list of player icons
  private ArrayList<String> availableIcons = new ArrayList<String>(Arrays.asList("Scottie Dog", "Tophat", "Thimble", "Battelship", "Racing Car", "Cat", "Wheelbarrow", "Boot"));

  //constructor 
  public Board(int playerCount){
    this.playerCount = playerCount;

    Scanner s = new Scanner(System.in);
    
    //builds tiles
    for(int i = 0; i < tilePos.length; i++){
      tilePos[i] = i;
    }
    
    //assigns player icons
    int selection;
    for(int i = 0; i < playerCount; i++){
      System.out.println("player " + (i + 1) + ", choose your icon");
      for(int l = 0; l < availableIcons.size(); l++){
        System.out.println("Press " + (l + 1) + " for " + availableIcons.get(l));
      }
      selection = s.nextInt() - 1;

      System.out.println("You chose " + availableIcons.get(selection));
      //Game.tryWait(1500);
      
      //initializes playerList
      Player player = new Player(availableIcons.get(selection));
      playerList.add(player);

      //updates available icons
      availableIcons.remove(selection);

      Board.clear();
    }
    
    //updates player turn 
    playerUp = playerList.get(0);
    playerUpIndex = 0;

    //starts the game 
    Game.turn(playerUp, this);
  }

  //mutators
  public ArrayList<Player> getPlayerList(){
    return playerList;
  }
  public void setTurn(int newIndex){
    if(newIndex > playerList.size() - 1 || newIndex <= 0){
    } else{
      playerUp = playerList.get(newIndex);
      playerUpIndex = newIndex;
    }
  }
  public Player getTurn(){
    return playerUp;
  }
  
  
  //prints a slice of the board (currently three tiles)
public static Tile printBoard(int startLocation){

    //initializes tiles
    Tile firstTile = new Tile(startLocation);
    Tile secondTile = new Tile(startLocation+1);
    Tile thirdTile = new Tile(startLocation+2);
  
    if(startLocation == 38){
      secondTile = new Tile(startLocation+1);
      thirdTile = new Tile(0);
    } else if(startLocation == 39){
      secondTile = new Tile(0);
      thirdTile = new Tile(1);
    } 
    
    String firstPrint[] = firstTile.getTile();
    String secondPrint[] = secondTile.getTile();
    String thirdPrint[] = thirdTile.getTile();
    
    for(int l = 0; l < firstPrint.length; l++){
      System.out.println(firstPrint[l] + secondPrint[l] + thirdPrint[l]);
    }

    return secondTile;
  }

  //simulates a dice roll
 public static int roll(Player player){
    Scanner s = new Scanner(System.in);
   
    Game.interact("Roll the Dice");
   
    int doublesCount = 0;
    int num1 = (int) ((Math.random() * 6) + 1);
    int num2 = (int) ((Math.random() * 6) + 1);
    int totalNum = num1 + num2;
    String userName = player.getIcon();
       

    System.out.println(userName + " rolled " + num1 + " and " + num2 + " for a total of " + totalNum);
    
    // if(num1 == num2){
    //   if(doublesCount > 0){
    //     System.out.println(userName + " rolled doubles again!");
    //   } else{
    //     System.out.println(userName + " rolled doubles!");
    //   }
    //   doublesCount++;
    // }

    if(doublesCount == 3){
      System.out.println("Go to jail");
      }
  return totalNum;
}

  public static Tile move(int moveAmount, Player player){
    //initializes player icon
    String icon = player.getIcon();
    Icon objIcon = new Icon(icon);
    String printIcon[] = objIcon.getIcon();
    Tile currentTile;

    //prints player icon and calls printboard
    for(int i = 0; i < moveAmount; i++){
      if(player.getPosition() == 0 && i != 0){
        player.addMoney(200);
        Game.tryWait(1000);
      }
      if(i == moveAmount - 2){
        break;
      }
      currentTile = printBoard(player.getPosition());
      player.setPosition(player.getPosition() + 1);
      for(int l = 0; l < printIcon.length; l++){
        System.out.print("       ");
        System.out.println(printIcon[l]);
      }
      if(moveAmount > 12){
        Game.tryWait(100);
      } else if(moveAmount > 6){
        Game.tryWait(500);
      } else if(moveAmount > 3){
        Game.tryWait(1000);
      } else {
        Game.tryWait(1500);
      }
      clear();
    }
    if(moveAmount == 0){
      currentTile = printBoard(player.getPosition() - 1);
    } else {
      currentTile = printBoard(player.getPosition());
      player.setPosition(player.getPosition() + 1);
    }
    for(int l = 0; l < printIcon.length; l++){
        System.out.println("                         " + printIcon[l]);
      }
    return currentTile;
  }
  
  public static void clear(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public int getPlayerUpIndex(){
    return playerUpIndex;
  }
  public void setPlayerUpIndex(int newIndex){
    playerUpIndex = newIndex;
  }
  public Tile[] getTiles(){
    return tiles;
  }
}

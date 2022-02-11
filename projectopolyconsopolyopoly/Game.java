import java.util.Scanner;
import java.util.Objects;
import java.util.ArrayList;
import java.util.*;

public class Game{
  private static int turnCount = 0;
  private static int rollAmount;
  
  public static void turn(Player currentPlayer, Board board){
    String username = currentPlayer.getIcon();
    Scanner s = new Scanner(System.in);
    
    ArrayList<Player> playerList = board.getPlayerList();
    int playerUpIndex = board.getPlayerUpIndex();

    //flair
    if(turnCount == 0){
      System.out.println(username + " is up first!");
    } else{
      System.out.println(username + " is up!");
    }
    
    //roll the dice
    rollAmount = Board.roll(currentPlayer);
    tryWait(1500);
    Board.clear();
    tryWait(1000);
    
    //calls Board move method 
    Tile currentTile = Board.move(rollAmount, currentPlayer);

    Tile tiles[] = board.getTiles();
    if(tiles[currentTile.getTileLocation()] != null){
      currentTile = tiles[currentTile.getTileLocation()];
    } else{
      tiles[currentTile.getTileLocation()] = currentTile;
    }

    //presents decisions
    decisions(currentPlayer, currentTile, board);
    
    turnCount++;

    if(playerUpIndex + 1 == playerList.size()){
      board.setPlayerUpIndex(0);
      turn(playerList.get(0), board);
    } else {
      board.setPlayerUpIndex(playerUpIndex + 1);
      turn(playerList.get(playerUpIndex + 1), board);
    }
  }

  public static int setPlayerCount(){
    Scanner s = new Scanner(System.in);
    int playerCount = 9;
    System.out.print("Player Count: ");
    while(playerCount > 8 || playerCount <= 1){
      playerCount = s.nextInt();
      if(playerCount > 8 || playerCount <= 1){
        System.out.println("playerCount must be between 2 and 8");
      }
    }
    Board.clear();
    return playerCount;
  }
  
  public static void decisions(Player currentPlayer, Tile currentTile, Board board){
    Player owner = currentTile.getOwner();
    String username = currentPlayer.getIcon();
    Scanner s = new Scanner(System.in);
    int type = currentTile.getType();

    System.out.println();
    System.out.println(username + " landed on " + currentTile.getTileColor() + currentTile.getInnerText() + Tile.ANSI_RESET);

    //checks if the tile is a property
    if(type == 0){
      //checks if the tile has an owner
      if(Objects.isNull(owner)){
        System.out.println("This property is unowned. Would you like to purchase it?");
        currentTile.printTileDetails();
        System.out.println();
        boolean decision = choice();
        if(decision){
          currentTile.purchase(currentPlayer);
        } else {
          System.out.println(currentTile.getInnerText() + " is up for auction");
          auction(currentTile, board);
        }
      } 
      //checks if the owner isn't the current player
      else if(username != owner.getIcon()){
        if(currentTile.isMortgaged()){
          System.out.println("This property is currently mortgaged");
        } else {
        System.out.println(currentPlayer.getIcon() + " landed on " + owner.getIcon() + "'s property");
              if(currentTile.getInnerText().contains("Electric Company") || currentTile.getInnerText().contains("Water Works")){
      currentPlayer.minusMoney(4 * rollAmount); 
    }
        currentPlayer.minusMoney(currentTile.getRent());
        }
      } 
      //checks if the owner is the current player
      else if(username == owner.getIcon()){
        System.out.println("You landed on your own property");
      }
      interact("Continue");
    }
    //checks if the tile is a tax tile
    else if(type == 1){
      if(currentTile.getInnerText().contains("Luxury")){
        currentPlayer.minusMoney(100);
      } else{
        currentPlayer.minusMoney(200);
      }
      interact("Continue");
    }
    //checks if the tile is a draw card tile
    else if(type == 2){
      System.out.println();
      if(currentTile.getInnerText().contains("Community")){
        System.out.println("Card:");
        Cards currentCard = new Cards(1, currentPlayer, board, currentTile);
      } else{
        System.out.println("Card:");
        Cards currentCard = new Cards(0, currentPlayer, board, currentTile);
      }
      
    }
    //checks if the tile is a go to jail tile
    else if(type == 3){
      System.out.println("Go to Jail!");
      //gotojail
    }
    //checks if the tile is a collect tile 
    else if(type == 4){
      System.out.println("Collect 200 dollars!");
      currentPlayer.addMoney(200);
    }
    //checks if the tile is a nothing tile 
    else if(type == 5){
    }
    
    
    
    Board.clear();
    
    Board.move(0, currentPlayer);
    //manage(currentPlayer);
    
  }

  // public static void manage(Player currentPlayer){
  //   Scanner s = new Scanner(System.in);

  //   System.out.println("Manage: ");
  //   System.out.println("Press 1 to Manage Your Property");
  //   System.out.println("Press 2 to Continue");

  //   int enter = 0;
  //   while(enter != 1 || enter != 2){
  //     enter = s.nextInt();
  //   }

  //   if(enter == 1){
  //     currentPlayer.setProperty();
  //     int playerInventory[][] = currentPlayer.getTileArray();

  //     for(int row = 0; row < playerInventory.length; row++){
  //       int zCount = 0;
  //       for(int column = 0; column < 3; column++){
  //         if(playerInventory[row][column] == 0){
  //           zCount++
  //         } else{
            
  //         }
  //       }
  //     }
  //   }
  // }

  public static boolean choice(){
    Scanner s = new Scanner(System.in);
    String choice = "";
      do {
        System.out.println("Yes or No");
        choice = s.nextLine();
      } while(choice.contains("Yes") != true && choice.contains("No") != true && choice.contains("yes") != true && choice.contains("no") != true);
      if(choice.contains("yes") || choice.contains("Yes")){
        return true;
      } 
      return false;
  }

  public static void auction(Tile currentTile, Board board) {
    clear();
    Scanner s = new Scanner(System.in);
    
    ArrayList<Player> players = board.getPlayerList();
    
    int biggestBid = 0;
    int turn = 0;
    int passAmount = 0;
    int bidNum = 0;
    Player biggestBidder = null;
    boolean win = false;
    while(players.size() > 1){
      System.out.println("You are bidding for: ");
      String tile[] = currentTile.getTile();
      for(String i: tile){
        System.out.println(i);
      }
      System.out.println("Tile Details: ");
      currentTile.printTileDetails();
      System.out.println("---------------------");
      System.out.println("Largest bid: " + biggestBid);
      
      //prevents index out of bounds
      if(turn >= players.size()){
        turn = 0;
      }

      System.out.println();
      
      //assigns turn
      Player player = players.get(turn);
      System.out.println(player.getIcon() + " is up");

      //options
      System.out.println("Press 1 to Bid");
      System.out.println("Press 2 to Pass");
      System.out.println("Press 3 to Forfeit");

      //declares input
      int input;
      do
      {
        //inputs input
        input = s.nextInt();
      } while(input != 1 && input != 2 && input != 3);

      //decides action based on input
      switch(input){
        case 1:
          //bid
          int bid;
          do
            {
              System.out.println("Input bid: ");
              bid = s.nextInt();
              if(bid <= biggestBid){
                if(bid == -1){
                  passAmount++;
                  break;
                }
                System.out.println("Bid must be bigger than " + biggestBid + ". Press -1 to cancel");
              }
            } while(bid <= biggestBid);
          biggestBid = bid;
          biggestBidder = player;
          passAmount = 0;
          bidNum += 1;
          System.out.println(player.getIcon() + " has bid " + biggestBid);
          tryWait(1000);
          break;
        case 2:
          //pass
          System.out.println(player.getIcon() + " has Passed");
          tryWait(1000);
          passAmount += 1;
          break;
        case 3:
          //removes forfeited player
          System.out.println(player.getIcon() + " has Forfeited");
          players.remove(turn);
          turn -= 1;
          passAmount = 0;
          tryWait(1000);
          break;
      }
      if(passAmount == players.size()){
        System.out.println();
        System.out.println("No bids were made. The bank retains the property.");
        win = true;
        break;
      } else if(passAmount == players.size() - 1){
        if(bidNum == 0){
        } else {
        System.out.println(biggestBidder.getIcon() + " has won with a bid of " + biggestBid);
          currentTile.purchase(biggestBidder);
          win = true;
          break;
          }
      }
      turn += 1;
      clear();
    }
    if(win){
    } else if(bidNum == 0){
      System.out.println(players.get(0).getIcon() + " has won");
    } else {
      System.out.println(biggestBidder.getIcon() + " has won with a bid of " + biggestBid);
      currentTile.purchase(biggestBidder);
  }
}

  public static void tryWait(int waitTime){
    try 
      {
          Thread.sleep(waitTime);
      } 
      catch(InterruptedException e)
      {
           
      }
  }

  public static void clear(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  
  public static void interact(String text){
    Scanner s = new Scanner(System.in);
    
    System.out.println("Press 1 to " + text);
    int enter = 0;
    while(enter != 1){
      enter = s.nextInt();
    }
  }
}
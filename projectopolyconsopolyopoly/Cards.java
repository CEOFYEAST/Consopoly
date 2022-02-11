import java.util.Arrays;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Objects;

public class Cards{
  private int type;
  private String innerText;
  
  private int availableChest[] = new int[16];
  private int availableChance[] = new int[16];

  private Player owner;
  private Board board;
  private Tile currentTile;
  
  public Cards(int type, Player owner, Board board, Tile currentTile){
    this.type = type;
    this.owner = owner;
    this.board = board;
    this.currentTile = currentTile;

    for(int i = 0; i < 16; i++){
      availableChest[i] = i;
      availableChance[i] = i;
    }
    buildCard();
  }

  public void buildCard(){
    int randInt = ((int) (Math.random() * 16)) + 1;
    if(this.type == 0){
      switch(randInt){
        case 1:
          innerText = "Advance to Boardwalk";
          System.out.println(innerText);
          goTo(39);
          break;
        // case 2:
        //   innerText = "Advance to Go (Collect $200)";
        //   System.out.println(innerText);
        //   goTo(0);
        //   break;
        case 3:
          innerText = "Advance to Illinois Avenue. If you pass Go, collect $200";
          System.out.println(innerText);
          goTo(24);
          break;
        case 4:
          innerText = "Advance to St. Charles Place. If you pass Go, collect $200";
          System.out.println(innerText);
          goTo(11);
          break;
        case 5:
          innerText = "Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled";
          System.out.println(innerText);
          System.out.println(innerText);
          if(owner.getPosition() < 5){
            goTo(5);
          } else if(owner.getPosition() <= 15){
            goTo(15);
          } else if(owner.getPosition() <= 25){
            goTo(25);
          } else{
            goTo(35);
          }
          break;
        case 6:
          innerText = "Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled";
          System.out.println(innerText);
          if(owner.getPosition() < 5){
            goTo(5);
          } else if(owner.getPosition() <= 15){
            goTo(15);
          } else if(owner.getPosition() <= 25){
            goTo(25);
          } else{
            goTo(35);
          }
          break;
        case 7:
          innerText = "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.";
          System.out.println(innerText);
          if(owner.getPosition() < 12){
            goTo(12);
          } else{
            goTo(28);
          }
          break;
        case 8:
          innerText = "Bank pays you dividend of $50";
          System.out.println(innerText);
          owner.addMoney(50);
          break;
        case 9: 
          innerText = "Get Out of Jail Free";
          System.out.println(innerText);
          break;
        case 10: 
          innerText = "Go Back 3 Spaces";
          System.out.println(innerText);
          owner.setPosition(owner.getPosition() - 3);
          currentTile = Board.move(0, owner);
          Game.decisions(owner, currentTile, board);
          break;
        case 11: 
          innerText = "Go to Jail. Go directly to Jail, do not pass Go, do not collect $200";
          System.out.println(innerText);
          goTo(30);
          owner.inJail(true);
          break;
        case 12: 
          innerText = "Make general repairs on all your property. For each house pay $25. For each hotel pay $100";
          System.out.println(innerText);
          //ArrayList<House> houseList =
          if(Objects.isNull(owner.getHouseList()) && Objects.isNull(owner.getHotelList())){
            System.out.println("You have no Homes or Hotels");
            break;
          } else if(Objects.isNull(owner.getHouseList())){
            ArrayList<Hotel> hotelList = owner.getHotelList();
            owner.minusMoney(hotelList.size() * 100);
          } else if(Objects.isNull(owner.getHotelList())){
            ArrayList<House> houseList = owner.getHouseList();
            owner.minusMoney(houseList.size() * 25);
          } else{
            ArrayList<House> houseList = owner.getHouseList();
            ArrayList<Hotel> hotelList = owner.getHotelList();
            owner.minusMoney(houseList.size() * 25 + hotelList.size() * 100);
          }
          break;
        case 13: 
          innerText = "Speeding fine $15";
          System.out.println(innerText);
          owner.minusMoney(15);
          break;
        case 14: 
          innerText = "Take a trip to Reading Railroad. If you pass Go, collect $200";
          System.out.println(innerText);
          goTo(5);
          break;
        case 15: 
          innerText = "You have been elected Chairman of the Board. Pay each player $50";
          System.out.println(innerText);
          ArrayList<Player> playerList = board.getPlayerList();
          for(int i = 0; i < playerList.size(); i++){
        if(playerList.get(i).getIcon().contains(owner.getIcon())){
              continue;
            }
            owner.minusMoney(50);
            playerList.get(i).addMoney(50);
          }
          break;
        case 16: 
          innerText = "Your building loan matures. Collect $150";
          System.out.println(innerText);
          owner.addMoney(150);
          break;
      }
    } else if(this.type == 1){
      switch(randInt){
        case 1:
          innerText = "Advance to Go (Collect $200)";
          System.out.println(innerText);
          goTo(0);
          break;
        case 2:
          innerText = "Bank error in your favor. Collect $200";
          System.out.println(innerText);
          owner.addMoney(200);
          break;
        case 3:
          innerText = "Doctor’s fee. Pay $50";
          System.out.println(innerText);
          owner.minusMoney(50);
          break;
        case 4:
          innerText = "From sale of stock you get $50";
          System.out.println(innerText);
          owner.addMoney(50);
          break;
        case 5:
          innerText = "Go to Jail. Go directly to jail, do not pass Go, do not collect $200";
          System.out.println(innerText);
          owner.inJail(true);
        case 6:
          innerText = "Go to Jail. Go directly to jail, do not pass Go, do not collect $200";
          System.out.println(innerText);
          owner.inJail(true);
          break;
        case 7:
          innerText = "Holiday fund matures. Receive $100";
          System.out.println(innerText);
          owner.addMoney(100);
          break;
        case 8:
          innerText = "Income tax refund. Collect $20";
          System.out.println(innerText);
          owner.addMoney(20);
          break;
        case 9:
          innerText = "It is your birthday. Collect $10 from every player";
          System.out.println(innerText);
          ArrayList<Player> playerList = board.getPlayerList();
          for(int i = 0; i < playerList.size(); i++){
if(playerList.get(i).getIcon().contains(owner.getIcon())){
              continue;
            }
            owner.addMoney(10);
            playerList.get(i).minusMoney(10);
          }
          break;
        case 10:
          innerText = "Life insurance matures. Collect $100";
          System.out.println(innerText);
          owner.addMoney(100);
          break;
        case 11:
          innerText = "Pay hospital fees of $100";
          System.out.println(innerText);
          owner.minusMoney(100);
          break;
        case 12:
          innerText = "Pay school fees of $50";
          System.out.println(innerText);
          owner.minusMoney(50);
          break;
        case 13:
          innerText = "Receive $25 consultancy fee";
          System.out.println(innerText);
          owner.addMoney(25);
          break;
        case 14:
          innerText = "You are assessed for street repair. $40 per house. $115 per hotel";
          System.out.println(innerText);
          if(Objects.isNull(owner.getHouseList()) && Objects.isNull(owner.getHotelList())){
            System.out.println("You have no Homes or Hotels");
            break;
          } else if(Objects.isNull(owner.getHouseList())){
            ArrayList<Hotel> hotelList = owner.getHotelList();
            owner.minusMoney(hotelList.size() * 115);
          } else if(Objects.isNull(owner.getHotelList())){
            ArrayList<House> houseList = owner.getHouseList();
            owner.minusMoney(houseList.size() * 40);
          } else{
            ArrayList<House> houseList = owner.getHouseList();
            ArrayList<Hotel> hotelList = owner.getHotelList();
            owner.minusMoney(houseList.size() * 40 + hotelList.size() * 115);
          }
          break;
        case 15:
          innerText = "You have won second prize in a beauty contest. Collect $10";
          System.out.println(innerText);
          owner.addMoney(10);
          break;
        case 16:
          innerText = "You inherit $100";
          System.out.println(innerText);
          owner.addMoney(100);
          break;
      }
      Game.interact("Continue");
    } else {
      
    }
  } 
      
  public void goTo(int moveTo){
    Game.interact("Continue");
    Board.clear();
    if(moveTo - owner.getPosition() < 0){
        currentTile = Board.move((39 - owner.getPosition()) + moveTo + 2, owner);
    } else{
      currentTile = Board.move(moveTo - owner.getPosition() + 1, owner);
    }
    Game.decisions(owner, currentTile, board);
  }

  // public static void staticGoTo(int moveTo, Player owner, Board board){
  //   Game.tryWait(3000);
  //   Board.clear();
  //   if(moveTo - owner.getPosition() < 0){
  //       Tile currentTile = Board.move((39 - owner.getPosition()) + moveTo + 2, owner);
  //   } else{
  //     Tile currentTile = Board.move(moveTo - owner.getPosition() + 1, owner);
  //   }
  //   //Game.decisions(owner, currentTile, board);
  // }
}
public class PrintBoard {
  public static void printBoard() {
      final String RESET = "\033[0m";  
    
    // Regular Colors
      final String BLACK = "\033[0;30m";   // BLACK
      final String RED = "\033[0;31m";     // RED
      final String GREEN = "\033[0;32m";   // GREEN
      final String YELLOW = "\033[0;33m";  // YELLOW
      final String BLUE = "\033[0;34m";    // BLUE
      final String PURPLE = "\033[0;35m";  // PURPLE
      final String CYAN = "\033[0;36m";    // CYAN
      final String WHITE = "\033[0;37m";   // WHITE
      final String LIGHT_PURPLE = "\033[38;2;200;0;200m"; //LIGHT PURPLE
      final String TEAL = "\033[38;2;0;225;221m"; //TEAL
      final String ORANGE = "\033[38;2;225;153;0m"; //ORANGE
      final String LIGHT_GREEN = "\033[38;2;136;255;0m"; //LIGHT GREEN
      final String LIGHT_BLUE = "\033[38;2;120;172;255m"; //LIGHT BLUE
      final String DARK_BLUE = "\033[38;2;72;0;255m"; //DARK BLUE
      final String ROSY_PINK = "\033[38;2;255;0;162m"; //ROSY PINK
      final String BROWN = "\033[38;2;135;82;62m"; //BROWN
      final String FOREST_GREEN = "\033[38;2;62;135;81m"; //FOREST GREEN
      final String BANANA_YELLOW = "\033[38;2;240;238;113m"; //BANANA YELLOW
      final String DARK_RED = "\033[38;2;145;40;16m"; //DARK RED
      final String LIGHT_PINK = "\033[38;2;255;153;240m"; //LIGHT PINK

    String boardArr[] = new String[]
    {
      " _________________________",
      "│|" + RED + "<>" + RESET + "|" + BROWN + "-" + RESET + "|" + LIGHT_BLUE + "|" + RESET + "|" + BROWN + "-" + RESET + "|" + BANANA_YELLOW + "*" + RESET + "|" + WHITE + "|" + RESET + "|" + LIGHT_BLUE + "-" + RESET + "|" + ROSY_PINK + "|" + RESET + "|" + LIGHT_BLUE + "-" + RESET + "|" + LIGHT_BLUE + "-" + RESET + "|" + ORANGE + "<>" + RESET + "|│",
      
      "│|" + PURPLE + "-" + RESET + "|                   |" + LIGHT_PURPLE + "-" + RESET + "|│",
      
      "│|" + BANANA_YELLOW + "|" + RESET + "|                   |"+ BANANA_YELLOW + "|" + RESET + "|│",
      
      "│|" + PURPLE + "-" + RESET + "|                   |" + LIGHT_PURPLE + "-" + RESET + "|│",
      
      "│|" + ORANGE + "|" + RESET + "|                   |" + LIGHT_PURPLE + "-" + RESET + "|│",
      
      "│|" + BLACK + "|" + RESET + "|                   |" + BLACK + "|" + RESET + "|│",
      
      "│|" + FOREST_GREEN + "-" + RESET + "|                   |" + ORANGE + "-" + RESET + "|│",
      
      "│|" + LIGHT_BLUE + "|" + RESET + "|                   |" + LIGHT_BLUE + "|" + RESET + "|│",
      
      "│|" + FOREST_GREEN + "-" + RESET + "|                   |" + ORANGE + "-" + RESET + "|│",

      "│|" + FOREST_GREEN + "-" + RESET + "|                   |" + ORANGE + "-" + RESET + "|│",
      
      "│|" + DARK_BLUE + "<>" + RESET + "|" + BANANA_YELLOW + "-" + RESET + "|" + ORANGE + "*" + RESET + "|" + BANANA_YELLOW + "-" + RESET + "|" + BANANA_YELLOW + "-" + RESET + "|" + BLACK + "|" + RESET + "|" + RED + "-" + RESET + "|" + RED + "-" + RESET + "|" + LIGHT_BLUE + "|" + RESET + "|" + RED + "-" + RESET + "|" + DARK_RED + "<>" + RESET + "|│",
      " ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"
    };

    for(String s: boardArr){
      System.out.println(s);
      Game.tryWait(150);
    }

    clear();

    for(int i = 0; i < boardArr.length; i++){
      if(i == 6){
        System.out.println("│|" + FOREST_GREEN + "-" + RESET + "|     CONSOPOLY     |" + ORANGE + "-" + RESET + "|│");
        continue;
      } 
      System.out.println(boardArr[i]);
    }
  }

  public static void clear(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}

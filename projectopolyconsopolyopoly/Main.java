class Main {
  public static void main(String[] args) {
    Board.clear();
    PrintBoard.printBoard();
    
    Board board = new Board(Game.setPlayerCount());
  }
}
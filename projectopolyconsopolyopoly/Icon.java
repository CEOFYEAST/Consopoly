public class Icon{
  private String icon;
  
  public Icon(String icon){
    this.icon = icon;
  }

  public String[] getIcon(){
    if(icon == "Scottie Dog"){
      String toReturn[] = new String[]{
        "_┌┐",
        "___",
        "| |"
      };
      return toReturn;
    } else if(icon == "Tophat"){
      String toReturn[] = new String[]{
        "|||",
        "|||",
        "---"
      };
      return toReturn;
    } else if(icon == "Thimble"){
      String toReturn[] = new String[]{
        " _ ",
        "| |",
        "---"
      };
      return toReturn;
    } else if(icon == "Battelship"){
      String toReturn[] = new String[]{
        " | ",
        "___",
        "|_/"
      };
      return toReturn;
    } else if(icon == "Racing Car"){
      String toReturn[] = new String[]{
        "___",
        "___",
        "0 0"
      };
      return toReturn;
    } else if(icon == "Cat"){
      String toReturn[] = new String[]{
        "__0",
        "___",
        "| |"
      };
      return toReturn;
    } else if(icon == "Wheelbarrow"){
      String toReturn[] = new String[]{
        "Why Would",
        "You Ever Choose",
        "The Wheelbarrow"
      };
      return toReturn;
    } else if(icon == "Boot"){
      String toReturn[] = new String[]{
        "__",
        "||",
        "|_|"
      };
      return toReturn;
    }
    return null;
  }
  
}
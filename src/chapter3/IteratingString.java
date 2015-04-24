package chapter3;

public class IteratingString {

  public static void main(String[] args) {
    /**going through each char**/
    final String str = "w00t";
    str.chars().forEach(System.out::println); //prints out numbers
    str.chars().forEach(IteratingString::printChar); // letters
    str.chars()
    .mapToObj(ch -> Character.valueOf((char)ch)).forEach(System.out::println); //no need to deal with extra function
    
    str.chars().filter(Character::isDigit).forEach(IteratingString::printChar);
  }
  
  private static void printChar(int aChar) {
    System.out.println((char)(aChar));
  }
}

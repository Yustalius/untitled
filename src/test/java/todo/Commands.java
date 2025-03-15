package todo;

public class Commands {

  // я получаю на вход пароль
  // если пароль == кодовое слово я верну true, если нет - false
  // кодовое слово - сосиска
  public boolean checkPassword(String password) {
    String kodovoeSlovo = "сосиска";
    if (password == kodovoeSlovo) {
      return true;
    }
    return false;
  }

}

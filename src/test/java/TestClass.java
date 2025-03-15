import org.junit.jupiter.api.Test;
import todo.Commands;

public class TestClass {
  String a = "vlad";
  String b = "zhenya";
  int chislo = 6;
  int chislo2 = 1;
  boolean bool = true;

  // zhenya vlad zhenya
  @Test
  void test() {
    // эй, sum, на тебе 2 числа, отдай мне сумму
    int summa = sum(chislo, chislo2);
//    int min = minus(chislo, chislo2);
//    int umn = umnozhenie(chislo, chislo2, 8);
//    showName(3);
    System.out.println(new Commands().checkPassword("1"));

    Commands a = new Commands();
    System.out.println(a.checkPassword("1"));

    boolean b = a.checkPassword("1");
    System.out.println(b);
  }

/*
*   public boolean checkPassword(String password) {
    String kodovoeSlovo = "сосиска";
    if (password == kodovoeSlovo) {
      return true;
    }
    return false;
  }*/






  // меня зовут oneGreaterThenTwo, мне нужны 2 числа
  // я сравню их, если 1е число больше 2го - я верну true
  // если 1е число меньше 2го - я верну false
  public boolean oneGreaterThenTwo(int int1, int int2) {
    // если int1 > int2 return true
    // если int1 < int2 return false
    if (int1 > int2) {
      return true;
    }
    return false;
  }

  // меня зовут sum, мне нужны 2 числа - я верну тебе их сумму
  // мне все равно, какие будут числа, главное, чтобы это был int
  // мне нахуй не нужен стринг, я складываю только числа
  public int sum(int int1, int int2) {
    return int1 + int2;
  }

  public int minus(int int1, int int2) {
    return int1 - int2;
  }

  // я беру 3 числа и возвращаю их умножение
  public int umnozhenie(int int1, int int2, int int3) {
    return int1 * int2 * int3;
  }

  // меня зовут showName
  // я получаю на вход имя в виде строки и вывожу его в консоль
  // я ничего не возвращаю
  // void - пустота, значит метод ничего не возвращает, его нельзя положит в переменную и не нужен return
  public void showName(String name) {
    System.out.println(name);
  }

}

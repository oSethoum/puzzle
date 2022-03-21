import java.util.ArrayList;
import java.util.Random;

public class Puzzle {

  public Integer[][] start;
  public Integer[][] end;

  public Puzzle(Integer[][] start, Integer[][] end) {
    this.start = start;
    this.end = end;
  }

  public Puzzle(int size) {
    start = generateRandom2DArray(size);
    end = generateRandom2DArray(size);
  }

  public void solve() {
    recursive(start);
  }

  private void recursive(Integer[][] array) {
    var config = getConfig(array);
    System.out.println("Config");
    for (Integer[][] c : config) {
      display(c);
    }
  }

  private void display(Integer[][] array) {
    for (Integer[] is : array) {
      for (Integer is2 : is) {
        System.out.print(is2 + "\t");
      }
      System.out.println("\n");
    }
  }

  private ArrayList<Integer[][]> getConfig(Integer[][] array) {
    var config = new ArrayList<Integer[][]>();
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length; j++) {
        if (array[i][j] == 0) {

          // corners cases
          if (i == 0 && j == 0) {
            config.add(move(i, j, Direction.DOWN, array));
            config.add(move(i, j, Direction.RIGHT, array));
          }
          if (i == 0 && j == array.length - 1) {
            config.add(move(i, j, Direction.DOWN, array));
            config.add(move(i, j, Direction.LEFT, array));
          }
          if (i == array.length - 1 && j == 0) {
            config.add(move(i, j, Direction.UP, array));
            config.add(move(i, j, Direction.RIGHT, array));
          }
          if (i == array.length - 1 && j == array.length - 1) {
            config.add(move(i, j, Direction.UP, array));
            config.add(move(i, j, Direction.LEFT, array));
          }

          // walls cases
          if (i == 0 && j > 0 && j < array.length - 1) {
            config.add(move(i, j, Direction.DOWN, array));
            config.add(move(i, j, Direction.LEFT, array));
            config.add(move(i, j, Direction.RIGHT, array));
          }
          if (i > 0 && i < array.length - 1 && j == 0) {
            config.add(move(i, j, Direction.UP, array));
            config.add(move(i, j, Direction.DOWN, array));
            config.add(move(i, j, Direction.RIGHT, array));
          }
          if (i == array.length - 1 && j > 0 && j < array.length - 1) {
            config.add(move(i, j, Direction.UP, array));
            config.add(move(i, j, Direction.LEFT, array));
            config.add(move(i, j, Direction.RIGHT, array));
          }
          if (i > 0 && i < array.length - 1 && j == array.length - 1) {
            config.add(move(i, j, Direction.UP, array));
            config.add(move(i, j, Direction.DOWN, array));
            config.add(move(i, j, Direction.LEFT, array));
          }

          // middle case
          if (i > 0 && i < array.length - 1 && j > 0 && j < array.length - 1) {
            config.add(move(i, j, Direction.UP, array));
            config.add(move(i, j, Direction.DOWN, array));
            config.add(move(i, j, Direction.LEFT, array));
            config.add(move(i, j, Direction.RIGHT, array));
          }

        }
      }
    }
    return config;
  }

  public void move_test() {
    Integer[][] arr = { { 1, 0 }, { 2, 3 } };
    Integer[][] arr2 = { { 2, 1 }, { 0, 3 } };
    display(move(0, 1, Direction.DOWN, arr));
    display(move(0, 1, Direction.LEFT, arr));
    display(move(1, 0, Direction.RIGHT, arr2));
    display(move(1, 0, Direction.UP, arr2));
  }

  Integer[][] clone(Integer[][] array) {
    var arr = new Integer[array.length][array.length];

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        arr[i][j] = array[i][j];
      }
    }
    return arr;
  }

  enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }

  private Integer[][] move(int i, int j, Direction direction, Integer[][] array) {
    switch (direction) {
      case UP:
        return swap(i, j, i - 1, j, array);
      case DOWN:
        return swap(i, j, i + 1, j, array);
      case LEFT:
        return swap(i, j, i, j - 1, array);
      default:
        return swap(i, j, i, j + 1, array);

    }
  }

  private Integer[][] swap(int ai, int aj, int bi, int bj, Integer[][] array) {
    var arr = clone(array);
    var tmp = arr[ai][aj];
    arr[ai][aj] = arr[bi][bj];
    arr[bi][bj] = tmp;
    return arr;
  }

  private Integer[][] generateRandom2DArray(int size) {
    var rand = new Random();
    var array = new Integer[size][size];
    var numbers = new ArrayList<Integer>();
    for (int i = 0; i < (size * size); i++) {
      numbers.add(i);
    }
    var i = 0;
    while (i < size) {
      var j = 0;
      while (j < size) {
        var index = rand.nextInt(numbers.size());
        array[i][j] = numbers.get(index);
        numbers.remove(index);
        j++;
      }
      i++;
    }

    return array;
  }

  public void show() {

    System.out.println("\nStart:\n");
    for (Integer[] is : start) {
      for (Integer is2 : is) {
        System.out.print(is2 + "\t");
      }
      System.out.println("\n");
    }

    System.out.println("\nEnd:\n");
    for (Integer[] is : end) {
      for (Integer is2 : is) {
        System.out.print(is2 + "\t");
      }
      System.out.println("\n");
    }
  }

  private boolean verify(int[][] a, int[][] b) {
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a.length; j++) {
        if (a[i][j] != b[i][j])
          return false;
      }
    }
    return true;
  }

}

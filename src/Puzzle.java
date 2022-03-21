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

  private boolean recursive(Integer[][] array) {

    var config = getConfig(array);
    // show config
    showConfig(config, array);

    // initialize the lowest
    var current = config.get(0);
    var g = difference(current, start);
    var h = difference(current, end);
    var chosenIndex = 0;
    if (h == 0) {
      System.out.println("Solved");
      display(current);
      return true;
    }

    var lowest = g + h;

    for (int i = 1; i < config.size(); i++) {
      current = config.get(i);
      g = difference(current, start);
      h = difference(current, end);

      if (h == 0) {
        System.out.println("Solved");
        display(current);
        return true;
      }

      var f = g + h;

      if (f < lowest) {
        lowest = f;
        chosenIndex = i;
      }
    }
    return recursive(config.get(chosenIndex));
  }

  private int difference(Integer[][] a, Integer[][] b) {
    var diff = 0;
    for (int i = 0; i < b.length; i++) {
      for (int j = 0; j < b.length; j++) {
        if (a[i][j] != b[i][j]) {
          diff++;
        }
      }
    }
    // substracte the case of 0
    if (diff > 0) {
      diff--;
    }
    return diff;
  }

  private void display(Integer[][] array) {
    for (Integer[] is : array) {
      for (Integer is2 : is) {
        System.out.print(is2 + "\t");
      }
      System.out.println("\n");
    }
  }

  private void showConfig(ArrayList<Integer[][]> config, Integer[][] array) {
    System.out.println("-------------------------------------------------");
    display(array);
    System.out.println("\t|");
    System.out.println("\t|");

    for (int i = 0; i < config.size(); i++) {
      display(config.get(i));
      System.out.println("-------------------");
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

}

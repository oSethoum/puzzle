public class App {

    public static void main(String[] args) throws Exception {
        Integer[][] start = { { 1, 2, 3 }, { 0, 4, 6 }, { 7, 5, 8 } };
        Integer[][] end = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
        var puzzle = new Puzzle(start, end);
        puzzle.show();
        puzzle.solve();
    }

}

public class Main {
    public static void main(String[] args) {
        int[][] cells;

        cells = Test(1);
        PlayingField playingField = new PlayingField(cells);
        if (playingField.isSolvable()) {
            AStarAlgorithm aStarSolver = new AStarAlgorithm(playingField);
            int i = 0;
            for (PlayingField field : aStarSolver.getSolution()) {
                System.out.println("шаг " + i + ":");
                System.out.println(field.getString());
                i++;
            }
        }
    }

    public static int[][] Test(int num) {
        if (num % 4 == 1) {
            return new int[][]{{1, 2, 3, 0}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 4}};
        }
        if (num % 4 == 2) {
            return new int[][]{{15, 5, 12, 1}, {10, 7, 6, 11}, {14, 8, 3, 0}, {9, 13, 4, 2}};
        }
        if (num % 4 == 3) {
            return new int[][]{{0, 1, 2, 3}, {6, 7, 8, 4}, {5, 9, 10, 11}, {13, 14, 15, 12}};
        }
        return new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    }
}
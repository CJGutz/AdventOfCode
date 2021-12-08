import java.util.ArrayList;

public class Matrix {
    
    private final int[][] matrix;
    private int[][] scratchBoard;
    private boolean hasWon;

    public Matrix(int[][] matrix) throws IllegalArgumentException {
        if (matrix.length > 5 || matrix[0].length > 5) throw new IllegalArgumentException("Matrix is too big.");
        this.matrix = deepCopy(matrix);
        this.scratchBoard = deepCopy(matrix);
        hasWon = false;
    }

    public int[] winningRow() {
        for (int i = 0; i < matrix.length; i++) {
            int hSum = 0;
            int vSum = 0;
            int[] col = new int[5];
            for (int j = 0; j < matrix[i].length; j++) {
                hSum += scratchBoard[i][j];
                vSum += scratchBoard[j][i];
                col[j] = matrix[j][i];
            }
            if (hSum == -5) { // Has horizontal win
                hasWon = true;
                return matrix[i];
            }
            if (vSum == -5) { // Has vertical win
                hasWon = true;
                return col;
            }
        }
        return null;
    }


    public void scratchNumber(int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target)
                    scratchBoard[i][j] = -1;
            }
        }
    }

    private int[][] deepCopy(int[][] matrix) {
        int[][] newMatrix = new int[5][5];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public int getUnmarkedNumbers(int target) {
        int answer = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (scratchBoard[i][j] != -1) {
                    answer += matrix[i][j];
                }
            }
        }
        return answer * target;
    }

    public boolean getHasWon() {
        return hasWon;
    }
}
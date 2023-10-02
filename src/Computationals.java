import javax.swing.*;

public class Computationals {
    public static String[][] interpretBoard(JButton[][] board){
        String[][] result = new String[board.length][board[0].length];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++){
                result[i][j] = board[i][j].getText();
            }
        }
        System.out.println(calculateScore(result));
        return result;
    }

    private static boolean hasWon(String x, String[][] board) {
        System.out.println("chicken dinner");
        boolean diagonalOne = true, diagonalTwo = true;
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(x) && board[i][1].equals(x) && board[i][2].equals(x)) {
                return true;
            }
            if (board[0][i].equals(x) && board[1][i].equals(x) && board[2][i].equals(x)) {
                return true;
            }
            if (!board[i][i].equals(x)) diagonalOne = false;
            if (!board[i][2 - i].equals(x)) diagonalTwo = false;
        }
        if (diagonalOne || diagonalTwo) return true;
        return false;
    }

    private static int calculateScore(String[][] board){
        System.out.println("Hello");
        if (hasWon("X", board)) {System.out.println("winner winner"); return 10;}
        if (hasWon("O", board)) {System.out.println("loser loser");return -10;}
        boolean tie = true;
        for (String[] row : board) {
            for (String x :row){
                if(x.isBlank()) tie=false;
            }
        }
        if(tie) {System.out.println("tie");return 0;}

        int score = 0; String[][] boardCopy;
        for (int row=0; row<board.length; row++) {
            for (int col=0; col<board[row].length; col++) {
                boardCopy = board;
                if(board[row][col].isBlank()){
                    boardCopy[row][col] = "X";
                    score += calculateScore(boardCopy);
                }
            }
        }
        System.out.println("Score: " + score);
        return score;
    }
    //TODO: Add "O" player
}

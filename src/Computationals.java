import javax.swing.*;
import java.util.Arrays;

public class Computationals {

    private static String[][] deepCopy(String[][] original){
        String[][] copy = new String[original.length][original[0].length];
        for(int i = 0; i < copy.length;i++){
            for (int j = 0;j < copy.length;j++){
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }
    public static String[][] interpretBoard(JButton[][] board){
        String[][] result = new String[board.length][board[0].length];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++){
                result[i][j] = board[i][j].getText();
            }
        }
        return result;
    }

    private static boolean hasWon(String x, String[][] board) {
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
        if (diagonalOne || diagonalTwo) {return true;}
        return false;
    }

    private static int calculateScore(final String[][] board, String letter){

        if (hasWon(letter, board)) {for (String[] l1 : board){System.out.println();for (String l2 : l1){if(l2.isBlank()){System.out.print("b");}System.out.print(l2 + " ");}}System.out.println("\n///////");return 10;}

        if (letter.equals("X") && hasWon("O", board)) {System.out.println("\n O wins///////");return -10;}

        else if(letter.equals("O") && hasWon("X", board)){System.out.println("\n X wins///////");return -10;}

        boolean tie = true;
        for (String[] row : board) {
            for (String x :row){
                if(x.isBlank()) tie=false;
            }
        }
        if(tie) {return 0;}

        int score = 0; String[][] boardCopy;
        for (int row=0; row<board.length; row++) {
            for (int col=0; col<board[row].length; col++) {
                boardCopy = deepCopy(board);
                if(board[row][col].isBlank()){
                    boardCopy[row][col] = letter;
                    System.out.println(letter + ": [" + row + "] [" + col + "]");
                    if(letter.equals("X")){score -= calculateScore(boardCopy, "O");}
                    if(letter.equals("O")){score -= calculateScore(boardCopy, "X");}
                }
            }
        }
        return score;
    }
    public static int[] findBestMove(JButton[][] board, String playa){
        System.out.println("New best move");
        String[][] boardClone = interpretBoard(board); String[][] boardCopy;
        int[] coord = new int[2]; int score=Integer.MIN_VALUE;int scoreCopy;
        for(int row=0; row<boardClone.length; row++) {
            for(int col=0;col<boardClone[0].length; col++) {
                boardClone = interpretBoard(board);
                if(boardClone[row][col].isBlank()){

                    boardCopy = Arrays.copyOf(boardClone, boardClone.length);
                    boardCopy[row][col] = playa;
                    System.out.println("\n\n--------------------------------\n[" + row + "] [" + col + "]");
                    scoreCopy =  -1 * calculateScore(boardCopy, "O");
                    System.out.println(scoreCopy);
                    if(scoreCopy > score){
                        coord[0] = row;coord[1] = col;
                        score = scoreCopy;
                    }

                }
            }
        }
        return coord;
    }
}

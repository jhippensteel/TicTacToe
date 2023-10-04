import javax.swing.*;

public class Computationals {
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

    private static int calculateScore(String[][] board, String letter){
        System.out.println("Hello");
        if (hasWon(letter, board)) {System.out.println("winner winner"); return 10;}
        //if (hasWon("O", board)) {System.out.println("loser loser");return -10;}
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
                    boardCopy[row][col] = letter;
                    if(letter.equals("X")){score += calculateScore(boardCopy, "X") - calculateScore(boardCopy, "O");}
                    if(letter.equals("O")){score += calculateScore(boardCopy, "O") - calculateScore(boardCopy, "X");}
                }
            }
        }
        System.out.println("Score: " + score);
        return score;
    }
    public static int[] findBestMove(JButton[][] board, String playa){
        String[][] boardClone = interpretBoard(board); String[][] boardCopy = boardClone;
        int[] coord = new int[2]; int score=Integer.MIN_VALUE;int scoreCopy;
        for(int row=0; row<boardClone.length; row++) {
            for(int col=0;col<boardClone[0].length; col++) {
                if(boardClone[row][col].isBlank()){
                    boardCopy = boardClone;
                    boardCopy[row][col] = playa;
                    scoreCopy = calculateScore(boardCopy, playa);
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

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is the main window of the game.
 * It contains all the components needed to display a game window.
 *
 * @extends  {<a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html">JFrame</a>}
 */
public class DisplayWindow extends JFrame {

    JPanel playerOneArea = new JPanel();JPanel boardArea = new JPanel();JPanel playerTwoArea = new JPanel();
    String player = "O";
    JButton[][] matrix = new JButton[3][3];

    JPanel winArea = new JPanel();
    int counter = 0;
    int playerOneScore = 0;JTextArea playerOneData = new JTextArea("Player One\n\nScore: " + playerOneScore);
    int playerTwoScore = 0;JTextArea playerTwoData = new JTextArea("Player Two\n\nScore: " + playerTwoScore);

    /**
     * This method is the constructor of the DisplayWindow class
     * @param title the title of the window
     */
    public DisplayWindow(String title){
        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        setSize(1000, 600);
        setTitle(title);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(playerOneArea, BorderLayout.WEST);add(boardArea, BorderLayout.CENTER);add(playerTwoArea, BorderLayout.EAST);

        playerOneArea.setFont(new Font("Arial", Font.ITALIC, 80));
        playerOneData.setEditable(false);playerOneData.setFont(new Font("Arial", Font.PLAIN, 25));playerOneData.setBackground(null);playerOneArea.add(playerOneData);
        playerOneArea.setBackground(Color.green);
        playerOneArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerTwoData.setEditable(false);playerTwoData.setFont(new Font("Arial", Font.PLAIN, 25));playerTwoData.setBackground(null);playerTwoArea.add(playerTwoData);
        playerTwoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        boardArea.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = new JButton();
                matrix[i][j].setFont(new Font("Arial", Font.BOLD, 80));
                int finalI = i;
                int finalJ = j;
                matrix[i][j].addActionListener(e -> updateBoard(finalI, finalJ));
                boardArea.add(matrix[i][j]);
            }
        }



        setVisible(true);
    }

    /**
     * Method that processes the gameplay of the game.
     * Displays button text based on which player pressed it.
     * Determines if either player has won the game, if not, changes the player
     *
     * @param row the row of the button that was pressed
     * @param col the column of the button that was pressed
     */
    public void updateBoard(int row, int col){
        counter++;
        matrix[row][col].setText(player);
        matrix[row][col].setEnabled(false);

        boolean diagonalOne = true;boolean diagonalTwo = true;
        for (int i = 0; i < 3; i++) {
            if(matrix[i][0].getText().equals(player) && matrix[i][1].getText().equals(player) && matrix[i][2].getText().equals(player)){
                winScreen();
            }
            if(matrix[0][i].getText().equals(player) && matrix[1][i].getText().equals(player) && matrix[2][i].getText().equals(player)){
                winScreen();
            }
            if(!matrix[i][i].getText().equals(player)) diagonalOne = false;
            if(!matrix[i][2-i].getText().equals(player)) diagonalTwo = false;
        }
        if(diagonalOne || diagonalTwo) winScreen();
        if(counter == 9){restart();}

        switch (player) {
            case "O" -> {
                player = "X";
                playerOneArea.setBackground(Color.gray);
                playerTwoArea.setBackground(Color.green);
            }
            case "X" -> {
                player = "O";
                playerOneArea.setBackground(Color.green);
                playerTwoArea.setBackground(Color.gray);
            }
        }
        if(player.equals("X")) {
            System.out.println("\n\n_________________________________\n\n");
            long memory = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
            int[] bestCoord = Computationals.findBestMove(matrix, "X");
            long afterMemory = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
            System.out.println("Used memory: " + (afterMemory-memory)/1e+6+" MB");
            matrix[bestCoord[0]][bestCoord[1]].doClick();
            player = "O";
        }
    }

    /**
     * This method is used to display the win screen.
     */
    public void winScreen(){
        switch (player) {case "O" -> playerOneScore++;case "X" -> playerTwoScore++;}
        playerOneData.setText("Player One\n\nScore: " + playerOneScore);
        playerTwoData.setText("Player Two\n\nScore: " + playerTwoScore);

        this.remove(boardArea);
        winArea.removeAll();
        winArea.add(new JLabel("Player " + (player.equals("O")? "One" : "Two") + " is the winner")).setFont(new Font("Times New Roman", Font.BOLD, 50));

        JButton restartButton = new JButton("Restart Game");
        restartButton.addActionListener(e -> restart());
        winArea.add(restartButton);

        this.add(winArea, BorderLayout.CENTER);
        this.setSize(1000, 601);this.setSize(1000, 600);
    }

    /**
     * This method is used to restart the game when a player has won the game or a tie occurs.
     */
    public void restart() {
        this.remove(winArea);
        boardArea.removeAll();
        this.add(boardArea, BorderLayout.CENTER);

        counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = new JButton();
                matrix[i][j].setFont(new Font("Arial", Font.BOLD, 80));
                int finalI = i;
                int finalJ = j;
                matrix[i][j].addActionListener(e -> updateBoard(finalI, finalJ));
                boardArea.add(matrix[i][j]);

            }
        }


        this.setSize(1000, 601);this.setSize(1000, 600);
    }
}

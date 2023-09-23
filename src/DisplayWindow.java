import javax.swing.*;
import java.awt.*;

public class DisplayWindow extends JFrame {

    JPanel playerOneArea = new JPanel();JPanel boardArea = new JPanel();JPanel playerTwoArea = new JPanel();
    String player = "O";
    JButton[][] matrix = new JButton[3][3];

    JPanel winArea = new JPanel();

    public DisplayWindow(String title){
        setSize(1000, 600);
        setTitle(title);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(playerOneArea, BorderLayout.WEST);add(boardArea, BorderLayout.CENTER);add(playerTwoArea, BorderLayout.EAST);

        playerOneArea.setFont(new Font("Arial", Font.ITALIC, 80));
        playerOneArea.add(new JLabel("Player One"));
        playerOneArea.setBackground(Color.green);
        playerOneArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerTwoArea.add(new JLabel("Player Two"));
        playerTwoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        boardArea.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = new JButton();
                matrix[i][j].setFont(new Font("Arial", Font.BOLD, 80));
                int finalI = i;
                int finalJ = j;
                matrix[i][j].addActionListener(e -> {updateBoard(finalI, finalJ);});
                boardArea.add(matrix[i][j]);
            }
        }


        setVisible(true);
    }

    public void updateBoard(int row, int col){
        matrix[row][col].setText(player);
        matrix[row][col].setEnabled(false);

        for (int i = 0; i < 3; i++) {
            if(matrix[i][0].getText().equals(player) && matrix[i][1].getText().equals(player) && matrix[i][2].getText().equals(player)){
                this.remove(boardArea);
                winArea.add(new JLabel("Player " + (player.equals("O")? "One" : "Two") + " is the winner")).setFont(new Font("Times New Roman", Font.BOLD, 50));
                this.add(winArea, BorderLayout.CENTER);
            }
            if(matrix[0][i].getText().equals(player) && matrix[1][i].getText().equals(player) && matrix[2][i].getText().equals(player)){
                this.remove(boardArea);
                winArea.add(new JLabel("Player " + (player.equals("O")? "One" : "Two") + " is the winner")).setFont(new Font("Times New Roman", Font.BOLD, 50));
                this.add(winArea, BorderLayout.CENTER);
            }
        }

        switch (player){
            case "O":
                player = "X";
                playerOneArea.setBackground(Color.gray);
                playerTwoArea.setBackground(Color.green);
                break;
            case "X":
                player = "O";
                playerOneArea.setBackground(Color.green);
                playerTwoArea.setBackground(Color.gray);
                break;
        }
    }
}

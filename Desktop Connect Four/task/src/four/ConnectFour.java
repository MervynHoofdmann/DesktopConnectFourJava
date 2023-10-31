package four;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ConnectFour extends JFrame {
    TurnCounter turnCounter = new TurnCounter();
    private final int rows = 6;
    private final int columns = 7;
    private final JButton[][] buttons = new JButton[rows][columns];
    private final ArrayList<JButton> correctGridButtons = new ArrayList<>();

    public ConnectFour() {
        //set frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(714, 700);
        setVisible(true);
        setLayout(null);
        setTitle("Connect Four");
        //set grid panel
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(rows, columns));
        grid.setBounds(0, 0, 700, 600);
        add(grid);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                char buttonChar = (char) ('A' + j);
                int buttonNumber = 6 - i;
                String buttonName = "Button" + buttonChar + buttonNumber;
                buttons[i][j] = new JButton(" ");
                var b = buttons[i][j];
                b.setSize(100, 100);
                b.setName(buttonName);
                b.setFocusPainted(false);
                b.addActionListener(e -> gridButton(b));
                grid.add(b);
            }
        }
        //set taskbar/reset button
        JPanel taskbar = new JPanel();
        taskbar.setBounds(0, 600, 700, 100);
        add(taskbar);
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(e -> resetButton());
        taskbar.add(resetButton);
    }

    private void gridButton(JButton b) {
        int currentTurn = turnCounter.getTurnCounter();
        String buttonName = b.getName();
        int column = (buttonName.charAt(6) - 'A');
        String stringToSet;
        if (currentTurn % 2 == 0) stringToSet = "X";
        else stringToSet = "O";
        for (int j = 5; j >= 0; j--) {
            if (buttons[j][column].getText().equals(" ")) {
                buttons[j][column].setText(stringToSet);
                if (checkVertical() || checkHorizontal() || checkBackDiagonal() || checkForwardDiagonal()) {
                    for (JButton c : correctGridButtons) {
                        c.setBackground(Color.green);
                    }
                    for (int k = 0; k < rows; k++) {
                        for (int l = 0; l < columns; l++) {
                            buttons[k][l].setEnabled(false);
                        }
                    }
                }
                turnCounter.incrementTurnCounter();
                break;
            }
        }

    }

    private void resetButton() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                var c = buttons[i][j];
                c.setText(" ");
                c.setBackground(null);
                c.setEnabled(true);
            }
        }
        correctGridButtons.clear();
        turnCounter.resetTurnCounter();
    }

    private boolean checkVertical() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows - 3; j++) {
                String stringToCheck = buttons[j][i].getText();
                if (!stringToCheck.equals(" ")) {
                    int correctSquares = 1;
                    for (int k = 1; k <= 3; k++) {
                        if (buttons[j + k][i].getText().equals(stringToCheck)) correctSquares++;
                    }
                    if (correctSquares == 4) {
                        for (int k = 0; k <= 3; k++) {
                            correctGridButtons.add(buttons[j + k][i]);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkHorizontal() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns - 3; j++) {
                String stringToCheck = buttons[i][j].getText();
                if (!stringToCheck.equals(" ")) {
                    int correctSquares = 1;
                    for (int k = 1; k <= 3; k++) {
                        if (buttons[i][j + k].getText().equals(stringToCheck)) correctSquares++;
                    }
                    if (correctSquares == 4) {
                        for (int k = 0; k <= 3; k++) {
                            correctGridButtons.add(buttons[i][j + k]);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkBackDiagonal() {
        for (int i = 0; i < columns - 3; i++) {
            for (int j = 0; j < rows - 3; j++) {
                String stringToCheck = buttons[j][i].getText();
                if (!stringToCheck.equals(" ")) {
                    int correctSquares = 1;
                    for (int k = 1; k <= 3; k++) {
                        if (buttons[j + k][i + k].getText().equals(stringToCheck)) correctSquares++;
                    }
                    if (correctSquares == 4) {
                        for (int k = 0; k <= 3; k++) {
                            correctGridButtons.add(buttons[j + k][i + k]);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkForwardDiagonal() {
        for (int i = 3; i < columns; i++) {
            for (int j = 0; j < rows - 3; j++) {
                String stringToCheck = buttons[j][i].getText();
                if (!stringToCheck.equals(" ")) {
                    int correctSquares = 1;
                    for (int k = 1; k <= 3; k++) {
                        if (buttons[j + k][i - k].getText().equals(stringToCheck)) correctSquares++;
                    }
                    if (correctSquares == 4) {
                        for (int k = 0; k <= 3; k++) {
                            correctGridButtons.add(buttons[j + k][i - k]);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
package org.cis120.sudoku;

import java.awt.*;
import javax.swing.*;

public class Cell extends JFormattedTextField {

    // Private instance variables
    private Color backgroundColor;
    private int row;
    private int col;
    private int correctNumber;
    CellState status;

    // Public constants
    public static final Color PREFILLEDCOLOR = Color.LIGHT_GRAY;
    public static final Color GUESS_RIGHT_COLOR = Color.GREEN;
    public static final Font FONT_NUMBERS =
            new Font("SansSerif", Font.BOLD, 25);

    // Constructor
    public Cell(int row, int col) {
        this.backgroundColor = Color.WHITE;
        this.row = row;
        this.col = col;
        setHorizontalAlignment(CENTER);
        setFont(FONT_NUMBERS);
    }

    // Initialize cell
    public void init (int correctNumber, CellState status) {
        this.correctNumber = correctNumber;
        this.status = status;
        cellDisplay();
    }

    // Get number
    public int getCorrectNumber() {
        return correctNumber;
    }

    // Get status
    public CellState getStatus() {
        return status;
    }

    // Set status
    public void setStatus(CellState status) {
        this.status = status;
    }

    // Paint the cell
    public void cellDisplay() {
        // If the cell can't be changed, display the #
        // and do not allow edits
        if (status == CellState.PREFILLED) {
            setText(String.valueOf(correctNumber));
            setEditable(false);
            setBackground(PREFILLEDCOLOR);
        }
        // If guessed incorrectly, make red
        if (status == CellState.GUESS_WRONG) {
            setBackground(Color.RED);
            // If number is guessed correctly, make cell green
        } else if (status == CellState.GUESS_RIGHT) {
            backgroundColor = GUESS_RIGHT_COLOR;
            setBackground(backgroundColor);
            // If number is guessed incorrectly, make cell text red
            // If the cell needs to be changed, let it be edited
            // Background initialized to unclicked - no change
        } else if (status == CellState.GUESS) {
            setEditable(true);
            setBackground(Color.WHITE);
        }


    }


}

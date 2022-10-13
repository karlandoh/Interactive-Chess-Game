/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.humber;

/**
 *
 * @author karlandoh
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class ChessPiece {

    final String name;
    final String short_name;
    String board_name;
    final String user;
    final String side;
    String[] coordinates;

    public ChessPiece(String name, String short_name, String user, String side) {
        this.name = name;
        this.user = user;
        this.side = side;
        this.short_name = short_name;
        this.board_name = String.format("%s_%s_%s", user, side, short_name);

    }

    public ArrayList<String[]> moveCapabilities() {
        return new ArrayList<String[]>();
    }
}

class Rook extends ChessPiece {

    public Rook(String user, String side) {
        super("Rook", "Ro", user, side);
    }

    @Override
    public ArrayList<String[]> moveCapabilities() {

        ArrayList<String[]> my_array = new ArrayList<String[]>();
        int[] num_coordinates = ChessBoard.convertToNumber(this.coordinates);

        for (int i = 0; i < 8; i++) { //HORIZONTAL

            if (i == num_coordinates[1]) {
                continue;
            }
            my_array.add(ChessBoard.convertFromNumber(new int[]{num_coordinates[0], i}));
        }

        for (int i = 0; i < 8; i++) { //VERTICAL

            if (i == num_coordinates[0]) {
                continue;
            }
            my_array.add(ChessBoard.convertFromNumber(new int[]{i, num_coordinates[1]}));

        }

        return my_array;
    }
}

class Knight extends ChessPiece {

    public Knight(String user, String side) {
        super("Knight", "Kn", user, side);
    }

    public ArrayList<String[]> moveCapabilities() {
        ArrayList<String[]> my_array = new ArrayList<String[]>();
        int[] num_coordinates = ChessBoard.convertToNumber(this.coordinates);
        for (int i = 0; i < 4; i++) {

            int potential_number_a = num_coordinates[0];
            int potential_number_b = num_coordinates[1];

            if (i == 0) {
                potential_number_a = num_coordinates[0] + 2;
                potential_number_b = num_coordinates[1] + 1;
            }
            if (i == 1) {
                potential_number_a = num_coordinates[0] + 2;
                potential_number_b = num_coordinates[1] - 1;
            }
            if (i == 2) {
                potential_number_a = num_coordinates[0] - 2;
                potential_number_b = num_coordinates[1] + 1;
            }
            if (i == 3) {
                potential_number_a = num_coordinates[0] - 2;
                potential_number_b = num_coordinates[1] - 1;
            }

            if (potential_number_a < 0 || potential_number_b < 0 || potential_number_a > 7 || potential_number_b > 7) {
                continue;
            }
            my_array.add(ChessBoard.convertFromNumber(new int[]{potential_number_a, potential_number_b}));
        }

        return my_array;
    }
}

class Bishop extends ChessPiece {

    public Bishop(String user, String side) {
        super("Bishop", "Bi", user, side);
    }

    public ArrayList<String[]> moveCapabilities() {
        ArrayList<String[]> my_array = new ArrayList<String[]>();
        int[] num_coordinates = ChessBoard.convertToNumber(this.coordinates);

        int potential_number_a = num_coordinates[0];
        int potential_number_b = num_coordinates[1];

        for (int i = num_coordinates[0] + 1; i < 8; i++) { //UP RIGHT

            potential_number_b += 1;

            if (potential_number_b > 7) {
                break;
            }

            my_array.add(ChessBoard.convertFromNumber(new int[]{i, potential_number_b}));
        }

        potential_number_a = num_coordinates[0];
        potential_number_b = num_coordinates[1];

        for (int i = num_coordinates[0] - 1; i >= 0; i--) { //DOWN RIGHT

            potential_number_b += 1;

            if (potential_number_b > 7) {
                break;
            }

            my_array.add(ChessBoard.convertFromNumber(new int[]{i, potential_number_b}));
        }

        potential_number_a = num_coordinates[0];
        potential_number_b = num_coordinates[1];

        for (int i = num_coordinates[0] - 1; i >= 0; i--) { //DOWN LEFT

            potential_number_b -= 1;

            if (potential_number_b < 0) {
                break;
            }

            my_array.add(ChessBoard.convertFromNumber(new int[]{i, potential_number_b}));
        }

        potential_number_a = num_coordinates[0];
        potential_number_b = num_coordinates[1];

        for (int i = num_coordinates[0] + 1; i < 8; i++) { //DOWN LEFT

            potential_number_b -= 1;

            if (potential_number_b < 7) {
                break;
            }

            my_array.add(ChessBoard.convertFromNumber(new int[]{i, potential_number_b}));
        }
        return my_array;
    }
}

class Queen extends ChessPiece {

    public Queen(String user) {
        super("Queen", "Qu", user, "L");
        //this.board_name = String.format("%s___%s", user, "Qu");
    }

    public ArrayList<String[]> moveCapabilities() {
        ArrayList<String[]> my_array = new ArrayList<String[]>();
        int[] num_coordinates = ChessBoard.convertToNumber(this.coordinates);

        for (int i = 0; i < 8; i++) { //HORIZONTAL

            if (i == num_coordinates[1]) {
                continue;
            }
            my_array.add(ChessBoard.convertFromNumber(new int[]{num_coordinates[0], i}));
        }

        for (int i = 0; i < 8; i++) { //VERTICAL

            if (i == num_coordinates[0]) {
                continue;
            }
            my_array.add(ChessBoard.convertFromNumber(new int[]{i, num_coordinates[1]}));

        }

        int potential_number_a = num_coordinates[0];
        int potential_number_b = num_coordinates[1];

        for (int i = num_coordinates[0] + 1; i < 8; i++) { //UP RIGHT

            potential_number_b += 1;

            if (potential_number_b > 7) {
                break;
            }

            my_array.add(ChessBoard.convertFromNumber(new int[]{i, potential_number_b}));
        }

        potential_number_a = num_coordinates[0];
        potential_number_b = num_coordinates[1];

        for (int i = num_coordinates[0] - 1; i >= 0; i--) { //DOWN RIGHT

            potential_number_b += 1;

            if (potential_number_b > 7) {
                break;
            }

            my_array.add(ChessBoard.convertFromNumber(new int[]{i, potential_number_b}));
        }

        potential_number_a = num_coordinates[0];
        potential_number_b = num_coordinates[1];

        for (int i = num_coordinates[0] - 1; i >= 0; i--) { //DOWN LEFT

            potential_number_b -= 1;

            if (potential_number_b < 0) {
                break;
            }

            my_array.add(ChessBoard.convertFromNumber(new int[]{i, potential_number_b}));
        }

        potential_number_a = num_coordinates[0];
        potential_number_b = num_coordinates[1];

        for (int i = num_coordinates[0] + 1; i < 8; i++) { //DOWN LEFT

            potential_number_b -= 1;

            if (potential_number_b < 7) {
                break;
            }

            my_array.add(ChessBoard.convertFromNumber(new int[]{i, potential_number_b}));
        }        

        return my_array;
    }
}

class King extends ChessPiece {

    public King(String user) {
        super("King", "Ki", user, "L");
        //this.board_name = String.format("%s___%s", user, "Ki");
    }

    public ArrayList<String[]> moveCapabilities() {
        ArrayList<String[]> my_array = new ArrayList<String[]>();
        int[] num_coordinates = ChessBoard.convertToNumber(this.coordinates);

        for (int i = 0; i < 8; i++) {

            int potential_number_a = num_coordinates[0];
            int potential_number_b = num_coordinates[1];

            if (i == 0) {
                potential_number_a = num_coordinates[0] + 1;
                potential_number_b = num_coordinates[1];
            }
            if (i == 1) {
                potential_number_a = num_coordinates[0];
                potential_number_b = num_coordinates[1] + 1;
            }
            if (i == 2) {
                potential_number_a = num_coordinates[0];
                potential_number_b = num_coordinates[1] - 1;
            }
            if (i == 3) {
                potential_number_a = num_coordinates[0] - 1;
                potential_number_b = num_coordinates[1];
            }
            if (i == 4) {
                potential_number_a = num_coordinates[0] + 1;
                potential_number_b = num_coordinates[1] + 1;
            }
            if (i == 5) {
                potential_number_a = num_coordinates[0] + 1;
                potential_number_b = num_coordinates[1] - 1;
            }
            if (i == 6) {
                potential_number_a = num_coordinates[0] - 1;
                potential_number_b = num_coordinates[1] + 1;
            }
            if (i == 7) {
                potential_number_a = num_coordinates[0] - 1;
                potential_number_b = num_coordinates[1] - 1;
            }

            if (potential_number_a < 0 || potential_number_b < 0 || potential_number_a > 7 || potential_number_b > 7) {
                continue;
            }
            my_array.add(ChessBoard.convertFromNumber(new int[]{potential_number_a, potential_number_b}));
        }

        return my_array;
    }
}

class Pawn extends ChessPiece {

    public int number;

    public Pawn(String user, int number) {
        super("Pawn", "Pa", user, String.valueOf(number));
        this.number = number;
        this.board_name = String.format("%s_%s_%s", user, this.short_name, number);
    }

    public ArrayList<String[]> moveCapabilities() {
        ArrayList<String[]> my_array = new ArrayList<String[]>();
        int[] num_coordinates = ChessBoard.convertToNumber(this.coordinates);

        int potential_number = num_coordinates[0];

        if (this.user == "B") {
            potential_number -= 1;
        }
        if (this.user == "W") {
            potential_number += 1;
        }

        if (potential_number < 0 || potential_number > 7) {
            return my_array;
        }

        my_array.add(ChessBoard.convertFromNumber(new int[]{potential_number, num_coordinates[1]}));

        return my_array;
    }
}

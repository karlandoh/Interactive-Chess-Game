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
import java.util.Map;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {

    private String[][] grid;

    public static String alphabet = "abcdefgh";
    public static String numbers = "12345678";
    public String resetX = "   X  ";
    
    public HashMap<String, HashMap<String, HashMap<String, ChessPiece>>> all_pieces;

    public ChessBoard() {
        this.grid = new String[8][8];
        this.initializeGrid();
        this.all_pieces = this.createPieces();

    }
    


    public HashMap<String, HashMap<String, HashMap<String, ChessPiece>>> createPieces() {

        ArrayList<ChessPiece> w_user = new ArrayList<ChessPiece>(
                Arrays.asList(new Rook("W", "L"), new Bishop("W", "L"), new Knight("W", "L"), new Queen("W"), new King("W"), new Rook("W", "R"), new Bishop("W", "R"), new Knight("W", "R"))
        );

        ArrayList<ChessPiece> b_user = new ArrayList<ChessPiece>(
                Arrays.asList(new Rook("B", "L"), new Bishop("B", "L"), new Knight("B", "L"), new Queen("B"), new King("B"), new Rook("B", "R"), new Bishop("B", "R"), new Knight("B", "R"))
        );
        
        for (int i=0; i<8; i++){
            w_user.add(new Pawn("W",i+1));
            b_user.add(new Pawn("B",i+1));
            
        }

        HashMap<String, HashMap<String, ChessPiece>> w_user_hash = new HashMap<String, HashMap<String, ChessPiece>>();
        HashMap<String, HashMap<String, ChessPiece>> b_user_hash = new HashMap<String, HashMap<String, ChessPiece>>();

        HashMap<String, ChessPiece> w_left_pieces = new HashMap<String, ChessPiece>();
        HashMap<String, ChessPiece> w_right_pieces = new HashMap<String, ChessPiece>();
        HashMap<String, ChessPiece> w_pawns = new HashMap<String, ChessPiece>();
        
        HashMap<String, ChessPiece> b_left_pieces = new HashMap<String, ChessPiece>();
        HashMap<String, ChessPiece> b_right_pieces = new HashMap<String, ChessPiece>();
        HashMap<String, ChessPiece> b_pawns = new HashMap<String, ChessPiece>();
        
        HashMap<String, HashMap<String, HashMap<String, ChessPiece>>> game = new HashMap<String, HashMap<String, HashMap<String, ChessPiece>>>();

        w_user.addAll(b_user);
        
        
        //CREATE THE HASHMAP
        for (ChessPiece piece : w_user) {
            if (piece.user.equals("W")) {
                
                if (piece.side.equals("L")) {
                   w_left_pieces.put(piece.short_name, piece);
                }
                if (piece.side.equals("R")) {
                   w_right_pieces.put(piece.short_name, piece);
                }
                
                if (piece.name.equals("Pawn")){
                    w_pawns.put(piece.side, piece);
                }
            }

            if (piece.user.equals("B")) {
                if (piece.side.equals("L")) {
                    b_left_pieces.put(piece.short_name, piece);
                }
                if (piece.side.equals("R")) {
                    b_right_pieces.put(piece.short_name, piece);
                }
                if (piece.name.equals("Pawn")){
                    b_pawns.put(piece.side, piece);
                }

            }

        }
        
        w_user_hash.put("L",w_left_pieces);
        w_user_hash.put("R",w_right_pieces);
        w_user_hash.put("Pa",w_pawns);
        
        b_user_hash.put("L",b_left_pieces);
        b_user_hash.put("R",b_right_pieces);
        b_user_hash.put("Pa",b_pawns);
        
        game.put("W",w_user_hash);
        game.put("B",b_user_hash);
        
        //Places the pieces on the board!
        this.place(game.get("W").get("L").get("Ro"),new String[] {"A","1"});
        this.place(game.get("W").get("L").get("Kn"),new String[] {"B","1"});
        this.place(game.get("W").get("L").get("Bi"),new String[] {"C","1"});
        this.place(game.get("W").get("L").get("Qu"),new String[] {"D","1"});
        this.place(game.get("W").get("L").get("Ki"),new String[] {"E","1"});
        this.place(game.get("W").get("R").get("Bi"),new String[] {"F","1"});
        this.place(game.get("W").get("R").get("Kn"),new String[] {"G","1"});
        this.place(game.get("W").get("R").get("Ro"),new String[] {"H","1"});
        
        this.place(game.get("W").get("Pa").get("1"),new String[] {"A","2"});
        this.place(game.get("W").get("Pa").get("2"),new String[] {"B","2"});
        this.place(game.get("W").get("Pa").get("3"),new String[] {"C","2"});
        this.place(game.get("W").get("Pa").get("4"),new String[] {"D","2"});
        this.place(game.get("W").get("Pa").get("5"),new String[] {"E","2"});
        this.place(game.get("W").get("Pa").get("6"),new String[] {"F","2"});
        this.place(game.get("W").get("Pa").get("7"),new String[] {"G","2"});
        this.place(game.get("W").get("Pa").get("8"),new String[] {"H","2"});
        
        this.place(game.get("B").get("L").get("Ro"),new String[] {"A","8"});
        this.place(game.get("B").get("L").get("Kn"),new String[] {"B","8"});
        this.place(game.get("B").get("L").get("Bi"),new String[] {"C","8"});
        this.place(game.get("B").get("L").get("Qu"),new String[] {"D","8"});
        this.place(game.get("B").get("L").get("Ki"),new String[] {"E","8"});
        this.place(game.get("B").get("R").get("Bi"),new String[] {"F","8"});
        this.place(game.get("B").get("R").get("Kn"),new String[] {"G","8"});
        this.place(game.get("B").get("R").get("Ro"),new String[] {"H","8"});
        
        this.place(game.get("B").get("Pa").get("1"),new String[] {"A","7"});
        this.place(game.get("B").get("Pa").get("2"),new String[] {"B","7"});
        this.place(game.get("B").get("Pa").get("3"),new String[] {"C","7"});
        this.place(game.get("B").get("Pa").get("4"),new String[] {"D","7"});
        this.place(game.get("B").get("Pa").get("5"),new String[] {"E","7"});
        this.place(game.get("B").get("Pa").get("6"),new String[] {"F","7"});
        this.place(game.get("B").get("Pa").get("7"),new String[] {"G","7"});
        this.place(game.get("B").get("Pa").get("8"),new String[] {"H","7"});
        
        return game;

    }
    
    public void initializeGrid() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                this.grid[row][column] = this.resetX;
            }
        }

    }
    public void place(ChessPiece piece, String[] _coordinates) {
         
        piece.coordinates = _coordinates;
        this.setCell(piece.board_name, piece.coordinates);  
        
    }

    public void moveTo(ChessPiece piece, String[] _coordinates) {
        
        this.resetCell(piece.coordinates);   
        piece.coordinates = _coordinates;
        this.setCell(piece.board_name, piece.coordinates);  
        
    }    
    
    public String isSomeoneThere(String current_user, String[] current_coordinates) {

        for (Map.Entry<String, HashMap<String, HashMap<String, ChessPiece>>> user_pieces : this.all_pieces.entrySet()) { //LEVEL 1 - USER

            String user = user_pieces.getKey();
            HashMap<String, HashMap<String, ChessPiece>> pieces = user_pieces.getValue();

            for (Map.Entry<String, HashMap<String, ChessPiece>> piece_types : pieces.entrySet()) { //LEVEL 2 - PIECE TYPES

                String piece_type = piece_types.getKey();
                HashMap<String, ChessPiece> piece = piece_types.getValue();

                for (Map.Entry<String, ChessPiece> piece_name : piece.entrySet()) { //LEVEL 3 - THE PIECE

                    String current_piece_type = piece_name.getKey();
                    ChessPiece selected_piece = piece_name.getValue();
                    
                    if (Arrays.equals(selected_piece.coordinates,current_coordinates)){
                            if (selected_piece.user.equals(current_user)){
                                return String.format("SAME_%s",selected_piece.board_name);
                            }
                            else{
                                return String.format("OPPOSITE_%s",selected_piece.board_name);
                            }
                }

        }

    }
        }
        return "EMPTY";
    }

    public void printGrid() {

        //Alphabet
        int i = 0;
        for (char letter : alphabet.toCharArray()) {
            if (i == 0) {
                System.out.print("       "); //First Expand here
            }
            System.out.print(String.valueOf(letter).toUpperCase() + "         "); // Between Expand here
            i += 1;
        }
        System.out.println();
        System.out.println();

        //Middle
        for (int row = 7; row >= 0; row--) {

            System.out.print(this.numbers.charAt(row) + "   "); //First Expand Here

            for (int column = 0; column < 8; column++) {

                System.out.print(this.grid[row][column] + "    "); //Between Expand here

            }

            System.out.print(this.numbers.charAt(row) + "   ");

            System.out.println();
            System.out.println();
        }

        //Alphabet
        i = 0;
        for (char letter : alphabet.toCharArray()) {
            if (i == 0) {
                System.out.print("       "); //First Expand here
            }
            System.out.print(String.valueOf(letter).toUpperCase() + "         "); // Between Expand here
            i += 1;
        }
        System.out.println();

    }
    

    private void resetCell(String[] coordinates) {
        
        int[] number_coordinates = convertToNumber(coordinates);
        
        this.grid[number_coordinates[0]][number_coordinates[1]] = this.resetX;
    }

    private void setCell(String name, String[] coordinates) {

        int[] number_coordinates = convertToNumber(coordinates);

        this.grid[number_coordinates[0]][number_coordinates[1]] = name;
    }

    public static int[] convertToNumber(String[] coordinates) {
        
        
        return new int[]{"12345678".indexOf(coordinates[1]), "abcdefgh".indexOf(coordinates[0].toLowerCase())};
    }

    public static String[] convertFromNumber(int[] coordinates) {

        //return new String[]{String.valueOf("abcdefgh".toUpperCase().charAt(coordinates[0])), String.valueOf(coordinates[1]+1)};
        return new String[]{String.valueOf("abcdefgh".toUpperCase().charAt(coordinates[1])),String.valueOf(coordinates[0]+1)};
        
        
        //return String.format("%s%d",this.alphabet.charAt(coordinates[0]),coordinates[1]);
    }
    //3,6 = G4
    
    

}

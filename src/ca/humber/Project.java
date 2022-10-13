/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca.humber;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ListIterator;

/**
 *
 * @author karlandoh
 */
public class Project {

    public static ArrayList<ArrayList<String>> playerOptions(String user, ChessPiece piece, ChessBoard board) {

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> my_moves_arraylist = new ArrayList<ArrayList<String>>();

        ArrayList<String[]> my_moves = piece.moveCapabilities();
        
        

        for (String[] coordinates : my_moves) {
            //System.out.println(coordinates[0]);
            //System.out.println(coordinates[1]);
            my_moves_arraylist.add(new ArrayList<>(Arrays.asList(coordinates)));
        }

        ListIterator<ArrayList<String>> li = my_moves_arraylist.listIterator(my_moves.size());

        while (user.equals("B") ? li.hasNext() : li.hasPrevious()) {
            ArrayList<String> coordinates = user.equals("B") ? li.next() : li.previous();
        }

        System.out.println("Available Empty Spots. ");

        while (user.equals("B") ? li.hasPrevious() : li.hasNext()) {
            ArrayList<String> coordinates = user.equals("B") ? li.previous() : li.next();

            String[] arr = new String[coordinates.size()];
            arr = coordinates.toArray(arr);

            String is_someone_there = board.isSomeoneThere(user, arr);

            if (is_someone_there.contains("SAME")) {
                continue;
            }

            if (is_someone_there.contains("OPPOSITE")) {
                continue;
            }

            System.out.println(coordinates.toString());

            coordinates.add(is_someone_there);

            result.add(coordinates);

        }
        return result;
    }

    public static boolean canMoveTo(String[] coordinates, ArrayList<ArrayList<String>> list) {
        for (ArrayList<String> a_coordinates : list) {
            if (a_coordinates.get(0).equals(coordinates[0]) && a_coordinates.get(1).equals(coordinates[1])) {
                return true;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String user = "W";

        ChessBoard chessboard = new ChessBoard();

        chessboard.printGrid();

        System.out.println(String.format("\n------------------\nU S E R       %s\n------------------\n", user));
        while (true) {
            String[] chosen_piece;
            ChessPiece selected_piece;
            
            while (true) {
                System.out.println("Please select a piece by name. Or type 'exit' Ex. W_L_Bi");

                try {
                    chosen_piece = scanner.nextLine().split("_");
                    
                    if (chosen_piece[0].equalsIgnoreCase("exit")){
                        System.out.println("Thanks for playing!e");
                        System.exit(0);
                    }
                    
                    selected_piece = chessboard.all_pieces.get(user).get(chosen_piece[1]).get(chosen_piece[2]);
                    String test = selected_piece.name;
                    break;
                } catch (Exception e) {
                    continue;
                }
                
                
            }

            if (!chosen_piece[0].equals(user)) {
                System.out.println(String.format("You cannot choose that piece. You are user %s.", user));
                continue;
            }

            

            ArrayList<ArrayList<String>> result = playerOptions(user, selected_piece, chessboard);

            String[] chosen_spot;
            
            if (result.isEmpty()){
                System.out.println("Cannot move this piece!");
                continue;
            }
            while (!result.isEmpty()) {
                System.out.println("Would you like to move the piece to one of the selected spots? Please type! Ex. 'A 5'");

                try {
                    chosen_spot = scanner.nextLine().split(" ");
                } catch (Exception e) {
                    continue;
                }
                
                if (canMoveTo(chosen_spot,result)){
                    
                    chessboard.moveTo(selected_piece, chosen_spot);
                    break;
                    
                }
                else{
                    System.out.println("You cannot move there.");
                }

            }

            if (user.equals("W")) {
                user = "B";
            } 
            else if (user.equals("B")) {
                user = "W";
            } else {;
            }
            chessboard.printGrid();
            System.out.println(String.format("\n------------------\nU S E R       %s\n------------------\n", user));

        }

    }

}

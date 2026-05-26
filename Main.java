/**
 * Author: Zijun Wei
 * Program name: 1D-Chess
 * Date: Apr/15/2026
 * Description: This program runs a 2 player game of chess on a 1 by 8 board
 * Difficulties: determining the validity of moves
 * future plannings: improve print outs, debug movement issues with the rook, add aditional features
 */


import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args){
        //initialize
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        boolean playing = true;
        int currentPlayer = 1;
        
        //nested while loop to avoid invalid user inputs
        while(playing){
            //update board
            board.printBoard();
            System.out.println("Player " + currentPlayer + "'s turn\n");
            
            //reset user input coordinates
            int from = -1;
            int to = -1;
            
            //get user inputs
            while(from==-1||to==-1)
            {
            System.out.print("Enter move (from  to): ");
            try
            {
                from = sc.nextInt()-1;
                to = sc.nextInt()-1;
                
                //evaluate move
                boolean isValidMove = board.move(from, to, currentPlayer);
                
                if (isValidMove)
                {
                    //switch players
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;  //ternary operator, works the same as if else
                }else
                {
                    //reobtain user input
                    board.printBoard();
                    from = -1;
                    to = -1;
                }
                
            }catch(InputMismatchException e)
            {
                System.out.println("invalid input! Please input 2 coordinates separated by space");
                sc.nextLine();
            } 
                //end game
                if(board.gameOver())
                {
                    board.printBoard();
                    playing = false;
                }
                       
        }

    }
    }
}

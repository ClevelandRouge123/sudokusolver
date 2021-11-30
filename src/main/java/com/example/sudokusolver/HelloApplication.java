package com.example.sudokusolver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication {
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
                {0,8,6,5,0,0,0,7,9},
                {2,7,0,0,8,0,0,5,0},
                {1,9,5,0,7,0,0,4,0},
                {5,0,0,7,0,0,4,0,0},
                {9,3,2,0,4,0,7,1,0},
                {6,4,7,3,0,1,9,0,5},
                {0,0,4,0,3,6,0,0,7},
                {0,0,0,1,5,7,8,6,4},
                {0,6,0,0,0,0,0,3,0}
        };

        if(solveBoard(board)){
            System.out.println("Solved");
        }else{
            System.out.println("not possible");
        }
        printBoard(board);
//        launch();
    }

    private static void printBoard(int[][] board) {
        for(int row = 0; row < GRID_SIZE; row++){
            for(int column = 0; column < GRID_SIZE; column++){
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i =0; i < GRID_SIZE; i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i =0; i < GRID_SIZE; i++){
            if(board[i][column] == number){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for(int i = localBoxRow; i < localBoxRow + 3; i++){
            for(int j = localBoxColumn; j < localBoxColumn + 3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean isValidPlacement(int[][] board, int number, int row, int column){
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board){
        for (int row = 0; row < GRID_SIZE; row++){
            for(int column = 0; column < GRID_SIZE; column++){
                if(board[row][column] == 0){
                    for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++){
                        if(isValidPlacement(board, numberToTry, row, column)){
                            board[row][column] = numberToTry;
                            if(solveBoard(board)){
                                return true;
                            }else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}

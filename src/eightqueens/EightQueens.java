/*
 * This is a program that can find a solution to the Eight Queens problem.
 * It creates an array of Queen objects which hold two int values that
 * represent its coordinates on the x and y axes. 
 */
package eightqueens;

import java.util.*;

/**
 *
 * @author Robert M Bryant
 * @version 7/6/2020
 */
public class EightQueens {

    private final static int N = 8;
    private static int heuristic = 0;
    private static int restarts = 0;
    private static int neighbors = 0;
    private static int stateChanges = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Queen[] startState = initialState();
        int currentHeuristic = findHeuristic(startState);

        //Loop through the different states of Queens to find the solution
        while (currentHeuristic != 0) {
            startState = bestNeighbor(startState);
            currentHeuristic = heuristic;
            System.out.println("Current heuristic: " + heuristic);
            System.out.print("Current State");
            printState(startState);
        }
        
        System.out.println("Solution Found!");
        System.out.println("State Changes: " + stateChanges);
        System.out.println("Restarts: " + restarts);

    }

    /**
     * Create the initial array list of Queen objects.
     * These represent the position of each Queen in a current state.
     *
     * @return array of Queen objects
     */
    public static Queen[] initialState() {
        
        Queen[] initialState = new Queen[N];

        for (int i = 0; i < N; i++) {
            int random = (int) (Math.random() * 8);
            initialState[i] = new Queen(random, i);
        }
        return initialState;
    }

    /**
     * Find the heuristic of the state passed in.
     *
     * @param state array of Queen objects representing the state.
     * @return int representing the heuristic.
     */
    public static int findHeuristic(Queen[] state) {
        int h = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = i + 1; j < state.length; j++) {
                if (state[i].inConflict(state[j])) {
                    h++;
                }
            }
        }
        return h;
    }

    /**
     * Print out the state.
     *
     * @param state array of Queen objects representing a state.
     */
    public static void printState(Queen[] state) {
        int[][] board = new int[N][N];

        //Assin a 1 to each coordinate that matches a Queen object
        for (int i = 0; i < N; i++) {
            board[state[i].getRow()][state[i].getColumn()] = 1;
        }

        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    /**
     * Take in the current state of Queens on the board and cycle through the
     * different Queen positions to find a solution.
     * 
     * @param currentState array of Queen objects.
     * @return array of Queens that has a lower heuristic.
     */
    public static Queen[] bestNeighbor(Queen[] currentState) {
        Queen[] neighbor = new Queen[N];
        Queen[] tempState = new Queen[N];
        int currentHeuristic = findHeuristic(currentState);
        int bestHeuristic = currentHeuristic;
        int tempHeuristic;

        //Copy the currentState into the neighbor and temp Queen array
        for (int i = 0; i < N; i++) {
            neighbor[i] = new Queen(currentState[i].getRow(), 
                    currentState[i].getColumn());
            tempState[i] = new Queen(currentState[i].getRow(), 
                    currentState[i].getColumn());
        }

        //Use the temporary Queen array to create different states
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                tempState[i - 1] = new Queen(currentState[i - 1].getRow(), 
                        currentState[i - 1].getColumn());
            }
            tempState[i] = new Queen(0, tempState[i].getColumn());
            for (int j = 0; j < N; j++) {
                //Find the heuristic for each neighbor
                tempHeuristic = findHeuristic(tempState);
                //If the neighbor has a lower heuristic keep the array
                if (tempHeuristic < bestHeuristic) {
                    neighbors++;
                    bestHeuristic = tempHeuristic;
                    for (int k = 0; k < N; k++) {
                        neighbor[k] = new Queen(tempState[k].getRow(), 
                                tempState[k].getColumn());
                    }
                }
                //Move the Queen down a row
                if (tempState[i].getRow() != N - 1) {
                    tempState[i].move();

                }
            }
            
        }
         //Display the amount of neighbors with a lower heuristic
        System.out.println("Neighbors with lower heuristic: " + neighbors);
        System.out.println("Setting a new current state");
        System.out.println();
        neighbors = 0;//Reset the amount of neighbors
        stateChanges++;//Keep track of how many state changes are made
        
        //When a local minima is found, do a random restart
        if (bestHeuristic == currentHeuristic) {
            restarts++;//Keep track of how many restarts occur
            System.out.println("Minima found, randomly restarting...");
            neighbor = initialState();//Create a new "board" of Queens
            heuristic = findHeuristic(neighbor);
        } else {
            heuristic = bestHeuristic;
        }
        
        return neighbor;
    }

}

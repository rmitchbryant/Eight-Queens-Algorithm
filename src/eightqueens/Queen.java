/*
 * This class is for Queen objects
 */
package eightqueens;

/**
 *
 * @author Robert M Bryant 
 * @version 7/6/2020
 */
public class Queen {
    
    private int row;
    private int column;
    
    /**
     * Constructor for a Queen object.
     * @param row int representing the row the Queen occupies.
     * @param column int representing the column the Queen occupies.
     */
    public Queen(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    /**
     * Move the Queen object to the row below it.
     */
    public void move(){
        row++;
    }
    
    /**
     * Compare two Queen objects to determine if they are in conflict.
     * @param q A Queen object.
     * @return true if a conflict is found, false if not.
     */
    public boolean inConflict(Queen q) {
        if (row == q.getRow()){ //See if the Queens are on the same row.
            return true;
        }
        //See if the Queens are on the same diagonal.
        else if(Math.abs(row-q.getRow()) == Math.abs(column-q.getColumn())){
            return true;
        }
        return false;
    }
    /**
     * Getter for row for Queen object.
     * @return int that represents the row the Queen occupies.
     */
    public int getRow(){
        return row;
    }
    /**
     * Getter for the column for Queen object.
     * @return int that represents the column the Queen occupies.
     */
    public int getColumn(){
        return column;
    }
    /**
     * Setter for the row for the Queen object.
     * @param r int that represents the row the Queen should occupy.
     */
    public void setRow(int r){
        row = r;
    }
    /**
     * Setter for the column for the Queen object.
     * @param c int that represents the column the Queen should occupy.
     */
    public void setColumn(int c){
        column = c;
    }
}

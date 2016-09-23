/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author outlaw
 */
public class Movement {
    
    public static final int MOVE=1;
    public static final int SELECT=0;
    public static final int DISELECT=2;
    
    private final char from;
    private final char to;
    private final int status;
    private final int disck;

    protected Movement(int status, char from, char to, int disck) {
        this.from = from;
        this.to = to;
        this.status = status;
        this.disck = disck;
    }

    protected char getFrom() {
        return from;
    }

    protected char getTo() {
        return to;
    }

    protected int getState() {
        return status;
    }

    protected int getDisc() {
        return disck;
    }
    
    
    
}

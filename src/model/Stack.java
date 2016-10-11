/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author outlaw
 */
public class Stack {

    private final ArrayList<Movement> undo;
    private final ArrayList<Movement> redo;
    private char from;
    private char to;
    private int disc;

    public Stack() {
        this.undo = new ArrayList();
        this.redo = new ArrayList();
    }

    /**
     *
     * @param from
     * @param to
     * @param disc
     */
    protected void addMovement(char from, char to, int disc) {
        undo.add(new Movement(from, to, disc));
        redo.clear();
    }

    protected boolean undo() { 
        if(undo.isEmpty()){
            from = Command.EMPTY;
            to = Command.EMPTY;
            disc = 0;
            return false;
        }
        Movement move =undo.remove(undo.size()-1);
        redo.add(move);
        from = move.from;
        to = move.to;
        disc = move.disc;
        return true;
    }
    
    protected Movement redo() { 
        if(redo.isEmpty())
            return null;
        Movement move =redo.remove(redo.size()-1);
        undo.add(move);
        return move;
    }

    protected boolean hadUndo() {
        return !undo.isEmpty();
    }

    protected boolean hadRedo() {
        return !redo.isEmpty();
    }

    protected char getFrom() {
        return from;
    }

    protected char getTo() {
        return to;
    }

    protected int getDisc() {
        return disc;
    }
    private class Movement{
        char from;
        char to;
        int disc;

        public Movement(char from, char to, int disc) {
            this.from = from;
            this.to = to;
            this.disc = disc;
        }
    }
}

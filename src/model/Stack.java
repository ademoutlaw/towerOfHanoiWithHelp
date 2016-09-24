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

    public Stack() {
        this.undo = new ArrayList();
        this.redo = new ArrayList();
    }

    
    
    protected void addMovement(Movement move) {
        undo.add(move);
        redo.clear();
    }

    protected Movement undo() { 
        if(undo.isEmpty())
            return null;
        Movement move =undo.remove(undo.size()-1);
        redo.add(move);
        return move;
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
    
}

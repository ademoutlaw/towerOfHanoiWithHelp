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

    private ArrayList<Movement> undo;
    private ArrayList<Movement> redo;

    public Stack() {
        this.undo = new ArrayList();
        this.redo = new ArrayList();
    }

    
    
    protected void addMovement(Movement move) {
        undo.add(move);
    }
    
}

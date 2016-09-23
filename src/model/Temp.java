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
public class Temp {
    private Disc disck;
    private Stack from;
    private char fromName;
    private boolean hasDisck;
    private final Stack stackA;
    private final Stack stackB;
    private final Stack stackC;
    private Move move;
    private int size;

    public Temp(int size) {
        this.size=size;
        stackA = new Stack(size);
        stackB = new Stack();
        stackC = new Stack();
    }

    public void setTemp(char stackName){
        move = getMove(stackName);
        
    }
    public ArrayList<Integer> getStackA(){
        return stackA.getStack();
        
    }
    public ArrayList<Integer> getStackB(){
        return stackB.getStack();
        
    }
    public ArrayList<Integer> getStackC(){
        return stackC.getStack();
        
    }
    
    private Move getMove(char stackName){
        Stack stack = getStack(stackName);
        if(hasDisck){
            hasDisck=false;
            if(stack.addDisk(disck))
                return new Move(Move.MOVE,fromName,stackName,disck.getSize());
            from.add(disck);
            return new Move(Move.MOVE,fromName,fromName,disck.getSize());               
        }
        from=stack;
        fromName=stackName;
        hasDisck=(disck=stack.getLasDisck())!=null;
        if(hasDisck)
           return new Move(Move.SELECT,fromName,' ',disck.getSize());
        return new Move(Move.MOVE,fromName,fromName,0);   
    }
    private Stack getStack(char panelName) {
        switch(panelName){
            case 'A':
                return stackA;
            case 'B':
                return stackB;
            case 'C':
                return stackC;
        }
        System.out.println("#####"+panelName);
        return null;
    }

    public int getMoveState() {
        return move.getState();
    }

    public char getMoveFrom() {
        return move.getFrom();
    }

    public char getMoveTo() {
        return move.getTo();
    }

    public int getMoveDisc() {
        return move.getDisc();
    }

    public int getSize() {
        return size;
    }
}

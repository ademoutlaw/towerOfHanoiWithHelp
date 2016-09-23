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
public class Command {
    
    private Disc disck;
    private Tower from;
    private char fromName;
    private boolean hasDisck;
    private final Tower stackA;
    private final Tower stackB;
    private final Tower stackC;
    private Movement move;
    private int size;

    public Command(int size) {
        this.size=size;
        stackA = new Tower(size);
        stackB = new Tower();
        stackC = new Tower();
    }

    public void setMove(char stackName){
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
    
    private Movement getMove(char stackName){
        Tower stack = getStack(stackName);
        if(hasDisck){
            hasDisck=false;
            if(stack.addDisk(disck))
                return new Movement(Movement.MOVE,fromName,stackName,disck.getSize());
            from.add(disck);
            return new Movement(Movement.MOVE,fromName,fromName,disck.getSize());               
        }
        from=stack;
        fromName=stackName;
        hasDisck=(disck=stack.getLasDisck())!=null;
        if(hasDisck)
           return new Movement(Movement.SELECT,fromName,' ',disck.getSize());
        return new Movement(Movement.MOVE,fromName,fromName,0);   
    }
    
    private Tower getStack(char panelName) {
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

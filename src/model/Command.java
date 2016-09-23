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
    private final Tower towerA;
    private final Tower towerB;
    private final Tower towerC;
    private Movement move;
    private int size;

    public Command(int size) {
        this.size=size;
        towerA = new Tower(size);
        towerB = new Tower();
        towerC = new Tower();
    }

    public void setMove(char stackName){
        move = getMove(stackName);
        
    }
    
    public ArrayList<Integer> getTowerA(){
        return towerA.getStack();
        
    }
    
    public ArrayList<Integer> getTowerB(){
        return towerB.getStack();
        
    }
    
    public ArrayList<Integer> getTowerC(){
        return towerC.getStack();
        
    }
    
    private Movement getMove(char stackName){
        Tower stack = getTower(stackName);
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
    
    private Tower getTower(char panelName) {
        switch(panelName){
            case 'A':
                return towerA;
            case 'B':
                return towerB;
            case 'C':
                return towerC;
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

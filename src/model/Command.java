/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author outlaw
 */
public class Command {
    
    public static final char TOWER_A = 'A';
    public static final char TOWER_C = 'C';
    public static final char TOWER_B = 'B';
    public static final char EMPTY = '_';
    
    public static final char MOVE = 'm';
    public static final char SELECT = 's';
    public static final char DESELECT = 'd';
    
    
    private boolean isSelected;
    private int disc; 
    private char discFrom;   
    private char discTo;
    private char action;
    
    private final Tower towerA;
    private final Tower towerB;
    private final Tower towerC;
    
    private int level;
    
 
    
    private final Stack stack;

    public Command() {
        this.level=9;
        stack = new Stack();
        towerA = new Tower(level);
        towerB = new Tower(level);
        towerC = new Tower(level);
    }

    public void setMove(char towerName){
        if(isSelected){
            if(discFrom != towerName && getTower(towerName).add(disc)){
                System.out.println("you can move");
                isSelected = false;                
                action = MOVE;
                discTo = towerName;                
            }else{
                getTower(discFrom).add(disc);
                System.out.println("diselected ");
                isSelected = false;                
                action = DESELECT;
                disc = 0;
                discFrom = EMPTY;
                discTo = EMPTY;                
            }            
        }else{
            disc = getTower(towerName).getLasDisck();
            System.out.println("selected ");
            isSelected = true && disc>0;
            action = SELECT;
            discFrom = towerName;
            discTo = EMPTY;
        }
        
    }
    
    private Tower getTower(char towerName) {
        switch(towerName){
            case 'A':
                return towerA;
            case 'B':
                return towerB;
            case 'C':
                return towerC;
        }
        return null;
    }
    
    public boolean undo(){
      /*  if(hasDisck){
            hasDisck=false;
            from.add(disck);
        }
        if((move = stack.undo())!=null){
            getTower(move.getFrom())
                    .addDisk(
                            getTower(move.getTo())
                                    .getLasDisck()
                    );
            reverseMove();
            return true;
        }*/
        return false;
    }
    
    public boolean redo() {
        /*if(hasDisck){
            hasDisck=false;
            from.add(disck);
        }
        if((move = stack.redo())!=null){
            getTower(move.getTo())
                    .addDisk(
                            getTower(move.getFrom())
                                    .getLasDisck()
                    );
            return true;
        }*/
        return false;
    }
    
    public List<Integer> getTowerA(){
        return  towerA.getDiscs();        
    }
    
    public List<Integer> getTowerB(){
        return towerB.getDiscs();
    }
    
    public List<Integer> getTowerC(){
        return towerC.getDiscs();        
    }
                 

    private void reverseMove() {
       // move = new Movement(Movement.MOVE,move.getTo(),move.getFrom(),move.getDisc());
    }

    public boolean hadUndo() {
        return stack.hadUndo();
    }

    public boolean hadRedo() {
        return stack.hadRedo();
    }

    public void setMove(int disc, char from, char to) {
        getTower(to).add(getTower(from).getLasDisck());
    }

    public int getDisc() {
        return disc;
    }

    public char getDiscFrom() {
        return discFrom;
    }

    public char getDiscTo() {
        return discTo;
    }

    public char getAction() {
        return action;
    }

    public int getLevel() {
        return level;
    }

    public boolean isSaved() {
        return false;
    }

    public boolean setLevel(int l) {                        
        if(l < level){
            level = l;
            towerA.init(level);
            towerB.init(0);
            towerC.init(0);
            return true;
        }
        return l==level;
    }

    public boolean win() {
        return false;
    }
        
}

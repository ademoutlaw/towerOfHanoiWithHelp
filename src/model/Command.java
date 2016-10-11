/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.bot.Bot;

/**
 *
 * @author outlaw
 */
public class Command {
    
    public static final char TOWER_A = 'A';
    public static final char TOWER_C = 'C';
    public static final char TOWER_B = 'B';
    public static final char EMPTY = '_';
    
    public static final char MOVE = 'M';
    public static final char MOVE_FAST = 'm';
    public static final char SELECT = 'S';
    public static final char DESELECT = 's';
    public static final char DESELECT_ERR = 'E';
    
    
    private boolean isSelected;
    private int disc; 
    private char discFrom;   
    private char discTo;
    private char action;
    
    private final Tower towerA;
    private final Tower towerB;
    private final Tower towerC;
    
    
    
 
    
    private final Stack stack;
    private final int lastLevel;
    private boolean saved;
    private int level;
    private int steps;
    private final Bot bot;
    private int helpUsed;
    private int helpTotal;


    public Command() {
        saved = true;
        lastLevel = 12;
        helpTotal = 126;
        helpUsed = 85;
        steps = 888;
        level=0;
        stack = new Stack();
        towerA = Test.getTowerA();//new Tower(level);
        towerB = Test.getTowerB();//new Tower(0);
        towerC = Test.getTowerC();//new Tower(0);
        bot =  new Bot(towerA, towerB, towerC);
    }

    public void setAction(char towerName){
        if(isSelected){
            if(discFrom != towerName && getTower(towerName).add(disc)){
                isSelected = false;                
                action = MOVE;
                discTo = towerName; 
                stack.addMovement(discFrom,discTo,disc);
                saved = false;
                steps++;
            }else{
                getTower(discFrom).add(disc);
                isSelected = false;                
                action = discFrom == towerName?DESELECT:DESELECT_ERR;
                disc = 0;
                discFrom = EMPTY;
                discTo = EMPTY;                
            }            
        }else{
            disc = getTower(towerName).getLasDisck();
            isSelected = disc>0;
            action = SELECT;
            discFrom = towerName;
            discTo = EMPTY;
        }
        
    }
    
    private Tower getTower(char towerName) {
        switch(towerName){
            case Command.TOWER_A:
                return towerA;
            case Command.TOWER_B:
                return towerB;
            case Command.TOWER_C:
                return towerC;
        }
        return null;
    }
    
    public boolean undo(){        
        if(stack.undo()){
            if(isSelected){
                isSelected=false;
                getTower(discFrom).add(disc);
            }
            action = MOVE_FAST;
            discTo = stack.getFrom();
            discFrom = stack.getTo();
            disc = getTower(discFrom).getLasDisck();
            getTower(discTo).add(disc);   
            steps--;
            return true;
        }
        return false;
    }
    
    public boolean redo() {
        if(stack.redo()){
            if(isSelected){
                isSelected=false;
                getTower(discFrom).add(disc);
            }
            action = MOVE_FAST;
            discFrom = stack.getFrom();
            discTo = stack.getTo();
            disc = getTower(discFrom).getLasDisck();
            getTower(discTo).add(disc);  
            steps++;
            return true;
        }
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
                 
    public boolean hadUndo() {
        return stack.hadUndo();
    }

    public boolean hadRedo() {
        return stack.hadRedo();
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

    public int getLastLevel() {
        return lastLevel;
    }

    public boolean setLevel(int l) { 
        if(saved&&lastLevel==l){
            return true;
        }
        if(l <=lastLevel&&l>0){
            steps = 0;
            helpUsed = 0;
            level = l;
            towerA.init(level);
            towerB.init(0);
            towerC.init(0);
            return true;
        }
        return false;
    }

    public boolean win() {
        return false;
    }

    public int getSteps() {
        return steps;
    }

    public void help() {
        bot.help();
        action = MOVE_FAST;
        disc = bot.getDisc();
        discTo = bot.getTo();
        discFrom = bot.getFrom();
        getTower(discTo).add(getTower(discFrom).getLasDisck());
        steps++;
        helpUsed++;
    }

    public int getNbrHeUsed() {
        return helpUsed;
    }

    public int getNbrHelp() {
        return helpTotal;
    }
          
}

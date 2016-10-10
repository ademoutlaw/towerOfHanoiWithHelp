/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bot.Bot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Movement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.SwingUtilities;
import model.Command;
import view.GameFrame;
import view.TowerPanel;
import view.TowerPanel_old;

/**
 *
 * @author outlaw
 */
public class TowerOfHanoi implements MouseListener, ActionListener {
 
    private final char LEVEL_FRAME = 'l';
    private final char DIALOG_FRAME = 'd';
    private final char GAME_FRAME = 'g';
    
    private final Command command;
    private final GameFrame frame;
    
    private int level;
    //private Bot bot;
    private char currentFrame;
    
    
    public TowerOfHanoi() {
        command = new Command();
        frame = new GameFrame();
        //bot = new Bot();
        level = command.getLevel();
        currentFrame = LEVEL_FRAME;
        //level = 3;//----------------------------------------------------------------------------------
        frame.setListeners(this,this);
        frame.setGame(command.getTowerA(),command.getTowerB(),command.getTowerC(),level);
        if(command.isSaved()){
            frame.showDialog();
        }
        else{
            frame.showLevels();
        }
    }
      
     @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e.getActionCommand());
        boolean update=false;
        switch(e.getActionCommand()){
            case GameFrame.UNDO:
                update=command.undo();
                break;
            case GameFrame.REDO:
                update=command.redo();
                break;                
            case GameFrame.HELP:
                //System.out.println("help");
                //frame.repaint();
                help();
                //update=command.help();
                //help();
                //frame.updating();
                break;
        }
        if(update){
            //frame.updateGameFrame(getCommandtState(command.getMoveState()), command.getMoveFrom(), command.getMoveTo(), command.getMoveDisc(), command.getTowerA(), command.getTowerB(), command.getTowerC());
            //frame.setRedoEnabled(command.hadRedo());
            //frame.setUndoEnabled(command.hadUndo());
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { 
        if(SwingUtilities.isRightMouseButton(e)){
            return;
        }             
        switch(currentFrame){
            case LEVEL_FRAME:
                getLevel(e);             
                break;
            case DIALOG_FRAME:                
                System.out.println("dialog");                
                break;
            case GAME_FRAME:
                setMove(e);
                System.out.println("game");                
                break;
        }
        
        //System.out.println(l);
       // System.out.println(getTowerName(e));
        //setMove(e);
        /*System.out.println(getAction());
        System.out.println(getDisc());
        System.out.println(getDiscFrom());
        System.out.println(getDiscTo());*/
        //updateGameFrame();/*getCommandtState(command.getMoveState()), 
               // command.getMoveFrom(), 
                //command.getMoveTo(), 
                //command.getMoveDisc(), 
                //command.getTowerA(), 
               // command.getTowerB(), 
               // command.getTowerC());*/
        //frame.setRedo(command.hadRedo());
        //frame.setUndoEnabled(command.hadUndo());
    }

    private void getLevel(MouseEvent e) {
        int l = frame.getLevel(e);
        if(l>0){
            if(command.setLevel(l)){
                List<Integer> test = command.getTowerB();
               System.out.println("???????????????????###################################");
                for(int tower:test){
                System.out.print(tower+"   ");
            }
                System.out.println();
            System.out.println(" ******************************* ");
                frame.acceptLevel(command.getTowerA(),command.getTowerB(),command.getTowerC(),l);
                currentFrame = GAME_FRAME;
            }else{
                frame.errorLevel(l);
            }
        }
    }
    
    private char getTowerName(MouseEvent e){
        switch (frame.getTowerName(e)){
            case TowerPanel.TOWER_A:
                return Command.TOWER_A;
            case TowerPanel.TOWER_B:
                return Command.TOWER_B;
            case TowerPanel.TOWER_C:
                return Command.TOWER_C;
        }
        return ' ';        
    }
      
    private void setMove(MouseEvent e) {
        char t = getTowerName(e);
        if( t != GameFrame.GAP){
            command.setMove(t);
            List<Integer> test = command.getTowerB();
               System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                for(int tower:test){
                System.out.print(tower+"   ");
            }
                System.out.println();
            System.out.println(" ******************************* ");
            frame.setTowers(command.getTowerA(), test, command.getTowerC());
            frame.setMove(covertAction(), getDiscFrom(), getDiscTo(), getDisc());
            frame.setUndoEnabled(true);
            frame.setRedoEnabled(true);
            frame.win(command.win());
        }
        
    }

    private char covertAction() {
        switch(command.getAction()){
            case Command.DESELECT:
                return GameFrame.DESELECT;
            case Command.SELECT:
                return GameFrame.SELECT;
            case Command.MOVE:
                return GameFrame.MOVE;
        }
        return ' ';
    }

    private int getDisc() {
        return command.getDisc();
    }

    private char getDiscFrom() {        
        return convertTower(command.getDiscFrom());
    }

    private char getDiscTo() {
        return convertTower(command.getDiscTo());
    }

    private char convertTower(char tower) {
        switch(tower){
            case Command.TOWER_A:
                return GameFrame.TOWER_A;
            case Command.TOWER_B:
                return GameFrame.TOWER_B;
            case Command.TOWER_C:
                return GameFrame.TOWER_C;
        }
        return ' ';
    }
        
    private void help() {
        //frame.loadGame(2,false);
       // bot.setGrid(command.getTowerA(),command.getTowerB(),command.getTowerC());
       // command.setMove(bot.getDisc(),bot.getFrom(),bot.getTo());
        /*frame.updateGameFrame(getCommandtState(command.getMoveState()), 
                 command.getMoveFrom(), 
                 command.getMoveTo(), 
                 command.getMoveDisc(), 
                 command.getTowerA(), 
                 command.getTowerB(), 
                 command.getTowerC());*/
         //frame.setRedo(command.hadRedo());
         //frame.setUndoEnabled(command.hadUndo());
    }
        
    @Override
    public void mousePressed(MouseEvent e) {        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }    

    

    public static void main(String[] args) {
               
        TowerOfHanoi controller = new TowerOfHanoi();
        //2*U(n-1) +1.= (2^n)-1
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import model.Command;
import view.GameFrame;
import view.TowerPanel;

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
    
    //private Bot bot;
    private char currentFrame;
    
    
    public TowerOfHanoi() {
        command = new Command();
        frame = new GameFrame();
        //bot = new Bot();
        frame.setListeners(this,this);
        frame.setGame(command.getTowerA(),command.getTowerB(),command.getTowerC(), command.getLastLevel());
                
    }
      
     @Override
    public void actionPerformed(ActionEvent e) {
        boolean update=false;
        switch(e.getActionCommand()){
            case GameFrame.BACK:
                back();
                break;
            case GameFrame.UNDO:
                undo();
                break;
            case GameFrame.REDO:
                update=command.redo();
                break;                
            case GameFrame.HELP:
                //frame.repaint();
                help();
                //update=command.help();
                //help();
                //frame.updating();
                break;
            case GameFrame.START:
               startGame();
               break;
            case GameFrame.CONTINUE:
               continueGame();
               break;
            case GameFrame.NEW_GAME:
               newGame();
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
                setAction(e);
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
      
    private void setAction(MouseEvent e) {
        char t = getTowerName(e);
        if( t != GameFrame.GAP){
            command.setAction(t);            
            frame.setTowers(command.getTowerA(), command.getTowerB(), command.getTowerC());
            frame.setMove(convertAction(), getDiscFrom(), getDiscTo(), getDisc());
            frame.setUndoRedoEnabled(command.hadUndo(),command.hadRedo());
            frame.win(command.win());
        }
        
    }

    private char convertAction() {
        switch(command.getAction()){
            case Command.DESELECT:
                return GameFrame.DESELECT;
            case Command.SELECT:
                return GameFrame.SELECT;
            case Command.MOVE:
                return GameFrame.MOVE;
            case Command.MOVE_FAST:
                return GameFrame.MOVE_FAST;
            case Command.DESELECT_ERR:
                return GameFrame.DESELECT_ERR;
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
    
    private void back() {
        frame.back();
        currentFrame = DIALOG_FRAME;
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
    
    private void startGame() {
        frame.showLevels();
        currentFrame = LEVEL_FRAME;
    }

    private void continueGame() {
        frame.showGame();
        currentFrame = GAME_FRAME;
    }

    private void newGame() {
        frame.showLevels();
        currentFrame = LEVEL_FRAME;
    }

    private void undo() {
        if(command.undo()){
            frame.setTowers(command.getTowerA(), command.getTowerB(), command.getTowerC());
            frame.setMove(convertAction(), getDiscFrom(), getDiscTo(), getDisc());
            frame.setUndoRedoEnabled(command.hadUndo(), command.hadRedo());
        }
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

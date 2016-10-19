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
    

    private char currentFrame;
    
    
    public TowerOfHanoi() {
        command = new Command();
        frame = new GameFrame();
        frame.setListeners(this,this);
        frame.setGame(command.getTowerA(),command.getTowerB(),command.getTowerC(), command.getLastLevel());
                
    }
      
     @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case GameFrame.BACK:
                back();
                break;
            case GameFrame.UNDO:
                undo();
                break;
            case GameFrame.REDO:
                redo();
                break;                
            case GameFrame.HELP:
                help();
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
            case GameFrame.MENU:
                menu();
                break;
            case GameFrame.NEXT:
                next();
                break;
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
    }

    private void getLevel(MouseEvent e) {
        int l = frame.getLevel(e);
        if(l>0){
            if(command.setLevel(l)){
                frame.acceptLevel(command.getTowerA(),command.getTowerB(),command.getTowerC(),l);
                frame.setSteps(command.getSteps());
                frame.setHelp(command.getNbrHeUsed(), command.getNbrHelp());
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
            updateFrame();
            if(command.isWin()){
                frame.win();
                currentFrame = DIALOG_FRAME;
            }
        }
        
    }

    private void updateFrame() {
        frame.setTowers(command.getTowerA(), command.getTowerB(), command.getTowerC());
        frame.setMove(convertAction(), getDiscFrom(), getDiscTo(), command.getDisc());
        frame.setUndoRedoEnabled(command.hadUndo(),command.hadRedo());        
        frame.setSteps(command.getSteps());
        frame.setHelp(command.getNbrHeUsed(), command.getNbrHelp());
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
        command.help();        
        updateFrame();
    }
    
    private void startGame() {
        if(command.isSaved()){
            frame.showGame();
            frame.reload();
            currentFrame = GAME_FRAME;
        }else{
            frame.showLevels();
            currentFrame = LEVEL_FRAME;
        }
        
    }

    private void continueGame() {
        frame.showGame();
        currentFrame = GAME_FRAME;
    }

    private void newGame() {
        frame.showLevels();
        currentFrame = LEVEL_FRAME;
    }
    
    private void next() {
        if(command.next()){
           frame.next(command.getLastLevel()); 
           
        }else{
            frame.showLevels();
        }
        currentFrame = LEVEL_FRAME;
    }

    private void undo() {
        if(command.undo()){            
            updateFrame();
        }
    }
    
    private void redo() {
        if(command.redo()){
            updateFrame();
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

    private void menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    

    
}

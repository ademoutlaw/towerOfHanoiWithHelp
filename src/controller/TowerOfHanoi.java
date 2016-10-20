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
import model.GameCommand;
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
    
    private final GameCommand game;
    private final GameFrame frame;
    int speed;
    boolean musicMuted;
    boolean soundMuted;

    private char currentFrame;
    
    
    public TowerOfHanoi() {
        game = new GameCommand();//Loader.getGame();
        frame = new GameFrame();
        frame.setGame(game.getTowerA(),game.getTowerB(),game.getTowerC(), game.getLastLevel());
        updateFrame();  
        frame.setListeners(this,this);
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
            case GameFrame.SAVE:
                save();
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
                chooseLevel(e);             
                break;
            case DIALOG_FRAME:                
                System.out.println("dialog");                
                break;
            case GAME_FRAME:
                shifting(e);
                System.out.println("game");                
                break;
        }
    }

    private void chooseLevel(MouseEvent e) {
        int l = frame.getLevel(e);
        if(l>0){
            if(game.setLevel(l)){
                frame.acceptLevel(game.getTowerA(),game.getTowerB(),game.getTowerC(),l);
                frame.setSteps(game.getSteps());
                frame.setHelp(game.getNbrHeUsed(), game.getNbrHelp());
                currentFrame = GAME_FRAME;
            }else{
                frame.errorLevel(l);
            }
        }
    }
    
    private char getTowerName(MouseEvent e){
        switch (frame.getTowerName(e)){
            case TowerPanel.TOWER_A:
                return GameCommand.TOWER_A;
            case TowerPanel.TOWER_B:
                return GameCommand.TOWER_B;
            case TowerPanel.TOWER_C:
                return GameCommand.TOWER_C;
        }
        return ' ';        
    }
      
    private void shifting(MouseEvent e) {
        char t = getTowerName(e);
        if( t != GameFrame.GAP){
            game.setAction(t);            
            updateFrame();
            if(game.isWin()){
                frame.win();
                currentFrame = DIALOG_FRAME;
            }
        }
        
    }

    private void updateFrame() {
        frame.setTowers(game.getTowerA(), game.getTowerB(), game.getTowerC());
        frame.setMove(convertAction(), getDiscFrom(), getDiscTo(), game.getDisc());
        frame.setUndoRedoEnabled(game.hadUndo(),game.hadRedo());        
        frame.setSteps(game.getSteps());
        frame.setHelp(game.getNbrHeUsed(), game.getNbrHelp());
    }

    private char convertAction() {
        switch(game.getAction()){
            case GameCommand.DESELECT:
                return GameFrame.DESELECT;
            case GameCommand.SELECT:
                return GameFrame.SELECT;
            case GameCommand.MOVE:
                return GameFrame.MOVE;
            case GameCommand.MOVE_FAST:
                return GameFrame.MOVE_FAST;
            case GameCommand.DESELECT_ERR:
                return GameFrame.DESELECT_ERR;
        }
        return ' ';
    }

    private char getDiscFrom() {        
        return convertTower(game.getDiscFrom());
    }

    private char getDiscTo() {
        return convertTower(game.getDiscTo());
    }

    private char convertTower(char tower) {
        switch(tower){
            case GameCommand.TOWER_A:
                return GameFrame.TOWER_A;
            case GameCommand.TOWER_B:
                return GameFrame.TOWER_B;
            case GameCommand.TOWER_C:
                return GameFrame.TOWER_C;
        }
        return ' ';
    }
    
    private void back() {
        frame.back();
        currentFrame = DIALOG_FRAME;
    }
    
    private void help() {
        game.help();        
        updateFrame();
    }
    
    private void startGame() {
        if(game.isSaved()){
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
    
    private void menu() {
        frame.menu();
        currentFrame = DIALOG_FRAME;
    }

    private void newGame() {
        frame.showLevels();
        currentFrame = LEVEL_FRAME;
    }
    
    private void next() {
        if(game.next()){
           frame.next(game.getLastLevel());            
        }else{
            frame.showLevels();
        }
        currentFrame = LEVEL_FRAME;
    }

    private void undo() {
        if(game.undo()){            
            updateFrame();
        }
    }
    
    private void redo() {
        if(game.redo()){
            updateFrame();
        }
    }  
    
    
    
    private void save() {
        speed = frame.getSpeed();
        musicMuted = frame.isMusicMuted();
        soundMuted = frame.isSoundMuted();
        frame.setSpeed(speed);
        frame.showGame();
        currentFrame = GAME_FRAME;
        
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

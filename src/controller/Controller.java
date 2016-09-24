/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Movement;
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
public class Controller implements MouseListener ,ActionListener {
 
    private final Command command;
    private GameFrame frame;

    public Controller(Command temp, GameFrame frame) {
        this.command = temp;

        this.frame = frame;
        frame.addListener(this,this);
        frame.loadGame(temp.getSize());
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) { 
        if(SwingUtilities.isRightMouseButton(e)){
            System.out.println("right");
            
            return;
        }
        command.setMove(frame.getPanelName(e));
        frame.updateGameFrame(getCommandtState(command.getMoveState()), 
                command.getMoveFrom(), 
                command.getMoveTo(), 
                command.getMoveDisc(), 
                command.getTowerA(), 
                command.getTowerB(), 
                command.getTowerC());
        //frame.setRedo(command.hadRedo());
        frame.setUndoEnabled(command.hadUndo());
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

    private int getCommandtState(int moveState) {
        switch (moveState){
            case Movement.MOVE:
                return TowerPanel.MOVE;
            case Movement.DISELECT:
                return TowerPanel.DISELECT;
            case Movement.SELECT:
                return TowerPanel.SELECT;
        }
        return TowerPanel.DISELECT;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        boolean update=false;
        switch(e.getActionCommand()){
            case GameFrame.UNDO:
                update=command.undo();
                break;
            case GameFrame.REDO:
                update=command.redo();
                break;                
            case GameFrame.HELP:
                System.out.println("help");
                //update=command.help();
                break;
        }
        if(update){
            frame.updateGameFrame(getCommandtState(command.getMoveState()), 
                    command.getMoveFrom(), 
                    command.getMoveTo(), 
                    command.getMoveDisc(), 
                    command.getTowerA(), 
                    command.getTowerB(), 
                    command.getTowerC());
            frame.setRedoEnabled(command.hadRedo());
            frame.setUndoEnabled(command.hadUndo());
        }
    }
        
}

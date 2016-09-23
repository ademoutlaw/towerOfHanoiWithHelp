/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Movement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Command;
import view.GameFrame;
import view.TowerPanel;

/**
 *
 * @author outlaw
 */
public class Controller implements MouseListener  {
 
    private final Command temp;
    private GameFrame frame;

    public Controller(Command temp, GameFrame frame) {
        this.temp = temp;

        this.frame = frame;
        frame.addListener(this);
        frame.loadGame(temp.getSize());
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {        
        temp.setMove(frame.getPanelName(e));
        frame.updateGameFrame(getMovementState(temp.getMoveState()), 
                temp.getMoveFrom(), 
                temp.getMoveTo(), 
                temp.getMoveDisc(), 
                temp.getTowerA(), 
                temp.getTowerB(), 
                temp.getTowerC());
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

    private int getMovementState(int moveState) {
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
        
}

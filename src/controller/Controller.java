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
import view.StackPanel;

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
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
        
        temp.setMove(frame.getPanelName(e));
        
        frame.updateGameFrame(getMoveState(temp.getMoveState()), 
                temp.getMoveFrom(), 
                temp.getMoveTo(), 
                temp.getMoveDisc(), 
                temp.getStackA(), 
                temp.getStackB(), 
                temp.getStackC());
        
        //stackPanelA.updateStackPanel(stackA.getDiscksSizes(),getStatus(move),move.getFrom(),move.getTo(),move.getDisck());
        //stackPanelB.updateStackPanel(stackB.getDiscksSizes(),getStatus(move),move.getFrom(),move.getTo(),move.getDisck());
        //stackPanelC.updateStackPanel(stackC.getDiscksSizes(),getStatus(move),move.getFrom(),move.getTo(),move.getDisck());
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

    

    private int getMoveState(int moveState) {
        switch (moveState){
            case Movement.MOVE:
                return StackPanel.MOVE;
            case Movement.DISELECT:
                return StackPanel.DISELECT;
            case Movement.SELECT:
                return StackPanel.SELECT;
        }
        return StackPanel.DISELECT;
    }

    

    
    
}

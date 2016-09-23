package controller;

import java.awt.Color;
import model.Command;
import view.GameFrame;
import view.TowerPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author outlaw
 */
public class TowerOfHanoi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        new Controller(new Command(9), new GameFrame());
        //MyMouseListener listener = new MyMouseListener(stackA,stackB,stackC,9);
        

    }
    
}

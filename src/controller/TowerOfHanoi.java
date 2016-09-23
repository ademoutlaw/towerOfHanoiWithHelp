<<<<<<< HEAD
package controller;

import java.awt.Color;
import model.Temp;
import view.GameFrame;
import view.StackPanel;

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
        
        
        new Controller(new Temp(9), new GameFrame());
        //MyMouseListener listener = new MyMouseListener(stackA,stackB,stackC,9);
        

    }
    
}
=======
package controller;

import view.GameFrame;

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
        new GameFrame();
    }
    
}
>>>>>>> 345a305dc6e6f9fa62c849f2c87d5d0d823dbfcf

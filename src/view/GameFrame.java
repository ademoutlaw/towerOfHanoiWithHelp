<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public class GameFrame extends JFrame {
    private final StackPanel stackA;
    private final StackPanel stackB;
    private final StackPanel stackC;

   

    public GameFrame() {
        
        super("tower of hanoi!");
        
        stackA = new StackPanel('A');
        stackB = new StackPanel('B');
        stackC = new StackPanel('C');
        
        
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.yellow);
        setSize(900, 400);
        jPanel.add(stackA);
        jPanel.add(stackB);
        jPanel.add(stackC);
        
        
        add(jPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void loadGame(int size){
        stackA.loadStackPanel(size);
        
    }
    public char getPanelName(MouseEvent e){
        return ((StackPanel)e.getSource()).getPanelName();
        
    }
    public void updateGameFrame(int move,char from,char to,int disc,
            ArrayList<Integer> stkA,ArrayList<Integer> stkB,ArrayList<Integer> stkC ){
        stackA.updateStackPanel(stkA, move, from, to, disc);
        stackB.updateStackPanel(stkB, move, from, to, disc);
        stackC.updateStackPanel(stkC, move, from, to, disc);
        
    }

    public void addListener(MouseListener aThis) {
        stackA.addMouseListener(aThis);
        stackB.addMouseListener(aThis);
        stackC.addMouseListener(aThis);
    }
    
      
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public class GameFrame {
    private final JPanel stackA;
    private final JPanel stackB;
    private final JPanel stackC;
    
    public GameFrame() {
        JFrame jFrame = new JFrame("tower of hanoi!");
        stackA = new StackPanel();
        stackB = new StackPanel(Color.red);
        stackC = new StackPanel(Color.green);
        
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.yellow);
        jFrame.setSize(900, 400);
        jPanel.add(stackC);
        jPanel.add(stackB);
        jPanel.add(stackA);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
}
>>>>>>> 345a305dc6e6f9fa62c849f2c87d5d0d823dbfcf

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

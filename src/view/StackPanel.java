/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public class StackPanel extends JPanel{
    private ArrayList stack;
    private final int Height=300;
    private int base;
    private int posX;
    
    public StackPanel() {
        this(Color.orange);
        
    }

    public StackPanel(Color red) {
        stack = new ArrayList();
        stack.add(6);
        stack.add(5);
        stack.add(4);
        stack.add(3);
        stack.add(2);
        stack.add(1);
        
        base=HEIGHT;
        
        //for test
        posX=0;
        setBackground(red);
        setPreferredSize(new Dimension(220,350));
        this.addAc
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        paintStack(g);
        
    }

    private void paintStack(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(110, 10, 5, 300);
        g.fillRect(10, 310, 200, 30);
        g.setColor(Color.blue);
        //g.fillRect(posX, base, 100, 50);
        System.out.println("========= "+base);
        base=290;
        posX=22;
        for(Object disk:stack){
            System.out.println(disk);
            g.fillRoundRect(posX, base, (int)disk*30, 20,10,10);
            
            posX += 15;
            base -= 21;
        }
    }
    
    
    
}

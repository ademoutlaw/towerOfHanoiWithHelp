/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author outlaw
 */
public class GamePanel extends JPanel {
    
    private final Image imgBackground;
    private final TowerPanel towers;
   
   
    private final  ButtonsPanel buttons;
    

    public GamePanel() {
        
        URL url = getClass().getResource("/view/images/background.jpg");
        ImageIcon icon = new ImageIcon(url);
        imgBackground = icon.getImage();
        towers = new TowerPanel();
        buttons = new ButtonsPanel();
        
        
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(0, 0, 50, 15));
        centerPanel.setOpaque(false);
        centerPanel.add(towers);
        
        setLayout(new BorderLayout());
        add(buttons,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.SOUTH);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);           
        g.drawImage(imgBackground, 0, 0,getWidth(), getHeight(), null);
        revalidate();       
    }    
    
    public void setListeners(MouseListener ml,ActionListener al){
        towers.addMouseListener(ml);
        buttons.addActionListener(al);       
    }

    protected void loadTower() {
        towers.loadTower();
    }

    protected char getTowerName(MouseEvent e) {
        return towers.getTowerName(e);
    }

    protected void setTowers(List<Integer> stkA, List<Integer> stkB, List<Integer> stkC) {        
        towers.setTowers(stkA, stkB, stkC);
    }

    protected void setMove(char move, char from, char to, int disc) {
        towers.setAction(move, from, to, disc);
    }

    protected void setRedoEnabled(boolean hadRedo) {
        buttons.setRedoEnabled(hadRedo);
    }

    protected void setUndoEnabled(boolean hadUndo) {
        buttons.setUndoEnabled(hadUndo);
    }

    protected void setSteps(int steps) {
        if(steps>1){
            buttons.setSteps(steps +" steps");
        }else{
            buttons.setSteps(steps +" step");
        }
    }

    protected void setHelp(int nbrHelpLeft, int nbrHelp) {
        buttons.setHelp(nbrHelpLeft+"/"+nbrHelp);
    }

    void setSpeed(int speed) {
        towers.setSpeed(speed);
    }
    
    
    

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
   
    private final JButton undoButton;
    private final JButton redoButton;
    private final JButton helpButton;
    private final JButton menuButton;
    private final ButtonImage reloadButton;
    private final JLabel stepsLabel;
    private final JLabel helpLabel;
    

    public GamePanel() {
        
        URL url = getClass().getResource("/view/images/background.jpg");
        ImageIcon icon = new ImageIcon(url);
        imgBackground = icon.getImage();
        towers = new TowerPanel();
                 
        undoButton = new ButtonImage("undo.png");
        redoButton = new ButtonImage("redo.png");
        helpButton = new ButtonImage("help.png");
        menuButton = new ButtonImage("menu.png",32);
        reloadButton = new ButtonImage("reload.png");
        
        undoButton.setActionCommand(GameFrame.UNDO);
        redoButton.setActionCommand(GameFrame.REDO);
        helpButton.setActionCommand(GameFrame.HELP); 
        
        undoButton.setToolTipText("undo the move");
        redoButton.setToolTipText("redo the move");
        helpButton.setToolTipText("get the best next move");
        
        helpLabel = new JLabel("1/1");
        stepsLabel = new JLabel("0 step");        
        helpLabel.setForeground(Color.green);
        stepsLabel.setForeground(Color.green);        
        helpLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        stepsLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0,0,0,200));
        buttonPanel.setLayout(new GridLayout(1,7));
        buttonPanel.add(reloadButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        buttonPanel.add(stepsLabel);
        buttonPanel.add(helpButton);
        buttonPanel.add(helpLabel);
        buttonPanel.add(menuButton);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
        centerPanel.setOpaque(false);
        centerPanel.add(towers);
        
        setLayout(new BorderLayout());
        add(buttonPanel,BorderLayout.NORTH);
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
        undoButton.addActionListener(al);
        redoButton.addActionListener(al);
        helpButton.addActionListener(al);        
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
        redoButton.setEnabled(hadRedo);
    }

    protected void setUndoEnabled(boolean hadUndo) {
        undoButton.setEnabled(hadUndo);
    }

    

    
}

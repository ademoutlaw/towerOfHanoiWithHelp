/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public class GameFrame extends JFrame {
    
    public final static String UNDO ="undo";
    public final static String REDO ="redo";
    public final static String HELP ="help";
    
    private final TowerPanel stackA;
    private final TowerPanel stackB;
    private final TowerPanel stackC;
    private final JButton undoButton;
    private final JButton redoButton;
    private final JButton helpButton;
    private  BufferedImage imgBackground;
   
    public GameFrame() {
        
        super("tower of hanoi!");
        
        stackA = new TowerPanel('A');
        stackB = new TowerPanel('B');
        stackC = new TowerPanel('C');
        
        URL urlBackground = getClass().getResource("/view/images/background.jpg");
        URL urlUndo = getClass().getResource("/view/images/undo.png");
        URL urlUndoHover = getClass().getResource("/view/images/undo_hover.png");
        URL urlUndoClick = getClass().getResource("/view/images/undo_click.png");
        URL urlRedo = getClass().getResource("/view/images/redo.png");
        URL urlRedoHover = getClass().getResource("/view/images/redo_hover.png");
        URL urlRedoClick = getClass().getResource("/view/images/redo_click.png");
        URL urlUndoDisable = getClass().getResource("/view/images/undo_unable.png");
        URL urlRedoDisable = getClass().getResource("/view/images/redo_unable.png");
        
        if (urlBackground != null) {
            try {
                imgBackground =ImageIO.read(urlBackground);
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(imgBackground);
        System.out.println(urlUndoClick);
        JPanel jPanel= new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(imgBackground, 0, 0, this);
            }  
        };        
        jPanel.setBackground(Color.yellow);
        setSize(900, 400);
        
        undoButton = new JButton();
        redoButton = new JButton();
        helpButton = new JButton();
        
        
        undoButton.setPreferredSize(new Dimension(64,64));
        redoButton.setPreferredSize(new Dimension(64,64));
        
        undoButton.setOpaque(false);
        redoButton.setOpaque(false);
        
        undoButton.setContentAreaFilled(false);
        redoButton.setContentAreaFilled(false);
        
        undoButton.setBorderPainted(false);        
        redoButton.setBorderPainted(false);
        
        //undoButton.setBorder(null);
        //redoButton.setBorder(null);
        
        undoButton.setIcon(new ImageIcon(urlUndo));
        redoButton.setIcon(new ImageIcon(urlRedo));
        
        undoButton.setPressedIcon(new ImageIcon(urlUndoClick));
        redoButton.setPressedIcon(new ImageIcon(urlRedoClick));
        
        undoButton.setRolloverIcon(new ImageIcon(urlUndoHover));
        redoButton.setRolloverIcon(new ImageIcon(urlRedoHover));
        
        undoButton.setDisabledIcon(new ImageIcon(urlUndoDisable));
        redoButton.setDisabledIcon(new ImageIcon(urlRedoDisable));
        
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);
        
        undoButton.setActionCommand(GameFrame.UNDO);
        redoButton.setActionCommand(GameFrame.REDO);
        helpButton.setActionCommand(GameFrame.HELP);        
        jPanel.add(undoButton);
        jPanel.add(redoButton);
        jPanel.add(stackA);
        jPanel.add(stackB);
        jPanel.add(stackC);
        jPanel.add(helpButton);
        
        add(jPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void loadGame(int size){
        stackA.loadStackPanel(size);
        
    }
    
    public char getPanelName(MouseEvent e){
        return ((TowerPanel)e.getSource()).getPanelName();        
    }
    
    public void updateGameFrame(int move,char from,char to,int disc,
            ArrayList<Integer> stkA,ArrayList<Integer> stkB,ArrayList<Integer> stkC ){
        stackA.updateStackPanel(stkA, move, from, to, disc);
        stackB.updateStackPanel(stkB, move, from, to, disc);
        stackC.updateStackPanel(stkC, move, from, to, disc);        
    }

    public void addListener(MouseListener mouse, ActionListener action) {
        stackA.addMouseListener(mouse);
        stackB.addMouseListener(mouse);
        stackC.addMouseListener(mouse);
        undoButton.addActionListener(action);
        redoButton.addActionListener(action);
        helpButton.addActionListener(action);
    }

    public void setUndoEnabled(boolean hadUndo) {
        undoButton.setEnabled(hadUndo);
    }

    public void setRedoEnabled(boolean hadRedo) {
        redoButton.setEnabled(hadRedo);
    }    
}

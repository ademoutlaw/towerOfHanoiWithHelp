/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private final TowerPanel stackA;
    private final TowerPanel stackB;
    private final TowerPanel stackC;
    private final JButton undoButton;
    private final JButton redoButton;
    private final JButton helpButton;
    File file;
    private  BufferedImage imgBackground;
    private BufferedImage imgUndoRedo;
   

    public GameFrame() {
        
        super("tower of hanoi!");
        
        stackA = new TowerPanel('A');
        stackB = new TowerPanel('B');
        stackC = new TowerPanel('C');
        //Images image = new Images();
        java.net.URL imgURLBackground = getClass().getResource("/view/images/background.jpg");
        java.net.URL imgURLUndo = getClass().getResource("/view/images/undo.png");
        java.net.URL imgURLUndoHover = getClass().getResource("/view/images/undo_hover.png");
        java.net.URL imgURLUndoClick = getClass().getResource("/view/images/undo_click.png");
        java.net.URL imgURLRedo = getClass().getResource("/view/images/redo.png");
        java.net.URL imgURLRedoHover = getClass().getResource("/view/images/redo_hover.png");
        java.net.URL imgURLRedoClick = getClass().getResource("/view/images/redo_click.png");
        if (imgURLBackground != null) {
            try {
                imgBackground =ImageIO.read(imgURLBackground);
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(imgBackground);
        System.out.println(imgUndoRedo);
        JPanel jPanel= new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                //img = null;
                g.drawImage(imgBackground, 0, 0, this);
               /* try {
                    //BufferedImage img =
                    //ImageIO.read(null);
                    
                    BufferedImage img = ImageIO.read(imgURL);
                } catch (IOException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
                
            }  
        };        
        jPanel.setBackground(Color.yellow);
        setSize(900, 400);
        
        undoButton = new JButton();
        redoButton = new JButton();
        helpButton = new JButton("help");
        
        
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
        
        undoButton.setIcon(new ImageIcon(imgURLUndo));
        redoButton.setIcon(new ImageIcon(imgURLRedo));
        
        undoButton.setPressedIcon(new ImageIcon(imgURLUndoClick));
        redoButton.setPressedIcon(new ImageIcon(imgURLRedoClick));
        
        undoButton.setRolloverIcon(new ImageIcon(imgURLUndoHover));
        redoButton.setRolloverIcon(new ImageIcon(imgURLRedoHover));
        
        //redoButton.setMargin(new Insets(0, 0, 0, 0));
        //redoButton.setIconTextGap(0);
        //redoButton.setBorderPainted(false);
        //redoButton.setBorder(null);
        //redoButton.setText(null);  
        
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

    public void addListener(MouseListener listener) {
        stackA.addMouseListener(listener);
        stackB.addMouseListener(listener);
        stackC.addMouseListener(listener);
    }
    
      
}

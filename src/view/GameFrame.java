/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
        this.setForeground(Color.red);
        
        
        setBar();
        stackA = new TowerPanel('A');
        stackB = new TowerPanel('B');
        stackC = new TowerPanel('C');
        
        URL urlBackground = getClass().getResource("/view/images/background.jpg");
        
        URL urlUndo = getClass().getResource("/view/images/undo.png");
        URL urlUndoHover = getClass().getResource("/view/images/undo_hover.png");
        URL urlUndoClick = getClass().getResource("/view/images/undo_click.png");
        URL urlUndoDisable = getClass().getResource("/view/images/undo_unable.png");
        
        URL urlRedo = getClass().getResource("/view/images/redo.png");
        URL urlRedoHover = getClass().getResource("/view/images/redo_hover.png");
        URL urlRedoClick = getClass().getResource("/view/images/redo_click.png");        
        URL urlRedoDisable = getClass().getResource("/view/images/redo_unable.png");
        
        URL urlNext = getClass().getResource("/view/images/next.png");
        URL urlNextHover = getClass().getResource("/view/images/next_hover.png");
        URL urlNextClick = getClass().getResource("/view/images/next_click.png");
        
        if (urlBackground != null) {
            try {
                imgBackground =ImageIO.read(urlBackground);
                setIconImage(imgBackground);
            } catch (IOException ex) {
                
            }
        }
        System.out.println(imgBackground);
        System.out.println(urlUndoClick);
        getRootPane().setBorder(BorderFactory.createMatteBorder(0, 4, 4, 4,Color.BLACK));
        JPanel jPanel= new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                int x = (this.getWidth()-imgBackground.getWidth())/2;
                int y = (this.getHeight()-imgBackground.getHeight());
                g.drawImage(imgBackground, x, y, null);
            }  
        };        

        undoButton = new JButton();
        redoButton = new JButton();
        helpButton = new JButton();
        
        
        undoButton.setPreferredSize(new Dimension(64,64));
        redoButton.setPreferredSize(new Dimension(64,64));
        helpButton.setPreferredSize(new Dimension(128,128));
        
        undoButton.setOpaque(false);
        redoButton.setOpaque(false);
        helpButton.setOpaque(false);
        
        undoButton.setContentAreaFilled(false);
        redoButton.setContentAreaFilled(false);
        helpButton.setContentAreaFilled(false);
        
        undoButton.setBorderPainted(false);        
        redoButton.setBorderPainted(false);
        helpButton.setBorderPainted(false);
                
        undoButton.setIcon(new ImageIcon(urlUndo));
        redoButton.setIcon(new ImageIcon(urlRedo));
        helpButton.setIcon(new ImageIcon(urlNext));
        
        undoButton.setPressedIcon(new ImageIcon(urlUndoClick));
        redoButton.setPressedIcon(new ImageIcon(urlRedoClick));
        helpButton.setPressedIcon(new ImageIcon(urlNextClick));
        
        undoButton.setRolloverIcon(new ImageIcon(urlUndoHover));
        redoButton.setRolloverIcon(new ImageIcon(urlRedoHover));
        helpButton.setRolloverIcon(new ImageIcon(urlNextHover));        
        
        undoButton.setDisabledIcon(new ImageIcon(urlUndoDisable));
        redoButton.setDisabledIcon(new ImageIcon(urlRedoDisable));
        
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);
        
        undoButton.setActionCommand(GameFrame.UNDO);
        redoButton.setActionCommand(GameFrame.REDO);
        helpButton.setActionCommand(GameFrame.HELP); 
        
        undoButton.setToolTipText("undo the move");
        redoButton.setToolTipText("redo the move");
        helpButton.setToolTipText("get the best next move");
        jPanel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(undoButton,BorderLayout.WEST);
        buttonPanel.add(redoButton,BorderLayout.WEST);
        buttonPanel.add(helpButton,BorderLayout.EAST);
        jPanel.add(buttonPanel, BorderLayout.NORTH);
        JPanel towerPanel = new JPanel();
        towerPanel.add(stackA);
        towerPanel.add(stackB);
        towerPanel.add(stackC);
        towerPanel.setOpaque(false);
        jPanel.add(towerPanel, BorderLayout.SOUTH);
        
        
        add(jPanel,BorderLayout.CENTER);
        
        setVisible(true);
        this.setMinimumSize(new Dimension(700,550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
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

    private void setBar() {
        //TODO: ???
        JMenuBar bar = new JMenuBar();
        bar.setForeground(Color.red);
        bar.setOpaque(false);
        JMenu fileMenu = new JMenu("menu");
        JMenu helpMenu = new JMenu("help");
        
        JMenuItem undoItem = new JMenuItem("undo");
        JMenuItem redoItem = new JMenuItem("redo");
        JMenuItem exitItem = new JMenuItem("exit");
        
        JMenu test = new JMenu("test");
        
        JMenuItem test1 = new JMenuItem("test1");
        JMenuItem test2 = new JMenuItem("test2");
        JMenuItem test3 = new JMenuItem("test3");
         test.add(test1);
         test.add(test2);
         test.add(test3);
        
        
        //undoItem.add(test);
        fileMenu.add(test);
        fileMenu.add(undoItem);
        fileMenu.add(redoItem);
        fileMenu.add(exitItem);
        
        bar.add(fileMenu);
        bar.add(helpMenu);
        setJMenuBar(bar);
        
        
    }
    
}

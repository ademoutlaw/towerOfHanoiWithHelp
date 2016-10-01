/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

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
    
    private BufferedImage imgBackground;
    private URL urlUndo;
    private URL urlUndoHover;
    private URL urlUndoClick;
    private URL urlUndoDisable;
    private URL urlRedo;
    private URL urlRedoHover;
    private URL urlNextClick;
    private URL urlNext;
    private URL urlRedoDisable;
    private URL urlRedoClick;
    private URL urlNextHover;
    private JDialog d;
    private URL urlBackground;
   
    public GameFrame() {
        
        super("tower of hanoi!");
        
        //this.setUndecorated(true);
        
        /* try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        setBar();
        stackA = new TowerPanel('A');
        stackB = new TowerPanel('B');
        stackC = new TowerPanel('C');
        this.setLocation(50, 50);
       
        getRootPane().setBorder(BorderFactory.createMatteBorder(0, 4, 4, 4,Color.white));
        GamePanel gamePanel;
        gamePanel = new GamePanel()
        /*{
            
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                int x = (this.getWidth()-imgBackground.getWidth())/2;
                int y = (this.getHeight()-imgBackground.getHeight());
                URL test = null;
                Image image = new ImageIcon(urlBackground).getImage();
                //System.out.println("test");
                g.drawImage(image, 0, 0, null);
            }
        }*/;        
        
        undoButton = new JButton();
        redoButton = new JButton();
        helpButton = new JButton();
        
        loadImages();
        
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
        
        helpButton.setBorder(new Border() {

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            }

            @Override
            public Insets getBorderInsets(Component c) {
                
                return new Insets(0,0,0,0);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });
             
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
        gamePanel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(undoButton,BorderLayout.WEST);
        buttonPanel.add(redoButton,BorderLayout.WEST);
        buttonPanel.add(helpButton,BorderLayout.EAST);
        gamePanel.add(buttonPanel, BorderLayout.NORTH);
        JPanel towerPanel = new JPanel();
        towerPanel.add(stackA);
        towerPanel.add(stackB);
        towerPanel.add(stackC);
        towerPanel.setOpaque(false);
        gamePanel.add(towerPanel, BorderLayout.SOUTH);
        
        
        add(gamePanel,BorderLayout.CENTER);
        
        setVisible(true);
        this.setMinimumSize(new Dimension(700,550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
    
    public void loadGame(int size){
        stackA.loadStackPanel(size);
        
    }
    
    private void loadImages(){
        urlBackground = getClass().getResource("/view/images/background.gif");
        if (urlBackground != null) {
            try {
                imgBackground =ImageIO.read(urlBackground);
                setIconImage(imgBackground);
            } catch (IOException ex) {
                
            }
        }
        urlUndo = getClass().getResource("/view/images/undo.png");
        urlUndoHover = getClass().getResource("/view/images/undo_hover.png");
        urlUndoClick = getClass().getResource("/view/images/undo_click.png");
        urlUndoDisable = getClass().getResource("/view/images/undo_unable.png");
        
        urlRedo = getClass().getResource("/view/images/redo.png");
        urlRedoHover = getClass().getResource("/view/images/redo_hover.png");
        urlRedoClick = getClass().getResource("/view/images/redo_click.png");        
        urlRedoDisable = getClass().getResource("/view/images/redo_unable.png");
        
        urlNext = getClass().getResource("/view/images/next.png");
        urlNextHover = getClass().getResource("/view/images/next_hover.png");
        urlNextClick = getClass().getResource("/view/images/next_click.png");
    }
    
    public char getPanelName(MouseEvent e){
        return ((TowerPanel)e.getSource()).getPanelName();        
    }
    
    public void updateGameFrame(int move,char from,char to,int disc,
            ArrayList<Integer> stkA,ArrayList<Integer> stkB,ArrayList<Integer> stkC ){
        stackA.updateStackPanel(stkA, move, from, to, disc);
        stackB.updateStackPanel(stkB, move, from, to, disc);
        stackC.updateStackPanel(stkC, move, from, to, disc);
        /* if(d==null){
            d = new JDialog();
            //d.setUndecorated(true);
            d.setVisible(true);
            d.setMinimumSize(new Dimension(120,400));
            
            d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }
        System.out.println(d);*/
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
        JMenuBar bar = new JMenuBar(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth() , getHeight() );
            }
        };
        bar.setForeground(Color.red);
        
        bar.setOpaque(false);
        JMenu fileMenu = new JMenu("menu");
        JMenu helpMenu = new JMenu("help");

        JMenuItem undoItem = new JMenuItem("undo");
        JMenuItem redoItem = new JMenuItem("redo");
        JMenuItem exitItem = new JMenuItem("exit");

        undoItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        
        undoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("heyyy");
            }
        });
        
        redoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("heyyy FFF");
            }
        });
        
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
        
        //UIManager.put("MenuBar.background", Color.RED);
        //UIManager.put("Menu.background", Color.GREEN);
        // UIManager.put("MenuItem.background", Color.MAGENTA);     
    }
    
}

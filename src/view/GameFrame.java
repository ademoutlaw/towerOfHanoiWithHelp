/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author outlaw
 */
public class GameFrame extends JFrame implements ActionListener{
    
    public final static String UNDO = "undo";
    public final static String REDO = "redo";
    public final static String HELP = "help";
    public final static String BACK = "back";
    public final static String START = "start";
    public final static String NEW_GAME = "new game";
    public final static String NEXT = "next";
    public final static String CONTINUE = "continue";
    public final static String MENU = "menu";
    public final static String SPEED = "speed";
    public final static String SAVE = "save";
    public final static String MUSIC = "mute music";
    public final static String SOUND = "mute sound";
    
    public static final char MOVE = 'm';
    public static final char MOVE_FAST = 'f';
    public static final char SELECT = 's';
    public static final char DESELECT = 'd';
    public static final char DESELECT_ERR = 'e';
    
    public static final char TOWER_A = 'a';
    public static final char TOWER_B = 'b';
    public static final char TOWER_C = 'c';
    public static final char GAP = ' ';
            
    private final Timer timer;
    private final GamePanel gamePanel;
    private final LevelsPanel levelsPanel;
    
    private boolean isNewLevel;
    private int frameToDraw;
    private int frameDrawed;
    private boolean wait;
    private final ConfirmPanel confirmPanel;
    private boolean isNextLevel;
    private final Sound dialogMusic;
    private final Sound gameMusic;
     
    public GameFrame() {     

        gamePanel = new GamePanel();
        levelsPanel = new LevelsPanel();
        frameToDraw = 20;
        levelsPanel.setFps(20);
        confirmPanel = new ConfirmPanel();
        
        timer = new Timer(30, this);
        timer.start();
        
        gameMusic = new Sound("play",true);
        dialogMusic = new Sound("pause",true);
        
        setLayout(null);
        add(confirmPanel);
        add(levelsPanel);
        add(gamePanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(710,550));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);        
    }
     
    public void setGame(List<Integer> stkA, List<Integer> stkB, List<Integer> stkC, int level){
        gamePanel.setTowers(stkA, stkB, stkC);
        gamePanel.setUndoEnabled(false);
        gamePanel.setRedoEnabled(false);
        levelsPanel.setLevel(level); 
        confirmPanel.setConfirmType(ConfirmPanel.START);        
        showDialog();
    }
    
    public char getTowerName(MouseEvent e){
        return gamePanel.getTowerName(e);        
    }
        
    public void setListeners(MouseListener mouse, ActionListener action) {
        gamePanel.setListeners(mouse, action);
        levelsPanel.addMouseListener(mouse);        
        confirmPanel.addListener(action);
    }
          
    public void back() {
        confirmPanel.setConfirmType(ConfirmPanel.CONTINUE);
        showDialog();
    }

    public void setUndoRedoEnabled(boolean hadUndo, boolean hadRedo) {
        gamePanel.setRedoEnabled(hadRedo);
        gamePanel.setUndoEnabled(hadUndo);
    }
    
    public int getLevel(MouseEvent e) {
        return levelsPanel.getLevel(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); 
        gamePanel.setBounds(0, 0, this.getWidth(), this.getHeight());
        levelsPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
        confirmPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
        if(wait){
            if(frameDrawed>=frameToDraw){
                if(isNewLevel){
                    showGame();
                    gamePanel.loadTower();
                    isNewLevel = false;
                    wait = false;
                }else if(isNextLevel){
                    confirmPanel.setConfirmType(ConfirmPanel.NEXT);
                    showDialog();
                    isNextLevel = false;
                    wait = false;
                }
            }else{
                frameDrawed++;
            }
        }
        
    }
    
    public void showDialog(){
        levelsPanel.setVisible(false);
        gamePanel.setVisible(true);
        confirmPanel.setVisible(true); 
        dialogMusic.loop();
        gameMusic.stop();
    }
    
    public void showGame(){
        levelsPanel.setVisible(false);
        gamePanel.setVisible(true);
        confirmPanel.setVisible(false);
        dialogMusic.stop();
        gameMusic.loop();
    }
    
    public void showLevels(){
        levelsPanel.setVisible(true);
        gamePanel.setVisible(false);
        confirmPanel.setVisible(false);
    }

    public void errorLevel(int l) {
        levelsPanel.errorLevel(l);        
    }

    public void acceptLevel(List<Integer> stkA, List<Integer> stkB, List<Integer> stkC, int l) {
        levelsPanel.acceptLevel(l);
        gamePanel.setTowers(stkA, stkB, stkC);
        gamePanel.setUndoEnabled(false);
        gamePanel.setRedoEnabled(false);
        isNewLevel = true;
        frameDrawed = 0;
        frameToDraw = 20;
        wait = true;
    }

    public void setHoveredLevel(MouseEvent e) {        
        levelsPanel.getLevel(e);
    }
    
    public void setFrameRate(int frameRate) {
        this.frameToDraw = frameRate;
        levelsPanel.setFps(frameRate);
    }

    public void setMove(char action, char discFrom, char discTo, int disc) {
        gamePanel.setMove(action, discFrom, discTo, disc);
    }

    public void setTowers(List<Integer> towerA, List<Integer> towerB, List<Integer> towerC) {
        gamePanel.setTowers(towerA, towerB, towerC);
    }
    
    public void setSteps(int steps) {
        gamePanel.setSteps(steps);
    }
    
    public void win() {
        wait = true;   
        frameDrawed = 0;
        isNextLevel = true;
        frameToDraw = 80 + 10;
    }
    
    public void setHelp(int nbrHelpLeft, int nbrHelp) {
        gamePanel.setHelp(nbrHelpLeft, nbrHelp);
    }

    public void next(int lastLevel) {
        levelsPanel.setLevel(lastLevel);
        showLevels();
    }
    
    public void reload() {
        gamePanel.loadTower();
    }

    public int getSpeed() {
        return confirmPanel.getSpeed();
    }

    public void setSpeed(int speed) {
        gamePanel.setSpeed(speed);
    }

    public void menu() {
        confirmPanel.setConfirmType(ConfirmPanel.MENU);
        showDialog();
    }

    public boolean isSoundMuted() {
        return confirmPanel.isSoundMuted();
    }

    public boolean isMusicMuted() {
        return confirmPanel.isMusicMuted();
    }
    
}
/*private void setBar() {
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
    }*/
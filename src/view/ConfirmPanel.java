/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public  class ConfirmPanel extends JPanel implements MouseListener, MouseMotionListener{
    
    public static final char START = 's';
    public static final char CONTINUE = 'c';
    public static final char NEXT = 'n';

    /*private final JPanel panel;
    private final JButton button1;
    private final JButton button2;
    private final JLabel message;*/
    private char type;
    private int h;
    private int w;
    //private final Color black;
    private final Section menu;
    private final Section startGame;
    private final Section newGame;
    private final Section exit;
    private final Section nextLevel;
    private Section section;
    private final Image dialogImg;
    private JButton button;
    public ConfirmPanel() {
        button = new JButton();
        dialogImg = getImage("dialog.png"); 
        JPanel panel = new JPanel(){
                 @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    paintConfirm(g);
                }
            };
        panel.setBackground(Color.green);
        
        menu = getMenu();
        startGame = getStartGame();
        newGame = getResume();
        nextLevel = getNextLevel();
        exit = getExit();
        section = newGame;
        Dimension d = new Dimension(500,500);
        //setOpaque(false);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.setPreferredSize(d);
        panel.setOpaque(false);
        add(panel);
        setBackground(new Color(0,0,0,100));
        //panel.setSize(d);
        /* black = new Color(0,0,0,200);
        type = START;
        panel = new JPanel();
        button1 =  new JButton();
        button2 = new JButton();
        message = new JLabel();
        message.setText("Are You Sure Want To Restart?");
        setBackground(black);
        //setOpaque(false);
        panel.add(message);
        panel.add(button1);
        panel.add(button2);
        Dimension d = new Dimension(500,200);
        panel.setOpaque(false);
        panel.setSize(d);
        
        setLayout(null);
        add(panel);
        
        
        //message.setVisible(false);*/
    }

   
    private void paintConfirm(Graphics g) {
        // g.setColor(Color.red);
        //g.fillRect(0, 0, getWidth(), getHeight());
       // paintDialog(g);
        g.drawImage(dialogImg, 0, 20, 500, 400, this);
        section.paint(g);   
    }
    protected void setConfirmType(char type) {
        this.type = type;   
        drawComponent();
    }

    protected void addListener(ActionListener action) {
        button.addActionListener(action);
        
    }

    private void drawComponent() {
        
        switch(type){
            case START:
                section = startGame;
                break;
            case CONTINUE:
                section = newGame;
                break;
            case NEXT:
                section = nextLevel;
                break;
        }
        
       
    }
    


    private Section getMenu() {
        return null;
    }

    
    private Image getImage(String img){
        URL url  = getClass().getResource("/view/images/"+img);
        if (url==null)
            return null;
        
        return new ImageIcon(url).getImage(); 
    }
    
    private Section getStartGame() {
        Image img = getImage("start.gif");
        ButtonIcon btn1 = new ButtonIcon(img,50,50,400,200);
        img = getImage("play.png");
        ButtonIcon btn2 = new ButtonIcon(img,200,300,64,64);
        btn1.setDisable(true);
        btn2.setCmd("start");
        Section sect = new Section(); 
        sect.add(btn1);
        sect.add(btn2);
        return sect;
        
    }
    
    private Section getResume() {
        Image img = getImage("continue.png");
        ButtonIcon btn1 = new ButtonIcon(img,50,50,400,200);
        img = getImage("resume.png");
        ButtonIcon btn2 = new ButtonIcon(img,100,300,128,64);
        img = getImage("new.png");
        ButtonIcon btn3 = new ButtonIcon(img,300,300,128,64);
        btn1.setDisable(true);
        btn2.setCmd("continue");
        btn3.setCmd("new game");
        Section sect = new Section(); 
        sect.add(btn1);
        sect.add(btn2);
        sect.add(btn3);
        
        return sect;
    }

    private Section getExit() {
        return null;
    }
    
    private Section getNextLevel() {
        Image img = getImage("win.png");
        ButtonIcon btn1 = new ButtonIcon(img,50,50,400,200);
        img = getImage("next.png");
        ButtonIcon btn2 = new ButtonIcon(img,190,200,256,256);
        btn1.setDisable(true);
        btn2.setCmd("next");
        Section sect = new Section(); 
        sect.add(btn1);
        sect.add(btn2);
        return sect;
    }
    
  

    @Override
    public void mouseClicked(MouseEvent e) {
        section.clicked(e.getX(), e.getY(), button);
        System.out.println("eeeeeeeeeeeeee");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
     /*     
    private void drawStart() {
        message.setText("<html><p>The goal of the puzzle is to move all the disks<br> from the leftmost peg "
        + "to the rightmost peg, adhering to the following rules:</p>"
        + "<p>--- Move only one disk at a time.</p>"
        + "<p>--- A larger disk may not be placed ontop of a smaller disk.</p>"
        + "<p>--- All disks, except the one being moved, must be on a peg.</p>"
        + "</html>");
        //<html><p><b>The goal of the puzzle is to move all the disks from the leftmost peg to the rightmost peg, adhering to the following rules:\n" +
        //"\n" +
        //"Move only one disk at a time.\n" +
        //"A larger disk may not be placed ontop of a smaller disk.\n" +
        //"All disks, except the one being moved, must be on a peg.</b></p></html>");
        message.setLocation(200, 60);
        button1.setText("START");
        button1.setLocation(50, 50);
        button1.setActionCommand(GameFrame.START);
        button2.setVisible(false);
        }

        private void drawContinue() {
        message.setText("Dou you want continue or start a new game?");
        button1.setText("new game");
        button2.setText("continue");
        button2.setVisible(true);
        message.setLocation(150, 50);
        button1.setLocation(180, 70);
        button2.setLocation(90, 70);

        button1.setActionCommand(GameFrame.NEW_GAME);
        button2.setActionCommand(GameFrame.CONTINUE);

        }

        private void paintDialog(Graphics g) {
        if(w != getWidth() || h != getHeight()){
        w = getWidth();
        h = getHeight();
        panel.setLocation(w/2-250, h/2-100);

        }
        //g.setColor(black);
        //g.fillRect(0, 0,w, h);
        g.setColor(Color.red);
        g.fillRoundRect(w/2-250, h/2-100, 500, 200, 50, 50);
        Graphics2D g2 = (Graphics2D) g;
        //g2.draw(null);

        }

        private void drawNext() {
        panel.setLayout(null);
        message.setText("barvoo!! you win ");
        message.setLocation(200, 60);
        button1.setText("NEXT");
        button1.setLocation(150, 150);
        button1.setActionCommand(GameFrame.NEXT);
        button2.setVisible(false);
        }
    */ 

    
}

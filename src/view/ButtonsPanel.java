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
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public class ButtonsPanel extends JPanel implements MouseListener, MouseMotionListener{
    
    private final ButtonIcon undoBtn;
    private final ButtonIcon redoBtn;
    private final ButtonIcon helpBtn;
    private final ButtonIcon backBtn;
    private final ButtonIcon menuBtn;
    private final Section section;
    private final LabelText stepsLabel;
    private final LabelText helpLabel;
    private final JButton btn;
    private int wdth;
    

    public ButtonsPanel() {
        undoBtn = new ButtonIcon();
        redoBtn = new ButtonIcon();
        helpBtn = new ButtonIcon();
        backBtn = new ButtonIcon();
        menuBtn = new ButtonIcon();
        setButton(undoBtn, "undo");
        setButton(redoBtn, "redo");
        setButton(helpBtn, "help");
        setButton(backBtn, "back");
        setButton(menuBtn, "menu");
        undoBtn.setLocal(50,0);
        undoBtn.setCmd("undo");
        redoBtn.setLocal(100,0);
        redoBtn.setCmd("redo");
        helpBtn.setLocal(300,0);
        helpBtn.setCmd("help");
        backBtn.setLocal(0,0);
        backBtn.setCmd("back");
        menuBtn.setLocal(364,0);
        menuBtn.setCmd("menu");
        stepsLabel = new LabelText("0step", 180, 30);
        helpLabel = new LabelText("0/5", 450, 30);
        section = new Section();
        section.add(undoBtn);
        section.add(redoBtn);
        section.add(helpBtn);
        section.add(menuBtn);
        section.add(backBtn);
        section.add(stepsLabel);
        section.add(helpLabel);
        btn = new JButton();
        setPreferredSize(new Dimension(0,64));
        addMouseListener(this);
        addMouseMotionListener(this);
    }


    private void setButton(ButtonIcon btn, String image) {
        
        Image img = getImage(image);
        Image img1 = getImage(image+"_rollover");
        Image img2 = getImage(image+"_pressed");
        Image img3 = getImage(image+"_disabled");
        
        btn.setImages(img,img1,img2,img3);
    }
    
    private Image getImage(String img){
        URL url  = getClass().getResource("/view/images/"+img+".png");
        if (url==null)
            return null;
        
        return new ImageIcon(url).getImage(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        if(wdth!=getWidth()){
            wdth = getWidth();
            menuBtn.setX(wdth-68);
            helpBtn.setX(wdth/2-48);
            helpLabel.setX(wdth/2);
        }
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, wdth, 50);
        g.setColor(Color.green);
        section.paint(g);
    }
        
    @Override
    public void mouseClicked(MouseEvent e) {
        section.clicked(e.getX(), e.getY(), btn);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        section.pressed(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        section.ready();
    }

    @Override
    public void mouseEntered(MouseEvent e) {        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        section.ready();
    }

    void addActionListener(ActionListener al) {
        btn.addActionListener(al);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        section.ready();
        section.rollover(e.getX(), e.getY());
    }

    void setHelp(String txt) {
        helpLabel.setText(txt);
    }

    void setSteps(String txt) {
        stepsLabel.setText(txt);
    }

    void setUndoEnabled(boolean hadUndo) {
        //System.out.println("undo:"+!hadUndo);
        undoBtn.setDisable(!hadUndo);
    }

    void setRedoEnabled(boolean hadRedo) {
        //System.out.println("redo:"+!hadRedo);
        redoBtn.setDisable(!hadRedo);
    }
    
    
}

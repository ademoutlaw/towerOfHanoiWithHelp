/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author outlaw
 */
public class DialogPanel extends JPanel {
    
    public static char CONTINUE = 'c';
    
    private final JButton button1;
    private final JButton button2;
    private int dialogWidth;
    private int dialogHeight;
    private int currentHeight;
    private int startX;
    private int endX;
    private boolean isShowed;
    private char dialog;

    public DialogPanel() {
        button1 = new JButton();
        button2 = new JButton();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        
        if(dialogWidth!=getWidth()){
            dialogWidth = getWidth(); 
            startX = dialogWidth/2-200;
            isShowed=false;
            
            System.out.println("xxxxxxxxxxxxxxxxxxx");
        }
        
        if(dialogHeight!=getHeight()){
            dialogHeight = getHeight();
            if(isShowed){
                currentHeight = dialogHeight-20;
            }
            System.out.println("yyyyyyyyyyyyyyyyyyy");
        }
        
        if(!isShowed){
            if(currentHeight<dialogHeight){
                currentHeight+=50;
            }else{
                currentHeight = dialogHeight-20;
                isShowed=true;
            }            
        }       
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0, 0, dialogWidth, dialogHeight);
        g.setColor(Color.red);
        g.fill3DRect(startX, 20, 400, currentHeight, true);
    
    }
    
    public void addListener(ActionListener action) {
        button1.addActionListener(action);
        button2.addActionListener(action);
    }
        
    @Override
    public void setVisible(boolean aFlag){
        super.setVisible(aFlag);
        currentHeight=0;        
    }

    public void setDialog(char dialog) {
        this.dialog = dialog;
    }
    
    
}

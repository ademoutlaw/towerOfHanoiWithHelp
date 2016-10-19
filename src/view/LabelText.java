/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author outlaw
 */
public class LabelText extends Paint{
    private String text;

    public LabelText(String text, int x,int y) {
        setX(x);
        setY(y);
        this.text = text;
    }
    
    
    
    @Override
    public void paint(Graphics g) {
        g.drawString(text, x, y);
    }

    @Override
    public void rollover() {
    }

   

    @Override
    public void pressed() {
    }

    
    @Override
    public void disabled() {
    }

    

    @Override
    public void ready() {
    }

    

   

    @Override
    public boolean clicked(int x, int y, JButton btn) {
        return false;
    }

    public void setText(String txt) {
        text = txt;
    }

    
    
    
    
}

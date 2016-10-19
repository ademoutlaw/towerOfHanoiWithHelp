/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author outlaw
 */

public class Section {
    
    private final List<Paint> buttons;
    //private final JButton jButton;
    
    public Section(){
        buttons = new ArrayList<Paint>();
        //jButton = new JButton();
    }
    
    public void add(Paint paint){
        buttons.add(paint);
    }
    
    public void paint(Graphics g){
        buttons.stream().forEach((button) -> {
            //System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            button.paint(g);
        });
        
    }
    
    public void rollover(){
        buttons.stream().forEach((button) -> {
            button.rollover();
        });
    }
    public void rollover(int x, int y){
        buttons.stream().forEach((button) -> {
            button.rollover(x, y);
        });
    }
    public void pressed(){
        buttons.stream().forEach((button) -> {
            button.pressed();
        });
    }
    public void pressed(int x, int y){
        buttons.stream().forEach((button) -> {
            button.pressed(x, y);
            //jButton.setActionCommand(button.getCommand(x,y));
            //jButton.doClick();
        });
    }
    public void clicked(int x, int y, JButton btn){
        System.out.println("hhhhhhhhhhhhhhhhhhh");
        for(Paint button:buttons){
            if(button.clicked(x, y, btn)){
                System.out.println("bbbbbbbbbbbbbbbbbb");
                break;
            }
        }
    }
    public void disabled(){
        buttons.stream().forEach((button) -> {
            button.disabled();
        });
    }
    public void disabled(int x, int y){
        buttons.stream().forEach((button) -> {
            button.disabled(x, y);
        });
    }
    public void ready(){
        buttons.stream().forEach((button) -> {
            button.ready();
        });
    }
    public void ready(int x, int y){
        buttons.stream().forEach((button) -> {
            button.ready(x, y);
        });
    }

    void addListenner(ActionListener aThis) {
       // jButton.addActionListener(aThis);
    }
}

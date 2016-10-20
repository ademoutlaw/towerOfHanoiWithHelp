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
abstract public class Paint {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    boolean disable;
    private String cmd;
    private boolean sleep;
    
     public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    protected  boolean isInclud(int a, int b){
        return !(x>a || y>b || a>width+x || b>height+y);
    }
    
    public abstract void paint(Graphics g);

    public abstract void rollover() ;

    public void rollover(int x, int y) {
        if(disable||sleep){
            return ;
        }
        if(isInclud(x,y)){
            rollover();
        }
    }

    public abstract void pressed() ;

    public void pressed(int x, int y){
        if(disable||sleep){
            return ;
        }
        if(isInclud(x,y)){
            pressed();
        }
    }

    public abstract void disabled();

    public void disabled(int x, int y){
        if(isInclud(x,y)){
            disabled();
        }
    }

    public abstract void ready() ;

    public void ready(int x, int y){
        if(disable||sleep){
            return;
        }
        if(isInclud(x,y)){
            ready();
        }
    }

    public String getCommand(int x, int y) {
        if(isInclud(x,y)){
            return cmd;
        }
        return null;
    }

    public boolean clicked(int x, int y, JButton btn) {
        System.out.print("sssssssssssssssssssssss"+disable+" "+cmd);
        System.out.print("///"+x+" "+y);
        System.out.println("///"+this.x+" "+this.y);
        if(disable||sleep){
            return false;
        }
        
        if(isInclud(x,y)){
            btn.setActionCommand(cmd);
            btn.doClick();
            return true;
        }
        return false;
    }
    public boolean clicked(JButton button) {
        return clicked(x,y,button);
    }
    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
    
    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }
    
}

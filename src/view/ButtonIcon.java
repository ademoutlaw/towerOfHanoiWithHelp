/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

/**
 *
 * @author outlaw
 */
public class ButtonIcon  extends Paint{
    
    private Image currentImage;
    private Image image;
    private Image pressedImage;
    private Image rolloverImage;
    private Image disabledImage;

    private String cmd;
    private boolean sleep;
    
    public ButtonIcon(Image image, Image imagePressed, Image imageRollover, Image imageDisable, int x, int y, int width, int height) {
        this.image = image;
        currentImage = image;
        this.pressedImage = imagePressed;
        this.rolloverImage = imageRollover;
        this.disabledImage = imageDisable;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public ButtonIcon() {
        this(null, null, null, null, 0 ,0, 48, 48);       
    }
    
    public ButtonIcon(Image image, int x, int y, int width, int height){
        this(image, image, image, image, x ,y, width, height);        
    }
    
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getPressedImage() {
        return pressedImage;
    }

    public void setPressedImage(Image pressedImage) {
        this.pressedImage = pressedImage;
    }

    public Image getRolloverImage() {
        return rolloverImage;
    }

    public void setRolloverImage(Image rolloverImage) {
        this.rolloverImage = rolloverImage;
    }

    public Image getDisabledImage() {
        return disabledImage;
    }

    public void setImageDisable(Image imageDisable) {
        this.disabledImage = imageDisable;
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    @Override
    public void rollover(int x, int y) {
        if(isInclud(x, y)){
            rollover();
        }            
    }
    
    @Override
    public void pressed(int x, int y) {
        if(isInclud(x, y)){
            pressed();
        }
    }
    
    @Override
    public void disabled(int x, int y) {
        if(isInclud(x, y)){
            disabled();
        }
    }
    
    @Override
    public void ready(int x, int y) {
        if(isInclud(x, y)){
            ready();
        }
    }
    
    @Override
    public void rollover() {
        if(disable){
            return;
        }
        this.currentImage = rolloverImage;
    }
    
    @Override
    public void pressed() {
        if(!disable&&!sleep){
            this.currentImage = pressedImage;
        }
    }
   
    @Override
    public void ready() {
        if(disable){
            return;
        }
        this.currentImage = image;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(currentImage, x, y, width, height, null);
    }
     
    public String getCommand(){
        if(disable){
            return null;
        }
        return cmd;
    }
    
    @Override
    public String getCommand(int x, int y){
        if(isInclud(x, y)){
            return getCommand();
        }
        return null;
    }

    @Override
    public void setDisable(boolean disable) {
        super.setDisable(disable);
        currentImage = disable?disabledImage:image;
    }

    public void setLocal(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setImages(Image image, Image imagePressed, Image imageRollover, Image imageDisable) {
        this.image = image;
        currentImage = image;
        this.pressedImage = imagePressed;
        this.rolloverImage = imageRollover;
        this.disabledImage = imageDisable;
    }

    @Override
    public void disabled() {
        setDisable(true);
    }
}

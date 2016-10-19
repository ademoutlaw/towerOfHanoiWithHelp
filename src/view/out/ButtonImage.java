/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author outlaw
 */
public class ButtonImage extends JButton{

    private final String iconName;
    private final String extension;
    private final String path;
    private final int size;
    public ButtonImage(String image, int size) {
        this.size = size;
        String[] fullName;
        fullName = image.split("\\./*");
        iconName = fullName[0];
        extension = "."+fullName[1];
        path = "/view/images/";
        setPreferredSize(new Dimension(size,size));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setIcon(icon());
        setPressedIcon(pressedIcon());
        setRolloverIcon(rolloverIcon());
        setDisabledIcon(disabledIcon());
    }
    
    public ButtonImage(String image) {
       this(image,48);
        
    }

    

    private ImageIcon icon() {
        String img = path+iconName+extension;
        return getIcon(img);
    }
    
    private ImageIcon pressedIcon() {        
        String img = path+iconName+"_pressed"+extension;
        return getIcon(img);
    }

    private ImageIcon rolloverIcon() {
        String img = path+iconName+"_rollover"+extension;
        return getIcon(img);
    }

    private ImageIcon disabledIcon() {
        String img = path+iconName+"_disabled"+extension;
        return getIcon(img);
    }
    
    private ImageIcon getIcon(String img){
        URL url  = getClass().getResource(img);
        if (url==null)
            return null;
        
        return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)); 
    }
    
    
}

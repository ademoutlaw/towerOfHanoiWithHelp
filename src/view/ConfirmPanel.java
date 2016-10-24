/*
*To change this license header, choose License Headers in Project Properties.
*To change this template file, choose Tools | Templates
*and open the template in the editor.
*/
package view;

/*
* Etape 6 : récupération du nom et du prénom
*/

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

public  class ConfirmPanel extends JPanel implements MouseListener, MouseMotionListener{
    
    public static final char START = 's';
    public static final char CONTINUE = 'c';
    public static final char NEXT = 'n';
    public static final char MENU = 'm';
    
    private char type;
    private final Section menu;
    private final Section startGame;
    private final Section newGame;
    private final Section exitGame;
    private final Section nextLevel;
    private Section section;
    private final Image dialogImg;
    private final JButton button;
    private int sliderX;
    private final int sliderY;
    private boolean toDraged;
    private ButtonIcon circleBtn;
    private Image img;
    private boolean soundMuted;
    private boolean musicMuted;
    private ButtonIcon musicMutedBtn;
    private ButtonIcon musicUnmutedBtn;
    private ButtonIcon soundUnmutedBtn;
    private ButtonIcon soundMutedBtn;
    private ButtonIcon chekedBtn;
    private boolean cheked;
    
    public ConfirmPanel() {
        
        sliderX =250;
        sliderY = 150;
        button = new JButton();
        dialogImg = getImage("dialog.png"); 
        soundMuted =false;
        musicMuted =false;
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
        exitGame = getExitGame();
        section = newGame;
        Dimension d = new Dimension(500,500);        
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.setPreferredSize(d);
        panel.setOpaque(false);
        add(panel);
        setBackground(new Color(0,0,0,100));        
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
            case MENU:
                section = menu;
                break;
        }
    }

    protected void addListener(ActionListener action) {
        button.addActionListener(action);
        
    }       

    private Section getMenu() {
        img = getImage("cancel.png");
        ButtonIcon cancel = new ButtonIcon(img,360,320,64,64);
        img = getImage("save.png");
        ButtonIcon save = new ButtonIcon(img,50,320,64,64);
        img = getImage("sound.png");
        soundUnmutedBtn = new ButtonIcon(img,150,80,46,46);
        img = getImage("music.png");
        musicUnmutedBtn = new ButtonIcon(img,250,80,46,46);
        img = getImage("sound_mute.png");
        soundMutedBtn = new ButtonIcon(img,150,80,46,46);
        img = getImage("music_mute.png");
        musicMutedBtn = new ButtonIcon(img,250,80,38,46);
        img = getImage("circle.png");
        circleBtn = new ButtonIcon(img,250,60,16,16);        
        circleBtn.setLocal(sliderX, sliderY);
        Section sect = new Section();
        img = getImage("slider.png");
        ButtonIcon slider = new ButtonIcon(img,93,130,285,46);
        img = getImage("rect.png");
        ButtonIcon rect = new ButtonIcon(img,90,200,46,46);
        img = getImage("cheked.png");
        chekedBtn = new ButtonIcon(img,100,190,46,46);
        img = getImage("exit.png");
        ButtonIcon exit = new ButtonIcon(img,170,250,80,64);
        img = getImage("free.png");
        ButtonIcon label = new ButtonIcon(img,160,200,180,46);
        exit.setCmd("exit"); 
        cancel.setCmd("continue"); 
        save.setCmd("save"); 
        setSoundMuted(true);
        setMusicMuted(true);
        circleBtn.disabled();
        musicMutedBtn.setCmd("mute music");
        soundMutedBtn.setCmd("mute sound");
        musicUnmutedBtn.setCmd("mute music");
        soundUnmutedBtn.setCmd("mute sound");
        slider.disabled();
        sect.add(musicMutedBtn);
        sect.add(musicUnmutedBtn);
        sect.add(soundMutedBtn);
        sect.add(soundUnmutedBtn);
        sect.add(label);
        
        sect.add(slider);
        sect.add(rect);
        sect.add(chekedBtn);
        sect.add(exit);
        sect.add(save);
        sect.add(cancel);
        sect.add(circleBtn);
        return sect;
    }

    private Section getStartGame() {
        img = getImage("start.gif");
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
    
    private void draged(MouseEvent e){
        if(!toDraged&&e.getX() >= sliderX && e.getY() >= sliderY){
            toDraged = true;
        }
        if(toDraged){
            sliderX = e.getX();
            if(e.getX()<150)
                sliderX = 150;
            if(e.getX()>300)
                sliderX = 300;
                
            circleBtn.setLocal(sliderX, sliderY);
        }
    }
    
    private Section getResume() {
        img = getImage("continue.png");
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

    private Section getExitGame() {
        return null;
    }
    
    private Section getNextLevel() {
        img = getImage("win.png");
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
    
    private Image getImage(String img){
        URL url  = getClass().getResource("/view/images/"+img);
        if (url==null)
            return null;        
        return new ImageIcon(url).getImage(); 
    }
  
    public void setSoundMuted(boolean mute){
        soundMuted =  mute;
        soundMutedBtn.setVisible(mute);
        soundUnmutedBtn.setVisible(!mute);
    }
    
    public void setMusicMuted(boolean mute){ 
        musicMuted =  mute;
        musicMutedBtn.setVisible(mute);
        musicUnmutedBtn.setVisible(!mute);       
    }
    
    private void musicClick(MouseEvent e){ 
        if(musicMutedBtn.isInclud(e.getX(), e.getY())){
            musicMuted =  !musicMuted;
            setMusicMuted(musicMuted);  
            musicMutedBtn.clicked(button);
        }
    }
    
    private void soundClick(MouseEvent e){ 
        if(soundMutedBtn.isInclud(e.getX(), e.getY())){
            soundMuted =  !soundMuted;
            setSoundMuted(soundMuted);
            musicMutedBtn.clicked(button);
        }
    }
    
    public void setCheked(boolean cheked){
        this.cheked = cheked;
        chekedBtn.setVisible(cheked); 
    }
    
    private void chkedClick(MouseEvent e){ 
        if(chekedBtn.isInclud(e.getX(), e.getY())){
            cheked =  !cheked;
            chekedBtn.setVisible(cheked);      
        }
    }
    
    public int getSpeed(){ 
        return  180 * (sliderX-150)/ (300-150);       
    }
    
    public void setSpeed(int speed){ 
        sliderX =150 * (speed)/ (180) + 150 ;      
    }
     
    @Override
    public void mouseClicked(MouseEvent e) {        
        section.clicked(e.getX(), e.getY(), button);
        soundClick(e);
        musicClick(e);
        chkedClick(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        toDraged = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println(e.getX()+" "+e.getY());
        draged(e);
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public boolean isSoundMuted() {
        return soundMuted;
    }

    public boolean isMusicMuted() {
        return musicMuted;
    }

    boolean isFreeDestin() {
        return cheked;
    }
    
}
